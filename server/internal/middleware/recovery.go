package middleware

import (
	"net/http"

	"github.com/rs/zerolog"
)

// Recovery recovers from panics and logs the error.
func Recovery(next http.Handler) http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		defer func() {
			if rec := recover(); rec != nil {
				log := zerolog.Ctx(r.Context())
				log.Error().Interface("panic", rec).Str("path", r.URL.Path).Msg("panic recovered")
				http.Error(w, `{"code":-1,"message":"internal server error"}`, http.StatusInternalServerError)
			}
		}()
		next.ServeHTTP(w, r)
	})
}
