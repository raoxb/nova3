package model

// DllpgdConfig is the configuration returned by getConfig.
type DllpgdConfig struct {
	Plugins                        []LocalPluginInfo `json:"plugins"`
	SessionID                      string            `json:"sessionId"`
	HookPkgNameStackTraces         []string          `json:"hookPkgNameStackTraces"`
	HookPackageManagerStackTraces  []string          `json:"hookPackageManagerStackTraces"`
	FixPackageName                 string            `json:"fixPackageName"`
}

// GetConfigRequest is the request body for /api/v1/dllpgd/getConfig.
type GetConfigRequest struct {
	Atom Atom `json:"atom"`
}

// GetConfigResponse is the response for getConfig.
type GetConfigResponse struct {
	Code         int64        `json:"code"`
	Message      string       `json:"message"`
	DllpgdConfig DllpgdConfig `json:"dllpgdConfig"`
}

// EventEntry is a single event in an updateEvent request.
type EventEntry struct {
	Timestamp int64  `json:"timestamp"`
	Name      string `json:"name"`
	Desc      string `json:"desc"`
}

// UpdateEventRequest is the request for /api/v1/dllpgd/updateEvent.
type UpdateEventRequest struct {
	Atom   Atom         `json:"atom"`
	Events []EventEntry `json:"events"`
}

// LogEntry is a single log record in an updateLog request.
type LogEntry struct {
	Timestamp int64  `json:"timestamp"`
	Level     string `json:"level"`
	Tag       string `json:"tag"`
	Message   string `json:"message"`
}

// UpdateLogRequest is the request for /api/v1/dllpgd/updateLog.
type UpdateLogRequest struct {
	Atom Atom       `json:"atom"`
	Log  []LogEntry `json:"log"`
}
