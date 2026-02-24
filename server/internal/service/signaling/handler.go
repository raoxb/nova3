package signaling

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

// CheckPluginStart handles POST /signaling/checkPluginStart.
func (h *Handler) CheckPluginStart(w http.ResponseWriter, r *http.Request) {
	log := zerolog.Ctx(r.Context())

	body, err := io.ReadAll(r.Body)
	if err != nil {
		writeJSON(w, http.StatusBadRequest, model.ErrorResponse(-1, "read body failed"))
		return
	}

	var req model.CheckPluginStartRequest
	if err := json.Unmarshal(body, &req); err != nil {
		log.Error().Err(err).Msg("unmarshal checkPluginStart request")
		writeJSON(w, http.StatusBadRequest, model.ErrorResponse(-1, "invalid JSON"))
		return
	}

	log.Info().Str("device_id", req.DeviceID).Msg("checkPluginStart")

	run, offerID, jobID, err := h.svc.CheckPluginStart(req.DeviceID)
	if err != nil {
		log.Error().Err(err).Msg("checkPluginStart service")
		writeJSON(w, http.StatusInternalServerError, model.ErrorResponse(-1, "internal error"))
		return
	}

	writeJSON(w, http.StatusOK, model.CheckPluginStartResponse{
		Code:    0,
		Message: "ok",
		Run:     run,
		OfferID: offerID,
		JobID:   jobID,
	})
}

// UpdateStatus handles POST /signaling/updateStatus.
func (h *Handler) UpdateStatus(w http.ResponseWriter, r *http.Request) {
	log := zerolog.Ctx(r.Context())

	body, err := io.ReadAll(r.Body)
	if err != nil {
		writeJSON(w, http.StatusBadRequest, model.ErrorResponse(-1, "read body failed"))
		return
	}

	var req model.UpdateStatusRequest
	if err := json.Unmarshal(body, &req); err != nil {
		log.Error().Err(err).Msg("unmarshal updateStatus request")
		writeJSON(w, http.StatusBadRequest, model.ErrorResponse(-1, "invalid JSON"))
		return
	}

	log.Info().Str("device_id", req.DeviceID).Str("status", req.Status).Str("job_id", req.JobID).Msg("updateStatus")

	if err := h.svc.UpdateStatus(req.JobID, req.Status); err != nil {
		log.Error().Err(err).Msg("updateStatus service")
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
