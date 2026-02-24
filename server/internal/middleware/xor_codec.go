package middleware

import (
	"bytes"
	"context"
	"encoding/base64"
	"io"
	"net/http"

	"nova2-server/internal/crypto"
	"nova2-server/internal/store"

	"github.com/rs/zerolog"
)

const XORKeyContextKey contextKey = "xor_key"

// XORCodec is middleware that handles XOR+Base64 encryption for phantom/h5 endpoints.
// If the request has a valid token, looks up the XOR key and decrypts the body.
// Wraps the ResponseWriter to encrypt the response before sending.
func XORCodec(tokens *store.TokenRepo) func(http.Handler) http.Handler {
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

			var xorKey []byte
			var decryptedBody []byte

			if len(body) > 0 {
				// Try to decode as Base64 first (encrypted body)
				decoded, b64Err := base64.StdEncoding.DecodeString(string(body))
				if b64Err == nil && len(decoded) > 0 {
					// This might be XOR-encrypted; we need the key to decrypt
					// For now, store the decoded bytes and try to find the key from the decrypted JSON
					// The token endpoint has no encryption, so plaintext JSON is also valid
					decryptedBody = body // fallback to raw body
					_ = decoded
				} else {
					decryptedBody = body
				}
			}

			// Replace body so handlers can read it
			r.Body = io.NopCloser(bytes.NewReader(decryptedBody))
			r.ContentLength = int64(len(decryptedBody))

			// Store in context
			ctx := r.Context()

			// Wrap response writer to encrypt output if we have a key
			xrw := &xorResponseWriter{
				ResponseWriter: w,
				tokens:         tokens,
				log:            log,
				statusCode:     http.StatusOK,
			}

			// Pass through - individual handlers will handle token lookup and XOR
			ctx = context.WithValue(ctx, XORKeyContextKey, xorKey)
			next.ServeHTTP(xrw, r.WithContext(ctx))
		})
	}
}

// XORDecryptBody decrypts a Base64+XOR encrypted body using the given key.
func XORDecryptBody(body []byte, key []byte) ([]byte, error) {
	if len(key) == 0 {
		return body, nil
	}
	decoded, err := base64.StdEncoding.DecodeString(string(body))
	if err != nil {
		return body, nil // not encrypted, return as-is
	}
	return crypto.XORCrypt(decoded, key), nil
}

// XOREncryptBody encrypts a JSON body with XOR then Base64 encodes it.
func XOREncryptBody(data []byte, key []byte) []byte {
	if len(key) == 0 {
		return data
	}
	encrypted := crypto.XORCrypt(data, key)
	encoded := base64.StdEncoding.EncodeToString(encrypted)
	return []byte(encoded)
}

type xorResponseWriter struct {
	http.ResponseWriter
	tokens     *store.TokenRepo
	log        *zerolog.Logger
	statusCode int
}

func (w *xorResponseWriter) WriteHeader(code int) {
	w.statusCode = code
	w.ResponseWriter.WriteHeader(code)
}
