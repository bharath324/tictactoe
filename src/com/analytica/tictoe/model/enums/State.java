package com.analytica.tictoe.model.enums;

/**
 * This class defines the 3 different states of Board after a user makes a move.
 */
public enum State {
    /**
     * If the current move makes the current player as the winner then it is {@link #WIN}
     */
    WIN(1),

    /**
     * If the move makes the board filled completely without any available moves
     * and the current player is not a winner,then it is {@link #TIE}
     */
    TIE(2),
    /**
     * If move does not make current player winner, and still there are available moves yet
     * to be played then it is  {@link #UNDECIDED}
     */
    UNDECIDED(3);

    int stateCode;

    State(int stateCode) {
        this.stateCode = stateCode;
    }

    public static State getState(int stateCode) {
        for (State state : values()) {
            if (state.getStateCode() == stateCode) {
                return state;
            }
        }
        throw new IllegalStateException("State type " + stateCode + " is illegal");
    }

    public int getStateCode() {
        return stateCode;
    }


}
