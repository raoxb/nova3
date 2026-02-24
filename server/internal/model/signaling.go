package model

import "encoding/json"

// SignalingRequest is the WebSocket message from a device or operator.
type SignalingRequest struct {
	Atom    string          `json:"atom"`
	Content json.RawMessage `json:"content"`
}

// SignalingContent is the typed content within a signaling message.
type SignalingContent struct {
	ContentType string          `json:"content_type"`
	Payload     json.RawMessage `json:"payload,omitempty"`
	SDP         string          `json:"sdp,omitempty"`
	Candidate   string          `json:"candidate,omitempty"`
	SDPMid      string          `json:"sdpMid,omitempty"`
	SDPMLineIdx int             `json:"sdpMLineIndex,omitempty"`
	Command     string          `json:"command,omitempty"`
}

// SignalingResponse is the WebSocket message sent back.
type SignalingResponse struct {
	Error   *SignalingError  `json:"error,omitempty"`
	Content *SignalingContent `json:"content,omitempty"`
}

// SignalingError represents an error in signaling.
type SignalingError struct {
	Code    int    `json:"code"`
	Message string `json:"message"`
}

// Content type constants for signaling.
const (
	ContentTypeSDP          = "sdp_offer"
	ContentTypeSDPAnswer    = "sdp_answer"
	ContentTypeICECandidate = "ice_candidate"
	ContentTypeControl      = "control"
	ContentTypePing         = "ping"
	ContentTypePong         = "pong"
	ContentTypeDone         = "done"
	ContentTypeStatus       = "status"
	ContentTypeError        = "error"
)
