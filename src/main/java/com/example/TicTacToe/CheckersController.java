package com.example.TicTacToe;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkers")
public class CheckersController {
    private CheckersBoard checkersBoard = new CheckersBoard();

    @GetMapping("/board")
    public String[][] getBoard() {
        System.out.println("getBoard called");
        return checkersBoard.getBoard();
    }

    @GetMapping("/currentPlayer")
    public String getCurrentPlayer() {
        System.out.println("getCurrentPlayer called");
        return checkersBoard.getCurrentPlayer();
    }

    @PostMapping("/reset")
    public void reset() {
        System.out.println("reset called");
        checkersBoard.resetBoard();
    }

    @PostMapping("/move")
    public ResponseEntity<String> movePiece(@RequestParam int fromX, @RequestParam int fromY,
                                            @RequestParam int toX, @RequestParam int toY) {
        if (checkersBoard.isValidMove(fromX, fromY, toX, toY)) {
            checkersBoard.move(fromX, fromY, toX, toY);
            return ResponseEntity.ok("Move successful");
        } else {
            return ResponseEntity.badRequest().body("Invalid move");
        }
    }
}
