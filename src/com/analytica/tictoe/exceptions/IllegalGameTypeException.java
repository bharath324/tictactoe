package com.analytica.tictoe.exceptions;

public class IllegalGameTypeException extends RuntimeException {
    public IllegalGameTypeException(String message) {
        super(message);
    }
}
