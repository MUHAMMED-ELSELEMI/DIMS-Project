package org.example.dimsproject.utils;

import javafx.scene.control.Alert;

public class PopUp {

    public static void showPopup(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
