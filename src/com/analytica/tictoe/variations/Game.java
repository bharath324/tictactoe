package com.analytica.tictoe.variations;

import com.analytica.tictoe.exceptions.IllegalUserInputException;
import com.analytica.tictoe.exceptions.NoAvailableNumbersToUseException;
import com.analytica.tictoe.model.Board;
import com.analytica.tictoe.model.Move;
import com.analytica.tictoe.model.enums.State;
import com.analytica.tictoe.model.players.Player;

import java.io.IOException;

import static com.analytica.tictoe.report.Report.*;

public abstract class Game {

    Player player1;
    Player player2;
    Board board;
    Player currentPlayer;

    public Game() {
        board = new Board();
    }

    public void start() throws Exception {

        askForSinglePlayerNameAndValidate();
        createPlayers();
        setInitialCurrentPlayer();
        printGameRules();

        while (board.getState() == State.UNDECIDED) {
            try {
                askCurrentUserToMakeMove(currentPlayer);
                String nextMove = getNextMove();
                makeMove(nextMove);
                if (currentPlayer.isWinner()) {
                    board.printBoard();
                    break;
                }
                setNextPlayer();
                printBoard();
            } catch (NoAvailableNumbersToUseException e) {
                println(e.getMessage());
                println("Game ends with TIE");
                break;
            } catch (RuntimeException e) {
                error(e.getMessage());
            }
        }
        if (board.getState() == State.TIE) {
            printDottedLine();
            println("Its a TIE!!!");
            return;
        }
        if (currentPlayer.isWinner()) {
            printDottedLine();
            println("Winner is %s ", currentPlayer.getName());
        }
    }

    private void printBoard() {
        board.printBoard();
    }

    private void setNextPlayer() {
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }

    private void makeMove(String nextMove) {
        int[] rowColumnValue = getRowColumnValue(nextMove);
        int row = rowColumnValue[0];
        int column = rowColumnValue[1];
        int value = rowColumnValue[2];
        Move move = new Move(row, column, currentPlayer);
        board.move(move, value);
    }

    private void setInitialCurrentPlayer() {
        currentPlayer = player1;
    }

    protected abstract String getNextMove() throws IOException;

    protected void askForSinglePlayerNameAndValidate() throws Exception {
    }

    public abstract void printGameRules();

    public abstract void createPlayers();

    public abstract int[] getRowColumnValue(String userInput);

    void checkIndexRange(int index) {
        if (index < 0 || index > 2) {
            throw new IllegalUserInputException(String.format(
                    "Index %d is out of range. It should be either 0,1 or 2 as board is 3x3", index));
        }
    }

}
