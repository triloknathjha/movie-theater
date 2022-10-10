package com.jpmc.theater.reservation.exception;

import org.springframework.validation.FieldError;

public class ApiErrorFactory {
    public static ApiError getApiError(FieldError fieldError){
        String property = fieldError.getField();
        String validationCode = fieldError.getCode() != null ? fieldError.getCode().toLowerCase() : "";
        switch (validationCode) {
            case Constants.NOT_EMPTY_VALIDATION:
            case Constants.NOT_BLANK_VALIDATION:
            case Constants.NOT_NULL_VALIDATION:
                return new ApiError(ErrorCode.MISSING_FIELD_VALUE.getCode(), ErrorCode.MISSING_FIELD_VALUE.getMessage(), property);
            default:
                return null;
        }
    }
}
