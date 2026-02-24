package config

import (
	"os"

	"gopkg.in/yaml.v3"
)

type Config struct {
	Server   ServerConfig   `yaml:"server"`
	Crypto   CryptoConfig   `yaml:"crypto"`
	Admin    AdminConfig    `yaml:"admin"`
	Database DatabaseConfig `yaml:"database"`
	Logging  LoggingConfig  `yaml:"logging"`
}

type ServerConfig struct {
	DllpgdAddr    string `yaml:"dllpgd_addr"`
	PhantomAddr   string `yaml:"phantom_addr"`
	SignalingAddr  string `yaml:"signaling_addr"`
	AdminAddr     string `yaml:"admin_addr"`
}

type CryptoConfig struct {
	AESKeySeed string `yaml:"aes_key_seed"`
}

type AdminConfig struct {
	Username string `yaml:"username"`
	Password string `yaml:"password"`
}

type DatabaseConfig struct {
	Path string `yaml:"path"`
}

type LoggingConfig struct {
	Level  string `yaml:"level"`
	Pretty bool   `yaml:"pretty"`
}

func Load(path string) (*Config, error) {
	data, err := os.ReadFile(path)
	if err != nil {
		return nil, err
	}

	cfg := &Config{
		Server: ServerConfig{
			DllpgdAddr:    ":8083",
			PhantomAddr:   ":8081",
			SignalingAddr:  ":8082",
			AdminAddr:     ":9090",
		},
		Crypto: CryptoConfig{
			AESKeySeed: "GreenDay",
		},
		Admin: AdminConfig{
			Username: "admin",
			Password: "nova2admin",
		},
		Database: DatabaseConfig{
			Path: "./nova2.db",
		},
		Logging: LoggingConfig{
			Level:  "debug",
			Pretty: true,
		},
	}

	if err := yaml.Unmarshal(data, cfg); err != nil {
		return nil, err
	}
	return cfg, nil
}
