package com.analytica.tictoe.exceptions;

public class NumberAlreadyUsedException extends RuntimeException {
    public NumberAlreadyUsedException(String message) {
        super(message);
    }
}
