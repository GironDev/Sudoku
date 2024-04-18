package com.example.sudoku.controller;

import com.example.sudoku.view.alert.AlertBox;
import com.example.sudoku.view.alert.AlertBoxRules;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    void onMouseClickedImageHelp(MouseEvent event) {
        showHelpAlert();

    }
    private AlertBox alertBox = new AlertBox();
    private AlertBoxRules alertBoxRules = new AlertBoxRules();

    private int[][] sudokuGrid; // Matriz para almacenar los números del Sudoku (sin resolver)
    private int[][] gridNumbers; // Matriz para almacenar los números predeterminados por cuadrícula
    private Random random = new Random(); // Generador de números aleatorios

    @FXML
    public void initialize() {
        // Create 9x9 grid of TextFields
        sudokuGrid = new int[9][9];
        gridNumbers = new int[3][3];

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

    private void showHelpAlert() {
        alertBoxRules.showMessage(
                "Reglas",
                "Reglas del juego",
                "Las reglas del sudoku son simples. Cada celda necesita tener un número del 1 al 9. Observa las celdas resaltadas en la cuadrícula." +
                        "\nTodos estos grupos necesitan tener los números del 1 al 9 sin repetir (cada número puede ser utilizado sólo una vez en cada columna, en cada línea y cada cuadrícula de 3x3). De esta forma, las secciones que se cruzan son el límite y la forma de resolver el sudoku.." +
                        "\n\nDiviertete!!"
        );
    }
}