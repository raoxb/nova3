.PHONY: build run test clean

BINARY := nova2-server
BUILD_DIR := ./bin

build:
	go build -o $(BUILD_DIR)/$(BINARY) ./cmd/nova2-server

run: build
	$(BUILD_DIR)/$(BINARY) -config config.yaml

test:
	go test ./internal/crypto/... -v

test-all:
	go test ./... -v

clean:
	rm -rf $(BUILD_DIR) nova2.db

deps:
	go mod tidy

fmt:
	go fmt ./...

vet:
	go vet ./...
