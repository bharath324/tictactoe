package com.analytica.tictoe.model.players;

/**
 * Standard variation of game played by a human player by entering user input on the console.
 * The player can be either "X"  or "O"
 */
public class StandardHumanPlayer extends StandardPlayer {

    public StandardHumanPlayer(String name) {
        super(name, true);
    }

}
