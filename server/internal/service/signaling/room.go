package signaling

import (
	"sync"

	"github.com/gorilla/websocket"
	"github.com/rs/zerolog/log"
)

// Room pairs a device WebSocket connection with an operator WebSocket connection.
type Room struct {
	ID         string
	DeviceConn *websocket.Conn
	OperConn   *websocket.Conn
	mu         sync.Mutex
}

// RoomManager manages signaling rooms for device↔operator pairing.
type RoomManager struct {
	mu    sync.RWMutex
	rooms map[string]*Room // keyed by device_id or session_id
}

func NewRoomManager() *RoomManager {
	return &RoomManager{
		rooms: make(map[string]*Room),
	}
}

// GetOrCreateRoom returns the room for a given ID, creating it if needed.
func (rm *RoomManager) GetOrCreateRoom(id string) *Room {
	rm.mu.Lock()
	defer rm.mu.Unlock()

	if r, ok := rm.rooms[id]; ok {
		return r
	}
	r := &Room{ID: id}
	rm.rooms[id] = r
	log.Info().Str("room_id", id).Msg("room created")
	return r
}

// RemoveRoom removes a room.
func (rm *RoomManager) RemoveRoom(id string) {
	rm.mu.Lock()
	defer rm.mu.Unlock()
	delete(rm.rooms, id)
	log.Info().Str("room_id", id).Msg("room removed")
}

// GetRoom gets an existing room.
func (rm *RoomManager) GetRoom(id string) *Room {
	rm.mu.RLock()
	defer rm.mu.RUnlock()
	return rm.rooms[id]
}

// ListRoomIDs returns all active room IDs.
func (rm *RoomManager) ListRoomIDs() []string {
	rm.mu.RLock()
	defer rm.mu.RUnlock()
	ids := make([]string, 0, len(rm.rooms))
	for id := range rm.rooms {
		ids = append(ids, id)
	}
	return ids
}

// SetDeviceConn sets the device connection for a room.
func (r *Room) SetDeviceConn(conn *websocket.Conn) {
	r.mu.Lock()
	defer r.mu.Unlock()
	r.DeviceConn = conn
}

// SetOperatorConn sets the operator connection for a room.
func (r *Room) SetOperatorConn(conn *websocket.Conn) {
	r.mu.Lock()
	defer r.mu.Unlock()
	r.OperConn = conn
}

// ForwardToDevice sends a message to the device connection.
func (r *Room) ForwardToDevice(msg []byte) error {
	r.mu.Lock()
	defer r.mu.Unlock()
	if r.DeviceConn == nil {
		return nil
	}
	return r.DeviceConn.WriteMessage(websocket.TextMessage, msg)
}

// ForwardToOperator sends a message to the operator connection.
func (r *Room) ForwardToOperator(msg []byte) error {
	r.mu.Lock()
	defer r.mu.Unlock()
	if r.OperConn == nil {
		return nil
	}
	return r.OperConn.WriteMessage(websocket.TextMessage, msg)
}
