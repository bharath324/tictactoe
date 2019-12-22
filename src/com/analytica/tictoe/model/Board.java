package com.analytica.tictoe.model;

import com.analytica.tictoe.exceptions.CellAlreadyOccupiedException;
import com.analytica.tictoe.exceptions.IllegalUserInputException;
import com.analytica.tictoe.model.enums.State;
import com.analytica.tictoe.model.players.Player;
import com.analytica.tictoe.model.players.StandardPlayer;
import com.analytica.tictoe.report.Report;

import java.util.HashSet;
import java.util.Set;

/**
 * A  TicTacToe 3X3 board.
 * It encapsulates {@link State state} of the board
 */
public class Board {
    private static final int ROW_COLUMN_SIZE = 3;
    int[][] cells = new int[ROW_COLUMN_SIZE][ROW_COLUMN_SIZE];
    private State state;
    private int currentRow;
    private int currentCol;
    private int currentValue;

    public Board() {
        this.state = State.UNDECIDED;
    }

    public State getState() {
        return state;
    }

    /**
     * <li/>Validates the {@code value} and checks if the cell associated with {@code move} is empty
     * <li/>Makes the {@code move} with {@code value} and computes the board state for this move
     * and sets the {@link #state} for this move.
     *
     * @param move the move the current player wants to make on this board
     * @param value  the value that user wants to place on the {@code move}
     */
    public void move(Move move, int value) {
        currentRow = move.getRow();
        currentCol = move.getCol();
        currentValue = value;

        Player currentPlayer = move.getPlayer();

        if (cells[currentRow][currentCol] != 0) {
            throwCellAlreadyOccupiedException(currentPlayer);
            return;
        }

        if (!currentPlayer.isValid(value)) {
            resetCurrentValues();
            throw new IllegalUserInputException(
                    String.format("Illegal user input value %s for player %s", value, currentPlayer.getName()));
        }
        cells[currentRow][currentCol] = value;
        int stateCode = currentPlayer.computeBoardStateAfterMove(this);
        state = State.getState(stateCode);
        resetCurrentValues();
    }

    private void throwCellAlreadyOccupiedException(Player currentPlayer) {
        String existingValue = String.valueOf(cells[currentRow][currentCol]);
        if (currentPlayer instanceof StandardPlayer) {
            existingValue = String.valueOf((char) cells[currentRow][currentCol]);
        }
        String errorMessage = String.format("Cell [%d,%d] is already occupied with a value %s ",
                currentRow, currentCol, existingValue);
        resetCurrentValues();
        throw new CellAlreadyOccupiedException(errorMessage);
    }

    private void resetCurrentValues() {
        currentRow = -1;
        currentCol = -1;
        currentValue = -1;
    }

    public boolean checkIfEmptyCellExists() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cells[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public int getCurrentCol() {
        return currentCol;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public int getCellValue(int row, int col) {
        return cells[row][col];
    }

    public void printBoard() {
        Report.printBoard(cells);
    }

    public Set<Move> getAllAvailableMoves(Player player) {
        Set<Move> emptyCells = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cells[i][j] == 0) {
                    emptyCells.add(new Move(i, j, player));
                }
            }
        }
        return emptyCells;
    }

    public void set(int row, int col, int value) {
        cells[row][col] = value;
    }

    /**
     * Evaluate the board with current player and opponent and rank them based on win, lose or tie.
     * If {@code player} wins he is ranked 10 and if opponent wins then the rank is -10 and 0 for tie.
     */
    public int rankBoardStateForStandardGame(int player, int opponent) {
        // Checking for Rows for X or O victory.
        for (int row = 0; row < 3; row++) {
            if (cells[row][0] == cells[row][1] &&
                    cells[row][1] == cells[row][2]) {
                if (cells[row][0] == player)
                    return +10;
                else if (cells[row][0] == opponent)
                    return -10;
            }
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col < 3; col++) {
            if (cells[0][col] == cells[1][col] &&
                    cells[1][col] == cells[2][col]) {
                if (cells[0][col] == player)
                    return +10;
                else if (cells[0][col] == opponent)
                    return -10;
            }
        }

        // Checking for forward diagonal .
        if (cells[0][0] == cells[1][1] && cells[1][1] == cells[2][2]) {
            if (cells[0][0] == player)
                return +10;
            else if (cells[0][0] == opponent)
                return -10;
        }

        //Checking for reverse diagonal
        if (cells[0][2] == cells[1][1] && cells[1][1] == cells[2][0]) {
            if (cells[0][2] == player)
                return +10;
            else if (cells[0][2] == opponent)
                return -10;
        }

        // Else if none of them have won then return 0
        return 0;
    }

    /**
     * Evaluate the board with current player and opponent and rank them based on win, lose or tie.
     * If {@code player} wins he is ranked 10 and if opponent wins then the rank is -10 and 0 for tie.
     */
    public int rankBoardStateForOddEvenGame(boolean isPlayer) {
        // Checking for Rows for X or O victory.
        final int SUM_TO_WIN = 15;
        boolean isWinner = false;
        for (int row = 0; row < 3; row++) {
            if (cells[row][0] + cells[row][1] + cells[row][2] == SUM_TO_WIN) {
                isWinner = true;
                break;
            }
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col < 3; col++) {
            if (cells[0][col] + cells[1][col] + cells[2][col] == SUM_TO_WIN) {
                isWinner = true;
                break;
            }
        }

        // Checking for forward diagonal .
        if (cells[0][0] + cells[1][1] + cells[2][2] == SUM_TO_WIN) {
            isWinner = true;
        }

        //Checking for reverse diagonal
        if (cells[0][2] + cells[1][1] + cells[2][0] == SUM_TO_WIN) {
            isWinner = true;
        }

        if (isWinner) {
            if (isPlayer) {
                return 10;
            } else {
                return -10;
            }
        }

        // Else if none of them have won then return 0
        return 0;
    }
}
