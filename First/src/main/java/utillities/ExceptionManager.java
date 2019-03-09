package utillities;

import javafx.scene.control.Alert;

public class ExceptionManager {

    public static void showAlert(String title, String message, Alert.AlertType type)
    {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        return;
    }

}
