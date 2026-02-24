package model

// CommonResponse is the base response for all API endpoints.
type CommonResponse struct {
	Code    int64  `json:"code"`
	Message string `json:"message"`
}

func SuccessResponse() CommonResponse {
	return CommonResponse{Code: 0, Message: "ok"}
}

func ErrorResponse(code int64, msg string) CommonResponse {
	return CommonResponse{Code: code, Message: msg}
}
