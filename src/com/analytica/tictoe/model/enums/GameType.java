package com.analytica.tictoe.model.enums;

import com.analytica.tictoe.exceptions.IllegalGameTypeException;

/**
 * Represents the two variations for TicTacToe game supported by this project
 */
public enum GameType {

    /**
     * In this game players uses 'X' or 'O' and who so ever has 3 same characters in a line
     *  (row,column,diagonal,reverse diagonal) then he wins.
     */
    STANDARD(1),
    /**
     * In this game player uses odd or even numbers and when ever after player makes a move
     * and a line (row, column, diagonal, reverse diagonal) has 3 numbers with sum 15 then the player wins.
     */
    ODDEVEN(2);

    private int code;

    GameType(int code) {
        this.code = code;
    }

    public static GameType getGameType(int gameTypeCode) {
        for (GameType gameType : values()) {
            if (gameType.getCode() == gameTypeCode) {
                return gameType;
            }
        }
        throw new IllegalGameTypeException("Game type "+gameTypeCode+" is illegal");
    }

    public int getCode() {
        return code;
    }

}
