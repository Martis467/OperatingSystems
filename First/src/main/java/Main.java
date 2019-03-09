import javafx.application.Application;
import javafx.stage.Stage;
import utillities.JFXLoader;

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
