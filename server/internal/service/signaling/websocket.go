package signaling

import (
	"encoding/json"
	"net/http"

	"nova2-server/internal/model"

	"github.com/gorilla/websocket"
	"github.com/rs/zerolog"
)

var upgrader = websocket.Upgrader{
	CheckOrigin: func(r *http.Request) bool { return true },
}

// HandleWebSocket upgrades HTTP to WebSocket and routes signaling messages.
func (svc *Service) HandleWebSocket(w http.ResponseWriter, r *http.Request) {
	log := zerolog.Ctx(r.Context())

	conn, err := upgrader.Upgrade(w, r, nil)
	if err != nil {
		log.Error().Err(err).Msg("websocket upgrade failed")
		return
	}
	defer conn.Close()

	// Determine role from query params
	role := r.URL.Query().Get("role")     // "device" or "operator"
	roomID := r.URL.Query().Get("room_id") // device_id or session_id

	if roomID == "" {
		conn.WriteJSON(model.SignalingResponse{
			Error: &model.SignalingError{Code: 400, Message: "room_id required"},
		})
		return
	}

	room := svc.rooms.GetOrCreateRoom(roomID)

	if role == "operator" {
		room.SetOperatorConn(conn)
		log.Info().Str("room_id", roomID).Msg("operator connected")
	} else {
		room.SetDeviceConn(conn)
		log.Info().Str("room_id", roomID).Msg("device connected")
	}

	defer func() {
		if role == "operator" {
			room.SetOperatorConn(nil)
		} else {
			room.SetDeviceConn(nil)
		}
	}()

	for {
		_, message, err := conn.ReadMessage()
		if err != nil {
			if websocket.IsUnexpectedCloseError(err, websocket.CloseGoingAway, websocket.CloseNormalClosure) {
				log.Error().Err(err).Msg("websocket read error")
			}
			break
		}

		var sigReq model.SignalingRequest
		if err := json.Unmarshal(message, &sigReq); err != nil {
			conn.WriteJSON(model.SignalingResponse{
				Error: &model.SignalingError{Code: 400, Message: "invalid JSON"},
			})
			continue
		}

		// Parse content to determine message type
		var content model.SignalingContent
		if err := json.Unmarshal(sigReq.Content, &content); err != nil {
			conn.WriteJSON(model.SignalingResponse{
				Error: &model.SignalingError{Code: 400, Message: "invalid content"},
			})
			continue
		}

		log.Debug().Str("type", content.ContentType).Str("room_id", roomID).Str("role", role).Msg("signaling message")

		switch content.ContentType {
		case model.ContentTypePing:
			// Respond with pong
			pong := model.SignalingResponse{
				Content: &model.SignalingContent{ContentType: model.ContentTypePong},
			}
			conn.WriteJSON(pong)

		case model.ContentTypeSDP, model.ContentTypeSDPAnswer, model.ContentTypeICECandidate, model.ContentTypeControl:
			// Forward to the other peer
			if role == "operator" {
				if err := room.ForwardToDevice(message); err != nil {
					log.Error().Err(err).Msg("forward to device")
				}
			} else {
				if err := room.ForwardToOperator(message); err != nil {
					log.Error().Err(err).Msg("forward to operator")
				}
			}

		case model.ContentTypeDone:
			// Session complete
			doneResp := model.SignalingResponse{
				Content: &model.SignalingContent{ContentType: model.ContentTypeDone},
			}
			room.ForwardToDevice(mustMarshal(doneResp))
			room.ForwardToOperator(mustMarshal(doneResp))
			svc.rooms.RemoveRoom(roomID)
			return

		default:
			conn.WriteJSON(model.SignalingResponse{
				Error: &model.SignalingError{Code: 400, Message: "unknown content_type: " + content.ContentType},
			})
		}
	}
}

func mustMarshal(v interface{}) []byte {
	data, _ := json.Marshal(v)
	return data
}
