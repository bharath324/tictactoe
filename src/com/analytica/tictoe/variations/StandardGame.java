package com.analytica.tictoe.variations;

import com.analytica.tictoe.exceptions.IllegalUserInputException;

public abstract class StandardGame extends Game {


    @Override
    public int[] getRowColumnValue(String userInput) {
        String[] tokens = userInput.split(",");
        if (tokens.length != 2) {
            throw new IllegalUserInputException(String.format("User input \"%s\" is invalid XO standard game variant"
                    , userInput));
        }
        checkIndexRange(Integer.valueOf(tokens[0]));
        checkIndexRange(Integer.valueOf(tokens[1]));
        int value = "X".equals(currentPlayer.getName()) ? (int) 'X' : (int) 'O';
        return new int[]{Integer.valueOf(tokens[0]), Integer.valueOf(tokens[1]), value};
    }
}
