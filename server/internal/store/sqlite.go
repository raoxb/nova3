package store

import (
	"database/sql"
	"fmt"

	_ "modernc.org/sqlite"
)

func Open(path string) (*sql.DB, error) {
	db, err := sql.Open("sqlite", path+"?_pragma=journal_mode(WAL)&_pragma=busy_timeout(5000)")
	if err != nil {
		return nil, fmt.Errorf("open database: %w", err)
	}
	db.SetMaxOpenConns(1) // SQLite best practice
	if err := migrate(db); err != nil {
		db.Close()
		return nil, fmt.Errorf("migrate: %w", err)
	}
	return db, nil
}

func migrate(db *sql.DB) error {
	statements := []string{
		`CREATE TABLE IF NOT EXISTS devices (
			id INTEGER PRIMARY KEY AUTOINCREMENT,
			device_id TEXT UNIQUE NOT NULL,
			app_package TEXT NOT NULL DEFAULT '',
			model TEXT NOT NULL DEFAULT '',
			brand TEXT NOT NULL DEFAULT '',
			android_version TEXT NOT NULL DEFAULT '',
			locale TEXT NOT NULL DEFAULT '',
			timezone TEXT NOT NULL DEFAULT '',
			channel TEXT NOT NULL DEFAULT '',
			screen_resolution TEXT NOT NULL DEFAULT '',
			network_type TEXT NOT NULL DEFAULT '',
			last_seen_at INTEGER NOT NULL DEFAULT 0,
			signaling_enabled INTEGER NOT NULL DEFAULT 0,
			created_at INTEGER NOT NULL DEFAULT (strftime('%s','now')),
			extra TEXT NOT NULL DEFAULT '{}'
		)`,
		`CREATE TABLE IF NOT EXISTS auth_tokens (
			id INTEGER PRIMARY KEY AUTOINCREMENT,
			device_id TEXT NOT NULL,
			app_id TEXT NOT NULL DEFAULT '',
			token TEXT UNIQUE NOT NULL,
			encryption_key TEXT NOT NULL DEFAULT '',
			created_at INTEGER NOT NULL DEFAULT (strftime('%s','now')),
			FOREIGN KEY (device_id) REFERENCES devices(device_id)
		)`,
		`CREATE TABLE IF NOT EXISTS plugins (
			id INTEGER PRIMARY KEY AUTOINCREMENT,
			name TEXT NOT NULL DEFAULT '',
			url TEXT NOT NULL DEFAULT '',
			md5 TEXT NOT NULL DEFAULT '',
			class_name TEXT NOT NULL DEFAULT '',
			need_run INTEGER NOT NULL DEFAULT 1,
			need_update INTEGER NOT NULL DEFAULT 0,
			delay_run_seconds INTEGER NOT NULL DEFAULT 0,
			last_version INTEGER NOT NULL DEFAULT 1,
			password TEXT NOT NULL DEFAULT '',
			plugin_status INTEGER NOT NULL DEFAULT 0,
			end_delete INTEGER NOT NULL DEFAULT 0,
			auto_start_on_init INTEGER NOT NULL DEFAULT 1,
			start_index INTEGER NOT NULL DEFAULT 0,
			run_in_sub_process INTEGER NOT NULL DEFAULT 0
		)`,
		`CREATE TABLE IF NOT EXISTS events (
			id INTEGER PRIMARY KEY AUTOINCREMENT,
			device_id TEXT NOT NULL,
			source TEXT NOT NULL DEFAULT 'dllpgd',
			name TEXT NOT NULL DEFAULT '',
			description TEXT NOT NULL DEFAULT '',
			event_timestamp INTEGER NOT NULL DEFAULT 0,
			created_at INTEGER NOT NULL DEFAULT (strftime('%s','now'))
		)`,
		`CREATE TABLE IF NOT EXISTS logs (
			id INTEGER PRIMARY KEY AUTOINCREMENT,
			device_id TEXT NOT NULL,
			source TEXT NOT NULL DEFAULT 'dllpgd',
			level TEXT NOT NULL DEFAULT 'INFO',
			tag TEXT NOT NULL DEFAULT '',
			message TEXT NOT NULL DEFAULT '',
			log_timestamp INTEGER NOT NULL DEFAULT 0,
			created_at INTEGER NOT NULL DEFAULT (strftime('%s','now'))
		)`,
		`CREATE TABLE IF NOT EXISTS jobs (
			id INTEGER PRIMARY KEY AUTOINCREMENT,
			job_id TEXT UNIQUE NOT NULL,
			offer_id TEXT NOT NULL DEFAULT '',
			device_id TEXT NOT NULL DEFAULT '',
			site_url TEXT NOT NULL DEFAULT '',
			status TEXT NOT NULL DEFAULT 'pending',
			created_at INTEGER NOT NULL DEFAULT (strftime('%s','now')),
			updated_at INTEGER NOT NULL DEFAULT (strftime('%s','now'))
		)`,
		`CREATE TABLE IF NOT EXISTS signaling_sessions (
			id INTEGER PRIMARY KEY AUTOINCREMENT,
			session_id TEXT UNIQUE NOT NULL,
			device_id TEXT NOT NULL DEFAULT '',
			operator_id TEXT NOT NULL DEFAULT '',
			offer_id TEXT NOT NULL DEFAULT '',
			job_id TEXT NOT NULL DEFAULT '',
			status TEXT NOT NULL DEFAULT 'pending',
			created_at INTEGER NOT NULL DEFAULT (strftime('%s','now')),
			updated_at INTEGER NOT NULL DEFAULT (strftime('%s','now'))
		)`,
		`CREATE TABLE IF NOT EXISTS task_files (
			id INTEGER PRIMARY KEY AUTOINCREMENT,
			file_name TEXT NOT NULL DEFAULT 'main.js',
			content TEXT NOT NULL DEFAULT '',
			version TEXT NOT NULL DEFAULT '1',
			created_at INTEGER NOT NULL DEFAULT (strftime('%s','now')),
			updated_at INTEGER NOT NULL DEFAULT (strftime('%s','now'))
		)`,
		// Indexes
		`CREATE INDEX IF NOT EXISTS idx_events_device ON events(device_id)`,
		`CREATE INDEX IF NOT EXISTS idx_logs_device ON logs(device_id)`,
		`CREATE INDEX IF NOT EXISTS idx_tokens_device ON auth_tokens(device_id)`,
		`CREATE INDEX IF NOT EXISTS idx_jobs_offer ON jobs(offer_id)`,
		`CREATE INDEX IF NOT EXISTS idx_jobs_device ON jobs(device_id)`,
	}

	for _, stmt := range statements {
		if _, err := db.Exec(stmt); err != nil {
			return fmt.Errorf("exec %q: %w", stmt[:60], err)
		}
	}
	return nil
}
