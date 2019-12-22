package com.analytica.tictoe.variations;

import com.analytica.tictoe.model.players.StandardHumanPlayer;

import java.io.IOException;

import static com.analytica.tictoe.report.Report.getUserInput;
import static com.analytica.tictoe.report.Report.printStandardGameRules;

/**
 * Two Player mode Standard game. It implements the following specific logic
 * <li/>It implements the logic for creating players.
 * <li/>Print game rules relevant to this combination of mode and variation
 * <li/>Gets the next move by returning the input from human on console
 */
public class TwoPlayerStandardGame extends StandardGame {

    @Override
    protected String getNextMove() throws IOException {
        return getUserInput();
    }

    @Override
    public void printGameRules() {
        printStandardGameRules("Player 1", "Player 2",
                "X");
    }

    @Override
    public void createPlayers() {
        player1 = new StandardHumanPlayer("X");
        player2 = new StandardHumanPlayer("O");
    }
}
