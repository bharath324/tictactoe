package com.analytica.tictoe.model.enums;

/**
 * Represent two modes of TicTacToe
 */
public enum GameMode {
    /**
     * User plays against computer
     */
    SINGLE_PLAYER(1),
    /**
     * A user plays against another user
     */
    TWO_PLAYER(2);

    private int mode;

    GameMode(int mode) {
        this.mode = mode;
    }

    public static GameMode getGameMode(int mode) {
        for (GameMode gameMode : values()) {
            if (gameMode.getMode() == mode) {
                return gameMode;
            }
        }
        throw new IllegalArgumentException("Game mode " + mode + " is illegal");
    }

    public int getMode() {
        return mode;
    }
}
