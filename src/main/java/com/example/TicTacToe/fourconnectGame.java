package com.example.TicTacToe;

public class fourconnectGame {


    private final int rows = 6;
    private final int cols = 7;
    private final int[][] board = new int[rows][cols];
    private int currentPlayer = 1;

    public boolean dropPiece(int col) {
        if (col < 0 || col >= cols) {
            throw new IllegalArgumentException("Column out of bounds");
        }

        for (int row = rows - 1; row >= 0; row--) {
            if (board[row][col] == 0) {
                board[row][col] = currentPlayer;
                currentPlayer = 3 - currentPlayer;
                return true;
            }
        }
        return false;
    }

    public int checkWinner() {
    	   for (int row = 0; row < rows; row++) {
               for (int col = 0; col < cols; col++) {
                   int player = board[row][col];
                   if (player == 0) continue;

                   // Check horizontal
                   if (col + 3 < cols &&
                       player == board[row][col + 1] &&
                       player == board[row][col + 2] &&
                       player == board[row][col + 3]) {
                       return player;
                   }

                   // Check vertical
                   if (row + 3 < rows &&
                       player == board[row + 1][col] &&
                       player == board[row + 2][col] &&
                       player == board[row + 3][col]) {
                       return player;
                   }

                   // Check diagonal (bottom-left to top-right)
                   if (row + 3 < rows && col + 3 < cols &&
                       player == board[row + 1][col + 1] &&
                       player == board[row + 2][col + 2] &&
                       player == board[row + 3][col + 3]) {
                       return player;
                   }

                   // Check diagonal (top-left to bottom-right)
                   if (row - 3 >= 0 && col + 3 < cols &&
                       player == board[row - 1][col + 1] &&
                       player == board[row - 2][col + 2] &&
                       player == board[row - 3][col + 3]) {
                       return player;
                   }
               }
           }
           return 0;
    }
    public void reset() {
        // Reset the game board and other state variables
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = 0;
            }
        }
        currentPlayer = 1;
    }
    public int[][] getBoard() {
        return board;
    }
}
