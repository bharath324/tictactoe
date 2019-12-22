package com.analytica.tictoe.exceptions;

public class IllegalUserInputException extends RuntimeException {
    public IllegalUserInputException(String message) {
        super(message);
    }

    public IllegalUserInputException(String message, Throwable e) {
        super(message, e);
    }
}
