package gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import utillities.ExceptionManager;

import java.net.URL;
import java.util.ResourceBundle;

public class MainPanelController implements Initializable {

    @FXML
    private BorderPane RootPane;

    //Scroll bars
    @FXML
    private ScrollBar RamScrollBar;
    @FXML
    private ScrollBar SupervisorScrollBar;

    //Register text boxes
    @FXML
    private TextField ICregister;
    @FXML
    private TextField PRGregister;
    @FXML
    private TextField SPregister;
    @FXML
    private TextField HRGregister;
    @FXML
    private TextField ORGregister;
    @FXML
    private TextField IRGregister;
    @FXML
    private TextField SIregister;
    @FXML
    private TextField TIregister;
    @FXML
    private TextField SMregister;
    @FXML
    private TextField MODEregister;

    //Code text area
    @FXML
    private TextArea CommandTextBox;

    //Child anchor panes
    @FXML
    private AnchorPane CPUPane;
    @FXML
    private AnchorPane CUPane;


    public void initialize(URL location, ResourceBundle resources) {
        ExceptionManager.showAlert("Test", "Testing alert class", Alert.AlertType.INFORMATION);
    }
}
