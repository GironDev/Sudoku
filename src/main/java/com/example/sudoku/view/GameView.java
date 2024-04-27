
package com.example.sudoku.view;

import com.example.sudoku.controller.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * La clase GameView representa la vista del juego Sudoku.
 * Es responsable de cargar y mostrar la interfaz de usuario del juego, así como
 * de proporcionar acceso al controlador del juego.
 * 
 * @version 1.0
 * @since 1.0
 */
public class GameView extends Stage {
    private GameController gameController; // Controlador del juego

    /**
     * Constructor de la clase GameView. Carga la interfaz de usuario del juego y
     * muestra la ventana del juego.
     * 
     * @throws IOException Si ocurre un error al cargar la interfaz de usuario.
     */

    public GameView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sudoku/game-view.fxml"));
        Parent root = loader.load();
        gameController = loader.getController();
        Scene scene = new Scene(root);
        setTitle("Sudoku");
        getIcons().add(
                new Image(
                        String.valueOf(getClass().getResource("/com/example/sudoku/images/favicon.png"))));
        setResizable(false);
        setScene(scene);
        show();
    }

    /**
     * Obtiene el controlador del juego asociado con esta vista.
     * 
     * @return El controlador del juego.
     */

    public GameController getGameController() {
        return gameController;
    }

    /**
     * Obtiene una instancia única de la vista del juego.
     * 
     * @return La instancia única de la vista del juego.
     * @throws IOException Si ocurre un error al crear la instancia de la vista del
     *                     juego.
     */
    public static GameView getInstance() throws IOException {
        return GameViewHolder.INSTANCE = new GameView();
    }

    /**
     * Elimina la instancia actual de la vista del juego.
     */
    public static void deleteInstance() {
        GameViewHolder.INSTANCE.close();
        GameViewHolder.INSTANCE = null;
    }

    /**
     * Clase interna para almacenar la instancia única de la vista del juego.
     */
    private static class GameViewHolder {
        private static GameView INSTANCE;
    }
}
