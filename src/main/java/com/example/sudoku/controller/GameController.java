package com.example.sudoku.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class GameController {
    @FXML
    private GridPane gridPaneBoard;

    public void initialize(){
        //System.out.println(name);
        for (int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                TextField wordTxt = new TextField();
                gridPaneBoard.add(wordTxt, i, j);
            }
        }
    }

}
