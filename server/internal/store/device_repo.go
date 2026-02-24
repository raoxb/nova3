package store

import (
	"database/sql"
	"encoding/json"
	"time"

	"nova2-server/internal/model"
)

type DeviceRepo struct {
	db *sql.DB
}

func NewDeviceRepo(db *sql.DB) *DeviceRepo {
	return &DeviceRepo{db: db}
}

// UpsertFromAtom creates or updates a device from an Atom fingerprint.
func (r *DeviceRepo) UpsertFromAtom(atom model.Atom) error {
	_, err := r.db.Exec(`
		INSERT INTO devices (device_id, app_package, model, android_version, locale, timezone, last_seen_at)
		VALUES (?, ?, ?, ?, ?, ?, ?)
		ON CONFLICT(device_id) DO UPDATE SET
			app_package = excluded.app_package,
			model = excluded.model,
			android_version = excluded.android_version,
			locale = excluded.locale,
			timezone = excluded.timezone,
			last_seen_at = excluded.last_seen_at`,
		atom.DeviceID,
		atom.AppPackageName,
		atom.DeviceInfo.PhoneModel,
		atom.DeviceInfo.AndroidVersion,
		atom.DeviceInfo.Locale,
		atom.DeviceInfo.Timezone,
		time.Now().Unix(),
	)
	return err
}

// UpsertFromFingerprint creates or updates a device from a DeviceFingerprint.
func (r *DeviceRepo) UpsertFromFingerprint(fp model.DeviceFingerprint) error {
	_, err := r.db.Exec(`
		INSERT INTO devices (device_id, app_package, model, brand, android_version, locale, timezone, channel, screen_resolution, network_type, last_seen_at)
		VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
		ON CONFLICT(device_id) DO UPDATE SET
			app_package = excluded.app_package,
			model = excluded.model,
			brand = excluded.brand,
			android_version = excluded.android_version,
			locale = excluded.locale,
			timezone = excluded.timezone,
			channel = excluded.channel,
			screen_resolution = excluded.screen_resolution,
			network_type = excluded.network_type,
			last_seen_at = excluded.last_seen_at`,
		fp.DeviceID, fp.AppPackage, fp.Model, fp.Brand, fp.AndroidVersion,
		fp.Locale, fp.Timezone, fp.Channel, fp.ScreenResolution, fp.NetworkType,
		time.Now().Unix(),
	)
	return err
}

type DeviceRow struct {
	ID                int64  `json:"id"`
	DeviceID          string `json:"device_id"`
	AppPackage        string `json:"app_package"`
	Model             string `json:"model"`
	Brand             string `json:"brand"`
	AndroidVersion    string `json:"android_version"`
	Locale            string `json:"locale"`
	Timezone          string `json:"timezone"`
	Channel           string `json:"channel"`
	ScreenResolution  string `json:"screen_resolution"`
	NetworkType       string `json:"network_type"`
	LastSeenAt        int64  `json:"last_seen_at"`
	SignalingEnabled  int    `json:"signaling_enabled"`
	CreatedAt         int64  `json:"created_at"`
	Extra             string `json:"extra"`
}

func (r *DeviceRepo) List() ([]DeviceRow, error) {
	rows, err := r.db.Query(`SELECT id, device_id, app_package, model, brand, android_version, locale, timezone, channel, screen_resolution, network_type, last_seen_at, signaling_enabled, created_at, extra FROM devices ORDER BY last_seen_at DESC`)
	if err != nil {
		return nil, err
	}
	defer rows.Close()

	var devices []DeviceRow
	for rows.Next() {
		var d DeviceRow
		if err := rows.Scan(&d.ID, &d.DeviceID, &d.AppPackage, &d.Model, &d.Brand, &d.AndroidVersion, &d.Locale, &d.Timezone, &d.Channel, &d.ScreenResolution, &d.NetworkType, &d.LastSeenAt, &d.SignalingEnabled, &d.CreatedAt, &d.Extra); err != nil {
			return nil, err
		}
		devices = append(devices, d)
	}
	return devices, rows.Err()
}

func (r *DeviceRepo) GetByDeviceID(deviceID string) (*DeviceRow, error) {
	d := &DeviceRow{}
	err := r.db.QueryRow(`SELECT id, device_id, app_package, model, brand, android_version, locale, timezone, channel, screen_resolution, network_type, last_seen_at, signaling_enabled, created_at, extra FROM devices WHERE device_id = ?`, deviceID).
		Scan(&d.ID, &d.DeviceID, &d.AppPackage, &d.Model, &d.Brand, &d.AndroidVersion, &d.Locale, &d.Timezone, &d.Channel, &d.ScreenResolution, &d.NetworkType, &d.LastSeenAt, &d.SignalingEnabled, &d.CreatedAt, &d.Extra)
	if err == sql.ErrNoRows {
		return nil, nil
	}
	return d, err
}

func (r *DeviceRepo) SetSignalingEnabled(deviceID string, enabled bool) error {
	v := 0
	if enabled {
		v = 1
	}
	_, err := r.db.Exec(`UPDATE devices SET signaling_enabled = ? WHERE device_id = ?`, v, deviceID)
	return err
}

func (r *DeviceRepo) UpdateExtra(deviceID string, extra map[string]interface{}) error {
	data, _ := json.Marshal(extra)
	_, err := r.db.Exec(`UPDATE devices SET extra = ? WHERE device_id = ?`, string(data), deviceID)
	return err
}

func (r *DeviceRepo) Count() (int64, error) {
	var count int64
	err := r.db.QueryRow(`SELECT COUNT(*) FROM devices`).Scan(&count)
	return count, err
}

func (r *DeviceRepo) CountOnline(sinceSeconds int64) (int64, error) {
	var count int64
	threshold := time.Now().Unix() - sinceSeconds
	err := r.db.QueryRow(`SELECT COUNT(*) FROM devices WHERE last_seen_at > ?`, threshold).Scan(&count)
	return count, err
}
