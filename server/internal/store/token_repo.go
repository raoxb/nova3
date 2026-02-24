package store

import (
	"crypto/rand"
	"database/sql"
	"encoding/base64"
	"time"

	"github.com/google/uuid"
)

type TokenRepo struct {
	db *sql.DB
}

func NewTokenRepo(db *sql.DB) *TokenRepo {
	return &TokenRepo{db: db}
}

// CreateToken generates a new token and optional encryption key for a device.
func (r *TokenRepo) CreateToken(deviceID, appID string) (token string, encKey string, err error) {
	token = uuid.New().String()

	// Generate 16-byte random XOR encryption key
	keyBytes := make([]byte, 16)
	if _, err = rand.Read(keyBytes); err != nil {
		return "", "", err
	}
	encKey = base64.StdEncoding.EncodeToString(keyBytes)

	_, err = r.db.Exec(`INSERT INTO auth_tokens (device_id, app_id, token, encryption_key, created_at) VALUES (?, ?, ?, ?, ?)`,
		deviceID, appID, token, encKey, time.Now().Unix())
	return
}

// GetEncryptionKey retrieves the XOR encryption key associated with a token.
func (r *TokenRepo) GetEncryptionKey(token string) ([]byte, error) {
	var encKeyB64 string
	err := r.db.QueryRow(`SELECT encryption_key FROM auth_tokens WHERE token = ?`, token).Scan(&encKeyB64)
	if err == sql.ErrNoRows {
		return nil, nil
	}
	if err != nil {
		return nil, err
	}
	if encKeyB64 == "" {
		return nil, nil
	}
	return base64.StdEncoding.DecodeString(encKeyB64)
}

// ValidateToken checks if a token exists.
func (r *TokenRepo) ValidateToken(token string) (bool, error) {
	var count int
	err := r.db.QueryRow(`SELECT COUNT(*) FROM auth_tokens WHERE token = ?`, token).Scan(&count)
	return count > 0, err
}

type TokenRow struct {
	ID            int64  `json:"id"`
	DeviceID      string `json:"device_id"`
	AppID         string `json:"app_id"`
	Token         string `json:"token"`
	EncryptionKey string `json:"encryption_key"`
	CreatedAt     int64  `json:"created_at"`
}

func (r *TokenRepo) ListByDevice(deviceID string) ([]TokenRow, error) {
	rows, err := r.db.Query(`SELECT id, device_id, app_id, token, encryption_key, created_at FROM auth_tokens WHERE device_id = ? ORDER BY created_at DESC`, deviceID)
	if err != nil {
		return nil, err
	}
	defer rows.Close()

	var tokens []TokenRow
	for rows.Next() {
		var t TokenRow
		if err := rows.Scan(&t.ID, &t.DeviceID, &t.AppID, &t.Token, &t.EncryptionKey, &t.CreatedAt); err != nil {
			return nil, err
		}
		tokens = append(tokens, t)
	}
	return tokens, rows.Err()
}
