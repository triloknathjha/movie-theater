package com.jpmc.theater.reservation.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        List<ApiError> apiErrors;
        apiErrors = result.getFieldErrors().stream().map(fieldError -> ApiErrorFactory.getApiError(fieldError)).filter(apiError -> apiError != null).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(apiErrors);
    }

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected  ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ApiError> apiErrors = new ArrayList<>();
        apiErrors.add(new ApiError(ErrorCode.REQUEST_MALFORMED_ERROR.getCode(),ErrorCode.REQUEST_MALFORMED_ERROR.getMessage(),ex));
        return ResponseEntity.badRequest().body(apiErrors);
    }

    @ExceptionHandler({InvalidInputException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleInvalidInputException(Exception ex) {
        List<ApiError> apiErrors = new ArrayList<>();
        apiErrors.add(new ApiError(String.valueOf(HttpStatus.BAD_REQUEST.value()),HttpStatus.BAD_REQUEST.getReasonPhrase(), ex));
        return ResponseEntity.badRequest().body(apiErrors);
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<Object> handleException(Exception ex) {
        List<ApiError> apiErrors = new ArrayList<>();
        apiErrors.add(new ApiError(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiErrors);
    }
}