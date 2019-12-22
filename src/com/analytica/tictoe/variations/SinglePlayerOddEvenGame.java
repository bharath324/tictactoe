package com.analytica.tictoe.variations;

import com.analytica.tictoe.model.players.OddEvenComputerPlayer;
import com.analytica.tictoe.model.players.OddEvenHumanPlayer;
import com.analytica.tictoe.model.players.OddEvenPlayer;

import java.io.IOException;

import static com.analytica.tictoe.report.Report.*;

public class SinglePlayerOddEvenGame extends OddEvenGame {

    private int userChoiceForOddEven;

    @Override
    public void printGameRules() {
        String oddPlayerName = userChoiceForOddEven == 1 ? "You" : "Computer";
        String evenPlayerName = userChoiceForOddEven == 1 ? "Computer" : "You";
        printOddEvenGameRules(oddPlayerName, evenPlayerName, currentPlayer.getName());
    }

    @Override
    protected void askForSinglePlayerNameAndValidate() throws Exception {
        askUserChoiceForEvenOdd();
        String userChoice = getUserInput();
        userChoiceForOddEven = validateUserInput(userChoice, 1, 2);
    }

    @Override
    public void createPlayers() {
        if (userChoiceForOddEven == 1) {
            player1 = new OddEvenHumanPlayer(ODD);
            player2 = new OddEvenComputerPlayer(EVEN);
        } else {
            player1 = new OddEvenHumanPlayer(EVEN);
            player2 = new OddEvenComputerPlayer(ODD);
        }
    }

    @Override
    protected String getNextMove() throws IOException {
        if (currentPlayer.isHuman()) {
            return getUserInput();
        } else {
            OddEvenPlayer otherPlayer = (OddEvenPlayer) (currentPlayer == player1 ? player2 : player1);
            String bestMove = ((OddEvenComputerPlayer) currentPlayer)
                    .getBestMove(board, (OddEvenPlayer) currentPlayer, otherPlayer);
            printDottedLine();
            println("%s turn : %s", currentPlayer.getName(), bestMove);
            return bestMove;
        }
    }
}
