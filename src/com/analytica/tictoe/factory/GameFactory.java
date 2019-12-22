package com.analytica.tictoe.factory;

import com.analytica.tictoe.variations.Game;

/**
 * Represents the generic factory of different variations of the games.
 * This factory produces Standard or OddEven games.
 */
public abstract class GameFactory {

    /**
     * Creates a Standard XO TicTacToe Game
     *
     * @return Standard XO TicTacToe Game
     */
    public abstract Game createStandardGame();

    /**
     * Creates a OddEven  TicTacToe Game
     *
     * @return OddEven  TicTacToe Game
     */
    public abstract Game createOddEvenGame();

}
