package middleware

import (
	"bytes"
	"context"
	"io"
	"net/http"

	"nova2-server/internal/crypto"

	"github.com/rs/zerolog"
)

type contextKey string

const DecryptedBodyKey contextKey = "decrypted_body"

// DllpgdCodec is middleware that decrypts the 5-layer dllpgd request pipeline.
// Request: HTTP body → Base64 decode → AES-256-CFB decrypt → Base64 decode → GZIP decompress → JSON
// Response: plaintext JSON (no encryption needed for responses).
func DllpgdCodec(aesKey []byte) func(http.Handler) http.Handler {
	return func(next http.Handler) http.Handler {
		return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
			log := zerolog.Ctx(r.Context())

			body, err := io.ReadAll(r.Body)
			r.Body.Close()
			if err != nil {
				log.Error().Err(err).Msg("read request body")
				http.Error(w, `{"code":-1,"message":"read body failed"}`, http.StatusBadRequest)
				return
			}

			if len(body) == 0 {
				log.Warn().Msg("empty request body")
				http.Error(w, `{"code":-1,"message":"empty body"}`, http.StatusBadRequest)
				return
			}

			decrypted, err := crypto.DecryptDllpgdRequest(body, aesKey)
			if err != nil {
				log.Error().Err(err).Msg("decrypt dllpgd request")
				http.Error(w, `{"code":-1,"message":"decryption failed"}`, http.StatusBadRequest)
				return
			}

			log.Debug().RawJSON("body", decrypted).Msg("decrypted dllpgd request")

			// Replace the request body with decrypted JSON
			r.Body = io.NopCloser(bytes.NewReader(decrypted))
			r.ContentLength = int64(len(decrypted))

			// Also store in context for handlers that need it
			ctx := context.WithValue(r.Context(), DecryptedBodyKey, decrypted)
			next.ServeHTTP(w, r.WithContext(ctx))
		})
	}
}
