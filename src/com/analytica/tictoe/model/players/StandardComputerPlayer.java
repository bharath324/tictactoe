package com.analytica.tictoe.model.players;

import com.analytica.tictoe.model.Board;
import com.analytica.tictoe.model.Move;

import static com.analytica.tictoe.report.Report.printDottedLine;
import static com.analytica.tictoe.report.Report.println;

/**
 * Standard Game  played by a computer using the game theory
 * logic minimax, evaluate
 */
public class StandardComputerPlayer extends StandardPlayer {

    public StandardComputerPlayer(String name) {
        super(name, false);
    }

    /**
     * Traverse all cells, evaluate board state and use minimax function
     * for all empty cells. And return the cell with optimal value.
     */
    private Move findBestMove(Board board, StandardPlayer currentPlayer, StandardPlayer otherPlayer) {
        int bestVal = Integer.MIN_VALUE;
        Move bestMove = new Move(-1, -1, currentPlayer);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Check if cell is empty
                if (board.getCellValue(i, j) == 0) {

                    // Make the move
                    board.set(i, j, currentPlayer.getCharCode());

                    // compute evaluation function for this
                    // move.
                    int moveVal = minimax(board, false, currentPlayer.getCharCode(), otherPlayer.getCharCode());

                    // Undo the move
                    board.set(i, j, 0);

                    // If the value of the current move is
                    // more than the best value, then update
                    // best
                    if (moveVal > bestVal) {
                        bestMove.setRow(i);
                        bestMove.setCol(j);
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }

    /**
     * This is the minimax function. It considers all
     * the possible ways the game can go and returns
     * the value of the board
     */
    private int minimax(Board board,
                        boolean isMax, int player, int opponent) {
        int score = board.rankBoardStateForStandardGame(player, opponent);

        // If Maximizer has won the game
        // return his/her evaluated score
        if (score == 10)
            return score;

        // If Minimizer has won the game
        // return his/her evaluated score
        if (score == -10)
            return score;

        // If there are no more moves and
        // no winner then it is a tie
        if (board.getAllAvailableMoves(this).isEmpty())
            return 0;

        if (isMax) {
            // If this maximizer's move
            return findBestMaximersMove(board, isMax, player, opponent);
        } else {
            // If this minimizers move
            return findBestMinimizersMove(board, isMax, player, opponent);
        }
    }

    private int findBestMinimizersMove(Board board, Boolean isMax, int player, int opponent) {
        int best = Integer.MAX_VALUE;

        // Traverse all cells
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Check if cell is empty
                if (board.getCellValue(i, j) == 0) {
                    // Make the move
                    board.set(i, j, opponent);

                    // Call minimax recursively and choose
                    // the minimum value
                    best = Math.min(best, minimax(board,
                            !isMax, player, opponent));

                    // Undo the move
                    board.set(i, j, 0);

                    if (best == -10) {
                        return best;
                    }
                }
            }
        }
        return best;
    }

    private int findBestMaximersMove(Board board, Boolean isMax, int player, int opponent) {
        int best = Integer.MIN_VALUE;

        // Traverse all cells
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Check if cell is empty
                if (board.getCellValue(i, j) == 0) {
                    // Make the move
                    board.set(i, j, player);

                    // Call minimax recursively and choose
                    // the maximum value
                    best = Math.max(best, minimax(board,
                            !isMax, player, opponent));

                    // Undo the move
                    board.set(i, j, 0);

                    if (best == 10) {
                        return best;
                    }
                }
            }
        }
        return best;
    }

    public String getBestMove(Board board, StandardPlayer currentPlayer, StandardPlayer otherPlayer) {
        Move bestMove = findBestMove(board, currentPlayer, otherPlayer);
        String bestMoveLine = String.valueOf(bestMove.getRow()) +
                "," + bestMove.getCol();
        printDottedLine();
        println("%s turn : %s", currentPlayer.getName(), bestMoveLine);
        return bestMoveLine;
    }
}
