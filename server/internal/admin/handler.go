package admin

import (
	"embed"
	"encoding/json"
	"html/template"
	"net/http"
	"strconv"

	"nova2-server/internal/service/signaling"
	"nova2-server/internal/store"

	"github.com/go-chi/chi/v5"
	"github.com/rs/zerolog/log"
)

//go:embed templates/*.html
var templateFS embed.FS

type Handler struct {
	devices  *store.DeviceRepo
	events   *store.EventRepo
	jobs     *store.JobRepo
	plugins  *store.PluginRepo
	sigSvc   *signaling.Service
	tmpls    map[string]*template.Template
	username string
	password string
}

func NewHandler(
	devices *store.DeviceRepo,
	events *store.EventRepo,
	jobs *store.JobRepo,
	plugins *store.PluginRepo,
	sigSvc *signaling.Service,
	username, password string,
) *Handler {
	funcMap := template.FuncMap{
		"json": func(v interface{}) string {
			b, _ := json.MarshalIndent(v, "", "  ")
			return string(b)
		},
	}

	pages := []string{"dashboard.html", "devices.html", "jobs.html", "events.html", "webrtc_control.html"}
	tmpls := make(map[string]*template.Template, len(pages))
	for _, page := range pages {
		t := template.Must(template.New("").Funcs(funcMap).ParseFS(templateFS, "templates/layout.html", "templates/"+page))
		tmpls[page] = t
	}

	return &Handler{
		devices:  devices,
		events:   events,
		jobs:     jobs,
		plugins:  plugins,
		sigSvc:   sigSvc,
		tmpls:    tmpls,
		username: username,
		password: password,
	}
}

// BasicAuth middleware for admin panel.
func (h *Handler) BasicAuth(next http.Handler) http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		user, pass, ok := r.BasicAuth()
		if !ok || user != h.username || pass != h.password {
			w.Header().Set("WWW-Authenticate", `Basic realm="Nova2 Admin"`)
			http.Error(w, "Unauthorized", http.StatusUnauthorized)
			return
		}
		next.ServeHTTP(w, r)
	})
}

// Dashboard shows the overview page.
func (h *Handler) Dashboard(w http.ResponseWriter, r *http.Request) {
	totalDevices, _ := h.devices.Count()
	onlineDevices, _ := h.devices.CountOnline(300) // 5 min
	totalEvents, _ := h.events.CountEvents()
	totalLogs, _ := h.events.CountLogs()
	totalJobs, _ := h.jobs.Count()
	activeRooms := h.sigSvc.Rooms().ListRoomIDs()

	data := map[string]interface{}{
		"Title":         "Dashboard",
		"TotalDevices":  totalDevices,
		"OnlineDevices": onlineDevices,
		"TotalEvents":   totalEvents,
		"TotalLogs":     totalLogs,
		"TotalJobs":     totalJobs,
		"ActiveRooms":   len(activeRooms),
	}

	h.render(w, "dashboard.html", data)
}

// Devices lists all registered devices.
func (h *Handler) Devices(w http.ResponseWriter, r *http.Request) {
	devices, err := h.devices.List()
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	data := map[string]interface{}{
		"Title":   "Devices",
		"Devices": devices,
	}

	h.render(w, "devices.html", data)
}

// DeviceDetail shows a single device.
func (h *Handler) DeviceDetail(w http.ResponseWriter, r *http.Request) {
	deviceID := chi.URLParam(r, "deviceID")
	device, err := h.devices.GetByDeviceID(deviceID)
	if err != nil || device == nil {
		http.Error(w, "Device not found", http.StatusNotFound)
		return
	}

	data := map[string]interface{}{
		"Title":  "Device: " + deviceID,
		"Device": device,
	}

	h.render(w, "devices.html", data)
}

// ToggleSignaling enables/disables signaling for a device.
func (h *Handler) ToggleSignaling(w http.ResponseWriter, r *http.Request) {
	deviceID := chi.URLParam(r, "deviceID")
	enable := r.FormValue("enable") == "true"

	if err := h.devices.SetSignalingEnabled(deviceID, enable); err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	http.Redirect(w, r, "/admin/devices", http.StatusSeeOther)
}

// Jobs lists all jobs.
func (h *Handler) Jobs(w http.ResponseWriter, r *http.Request) {
	jobs, err := h.jobs.List()
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	data := map[string]interface{}{
		"Title": "Jobs",
		"Jobs":  jobs,
	}

	h.render(w, "jobs.html", data)
}

// CreateJob creates a new job/offer.
func (h *Handler) CreateJob(w http.ResponseWriter, r *http.Request) {
	offerID := r.FormValue("offer_id")
	deviceID := r.FormValue("device_id")
	siteURL := r.FormValue("site_url")

	if offerID == "" || deviceID == "" {
		http.Error(w, "offer_id and device_id required", http.StatusBadRequest)
		return
	}

	if _, err := h.jobs.Create(offerID, deviceID, siteURL); err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	http.Redirect(w, r, "/admin/jobs", http.StatusSeeOther)
}

// Events lists events and logs.
func (h *Handler) Events(w http.ResponseWriter, r *http.Request) {
	page, _ := strconv.Atoi(r.URL.Query().Get("page"))
	if page < 1 {
		page = 1
	}
	limit := 50
	offset := (page - 1) * limit

	events, err := h.events.ListEvents(limit, offset)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	logs, err := h.events.ListLogs(limit, offset)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	data := map[string]interface{}{
		"Title":  "Events & Logs",
		"Events": events,
		"Logs":   logs,
		"Page":   page,
	}

	h.render(w, "events.html", data)
}

// WebRTCControl shows the WebRTC operator control panel.
func (h *Handler) WebRTCControl(w http.ResponseWriter, r *http.Request) {
	activeRooms := h.sigSvc.Rooms().ListRoomIDs()
	devices, _ := h.devices.List()
	turnCfg := h.sigSvc.TURNConfig()

	// Build ICE servers JSON for the JS client
	iceServers := []map[string]interface{}{}
	if turnCfg.STUNURL != "" {
		iceServers = append(iceServers, map[string]interface{}{"urls": turnCfg.STUNURL})
	}
	for _, ts := range turnCfg.Servers {
		iceServers = append(iceServers, map[string]interface{}{
			"urls":       ts.URL,
			"username":   ts.Username,
			"credential": ts.Password,
		})
	}
	iceJSON, _ := json.Marshal(iceServers)

	data := map[string]interface{}{
		"Title":       "WebRTC Control",
		"ActiveRooms": activeRooms,
		"Devices":     devices,
		"ICEServers":  template.JS(iceJSON),
		"TURNConfig":  turnCfg,
	}

	h.render(w, "webrtc_control.html", data)
}

// API endpoints for admin panel AJAX calls.

func (h *Handler) APIListDevices(w http.ResponseWriter, r *http.Request) {
	devices, err := h.devices.List()
	if err != nil {
		writeJSON(w, http.StatusInternalServerError, map[string]string{"error": err.Error()})
		return
	}
	writeJSON(w, http.StatusOK, devices)
}

func (h *Handler) APIListJobs(w http.ResponseWriter, r *http.Request) {
	jobs, err := h.jobs.List()
	if err != nil {
		writeJSON(w, http.StatusInternalServerError, map[string]string{"error": err.Error()})
		return
	}
	writeJSON(w, http.StatusOK, jobs)
}

func (h *Handler) APIListRooms(w http.ResponseWriter, r *http.Request) {
	rooms := h.sigSvc.Rooms().ListRoomIDs()
	writeJSON(w, http.StatusOK, rooms)
}

func (h *Handler) APIGetTURNConfig(w http.ResponseWriter, r *http.Request) {
	writeJSON(w, http.StatusOK, h.sigSvc.TURNConfig())
}

func (h *Handler) render(w http.ResponseWriter, name string, data interface{}) {
	w.Header().Set("Content-Type", "text/html; charset=utf-8")
	t, ok := h.tmpls[name]
	if !ok {
		http.Error(w, "Template not found: "+name, http.StatusInternalServerError)
		return
	}
	if err := t.ExecuteTemplate(w, "layout", data); err != nil {
		log.Error().Err(err).Str("template", name).Msg("render template")
		http.Error(w, "Template error", http.StatusInternalServerError)
	}
}

func writeJSON(w http.ResponseWriter, code int, v interface{}) {
	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(code)
	json.NewEncoder(w).Encode(v)
}
