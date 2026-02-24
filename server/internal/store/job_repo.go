package store

import (
	"database/sql"
	"time"

	"github.com/google/uuid"
)

type JobRepo struct {
	db *sql.DB
}

func NewJobRepo(db *sql.DB) *JobRepo {
	return &JobRepo{db: db}
}

type JobRow struct {
	ID        int64  `json:"id"`
	JobID     string `json:"job_id"`
	OfferID   string `json:"offer_id"`
	DeviceID  string `json:"device_id"`
	SiteURL   string `json:"site_url"`
	Status    string `json:"status"`
	CreatedAt int64  `json:"created_at"`
	UpdatedAt int64  `json:"updated_at"`
}

func (r *JobRepo) Create(offerID, deviceID, siteURL string) (*JobRow, error) {
	jobID := uuid.New().String()
	now := time.Now().Unix()
	_, err := r.db.Exec(`INSERT INTO jobs (job_id, offer_id, device_id, site_url, status, created_at, updated_at) VALUES (?, ?, ?, ?, 'pending', ?, ?)`,
		jobID, offerID, deviceID, siteURL, now, now)
	if err != nil {
		return nil, err
	}
	return &JobRow{JobID: jobID, OfferID: offerID, DeviceID: deviceID, SiteURL: siteURL, Status: "pending", CreatedAt: now, UpdatedAt: now}, nil
}

func (r *JobRepo) GetByJobID(jobID string) (*JobRow, error) {
	j := &JobRow{}
	err := r.db.QueryRow(`SELECT id, job_id, offer_id, device_id, site_url, status, created_at, updated_at FROM jobs WHERE job_id = ?`, jobID).
		Scan(&j.ID, &j.JobID, &j.OfferID, &j.DeviceID, &j.SiteURL, &j.Status, &j.CreatedAt, &j.UpdatedAt)
	if err == sql.ErrNoRows {
		return nil, nil
	}
	return j, err
}

func (r *JobRepo) GetByOfferID(offerID string) (*JobRow, error) {
	j := &JobRow{}
	err := r.db.QueryRow(`SELECT id, job_id, offer_id, device_id, site_url, status, created_at, updated_at FROM jobs WHERE offer_id = ? ORDER BY created_at DESC LIMIT 1`, offerID).
		Scan(&j.ID, &j.JobID, &j.OfferID, &j.DeviceID, &j.SiteURL, &j.Status, &j.CreatedAt, &j.UpdatedAt)
	if err == sql.ErrNoRows {
		return nil, nil
	}
	return j, err
}

func (r *JobRepo) GetPendingForDevice(deviceID string) (*JobRow, error) {
	j := &JobRow{}
	err := r.db.QueryRow(`SELECT id, job_id, offer_id, device_id, site_url, status, created_at, updated_at FROM jobs WHERE device_id = ? AND status = 'pending' ORDER BY created_at ASC LIMIT 1`, deviceID).
		Scan(&j.ID, &j.JobID, &j.OfferID, &j.DeviceID, &j.SiteURL, &j.Status, &j.CreatedAt, &j.UpdatedAt)
	if err == sql.ErrNoRows {
		return nil, nil
	}
	return j, err
}

func (r *JobRepo) UpdateStatus(jobID, status string) error {
	_, err := r.db.Exec(`UPDATE jobs SET status = ?, updated_at = ? WHERE job_id = ?`, status, time.Now().Unix(), jobID)
	return err
}

func (r *JobRepo) List() ([]JobRow, error) {
	rows, err := r.db.Query(`SELECT id, job_id, offer_id, device_id, site_url, status, created_at, updated_at FROM jobs ORDER BY created_at DESC`)
	if err != nil {
		return nil, err
	}
	defer rows.Close()

	var jobs []JobRow
	for rows.Next() {
		var j JobRow
		if err := rows.Scan(&j.ID, &j.JobID, &j.OfferID, &j.DeviceID, &j.SiteURL, &j.Status, &j.CreatedAt, &j.UpdatedAt); err != nil {
			return nil, err
		}
		jobs = append(jobs, j)
	}
	return jobs, rows.Err()
}

func (r *JobRepo) Count() (int64, error) {
	var count int64
	err := r.db.QueryRow(`SELECT COUNT(*) FROM jobs`).Scan(&count)
	return count, err
}
