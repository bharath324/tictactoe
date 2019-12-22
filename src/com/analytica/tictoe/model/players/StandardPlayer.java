package com.analytica.tictoe.model.players;

import com.analytica.tictoe.model.Board;
import com.analytica.tictoe.model.enums.State;

/**
 * An Standard player who can either be "X" or "O"
 * <li/> This class has Standard variation game logic for validating user input
 * <li/>It handles computing the board state by traversing through the board and checking
 * whether any line (row,column,diagonal,reverse diagonal) has all character numbers
 * as same i.e. XXX or OOO
 */
public class StandardPlayer extends Player {

    private final int xCode = (int) 'X';
    private final int oCode = (int) 'O';

    private int[] rowSum = new int[3];
    private int[] colSum = new int[3];
    private int diagonalSum;
    private int reverseDiagonalSum;

    private int charCode;

    StandardPlayer(String name, boolean isHuman) {
        super(name, isHuman);
        charCode = (int) name.charAt(0);
    }

    @Override
    public boolean isValid(int val) {
        return val == xCode || val == oCode;
    }

    @Override
    public int computeBoardStateAfterMove(Board board) {

        int row = board.getCurrentRow();
        int column = board.getCurrentCol();
        int currentValue = board.getCurrentValue();

        if (!isValid(board.getCurrentValue())) {
            throw new IllegalArgumentException("Illegal value " + currentValue + " selected by user " + this.getName());
        }

        rowSum[row] += currentValue;
        colSum[column] += currentValue;
        if (row == column) {
            diagonalSum += currentValue;
        }
        if (2 - column == row) {
            reverseDiagonalSum += currentValue;
        }
        int valueToAssert = xCode == currentValue ? 3 * xCode : 3 * oCode;
        if (rowSum[row] == valueToAssert
                || colSum[column] == valueToAssert
                || diagonalSum == valueToAssert
                || reverseDiagonalSum == valueToAssert) {
            this.isWinner = true;
            return State.WIN.getStateCode();
        }

        if (board.checkIfEmptyCellExists()) {
            return State.UNDECIDED.getStateCode();
        }

        return State.TIE.getStateCode();
    }

    int getCharCode() {
        return charCode;
    }

}
