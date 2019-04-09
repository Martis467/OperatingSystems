import javafx.application.Application;
import javafx.stage.Stage;
import utillities.JFXLoader;

/**
 * OS project
 *
 * @author Martynas Jakimcikas
 * @author Vytautas Matas Martusevicius
 * @version 1.0 2019-04-01
 *
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage = JFXLoader.loadWindow("MainPanel", "Real computer");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
