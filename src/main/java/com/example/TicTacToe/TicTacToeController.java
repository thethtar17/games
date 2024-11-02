package com.example.TicTacToe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tictactoe")
public class TicTacToeController {
    private final GameResultRepository gameResultRepository;

    private char[][] board;
    private char currentPlayer;

    @Autowired
    public TicTacToeController(GameResultRepository gameResultRepository) {
        this.gameResultRepository = gameResultRepository;
        board = new char[3][3];
        currentPlayer = 'X'; // X starts the game
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    @PostMapping("/move")
    public String makeMove(@RequestParam int row, @RequestParam int col) {
        if (board[row][col] == '-') {
            board[row][col] = currentPlayer;
            if (checkWin(row, col)) {
                saveGameResult(currentPlayer, "Player " + currentPlayer + " wins!");
                return "Player " + currentPlayer + " wins!";
            } else if (isBoardFull()) {
                saveGameResult('-', "It's a draw!");
                return "It's a draw!";
            } else {
                switchPlayer();
                return "Move successful. Next player: " + currentPlayer;
            }
        } else {
            return "Invalid move. Cell already occupied.";
        }
    }

    private boolean checkWin(int row, int col) {
        return (board[row][0] == currentPlayer && board[row][1] == currentPlayer && board[row][2] == currentPlayer) ||
                (board[0][col] == currentPlayer && board[1][col] == currentPlayer && board[2][col] == currentPlayer) ||
                (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
    @PostMapping("/cleanResults")
    public String cleanResults() {
        gameResultRepository.deleteAll();
        return "Database results cleaned successfully.";
    }
    @GetMapping("/board")
    public String printBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private void saveGameResult(char winner, String result) {
        GameResult gameResult = new GameResult();
        gameResult.setWinner(winner);
        gameResult.setResult(result);
        gameResultRepository.save(gameResult);
    }

    @GetMapping("/results")
    public List<GameResult> getAllResults() {
        return gameResultRepository.findAll();
    }
    @PostMapping("/initialize")
    public String initializeGame() {
        initializeBoard();
        currentPlayer = 'X';
        return "New game started. Player X goes first.";
    }
}
