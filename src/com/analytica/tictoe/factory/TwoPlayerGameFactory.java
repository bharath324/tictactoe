package com.analytica.tictoe.factory;

import com.analytica.tictoe.variations.Game;
import com.analytica.tictoe.variations.TwoPlayerOddEvenGame;
import com.analytica.tictoe.variations.TwoPlayerStandardGame;

/**
 * Represents a {@link GameFactory} which is a
 * {@link com.analytica.tictoe.model.enums.GameMode#TWO_PLAYER Two Player}
 * mode  that can produce variations of Standard and OddEven game
 */
public class TwoPlayerGameFactory extends GameFactory {

    @Override
    public Game createStandardGame() {
        return new TwoPlayerStandardGame();
    }

    @Override
    public Game createOddEvenGame() {
        return new TwoPlayerOddEvenGame();
    }
}
