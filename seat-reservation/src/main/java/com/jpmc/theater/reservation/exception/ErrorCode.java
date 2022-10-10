package com.jpmc.theater.reservation.exception;

public enum ErrorCode {
    MISSING_FIELD_VALUE("1001", "Value is required for field {0}."),
    REQUEST_MALFORMED_ERROR("1004","Request is malformed.");
    ErrorCode(final String code, final String message){
        this.code = code;
        this.message = message;
    }

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(String code) { this.code = code; }

    public void setMessage(String message) { this.message = message; }
}
