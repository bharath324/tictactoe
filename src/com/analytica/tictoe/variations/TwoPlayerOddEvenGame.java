package com.analytica.tictoe.variations;

import com.analytica.tictoe.model.players.OddEvenHumanPlayer;

import java.io.IOException;

import static com.analytica.tictoe.report.Report.getUserInput;
import static com.analytica.tictoe.report.Report.printOddEvenGameRules;

/**
 * Two Player mode OddEven game. It implements the following specific logic
 * <li/>It implements the logic for creating players.
 * <li/>Print game rules relevant to this combination of mode and variation
 * <li/>Gets the next move by returning the input from human on console
 */
public class TwoPlayerOddEvenGame extends OddEvenGame {

    @Override
    protected String getNextMove() throws IOException {
        return getUserInput();
    }

    @Override
    public void printGameRules() {
        printOddEvenGameRules("Player 1", "Player 2",
                "Odd");
    }

    @Override
    public void createPlayers() {
        player1 = new OddEvenHumanPlayer(ODD);
        player2 = new OddEvenHumanPlayer(EVEN);
    }
}
