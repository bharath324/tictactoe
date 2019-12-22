package com.analytica.tictoe.exceptions;

public class CellAlreadyOccupiedException extends RuntimeException {
    public CellAlreadyOccupiedException(String message) {
        super(message);
    }
}
