package com.analytica.tictoe.model.players;

import com.analytica.tictoe.model.Board;

/**
 * Generic player who can be a
 * <li/> Standard Game Human Player
 * <li/> Standard Game Computer
 * <li/> OddEven Game Human Player
 * <li/> OddEven Game Computer
 */
public abstract class Player {
    boolean isWinner = false;
    boolean isHuman = false;
    private String name;

    Player(String name, boolean isHuman) {
        this.name = name;
        this.isHuman = isHuman;
    }

    public String getName() {
        return name;
    }

    /**
     * Checks the validity of the given value entered by the user
     * This value has to be placed in a cell of the TicTacToe board.
     */
    public abstract boolean isValid(int val);

    /**
     * Compute the current {@link Board } state after the current player makes his move.
     *
     * @param board the Board whose state needs to be computed
     * @return an int which states the board state the valid values are from enum
     * {@link com.analytica.tictoe.model.enums.State state}
     */
    public abstract int computeBoardStateAfterMove(Board board);

    public boolean isWinner() {
        return isWinner;
    }

    public boolean isHuman() {
        return isHuman;
    }

}
