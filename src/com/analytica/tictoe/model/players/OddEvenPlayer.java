package com.analytica.tictoe.model.players;

import com.analytica.tictoe.exceptions.NoAvailableNumbersToUseException;
import com.analytica.tictoe.exceptions.NumberAlreadyUsedException;
import com.analytica.tictoe.model.Board;
import com.analytica.tictoe.model.enums.State;
import com.analytica.tictoe.variations.OddEvenGame;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * An OddEven player who can either be "Even" or "Odd"
 * <li/> This class has OddEven game logic for validating user input
 * <li/>It handles computing the board state by traversing through the board and checking
 * whether any line (row,column,diagonal,reverse diagonal) has 3 numbers with sum 15
 */
public class OddEvenPlayer extends Player {

    static final int SUM_TO_WIN = 15;
    Set<Integer> userSelectedValues = new HashSet<>();

    OddEvenPlayer(String name, boolean isHuman) {
        super(name, isHuman);
    }

    @Override
    public boolean isValid(int val) {
        checkNumbersAvailability();
        if (val > 9 || val < 1) {
            return false;
        }
        checkIfNumberAlreadyUsed(val);
        if (OddEvenGame.ODD.equalsIgnoreCase(getName())) {
            return (val & 1) == 1;
        } else if (OddEvenGame.EVEN.equalsIgnoreCase(getName())) {
            return (val & 1) == 0;
        }
        return false;
    }

    private void checkIfNumberAlreadyUsed(int val) {
        if (userSelectedValues.contains(val)) {
            throw new NumberAlreadyUsedException(
                    String.format("Number %d is already used by player %s", val, this.getName()));
        }
    }

    private void checkNumbersAvailability() {
        int size = userSelectedValues.size();
        if (isEven() ? size == 4 : size == 5) {
            throw new NoAvailableNumbersToUseException(
                    String.format("Player %s used all available numbers.There are no numbers to play", this.getName()));
        }
    }

    @Override
    public int computeBoardStateAfterMove(Board board) {

        int row = board.getCurrentRow();
        int column = board.getCurrentCol();

        int rowSum = 0;
        int colSum = 0;
        int diagonalSum = 0;
        int reverseDiagonalSum = 0;

        userSelectedValues.add(board.getCurrentValue());


        for (int i = 0; i < 3; i++) {
            if (board.getCellValue(row, i) == 0) {
                rowSum = Integer.MIN_VALUE;//set it to a large negative which won't affect the sum as maximum
                // sum can be (9+8+6)
            }
            rowSum += board.getCellValue(row, i);

            if (board.getCellValue(i, column) == 0) {
                colSum = Integer.MIN_VALUE;
            }
            colSum += board.getCellValue(i, column);

            if (board.getCellValue(i, i) == 0) {
                diagonalSum = Integer.MIN_VALUE;
            }
            diagonalSum += board.getCellValue(i, i);

            if (board.getCellValue(2 - i, i) == 0) {
                reverseDiagonalSum = Integer.MIN_VALUE;
            }
            reverseDiagonalSum += board.getCellValue(2 - i, i);
        }
        if (rowSum == SUM_TO_WIN || colSum == SUM_TO_WIN || diagonalSum == SUM_TO_WIN
                || reverseDiagonalSum == SUM_TO_WIN) {
            isWinner = true;
            return State.WIN.getStateCode();
        }
        if (board.checkIfEmptyCellExists()) {
            return State.UNDECIDED.getStateCode();
        }
        return State.TIE.getStateCode();
    }

    public boolean isEven() {
        return this.getName().equalsIgnoreCase("Even");
    }

    public Set<Integer> getAvailableNumbers() {
        boolean isEven = isEven();
        Set<Integer> allAvailableNumbers;
        if (isEven) {
            allAvailableNumbers = IntStream.iterate(2, i -> i + 2).limit(4)
                    .filter(i -> !userSelectedValues.contains(i)).collect(HashSet::new, HashSet::add, HashSet::addAll);
        } else {
            allAvailableNumbers = IntStream.iterate(1, i -> i + 2).limit(5)
                    .filter(i -> !userSelectedValues.contains(i)).collect(HashSet::new, HashSet::add, HashSet::addAll);
        }
        return allAvailableNumbers;
    }
}
