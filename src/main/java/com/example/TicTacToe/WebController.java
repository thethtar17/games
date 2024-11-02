package com.example.TicTacToe;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/")
public class WebController {

  
    @GetMapping("/")
    public String home() {
        return "home"; // This will return src/main/resources/templates/index.html
    }
  
    @GetMapping("/tictactoe")
    public String tictactoePage() {
        return "index"; // Ensure tictactoe.html exists in the templates directory
    }

    @GetMapping("/connectfour")
    public String connectFourPage() {
        return "connectfour"; // Ensure connectfour.html exists in the templates directory
    }

    @GetMapping("/checkers")
    public String checkersPage() {
        return "checkers"; // Ensure checkers.html exists in the templates directory
    }
  
}
