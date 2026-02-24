package h5

import (
	"encoding/json"
	"io"
	"net/http"

	"nova2-server/internal/middleware"
	"nova2-server/internal/model"
	"nova2-server/internal/store"

	"github.com/rs/zerolog"
)

type Handler struct {
	svc    *Service
	tokens *store.TokenRepo
}

func NewHandler(svc *Service, tokens *store.TokenRepo) *Handler {
	return &Handler{svc: svc, tokens: tokens}
}

// JSFileForSignaling handles POST /h5/js_file_for_signaling.
func (h *Handler) JSFileForSignaling(w http.ResponseWriter, r *http.Request) {
	log := zerolog.Ctx(r.Context())

	req, xorKey, err := h.readAndDecrypt(r)
	if err != nil {
		log.Error().Err(err).Msg("read/decrypt js_file request")
		writeJSON(w, http.StatusBadRequest, model.ErrorResponse(-1, "invalid request"))
		return
	}

	var h5Req model.H5Request
	if err := json.Unmarshal(req, &h5Req); err != nil {
		log.Error().Err(err).Msg("unmarshal js_file request")
		writeJSON(w, http.StatusBadRequest, model.ErrorResponse(-1, "invalid JSON"))
		return
	}

	log.Info().Str("device_id", h5Req.DeviceID).Msg("js_file_for_signaling")

	resp, err := h.svc.GetJSFileForSignaling(h5Req)
	if err != nil {
		log.Error().Err(err).Msg("getJSFile service")
		writeJSON(w, http.StatusInternalServerError, model.ErrorResponse(-1, "internal error"))
		return
	}

	h.writeEncrypted(w, resp, xorKey)
}

// GetJobByOffer handles POST /h5/get_job_by_offer.
func (h *Handler) GetJobByOffer(w http.ResponseWriter, r *http.Request) {
	log := zerolog.Ctx(r.Context())

	req, xorKey, err := h.readAndDecrypt(r)
	if err != nil {
		log.Error().Err(err).Msg("read/decrypt get_job request")
		writeJSON(w, http.StatusBadRequest, model.ErrorResponse(-1, "invalid request"))
		return
	}

	var h5Req model.H5Request
	if err := json.Unmarshal(req, &h5Req); err != nil {
		log.Error().Err(err).Msg("unmarshal get_job request")
		writeJSON(w, http.StatusBadRequest, model.ErrorResponse(-1, "invalid JSON"))
		return
	}

	log.Info().Str("device_id", h5Req.DeviceID).Str("offer_id", h5Req.OfferID).Msg("get_job_by_offer")

	resp, err := h.svc.GetJobByOffer(h5Req)
	if err != nil {
		log.Error().Err(err).Msg("getJobByOffer service")
		writeJSON(w, http.StatusInternalServerError, model.ErrorResponse(-1, "internal error"))
		return
	}

	h.writeEncrypted(w, resp, xorKey)
}

// UploadLogs handles POST /h5/upload_logs_v2.
func (h *Handler) UploadLogs(w http.ResponseWriter, r *http.Request) {
	log := zerolog.Ctx(r.Context())

	req, xorKey, err := h.readAndDecrypt(r)
	if err != nil {
		log.Error().Err(err).Msg("read/decrypt upload_logs request")
		writeJSON(w, http.StatusBadRequest, model.ErrorResponse(-1, "invalid request"))
		return
	}

	var h5Req model.H5Request
	if err := json.Unmarshal(req, &h5Req); err != nil {
		log.Error().Err(err).Msg("unmarshal upload_logs request")
		writeJSON(w, http.StatusBadRequest, model.ErrorResponse(-1, "invalid JSON"))
		return
	}

	log.Info().Str("device_id", h5Req.DeviceID).Int("logs", len(h5Req.Events)).Msg("upload_logs_v2")

	if err := h.svc.UploadLogs(h5Req); err != nil {
		log.Error().Err(err).Msg("uploadLogs service")
		writeJSON(w, http.StatusInternalServerError, model.ErrorResponse(-1, "internal error"))
		return
	}

	h.writeEncrypted(w, model.SuccessResponse(), xorKey)
}

// ReportEvents handles POST /h5/report_events.
func (h *Handler) ReportEvents(w http.ResponseWriter, r *http.Request) {
	log := zerolog.Ctx(r.Context())

	req, xorKey, err := h.readAndDecrypt(r)
	if err != nil {
		log.Error().Err(err).Msg("read/decrypt report_events request")
		writeJSON(w, http.StatusBadRequest, model.ErrorResponse(-1, "invalid request"))
		return
	}

	var h5Req model.H5Request
	if err := json.Unmarshal(req, &h5Req); err != nil {
		log.Error().Err(err).Msg("unmarshal report_events request")
		writeJSON(w, http.StatusBadRequest, model.ErrorResponse(-1, "invalid JSON"))
		return
	}

	log.Info().Str("device_id", h5Req.DeviceID).Int("events", len(h5Req.Events)).Msg("report_events")

	if err := h.svc.ReportEvents(h5Req); err != nil {
		log.Error().Err(err).Msg("reportEvents service")
		writeJSON(w, http.StatusInternalServerError, model.ErrorResponse(-1, "internal error"))
		return
	}

	h.writeEncrypted(w, model.SuccessResponse(), xorKey)
}

func (h *Handler) readAndDecrypt(r *http.Request) ([]byte, []byte, error) {
	body, err := io.ReadAll(r.Body)
	if err != nil {
		return nil, nil, err
	}

	// Try plaintext parse to extract token
	var peek struct {
		Token  string `json:"token"`
		APIKey string `json:"api_key"`
	}
	_ = json.Unmarshal(body, &peek)

	token := peek.Token
	if token == "" {
		token = peek.APIKey
	}

	var xorKey []byte
	if token != "" {
		xorKey, _ = h.tokens.GetEncryptionKey(token)
	}

	if xorKey != nil {
		decrypted, err := middleware.XORDecryptBody(body, xorKey)
		if err == nil {
			return decrypted, xorKey, nil
		}
	}

	return body, xorKey, nil
}

func (h *Handler) writeEncrypted(w http.ResponseWriter, v interface{}, xorKey []byte) {
	data, err := json.Marshal(v)
	if err != nil {
		http.Error(w, `{"code":-1,"message":"marshal error"}`, http.StatusInternalServerError)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	if xorKey != nil {
		encrypted := middleware.XOREncryptBody(data, xorKey)
		w.Write(encrypted)
	} else {
		w.Write(data)
	}
}

func writeJSON(w http.ResponseWriter, code int, v interface{}) {
	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(code)
	json.NewEncoder(w).Encode(v)
}
