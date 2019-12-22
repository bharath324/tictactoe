package com.analytica.tictoe.model;

import com.analytica.tictoe.model.players.Player;

/**
 * Move class encapsulates the row, column and the current player who wants to
 * make this move.
 */
public class Move {

    private int row;
    private int col;
    private Player player;

    public Move(int row, int col, Player player) {
        this.row = row;
        this.col = col;
        this.player = player;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Player getPlayer() {
        return player;
    }
}
