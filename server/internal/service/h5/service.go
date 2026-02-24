package h5

import (
	"encoding/json"

	"nova2-server/internal/config"
	"nova2-server/internal/model"
	"nova2-server/internal/store"
)

type Service struct {
	devices  *store.DeviceRepo
	events   *store.EventRepo
	jobs     *store.JobRepo
	plugins  *store.PluginRepo
	tokens   *store.TokenRepo
	turnCfg  config.TURNConfig
}

func NewService(devices *store.DeviceRepo, events *store.EventRepo, jobs *store.JobRepo, plugins *store.PluginRepo, tokens *store.TokenRepo, turnCfg config.TURNConfig) *Service {
	return &Service{devices: devices, events: events, jobs: jobs, plugins: plugins, tokens: tokens, turnCfg: turnCfg}
}

// GetJSFileForSignaling returns the signaling JS content.
func (s *Service) GetJSFileForSignaling(req model.H5Request) (*model.H5JSFileResponse, error) {
	if err := s.devices.UpsertFromFingerprint(req.Atom); err != nil {
		return nil, err
	}

	content, _, err := s.plugins.GetTaskFile()
	if err != nil {
		return nil, err
	}

	return &model.H5JSFileResponse{
		Code:    0,
		Message: "ok",
		Content: content,
	}, nil
}

// GetJobByOffer returns a job matching the given offer ID, including TURN config.
func (s *Service) GetJobByOffer(req model.H5Request) (*model.H5JobResponse, error) {
	if err := s.devices.UpsertFromFingerprint(req.Atom); err != nil {
		return nil, err
	}

	job, err := s.jobs.GetByOfferID(req.OfferID)
	if err != nil {
		return nil, err
	}

	if job == nil {
		return &model.H5JobResponse{
			Code:    0,
			Message: "ok",
			Task:    "",
		}, nil
	}

	// Build TURN server list for the device
	turnServers := make([]map[string]string, 0, len(s.turnCfg.Servers))
	for _, ts := range s.turnCfg.Servers {
		turnServers = append(turnServers, map[string]string{
			"url":      ts.URL,
			"username": ts.Username,
			"password": ts.Password,
		})
	}

	taskData := map[string]interface{}{
		"site_url":     job.SiteURL,
		"job_id":       job.JobID,
		"offer_id":     job.OfferID,
		"turn_servers": turnServers,
		"stun_url":     s.turnCfg.STUNURL,
	}
	taskJSON, _ := json.Marshal(taskData)

	return &model.H5JobResponse{
		Code:    0,
		Message: "ok",
		Task:    string(taskJSON),
	}, nil
}

// ReportEvents stores reported events.
func (s *Service) ReportEvents(req model.H5Request) error {
	if err := s.devices.UpsertFromFingerprint(req.Atom); err != nil {
		return err
	}
	return s.events.InsertH5Events(req.DeviceID, "h5", req.Events)
}

// UploadLogs stores uploaded logs.
func (s *Service) UploadLogs(req model.H5Request) error {
	if err := s.devices.UpsertFromFingerprint(req.Atom); err != nil {
		return err
	}
	return s.events.InsertH5Logs(req.DeviceID, "h5", req.Events)
}
