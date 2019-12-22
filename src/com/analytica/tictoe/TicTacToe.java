package com.analytica.tictoe;

import com.analytica.tictoe.factory.GameFactory;
import com.analytica.tictoe.factory.SinglePlayerGameFactory;
import com.analytica.tictoe.factory.TwoPlayerGameFactory;
import com.analytica.tictoe.model.enums.GameMode;
import com.analytica.tictoe.model.enums.GameType;
import com.analytica.tictoe.variations.Game;

import java.util.HashMap;
import java.util.Map;

import static com.analytica.tictoe.report.Report.*;

public class TicTacToe {


    private static Map<GameType, Game> gameTypeGameMap = new HashMap<>();
    private static Map<GameMode, GameFactory> gameFactoryMap = new HashMap<>();

    static {
        gameFactoryMap.put(GameMode.SINGLE_PLAYER, new SinglePlayerGameFactory());
        gameFactoryMap.put(GameMode.TWO_PLAYER, new TwoPlayerGameFactory());
    }

    public static void main(String[] args) throws Exception {
        try {
            askForGameMode();
            String gameModeLiteral = getUserInput();
            validateUserInput(gameModeLiteral, 1, 2);

            GameMode gameMode = GameMode.getGameMode(Integer.valueOf(gameModeLiteral));
            GameFactory gameFactory = gameFactoryMap.get(gameMode);

            askForGameVariant();
            String gameTypeLiteral = getUserInput();
            validateUserInput(gameTypeLiteral, 1, 2);

            GameType gameType = GameType.getGameType(Integer.valueOf(gameTypeLiteral));
            createGamesAndRegister(gameFactory);

            Game game = gameTypeGameMap.get(gameType);
            game.start();
        } finally {
            close();
        }
    }

     private static void createGamesAndRegister(GameFactory gameFactory) {
        gameTypeGameMap.put(GameType.STANDARD, gameFactory.createStandardGame());
        gameTypeGameMap.put(GameType.ODDEVEN, gameFactory.createOddEvenGame());
    }
}
