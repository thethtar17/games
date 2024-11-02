package com.example.TicTacToe;

//public class CheckersBoard {
//    private String[][] board;
//    private String currentPlayer;
//
//    public CheckersBoard() {
//        board = new String[8][8];
//        resetBoard();
//        currentPlayer = "WHITE";
//    }
//
//    public void resetBoard() {
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//                if ((i + j) % 2 != 0) {
//                    if (i < 3) {
//                        board[i][j] = "BLACK";
//                    } else if (i > 4) {
//                        board[i][j] = "WHITE";
//                    } else {
//                        board[i][j] = null;
//                    }
//                } else {
//                    board[i][j] = null;
//                }
//            }
//        }
//    }
//
//    public String[][] getBoard() {
//        return board;
//    }
//
//    public String getCurrentPlayer() {
//        return currentPlayer;
//    }
//
//    public boolean isValidMove(int fromX, int fromY, int toX, int toY) {
//        if (toX < 0 || toX >= 8 || toY < 0 || toY >= 8) {
//            return false;
//        }
//
//        if (board[toX][toY] != null) {
//            return false;
//        }
//
//        int deltaX = toX - fromX;
//        int deltaY = toY - fromY;
//        if (Math.abs(deltaX) != 1 || Math.abs(deltaY) != 1) {
//            return false;
//        }
//
//        String pieceColor = board[fromX][fromY];
//        if (pieceColor == null || !pieceColor.contains(currentPlayer)) {
//            return false;
//        }
//
//        if (!isKing(fromX, fromY)) {
//            int direction = pieceColor.equals("WHITE") ? -1 : 1;
//            if (deltaX != direction) {
//                return false;
//            }
//        }
//
//        return true;
//    }
//
//    private boolean isKing(int x, int y) {
//        return board[x][y] != null && board[x][y].contains("KING");
//    }
//
//    public boolean move(int fromX, int fromY, int toX, int toY) {
//        if (isValidMove(fromX, fromY, toX, toY)) {
//            board[toX][toY] = board[fromX][fromY];
//            board[fromX][fromY] = null;
//
//            if ((toX == 0 && board[toX][toY].equals("WHITE")) || (toX == 7 && board[toX][toY].equals("BLACK"))) {
//                board[toX][toY] += "_KING";
//            }
//
//            switchPlayer();
//            return true;
//        }
//        return false;
//    }
//
//    private void switchPlayer() {
//        currentPlayer = currentPlayer.equals("WHITE") ? "BLACK" : "WHITE";
//    }
//}



public class CheckersBoard {
    private String[][] board;
    private String currentPlayer;

    public CheckersBoard() {
        board = new String[8][8];
        resetBoard();
        currentPlayer = "WHITE";
    }

    public void resetBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 != 0) {
                    if (i < 3) {
                        board[i][j] = "BLACK";
                    } else if (i > 4) {
                        board[i][j] = "WHITE";
                    } else {
                        board[i][j] = null;
                    }
                } else {
                    board[i][j] = null;
                }
            }
        }
    }

    public String[][] getBoard() {
        return board;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isValidMove(int fromX, int fromY, int toX, int toY) {
        if (toX < 0 || toX >= 8 || toY < 0 || toY >= 8) {
            return false;
        }

        if (board[toX][toY] != null) {
            return false;
        }

        int deltaX = toX - fromX;
        int deltaY = toY - fromY;

        String pieceColor = board[fromX][fromY];
        if (pieceColor == null || !pieceColor.contains(currentPlayer)) {
            return false;
        }

        if (Math.abs(deltaX) == 1 && Math.abs(deltaY) == 1) {
            if (!isKing(fromX, fromY)) {
                int direction = pieceColor.equals("WHITE") ? -1 : 1;
                if (deltaX != direction) {
                    return false;
                }
            }
            return true;
        }

        if (Math.abs(deltaX) == 2 && Math.abs(deltaY) == 2) {
            int midX = fromX + deltaX / 2;
            int midY = fromY + deltaY / 2;
            String opponentColor = currentPlayer.equals("WHITE") ? "BLACK" : "WHITE";
            if (board[midX][midY] != null && board[midX][midY].contains(opponentColor)) {
                return true;
            }
        }

        return false;
    }

    private boolean isKing(int x, int y) {
        return board[x][y] != null && board[x][y].contains("KING");
    }

    public boolean move(int fromX, int fromY, int toX, int toY) {
        if (isValidMove(fromX, fromY, toX, toY)) {
            board[toX][toY] = board[fromX][fromY];
            board[fromX][fromY] = null;

            if ((toX == 0 && board[toX][toY].equals("WHITE")) || (toX == 7 && board[toX][toY].equals("BLACK"))) {
                board[toX][toY] += "_KING";
            }

            int deltaX = toX - fromX;
            int deltaY = toY - fromY;
            if (Math.abs(deltaX) == 2 && Math.abs(deltaY) == 2) {
                int midX = fromX + deltaX / 2;
                int midY = fromY + deltaY / 2;
                board[midX][midY] = null;

                if (canCapture(toX, toY)) {
                    return false;
                }
            }

            switchPlayer();
            return true;
        }
        return false;
    }

    private boolean canCapture(int x, int y) {
        String opponentColor = currentPlayer.equals("WHITE") ? "BLACK" : "WHITE";
        int[] directions = {-2, 2};
        for (int dx : directions) {
            for (int dy : directions) {
                int newX = x + dx;
                int newY = y + dy;
                if (isValidMove(x, y, newX, newY)) {
                    int midX = x + dx / 2;
                    int midY = y + dy / 2;
                    if (board[midX][midY] != null && board[midX][midY].contains(opponentColor)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void switchPlayer() {
        currentPlayer = currentPlayer.equals("WHITE") ? "BLACK" : "WHITE";
    }
}
