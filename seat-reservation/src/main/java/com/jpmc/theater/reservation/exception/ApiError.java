package com.jpmc.theater.reservation.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;
import java.text.MessageFormat;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"code","message","debugMessage"})
@Data
public class ApiError implements Serializable {
    private String message;
    private String code;
    private String debugMessage;

    public ApiError() {
    }

    public ApiError(String code, String message, String params){
        this.code = code;
        this.message = getExceptionMessage(message, params);
    }
    public ApiError(String code, String message, Throwable ex){
        this.code = code;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    private String getExceptionMessage(String message, String params) {
        return MessageFormat.format(message, params);
    }

    @Override
    public String toString() {
        return "ApiError{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", debugMessage='" + debugMessage + '\'' +
                '}';
    }
}
