package com.example.sudoku.controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GameController {

    @FXML
    private GridPane gridPaneBoard;
    @FXML
    private Button buttonHandlerReset;

    @FXML
    private Button buttonHandlerResolve;

    @FXML
    private Button buttonHandlerVerify;
    @FXML
    private ImageView imageHelp;

    @FXML
    public void initialize() {
        // Create 9x9 grid of TextFields

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                TextField textField = new TextField();
                textField.setMaxWidth(30); // Set preferred width for each cell
                textField.setMaxHeight(30); // Set preferred height for each cell
                // Add event listener for input validation (optional)
                textField.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue.matches("\\d{0,1}")) {
                        textField.setText(oldValue); // Restrict input to single digit (0-9)
                    }
                });
                textField.setAlignment(Pos.CENTER);
                textField.setStyle("-fx-background-color:  #e0d8c5;");
                gridPaneBoard.add(textField, col, row); // Add to GridPane with correct indexing
            }
        }
    }
}