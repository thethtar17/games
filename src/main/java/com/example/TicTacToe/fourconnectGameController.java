package com.example.TicTacToe;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class fourconnectGameController {
	 private fourconnectGame game = new fourconnectGame();

	    @PostMapping("/drop/{col}")
	    public String dropPiece(@PathVariable int col) {
	        boolean success = game.dropPiece(col);
	        if (success) {
	            int winner = game.checkWinner();
	            if (winner != 0) {
	                return "Player " + winner + " wins!";
	            }
	            return "Piece placed";
	        } else {
	            return "Column full";
	        }
	    }

	    @GetMapping("/board")
	    public int[][] getBoard() {
	        return game.getBoard();
	    }
	    @PostMapping("/restart-game")
	    public void restartGame() {
	        game.reset(); // Reset the game state
	    }
	  
	}