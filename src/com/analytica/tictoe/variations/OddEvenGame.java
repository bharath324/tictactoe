package com.analytica.tictoe.variations;

import com.analytica.tictoe.exceptions.IllegalUserInputException;

public abstract class OddEvenGame extends Game {

    public static final String ODD = "Odd";
    public static final String EVEN = "Even";

    @Override
    public int[] getRowColumnValue(String userInput) {
        String[] tokens = userInput.split(",");
        checkIndexRange(Integer.valueOf(tokens[0]));
        checkIndexRange(Integer.valueOf(tokens[1]));
        if (tokens.length != 3) {
            throw new IllegalUserInputException(String.format("User input \"%s\" is invalid for OddEven variant"
                    , userInput));
        }
        return new int[]{Integer.valueOf(tokens[0]), Integer.valueOf(tokens[1]),
                Integer.valueOf(tokens[2])};
    }
}
