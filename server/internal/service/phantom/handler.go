package phantom

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

// Token handles POST /phantom/token — no encryption on this endpoint.
func (h *Handler) Token(w http.ResponseWriter, r *http.Request) {
	log := zerolog.Ctx(r.Context())

	body, err := io.ReadAll(r.Body)
	if err != nil {
		writeJSON(w, http.StatusBadRequest, model.ErrorResponse(-1, "read body failed"))
		return
	}

	var req model.DeviceAuthRequest
	if err := json.Unmarshal(body, &req); err != nil {
		log.Error().Err(err).Msg("unmarshal token request")
		writeJSON(w, http.StatusBadRequest, model.ErrorResponse(-1, "invalid JSON"))
		return
	}

	log.Info().Str("device_id", req.DeviceID).Str("app_id", req.AppID).Msg("token request")

	resp, err := h.svc.GetToken(req)
	if err != nil {
		log.Error().Err(err).Msg("getToken service")
		writeJSON(w, http.StatusInternalServerError, model.ErrorResponse(-1, "internal error"))
		return
	}

	writeJSON(w, http.StatusOK, resp)
}

// Task handles POST /phantom/task — XOR encrypted if token present.
func (h *Handler) Task(w http.ResponseWriter, r *http.Request) {
	log := zerolog.Ctx(r.Context())

	req, xorKey, err := h.readAndDecrypt(r)
	if err != nil {
		log.Error().Err(err).Msg("read/decrypt task request")
		writeJSON(w, http.StatusBadRequest, model.ErrorResponse(-1, "invalid request"))
		return
	}

	var authReq model.DeviceAuthRequest
	if err := json.Unmarshal(req, &authReq); err != nil {
		log.Error().Err(err).Msg("unmarshal task request")
		writeJSON(w, http.StatusBadRequest, model.ErrorResponse(-1, "invalid JSON"))
		return
	}

	log.Info().Str("device_id", authReq.DeviceID).Msg("task request")

	resp, err := h.svc.GetTask(authReq)
	if err != nil {
		log.Error().Err(err).Msg("getTask service")
		writeJSON(w, http.StatusInternalServerError, model.ErrorResponse(-1, "internal error"))
		return
	}

	h.writeEncrypted(w, resp, xorKey)
}

// FileVersion handles POST /phantom/file_version.
func (h *Handler) FileVersion(w http.ResponseWriter, r *http.Request) {
	log := zerolog.Ctx(r.Context())

	req, xorKey, err := h.readAndDecrypt(r)
	if err != nil {
		log.Error().Err(err).Msg("read/decrypt file_version request")
		writeJSON(w, http.StatusBadRequest, model.ErrorResponse(-1, "invalid request"))
		return
	}

	var authReq model.DeviceAuthRequest
	if err := json.Unmarshal(req, &authReq); err != nil {
		log.Error().Err(err).Msg("unmarshal file_version request")
		writeJSON(w, http.StatusBadRequest, model.ErrorResponse(-1, "invalid JSON"))
		return
	}

	log.Info().Str("device_id", authReq.DeviceID).Msg("file_version request")

	resp, err := h.svc.GetFileVersion(authReq)
	if err != nil {
		log.Error().Err(err).Msg("getFileVersion service")
		writeJSON(w, http.StatusInternalServerError, model.ErrorResponse(-1, "internal error"))
		return
	}

	h.writeEncrypted(w, resp, xorKey)
}

// File handles POST /phantom/file.
func (h *Handler) File(w http.ResponseWriter, r *http.Request) {
	log := zerolog.Ctx(r.Context())

	req, xorKey, err := h.readAndDecrypt(r)
	if err != nil {
		log.Error().Err(err).Msg("read/decrypt file request")
		writeJSON(w, http.StatusBadRequest, model.ErrorResponse(-1, "invalid request"))
		return
	}

	var authReq model.DeviceAuthRequest
	if err := json.Unmarshal(req, &authReq); err != nil {
		log.Error().Err(err).Msg("unmarshal file request")
		writeJSON(w, http.StatusBadRequest, model.ErrorResponse(-1, "invalid JSON"))
		return
	}

	log.Info().Str("device_id", authReq.DeviceID).Msg("file request")

	resp, err := h.svc.GetFileContent(authReq)
	if err != nil {
		log.Error().Err(err).Msg("getFileContent service")
		writeJSON(w, http.StatusInternalServerError, model.ErrorResponse(-1, "internal error"))
		return
	}

	h.writeEncrypted(w, resp, xorKey)
}

// Done handles POST /phantom/done.
func (h *Handler) Done(w http.ResponseWriter, r *http.Request) {
	log := zerolog.Ctx(r.Context())

	req, xorKey, err := h.readAndDecrypt(r)
	if err != nil {
		log.Error().Err(err).Msg("read/decrypt done request")
		writeJSON(w, http.StatusBadRequest, model.ErrorResponse(-1, "invalid request"))
		return
	}

	var doneReq model.DoneRequest
	if err := json.Unmarshal(req, &doneReq); err != nil {
		log.Error().Err(err).Msg("unmarshal done request")
		writeJSON(w, http.StatusBadRequest, model.ErrorResponse(-1, "invalid JSON"))
		return
	}

	log.Info().Str("device_id", doneReq.DeviceID).Str("result", doneReq.Result).Msg("done report")

	if err := h.svc.ReportDone(doneReq); err != nil {
		log.Error().Err(err).Msg("reportDone service")
		writeJSON(w, http.StatusInternalServerError, model.ErrorResponse(-1, "internal error"))
		return
	}

	h.writeEncrypted(w, model.SuccessResponse(), xorKey)
}

// readAndDecrypt reads request body and decrypts it using the token's XOR key.
func (h *Handler) readAndDecrypt(r *http.Request) ([]byte, []byte, error) {
	body, err := io.ReadAll(r.Body)
	if err != nil {
		return nil, nil, err
	}

	// Try to extract token from the body to find XOR key
	// First try plaintext parse to get token
	var peek struct {
		Token string `json:"token"`
	}
	_ = json.Unmarshal(body, &peek)

	var xorKey []byte
	if peek.Token != "" {
		xorKey, _ = h.tokens.GetEncryptionKey(peek.Token)
	}

	// Decrypt body if we have a key
	if xorKey != nil {
		decrypted, err := middleware.XORDecryptBody(body, xorKey)
		if err == nil {
			return decrypted, xorKey, nil
		}
	}

	return body, xorKey, nil
}

// writeEncrypted writes a JSON response, encrypting with XOR if key is available.
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
