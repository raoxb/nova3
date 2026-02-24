package dllpgd

import (
	"encoding/json"
	"io"
	"net/http"

	"nova2-server/internal/model"

	"github.com/rs/zerolog"
)

type Handler struct {
	svc *Service
}

func NewHandler(svc *Service) *Handler {
	return &Handler{svc: svc}
}

// GetConfig handles POST /api/v1/dllpgd/getConfig
func (h *Handler) GetConfig(w http.ResponseWriter, r *http.Request) {
	log := zerolog.Ctx(r.Context())

	body, err := io.ReadAll(r.Body)
	if err != nil {
		writeJSON(w, http.StatusBadRequest, model.ErrorResponse(-1, "read body failed"))
		return
	}

	var req model.GetConfigRequest
	if err := json.Unmarshal(body, &req); err != nil {
		log.Error().Err(err).Msg("unmarshal getConfig request")
		writeJSON(w, http.StatusBadRequest, model.ErrorResponse(-1, "invalid JSON"))
		return
	}

	log.Info().Str("device_id", req.Atom.DeviceID).Str("app", req.Atom.AppPackageName).Msg("getConfig")

	resp, err := h.svc.GetConfig(req)
	if err != nil {
		log.Error().Err(err).Msg("getConfig service")
		writeJSON(w, http.StatusInternalServerError, model.ErrorResponse(-1, "internal error"))
		return
	}

	writeJSON(w, http.StatusOK, resp)
}

// UpdateEvent handles POST /api/v1/dllpgd/updateEvent
func (h *Handler) UpdateEvent(w http.ResponseWriter, r *http.Request) {
	log := zerolog.Ctx(r.Context())

	body, err := io.ReadAll(r.Body)
	if err != nil {
		writeJSON(w, http.StatusBadRequest, model.ErrorResponse(-1, "read body failed"))
		return
	}

	var req model.UpdateEventRequest
	if err := json.Unmarshal(body, &req); err != nil {
		log.Error().Err(err).Msg("unmarshal updateEvent request")
		writeJSON(w, http.StatusBadRequest, model.ErrorResponse(-1, "invalid JSON"))
		return
	}

	log.Info().Str("device_id", req.Atom.DeviceID).Int("events", len(req.Events)).Msg("updateEvent")

	if err := h.svc.UpdateEvent(req); err != nil {
		log.Error().Err(err).Msg("updateEvent service")
		writeJSON(w, http.StatusInternalServerError, model.ErrorResponse(-1, "internal error"))
		return
	}

	writeJSON(w, http.StatusOK, model.SuccessResponse())
}

// UpdateLog handles POST /api/v1/dllpgd/updateLog
func (h *Handler) UpdateLog(w http.ResponseWriter, r *http.Request) {
	log := zerolog.Ctx(r.Context())

	body, err := io.ReadAll(r.Body)
	if err != nil {
		writeJSON(w, http.StatusBadRequest, model.ErrorResponse(-1, "read body failed"))
		return
	}

	var req model.UpdateLogRequest
	if err := json.Unmarshal(body, &req); err != nil {
		log.Error().Err(err).Msg("unmarshal updateLog request")
		writeJSON(w, http.StatusBadRequest, model.ErrorResponse(-1, "invalid JSON"))
		return
	}

	log.Info().Str("device_id", req.Atom.DeviceID).Int("logs", len(req.Log)).Msg("updateLog")

	if err := h.svc.UpdateLog(req); err != nil {
		log.Error().Err(err).Msg("updateLog service")
		writeJSON(w, http.StatusInternalServerError, model.ErrorResponse(-1, "internal error"))
		return
	}

	writeJSON(w, http.StatusOK, model.SuccessResponse())
}

func writeJSON(w http.ResponseWriter, code int, v interface{}) {
	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(code)
	json.NewEncoder(w).Encode(v)
}
