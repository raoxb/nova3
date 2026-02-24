package model

// CheckPluginStartRequest is the HTTP request for /signaling/checkPluginStart.
type CheckPluginStartRequest struct {
	AppID    string `json:"app_id"`
	DeviceID string `json:"device_id"`
	Token    string `json:"token"`
}

// CheckPluginStartResponse is the response for checkPluginStart.
type CheckPluginStartResponse struct {
	Code    int64  `json:"code"`
	Message string `json:"message"`
	Run     bool   `json:"run"`
	OfferID string `json:"offerId"`
	JobID   string `json:"jobId"`
}

// UpdateStatusRequest is the HTTP request for /signaling/updateStatus.
type UpdateStatusRequest struct {
	AppID    string `json:"app_id"`
	DeviceID string `json:"device_id"`
	Token    string `json:"token"`
	OfferID  string `json:"offer_id"`
	JobID    string `json:"job_id"`
	Status   string `json:"status"`
}

// Status constants.
const (
	StatusUnknown   = "UNKNOWN"
	StatusStart     = "START"
	StatusInLanding = "IN_LANDING"
	StatusDone      = "DONE"
)
