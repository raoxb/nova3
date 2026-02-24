package dllpgd

import (
	"nova2-server/internal/model"
	"nova2-server/internal/store"

	"github.com/google/uuid"
)

type Service struct {
	devices *store.DeviceRepo
	events  *store.EventRepo
	plugins *store.PluginRepo
}

func NewService(devices *store.DeviceRepo, events *store.EventRepo, plugins *store.PluginRepo) *Service {
	return &Service{devices: devices, events: events, plugins: plugins}
}

func (s *Service) GetConfig(req model.GetConfigRequest) (*model.GetConfigResponse, error) {
	// Upsert device
	if err := s.devices.UpsertFromAtom(req.Atom); err != nil {
		return nil, err
	}

	// Fetch plugins
	plugins, err := s.plugins.List()
	if err != nil {
		return nil, err
	}
	if plugins == nil {
		plugins = []model.LocalPluginInfo{}
	}

	return &model.GetConfigResponse{
		Code:    0,
		Message: "ok",
		DllpgdConfig: model.DllpgdConfig{
			Plugins:                       plugins,
			SessionID:                     uuid.New().String(),
			HookPkgNameStackTraces:        []string{},
			HookPackageManagerStackTraces: []string{},
			FixPackageName:                "",
		},
	}, nil
}

func (s *Service) UpdateEvent(req model.UpdateEventRequest) error {
	if err := s.devices.UpsertFromAtom(req.Atom); err != nil {
		return err
	}
	return s.events.InsertEvents(req.Atom.DeviceID, "dllpgd", req.Events)
}

func (s *Service) UpdateLog(req model.UpdateLogRequest) error {
	if err := s.devices.UpsertFromAtom(req.Atom); err != nil {
		return err
	}
	return s.events.InsertLogs(req.Atom.DeviceID, "dllpgd", req.Log)
}
