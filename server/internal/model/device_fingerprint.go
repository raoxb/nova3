package model

// DeviceFingerprint is the extended device fingerprint (15 fields) used by phantom/h5 endpoints.
type DeviceFingerprint struct {
	DeviceID         string `json:"device_id"`
	AppPackage       string `json:"app_package"`
	AppVersion       string `json:"app_version"`
	SessionID        string `json:"session_id"`
	Channel          string `json:"channel"`
	Timezone         string `json:"timezone"`
	Locale           string `json:"locale"`
	Model            string `json:"model"`
	Brand            string `json:"brand"`
	ScreenResolution string `json:"screen_resolution"`
	ScreenDensity    string `json:"screen_density"`
	Orientation      string `json:"orientation"`
	AndroidVersion   string `json:"android_version"`
	TimestampNow     int64  `json:"timestamp_now"`
	NetworkType      string `json:"network_type"`
}

// DeviceAuthRequest is the common request body for phantom endpoints.
type DeviceAuthRequest struct {
	AppID    string            `json:"app_id"`
	DeviceID string            `json:"device_id"`
	Token    string            `json:"token"`
	Atom     DeviceFingerprint `json:"atom"`
}
