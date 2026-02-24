package model

// H5Request is a generic request for /h5/ endpoints.
type H5Request struct {
	Token    string            `json:"token"`
	AppID    string            `json:"app_id"`
	DeviceID string            `json:"device_id"`
	Platform string            `json:"platform"`
	APIKey   string            `json:"api_key"`
	OfferID  string            `json:"offer_id"`
	Offer    string            `json:"offer"`
	Events   []string          `json:"events"`
	Atom     DeviceFingerprint `json:"atom"`
}

// H5JSFileResponse is the response for /h5/js_file_for_signaling.
type H5JSFileResponse struct {
	Code    int64  `json:"code"`
	Message string `json:"message"`
	Content string `json:"content"`
}

// H5JobResponse is the response for /h5/get_job_by_offer.
type H5JobResponse struct {
	Code    int64  `json:"code"`
	Message string `json:"message"`
	Task    string `json:"task"`
}
