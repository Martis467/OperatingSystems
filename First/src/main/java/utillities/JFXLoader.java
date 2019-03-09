package utillities;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class JFXLoader {

    /**
     * Loads a Java FX stage
     * @param xmlFileName - file name without the extension
     * @param title - stage title
     * @return stage
     */
    public static Stage loadWindow(String xmlFileName, String title) throws IOException {
        URL resource = JFXLoader.class.getClassLoader().getResource(createPath(xmlFileName));
        System.out.println("Loading resource: " + resource); //For debugging purposes
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(title);
        return stage;
    }

    private static String createPath(String xmlFileName) {
        return "gui/" + xmlFileName + ".fxml";
    }
}
