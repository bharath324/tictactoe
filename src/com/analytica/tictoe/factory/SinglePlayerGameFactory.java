package com.analytica.tictoe.factory;

import com.analytica.tictoe.variations.Game;
import com.analytica.tictoe.variations.SinglePlayerOddEvenGame;
import com.analytica.tictoe.variations.SinglePlayerStandardGame;

/**
 * Represents a {@link GameFactory} which is a
 * {@link com.analytica.tictoe.model.enums.GameMode#SINGLE_PLAYER Single Player}
 * mode  that can produce variations of Standard and OddEven game
 */
public class SinglePlayerGameFactory extends GameFactory {
    @Override
    public Game createStandardGame() {
        return new SinglePlayerStandardGame();
    }

    @Override
    public Game createOddEvenGame() {
        return new SinglePlayerOddEvenGame();
    }
}
