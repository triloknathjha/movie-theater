package com.jpmc.theater.reservation.exception;

public class InvalidInputException extends Exception {

    public InvalidInputException(String message, Throwable t) {
        super(message, t);
    }

    public InvalidInputException(String message) {
        super(message);
    }
}
