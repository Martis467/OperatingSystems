package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class VMController implements Initializable {

    //region FXML variables

    //Parent border pane
    @FXML
    private BorderPane ParentPane;

    //Client Ram table
    @FXML
    private TableView ClientMemoryTable;
    @FXML
    private TableColumn AddressColumn;
    @FXML
    private TableColumn ValueColumn;

    //HDD table
    @FXML
    private TableView HardDriveTable;
    @FXML
    private TableColumn HardDriveAddressColumn;
    @FXML
    private TableColumn HardDriveValueColumn;

    //Registers
    @FXML
    private TextField SPregister;
    @FXML
    private TextField ICregister;

    //TextBoxes
    @FXML
    private TextArea DataTextBox;
    @FXML
    private TextArea CodeTextBox;
    @FXML
    private TextArea MachineTextBox;

    //endregion

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void InitData()
    {
        DataTextBox.setText("asdasdas");
    }

    public void DSreadAll(ActionEvent actionEvent) {

    }

    public void DSreadOne(ActionEvent actionEvent) {

    }

    public void CSreadAll(ActionEvent actionEvent) {

    }

    public void CSreadOne(ActionEvent actionEvent) {

    }
}
