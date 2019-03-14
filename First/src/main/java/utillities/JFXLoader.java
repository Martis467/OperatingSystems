package utillities;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class JFXLoader {

    /**
     * Loads a Java FX stage, from xml file name
     * @param xmlFileName - file name without the extension
     * @param title - stage title
     * @return stage
     */
    public static Stage loadWindow(String xmlFileName, String title) throws IOException {
        URL resource = JFXLoader.class.getClassLoader().getResource(createPath(xmlFileName));
        System.out.println("Loading resource: " + resource); //For debugging purposes
        FXMLLoader loader = new FXMLLoader(resource);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        scene.setUserData(loader); //Remembering this thing for later reference
        stage.setScene(scene);
        stage.setTitle(title);
        return stage;
    }

    /**
     * Loads a Java FX stage, from FXMLoader
     * @param loader
     * @param title
     * @return
     * @throws IOException
     */
    public static Stage loadWindow(FXMLLoader loader, String title) throws IOException {
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        scene.setUserData(loader); //Remembering this thing for later reference
        stage.setScene(scene);
        stage.setTitle(title);
        return stage;
    }

    public static FXMLLoader getLoader(String xmlFileName){
        URL resource = JFXLoader.class.getClassLoader().getResource(createPath(xmlFileName));
        FXMLLoader loader = new FXMLLoader(resource);
        return loader;
    }

    private static String createPath(String xmlFileName) {
        return "gui/" + xmlFileName + ".fxml";
    }
}
