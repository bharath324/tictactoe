package com.analytica.tictoe.report;

import com.analytica.tictoe.exceptions.IllegalUserInputException;
import com.analytica.tictoe.model.players.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;

public class Report {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void println(String message, Object... args) {
        if (args == null) {
            System.out.println(message);
            return;
        }
        System.out.format(message + "\n", args);
    }

    public static String getUserInput() throws IOException {
        String line = br.readLine();
        if (Objects.isNull(line) || line.isEmpty()) {
            throw new IllegalArgumentException("User did not input anything");
        }
        return line;
    }

    public static void askForGameVariant() {
        System.out.println("Select a variant of TicTacToe.");
        System.out.println("Press 1  for Standard XO.\nPress 2 for OddEven .");
    }

    public static void askForGameMode() {
        System.out.println("Lets Play TicTacToe!");
        System.out.println("Choose the mode of the play.");
        System.out.println("Press 1  for \"Player vs Computer(Single Player)\"." +
                "\nPress 2 for \"Player vs Player(Two Player)\".");
    }


    public static void printOddEvenGameRules(String oddPlayerName, String evenPlayerName, String startingPlayer) {
        System.out.format("Please read OddEven Game rules below:\n");
        printDottedLine();
        System.out.format("1.%s will be Odd and %s will be Even.\n", oddPlayerName, evenPlayerName);
        System.out.println("2.Odd player will only enter any of the following numbers : 1,3,5,7.");
        System.out.println("3.Even player will only enter any of the following numbers : 2,4,6,8,9.");
        System.out.println("4.Player should not enter a number which he already used");
        System.out.println("5.Player should enter position of cell as a row,col,value \n " +
                "\tE.g. \"0,1,6\" for 0th row,1st column and 6 is the value of cell.");
        System.out.format("7.%s will start first.\n", startingPlayer);
    }

    public static void printStandardGameRules(String xPlayerName, String oPlayerName, String startingPlayer) {
        System.out.format("Please read Standard Game rules below:\n");
        printDottedLine();
        System.out.format("1.%s will be X and %s will be O.\n", xPlayerName, oPlayerName);
        System.out.println("2.Player should enter position of cell as a row,col \n " +
                "E.g. \"0,1\" for 0th row, 1st column and value 'X' or 'O' is implicit depending on current player.");
        System.out.format("3.%s will start first.\n", startingPlayer);
    }


    public static void printDottedLine() {
        System.out.println("--------------------------------------");
    }

    public static void askCurrentUserToMakeMove(Player currentPlayer) {
        if (currentPlayer.isHuman()) {
            printDottedLine();
            System.out.format("%s's turn : ", currentPlayer.getName());
        }
    }

    public static void printBoard(int[][] cells) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String value = getValue(cells[i][j]);
                System.out.format("%s |", value);
            }
            System.out.println("\n---------");
        }
    }

    private static String getValue(int cellValue) {
        String value = String.valueOf(cellValue);
        if (cellValue == (int) 'X') {
            value = "X";
        } else if (cellValue == (int) 'O') {
            value = "O";
        }
        return value;
    }


    public static void askUserChoiceXO() {
        System.out.println("Do you want to be 'X' or 'O' \n1.Select 1 for 'X'\n2.Select 2 for 'O'");
    }

    public static void askUserChoiceForEvenOdd() {
        System.out.println("Do you want to be 'Even' or 'Odd' \n1.Select 1 for 'Odd'\n2.Select 2 for 'Even'");
    }

    /**
     * Checks  the given user input is a valid integer and exists in the given {@code values}
     * array
     *
     */
    public static int validateUserInput(String input, Integer... values) {
        try {
            int userCode = Integer.valueOf(input);
            if (!Arrays.asList(values).contains(userCode)) {
                throw new IllegalUserInputException(String.format("User input %d is invalid", userCode));
            }
            return userCode;
        } catch (NumberFormatException e) {
            throw new IllegalUserInputException("User input is invalid ", e);
        }
    }

    public static void close() throws IOException {
        br.close();
    }

    public static void error(String message) {
        System.out.format("Error: %s.\nPlease enter a valid input as per rules described above\n"
                , message);
    }

}
