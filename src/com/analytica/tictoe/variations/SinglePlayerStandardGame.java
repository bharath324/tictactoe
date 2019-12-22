package com.analytica.tictoe.variations;

import com.analytica.tictoe.model.players.StandardComputerPlayer;
import com.analytica.tictoe.model.players.StandardHumanPlayer;
import com.analytica.tictoe.model.players.StandardPlayer;

import java.io.IOException;

import static com.analytica.tictoe.report.Report.*;

public class SinglePlayerStandardGame extends StandardGame {

    private int userInputForPlayerName;

    @Override
    public void printGameRules() {
        String xPlayerName = userInputForPlayerName == 1 ? "You" : "Computer";
        String oPlayerName = userInputForPlayerName == 1 ? "Computer" : "You";
        printStandardGameRules(xPlayerName, oPlayerName, currentPlayer.getName());
    }

    @Override
    protected void askForSinglePlayerNameAndValidate() throws Exception {
        askUserChoiceXO();
        String userInputSelection = getUserInput();
        userInputForPlayerName = validateUserInput(userInputSelection, 1, 2);
    }

    @Override
    public void createPlayers() {
        if (userInputForPlayerName == 1) {
            player1 = new StandardHumanPlayer("X");
            player2 = new StandardComputerPlayer("O");
        } else {
            player1 = new StandardHumanPlayer("O");
            player2 = new StandardComputerPlayer("X");
        }
    }

    @Override
    protected String getNextMove() throws IOException {
        if (currentPlayer.isHuman()) {
            return getUserInput();
        } else {
            StandardPlayer otherPlayer = (StandardPlayer) (currentPlayer == player1 ? player2 : player1);
            return ((StandardComputerPlayer) currentPlayer).getBestMove(board, (StandardPlayer) currentPlayer, otherPlayer);
        }
    }
}
