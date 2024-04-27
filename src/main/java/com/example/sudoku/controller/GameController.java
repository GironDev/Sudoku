package com.example.sudoku.controller;

import com.example.sudoku.model.Sudoku;
import com.example.sudoku.view.GameView;
import com.example.sudoku.view.alert.AlertBox;
import com.example.sudoku.view.alert.AlertBoxRules;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;
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


    private int attempts = 0;

    private Sudoku sudoku;


    @FXML
    public void initialize() {

        sudoku = new Sudoku();


        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                TextField textField = new TextField();
                textField.setMaxWidth(54); // Set preferred width for each cell
                textField.setMaxHeight(42); // Set preferred height for each cell
                String emptyCell = String.valueOf(sudoku.getSudokuGrid()[row][col]);
                if (!emptyCell.equalsIgnoreCase("0")) {
                    textField.setText(emptyCell);
                    textField.setEditable(false);
                    textField.setBackground(new Background(new BackgroundFill(Color.rgb(207, 226, 227), null, null)));
//                    System.out.println(emptyCell);
                    textField.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
                } else {
                    textField.setFont(Font.font("Verdana", FontWeight.LIGHT, 20));
                }

                // Add event listener for input validation (optional)
                textField.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue.matches("\\d{0,1}")) {
                        textField.setText(oldValue); // Restrict input to single digit (0-9)
                    }

                });
                textField.setAlignment(Pos.CENTER);
                gridPaneBoard.add(textField, col, row); // Add to GridPane with correct indexing
                updateGrid(textField, row, col);
            }
        }
    }

    @FXML
    void onHandlerVerify(ActionEvent event) throws IOException {
        // updateGrid();
        getVerifyGrid();
    }

    @FXML
    void onHandlerReset(ActionEvent event) throws  IOException {
        GameView.deleteInstance();
        GameView.getInstance();
    }

    private void updateGrid(TextField textField, int row, int col) {
        textField.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {

//                System.out.println(textField.getText());
                String number = String.valueOf(textField.getText());
                if (!number.equalsIgnoreCase("")) {
                    int numberText = Integer.parseInt(number);
//                    System.out.println(number);

                    if (!sudoku.isNumberHorizontal(numberText, row)) {
                        if (!sudoku.isNumberVertical(numberText, col)) {
                            int[][] subGridVerify = sudoku.getSudokuGrid();
                            if (!sudoku.isNumberInGroupCell(subGridVerify, numberText, row, col)){
//                                System.out.println("Entró");
                                sudoku.setNumberInSudokuGrid(numberText, row, col);
                                textField.setStyle("-fx-text-inner-color: #4fa773;");
                            }else {
                                attempts += 1;
                                textField.setStyle("-fx-text-inner-color: red;");
                            }
                        } else {
                            attempts += 1;
                            textField.setStyle("-fx-text-inner-color: red;");
                        }
                    } else {
                        attempts += 1;
                        textField.setStyle("-fx-text-inner-color: red;");
                    }
                } else {
                    sudoku.setNumberInSudokuGrid(0, row, col);
                    textField.setStyle("-fx-text-inner-color: #4fa773;");
                }

            }
        });
    }

    public void getVerifyGrid() throws IOException {
        int[][] finalGrid = sudoku.getSudokuGrid();
        boolean result = sudoku.isValidSudoku(finalGrid);

        if (result == true) {
            System.out.println("Errores: " + attempts);
            showVictoryMessage();
        } else {
            showVerifyBad();
        }

    }

    private void showHelpAlert() {
        alertBoxRules.showMessage(
                "Reglas",
                "Reglas del juego",
                "Las reglas del sudoku son simples. Cada celda necesita tener un número del 1 al 9. Observa las celdas resaltadas en la cuadrícula."
                        +
                        "\nTodos estos grupos necesitan tener los números del 1 al 9 sin repetir (cada número puede ser utilizado sólo una vez en cada columna, en cada línea y cada cuadrícula de 3x3). De esta forma, las secciones que se cruzan son el límite y la forma de resolver el sudoku.."
                        +
                        "\n\nDiviertete!!");
    }

    private void showVictoryMessage() throws IOException {
        alertBoxRules.showMessage(
                "¡Excelente!",
                "¡Excelente!",
                "Has completado tu sudoku satisfactoriamente."
        );
        GameView.deleteInstance();
        GameView.getInstance();
    }

    private void showVerifyBad() throws IOException {
        alertBox.showMessage(
                "¡Fallo!",
                "Fallo",
                "Una celda tiene un valor erroneo, revísalo para ganar!"
        );
    }
}