package store

import (
	"database/sql"
	"time"

	"nova2-server/internal/model"
)

type EventRepo struct {
	db *sql.DB
}

func NewEventRepo(db *sql.DB) *EventRepo {
	return &EventRepo{db: db}
}

func (r *EventRepo) InsertEvents(deviceID, source string, events []model.EventEntry) error {
	tx, err := r.db.Begin()
	if err != nil {
		return err
	}
	defer tx.Rollback()

	stmt, err := tx.Prepare(`INSERT INTO events (device_id, source, name, description, event_timestamp, created_at) VALUES (?, ?, ?, ?, ?, ?)`)
	if err != nil {
		return err
	}
	defer stmt.Close()

	now := time.Now().Unix()
	for _, e := range events {
		if _, err := stmt.Exec(deviceID, source, e.Name, e.Desc, e.Timestamp, now); err != nil {
			return err
		}
	}
	return tx.Commit()
}

func (r *EventRepo) InsertH5Events(deviceID, source string, eventStrings []string) error {
	tx, err := r.db.Begin()
	if err != nil {
		return err
	}
	defer tx.Rollback()

	stmt, err := tx.Prepare(`INSERT INTO events (device_id, source, name, description, event_timestamp, created_at) VALUES (?, ?, ?, ?, ?, ?)`)
	if err != nil {
		return err
	}
	defer stmt.Close()

	now := time.Now().Unix()
	for _, es := range eventStrings {
		if _, err := stmt.Exec(deviceID, source, "h5_event", es, now, now); err != nil {
			return err
		}
	}
	return tx.Commit()
}

func (r *EventRepo) InsertLogs(deviceID, source string, logs []model.LogEntry) error {
	tx, err := r.db.Begin()
	if err != nil {
		return err
	}
	defer tx.Rollback()

	stmt, err := tx.Prepare(`INSERT INTO logs (device_id, source, level, tag, message, log_timestamp, created_at) VALUES (?, ?, ?, ?, ?, ?, ?)`)
	if err != nil {
		return err
	}
	defer stmt.Close()

	now := time.Now().Unix()
	for _, l := range logs {
		if _, err := stmt.Exec(deviceID, source, l.Level, l.Tag, l.Message, l.Timestamp, now); err != nil {
			return err
		}
	}
	return tx.Commit()
}

func (r *EventRepo) InsertH5Logs(deviceID, source string, logStrings []string) error {
	tx, err := r.db.Begin()
	if err != nil {
		return err
	}
	defer tx.Rollback()

	stmt, err := tx.Prepare(`INSERT INTO logs (device_id, source, level, tag, message, log_timestamp, created_at) VALUES (?, ?, ?, ?, ?, ?, ?)`)
	if err != nil {
		return err
	}
	defer stmt.Close()

	now := time.Now().Unix()
	for _, ls := range logStrings {
		if _, err := stmt.Exec(deviceID, source, "INFO", "h5", ls, now, now); err != nil {
			return err
		}
	}
	return tx.Commit()
}

type EventRow struct {
	ID             int64  `json:"id"`
	DeviceID       string `json:"device_id"`
	Source         string `json:"source"`
	Name           string `json:"name"`
	Description    string `json:"description"`
	EventTimestamp int64  `json:"event_timestamp"`
	CreatedAt      int64  `json:"created_at"`
}

func (r *EventRepo) ListEvents(limit, offset int) ([]EventRow, error) {
	rows, err := r.db.Query(`SELECT id, device_id, source, name, description, event_timestamp, created_at FROM events ORDER BY id DESC LIMIT ? OFFSET ?`, limit, offset)
	if err != nil {
		return nil, err
	}
	defer rows.Close()

	var events []EventRow
	for rows.Next() {
		var e EventRow
		if err := rows.Scan(&e.ID, &e.DeviceID, &e.Source, &e.Name, &e.Description, &e.EventTimestamp, &e.CreatedAt); err != nil {
			return nil, err
		}
		events = append(events, e)
	}
	return events, rows.Err()
}

type LogRow struct {
	ID           int64  `json:"id"`
	DeviceID     string `json:"device_id"`
	Source       string `json:"source"`
	Level        string `json:"level"`
	Tag          string `json:"tag"`
	Message      string `json:"message"`
	LogTimestamp int64  `json:"log_timestamp"`
	CreatedAt    int64  `json:"created_at"`
}

func (r *EventRepo) ListLogs(limit, offset int) ([]LogRow, error) {
	rows, err := r.db.Query(`SELECT id, device_id, source, level, tag, message, log_timestamp, created_at FROM logs ORDER BY id DESC LIMIT ? OFFSET ?`, limit, offset)
	if err != nil {
		return nil, err
	}
	defer rows.Close()

	var logs []LogRow
	for rows.Next() {
		var l LogRow
		if err := rows.Scan(&l.ID, &l.DeviceID, &l.Source, &l.Level, &l.Tag, &l.Message, &l.LogTimestamp, &l.CreatedAt); err != nil {
			return nil, err
		}
		logs = append(logs, l)
	}
	return logs, rows.Err()
}

func (r *EventRepo) CountEvents() (int64, error) {
	var count int64
	err := r.db.QueryRow(`SELECT COUNT(*) FROM events`).Scan(&count)
	return count, err
}

func (r *EventRepo) CountLogs() (int64, error) {
	var count int64
	err := r.db.QueryRow(`SELECT COUNT(*) FROM logs`).Scan(&count)
	return count, err
}
