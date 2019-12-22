package com.analytica.tictoe.exceptions;

public class NoAvailableNumbersToUseException extends RuntimeException {
    public NoAvailableNumbersToUseException(String message) {
        super(message);
    }
}
