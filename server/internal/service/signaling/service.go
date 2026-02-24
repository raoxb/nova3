package signaling

import (
	"nova2-server/internal/config"
	"nova2-server/internal/store"
)

type Service struct {
	devices *store.DeviceRepo
	jobs    *store.JobRepo
	rooms   *RoomManager
	turnCfg config.TURNConfig
}

func NewService(devices *store.DeviceRepo, jobs *store.JobRepo, turnCfg config.TURNConfig) *Service {
	return &Service{
		devices: devices,
		jobs:    jobs,
		rooms:   NewRoomManager(),
		turnCfg: turnCfg,
	}
}

func (s *Service) Rooms() *RoomManager {
	return s.rooms
}

func (s *Service) TURNConfig() config.TURNConfig {
	return s.turnCfg
}

// CheckPluginStart determines if a device should start the signaling plugin.
func (s *Service) CheckPluginStart(deviceID string) (run bool, offerID, jobID string, err error) {
	dev, err := s.devices.GetByDeviceID(deviceID)
	if err != nil {
		return false, "", "", err
	}
	if dev == nil || dev.SignalingEnabled == 0 {
		return false, "", "", nil
	}

	// Find a pending job for this device
	job, err := s.jobs.GetPendingForDevice(deviceID)
	if err != nil {
		return false, "", "", err
	}
	if job == nil {
		return true, "", "", nil // signaling enabled but no pending job
	}

	return true, job.OfferID, job.JobID, nil
}

// UpdateStatus updates the status of a signaling job.
func (s *Service) UpdateStatus(jobID, status string) error {
	if jobID == "" {
		return nil
	}
	return s.jobs.UpdateStatus(jobID, status)
}
