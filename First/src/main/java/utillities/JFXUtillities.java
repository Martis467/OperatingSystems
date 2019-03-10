package utillities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import models.VirtualMachineSize;

import java.util.ArrayList;
import java.util.List;

public class JFXUtillities {

    /**
     * Displays and alert message and waits for the user to press confirm
     * @param title
     * @param message
     * @param type
     */
    public static void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        return;
    }

    /**
     * Gets the VM size list
     * @return
     */
    public static ObservableList<String> getVirtualMachineSizes(){
        ObservableList<String> VMValues = FXCollections.observableArrayList();

        for(VirtualMachineSize size : VirtualMachineSize.values())
            VMValues.add(size.toString());

        return VMValues;
    }

}
