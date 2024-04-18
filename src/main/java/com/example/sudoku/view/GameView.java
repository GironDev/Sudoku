
package com.example.sudoku.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class GameView extends Stage {


    public GameView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sudoku/game-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        setTitle("Sudoku");
        getIcons().add(
                new Image(
                        String.valueOf(getClass().getResource("/com/example/sudoku/images/favicon.png"))));
        setResizable(false);
        setScene(scene);
        show();
    }


    public static GameView getInstance() throws IOException{
        return GameViewHolder.INSTANCE = new GameView();
    }

    public static void deleteInstance() {
        GameViewHolder.INSTANCE.close();
        GameViewHolder.INSTANCE = null;
    }

    private static class GameViewHolder {
        private static GameView INSTANCE;
    }
}
