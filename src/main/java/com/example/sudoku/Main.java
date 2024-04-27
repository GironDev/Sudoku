package com.example.sudoku;

import com.example.sudoku.view.GameView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * La clase Main es la clase de entrada principal para la aplicación Sudoku.
 * Inicia la aplicación JavaFX y muestra la vista del juego Sudoku.
 * 
 * @version 1.0
 * @since 1.0
 */
public class Main extends Application {

    /**
     * El método main es el punto de entrada principal para la aplicación.
     * Llama al método launch() de la clase Application para iniciar la aplicación
     * JavaFX.
     * 
     * @param args Argumentos de la línea de comandos (no se utilizan en esta
     *             aplicación).
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * El método start inicializa la aplicación JavaFX y muestra la vista del juego
     * Sudoku.
     * 
     * @param primaryStage El escenario primario de la aplicación.
     * @throws IOException Si ocurre un error durante la inicialización de la vista
     *                     del juego.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        GameView.getInstance();
    }
}
