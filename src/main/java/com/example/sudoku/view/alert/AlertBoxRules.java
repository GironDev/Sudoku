package com.example.sudoku.view.alert;

import javafx.scene.control.Alert;

public class AlertBoxRules implements lAlertBox{

    @Override
    public void showMessage(String title, String header, String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
