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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * La clase GameController es el controlador de la vista del juego Sudoku.
 * Se encarga de inicializar el tablero de juego, verificar la solución del
 * tablero,
 * resolver el tablero y reiniciar el juego.
 * También se encarga de mostrar mensajes de alerta al usuario.
 * 
 * @version 1.0
 * @since 1.0
 * @
 */
public class GameController {

    // Declaración de atributos

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

    private AlertBox alertBox = new AlertBox();
    private AlertBoxRules alertBoxRules = new AlertBoxRules();
    private int attempts = 0;
    private Sudoku sudoku;

    /**
     * Inicializa el tablero de Sudoku y configura los elementos de la interfaz de
     * usuario.
     * 
     * @throws IOException Si ocurre un error durante la inicialización.
     */
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
                    // System.out.println(emptyCell);
                    textField.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
                    if (col == 2 || col == 5){
                        BorderStroke borderStroke = new BorderStroke(
                                Color.GRAY,                    // Color del borde
                                BorderStrokeStyle.SOLID,       // Estilo del borde (sólido)
                                null,                          // Radio de esquina (null para esquinas rectas)
                                new BorderWidths(0, 3, 0, 0)  // Ancho del borde (0 en los otros lados excepto el derecho)
                        );

                        textField.setBorder(new Border(borderStroke));
                    }

                } else {
                    textField.setFont(Font.font("Verdana", FontWeight.LIGHT, 20));
                    if (col == 2 || col == 5){
                        BorderStroke borderStroke = new BorderStroke(
                                Color.GRAY,                    // Color del borde
                                BorderStrokeStyle.SOLID,       // Estilo del borde (sólido)
                                null,                          // Radio de esquina (null para esquinas rectas)
                                new BorderWidths(0, 3, 0, 0)  // Ancho del borde (0 en los otros lados excepto el derecho)
                        );

                        textField.setBorder(new Border(borderStroke));
                    }

                }

                if (row == 2 || row == 5){
                    BorderStroke borderStyle = new BorderStroke(
                            Color.GRAY,                    // Color del borde
                            BorderStrokeStyle.SOLID,       // Estilo del borde (sólido)
                            null,                          // Radio de esquina (null para esquinas rectas)
                            new BorderWidths(0, 0, 3, 0)  // Ancho del borde (0 en los otros lados excepto el derecho)
                    );

                    textField.setBorder(new Border(borderStyle));
                    if (col == 2 || col ==5){
                        BorderStroke alternativeBorder = new BorderStroke(
                                Color.GRAY,                    // Color del borde
                                BorderStrokeStyle.SOLID,       // Estilo del borde (sólido)
                                null,                          // Radio de esquina (null para esquinas rectas)
                                new BorderWidths(0, 3, 3, 0)  // Ancho del borde (0 en los otros lados excepto el derecho)
                        );

                        textField.setBorder(new Border(alternativeBorder));
                    }
                }

                // Add event listener for input validation (optional)
                textField.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue.matches("[1-9]{0,1}")) {
                        textField.setText(oldValue); // Restrict input to single digit (0-9)
                    }

                });
                textField.setAlignment(Pos.CENTER);
                gridPaneBoard.add(textField, col, row); // Add to GridPane with correct indexing
                updateGrid(textField, row, col);
            }
        }
    }

    /**
     * Maneja el evento de clic en la imagen de ayuda.
     * 
     * @param event
     */
    @FXML
    void onMouseClickedImageHelp(MouseEvent event) {
        showHelpAlert();
    }

    /**
     * Maneja el evento de verificación del tablero de Sudoku.
     * Muestra un mensaje de éxito si el tablero es válido, de lo contrario, muestra
     * un mensaje de error.
     * 
     * @throws IOException Si ocurre un error durante la operación.
     */
    @FXML
    void onHandlerVerify(ActionEvent event) throws IOException {
        // updateGrid();
        getVerifyGrid();
    }

    /**
     * Maneja el evento de reinicio del juego de Sudoku.
     * Reinicia el juego y muestra una nueva instancia de la vista del juego.
     * 
     * @throws IOException Si ocurre un error durante el reinicio.
     */

    @FXML
    void onHandlerReset(ActionEvent event) throws IOException {
        GameView.deleteInstance();
        GameView.getInstance();
    }

    /**
     * Maneja el evento de resolución del tablero de Sudoku.
     * Resuelve automáticamente el tablero y muestra la solución.
     * 
     * @throws IOException Si ocurre un error durante la resolución.
     */
    @FXML
    void onHandleResolve(ActionEvent event) throws IOException {
        buttonHandlerResolve.setDisable(true);
        buttonHandlerVerify.setDisable(true);
        System.out.println(event);
        sudoku.getSolution();
        showSolutionInfo();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                TextField textField = new TextField();
                textField.setMaxWidth(54); // Set preferred width for each cell
                textField.setMaxHeight(42); // Set preferred height for each cell
                String emptyCell = String.valueOf(sudoku.getSudokuGrid()[row][col]);
                // System.out.println(emptyCell);
                if (!emptyCell.equalsIgnoreCase("0")) {
                    textField.setText(emptyCell);
                    textField.setEditable(false);
                    textField.setBackground(new Background(new BackgroundFill(Color.rgb(220, 165, 103), null, null)));
                    // System.out.println(emptyCell);
                    if (col == 2 || col == 5){
                        BorderStroke borderStroke = new BorderStroke(
                                Color.GRAY,                    // Color del borde
                                BorderStrokeStyle.SOLID,       // Estilo del borde (sólido)
                                null,                          // Radio de esquina (null para esquinas rectas)
                                new BorderWidths(0, 3, 0, 0)  // Ancho del borde (0 en los otros lados excepto el derecho)
                        );

                        textField.setBorder(new Border(borderStroke));
                    }
                    textField.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
                } else {
                    textField.setFont(Font.font("Verdana", FontWeight.LIGHT, 20));
                }

                // Add event listener for input validation (optional)
                textField.setAlignment(Pos.CENTER);
                gridPaneBoard.add(textField, col, row); // Add to GridPane with correct indexing
                // updateGrid(textField, row, col);

                if (row == 2 || row == 5){
                    BorderStroke borderStyle = new BorderStroke(
                            Color.GRAY,                    // Color del borde
                            BorderStrokeStyle.SOLID,       // Estilo del borde (sólido)
                            null,                          // Radio de esquina (null para esquinas rectas)
                            new BorderWidths(0, 0, 3, 0)  // Ancho del borde (0 en los otros lados excepto el derecho)
                    );

                    textField.setBorder(new Border(borderStyle));
                    if (col == 2 || col ==5){
                        BorderStroke alternativeBorder = new BorderStroke(
                                Color.GRAY,                    // Color del borde
                                BorderStrokeStyle.SOLID,       // Estilo del borde (sólido)
                                null,                          // Radio de esquina (null para esquinas rectas)
                                new BorderWidths(0, 3, 3, 0)  // Ancho del borde (0 en los otros lados excepto el derecho)
                        );

                        textField.setBorder(new Border(alternativeBorder));
                    }
                }
            }


        }

    }

    /**
     * Actualiza el tablero de Sudoku con los valores ingresados por el usuario.
     * 
     * @param textField El campo de texto que contiene el valor ingresado por el
     *                  usuario.
     * @param row       La fila de la celda en el tablero de Sudoku.
     * @param col       La columna de la celda en el tablero de Sudoku.
     */
    private void updateGrid(TextField textField, int row, int col) {
        textField.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {

                // System.out.println(textField.getText());
                String number = String.valueOf(textField.getText());
                if (!number.equalsIgnoreCase("")) {
                    int numberText = Integer.parseInt(number);
                    // System.out.println(number);

                    if (!sudoku.isNumberHorizontal(numberText, row)) {
                        if (!sudoku.isNumberVertical(numberText, col)) {
                            int[][] subGridVerify = sudoku.getSudokuGrid();
                            if (!sudoku.isNumberInGroupCell(subGridVerify, numberText, row, col)) {
                                // System.out.println("Entró");
                                sudoku.setNumberInSudokuGrid(numberText, row, col);
                                textField.setStyle("-fx-text-inner-color: #4fa773;");
                            } else {
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

    /**
     * Verifica si el tablero de Sudoku es válido.
     * Muestra un mensaje de éxito si el tablero es válido, de lo contrario, muestra
     * un mensaje de error.
     * 
     * @throws IOException Si ocurre un error durante la verificación.
     */
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

    /**
     * Muestra un mensaje de ayuda al usuario.
     */
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

    /**
     * Muestra un mensaje de victoria al usuario.
     * 
     * @throws IOException Si ocurre un error durante la operación.
     */
    private void showVictoryMessage() throws IOException {
        alertBoxRules.showMessage(
                "¡Excelente!",
                "¡Excelente!",
                "Has completado tu sudoku satisfactoriamente." +
                        "\nAún así, tuviste " + attempts + " errores durante éste intento. ¡Sigue Mejorando!");
        GameView.deleteInstance();
        GameView.getInstance();
    }

    /**
     * Muestra un mensaje de error al usuario.
     * 
     * @throws IOException Si ocurre un error durante la operación.
     */
    private void showVerifyBad() throws IOException {
        alertBox.showMessage(
                "¡Fallo!",
                "Fallo",
                "Una celda tiene un valor erróneo o está vacía, revísala para ganar!");
    }

    /**
     * Muestra un mensaje con información de la solución al usuario.
     * 
     * @throws IOException Si ocurre un error durante la operación.
     */
    private void showSolutionInfo() throws IOException {
        alertBox.showMessage(
                "¿Te rendiste?",
                "La solución ha sido revelada",
                "Tras haber revelado la solución, sólo podrás reiniciar el juego " +
                        "\ny volverlo a intentar. ¡Buena suerte la próxima vez!");
    }

}