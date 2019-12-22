package com.analytica.tictoe.model;

/**
 * This class is used as an encapsulation of move and value the user wants to place for
 * OddEven game
 */
public class MoveResultScore {
    Move move;
    int value;
    int score;

    public MoveResultScore(Move move, int value, int score) {
        this.move = move;
        this.value = value;
        this.score = score;
    }

    public Move getMove() {
        return move;
    }

    public int getValue() {
        return value;
    }

    public int getScore() {
        return score;
    }
}
