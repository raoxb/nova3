package model

// TokenResponse is the response for /phantom/token.
type TokenResponse struct {
	Code    int64  `json:"code"`
	Message string `json:"message"`
	Content string `json:"content"`
}

// TaskResponse is the response for /phantom/task.
type TaskResponse struct {
	Code    int64  `json:"code"`
	Message string `json:"message"`
	Task    string `json:"task"`
}

// FileVersionResponse is the response for /phantom/file_version.
type FileVersionResponse struct {
	Code    int64  `json:"code"`
	Message string `json:"message"`
	Version string `json:"version"`
}

// FileContentResponse is the response for /phantom/file.
type FileContentResponse struct {
	Code    int64  `json:"code"`
	Message string `json:"message"`
	Content string `json:"content"`
}

// DoneRequest is the request for /phantom/done.
type DoneRequest struct {
	AppID    string            `json:"app_id"`
	DeviceID string            `json:"device_id"`
	Token    string            `json:"token"`
	APIKey   string            `json:"api_key"`
	OfferID  string            `json:"offer_id"`
	Result   string            `json:"result"`
	Atom     DeviceFingerprint `json:"atom"`
}
