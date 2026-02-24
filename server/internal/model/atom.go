package model

// DeviceInfo contains basic device information within an Atom.
type DeviceInfo struct {
	Timezone       string `json:"timezone"`
	Locale         string `json:"locale"`
	PhoneTimestamp int64  `json:"phoneTimestamp"`
	PhoneModel     string `json:"phoneModel"`
	AndroidVersion string `json:"androidVersion"`
}

// Atom is the core device fingerprint sent with dllpgd requests.
type Atom struct {
	DeviceID               string       `json:"deviceId"`
	Version                int64        `json:"version"`
	AppPackageName         string       `json:"appPackageName"`
	GaID                   string       `json:"gaId"`
	GID                    string       `json:"gaid"`
	SessionID              string       `json:"sessionId"`
	AppChannel             string       `json:"appChannel"`
	IsGeneratedBySubProcess bool        `json:"isGeneratedBySubProcess"`
	DeviceInfo             DeviceInfo   `json:"deviceInfo"`
	PluginInfos            []PluginInfo `json:"pluginInfos"`
}
