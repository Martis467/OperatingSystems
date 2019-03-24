package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import models.CPU;
import models.WordFX;
import models.commands.CommandHandler;

import javax.xml.crypto.Data;
import java.net.URL;
import java.util.List;
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
    private TableColumn ClientAddressColumn;
    @FXML
    private TableColumn ClientValueColumn;

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

    CommandHandler commandHandler;

    private ObservableList<WordFX> realMemory = FXCollections.observableArrayList();
    private ObservableList<WordFX> clientMemory = FXCollections.observableArrayList();
    private ObservableList<WordFX> supervizorMemory = FXCollections.observableArrayList();

    private final String NEW_LINE_REGEX = "\\r?\\r";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        InitColumns();

    }

    public void InitData(List vMemory, ObservableList<WordFX> supervizorMemory, int vmSize) {

        CPU cpu = CPU.getInstance();
        //Match real memory
        this.realMemory.addAll(vMemory);
        InitClientMemory();

        //Add size to the right the place
        clientMemory.get(0).setValue(vmSize);
        realMemory.get(0).setValue(vmSize);

        ClientMemoryTable.getItems().setAll(clientMemory);
        this.supervizorMemory = supervizorMemory;
    }

    public void DSreadAll(ActionEvent actionEvent) {
        String dataSegment = DataTextBox.getText();
        String[] commands = dataSegment.split(NEW_LINE_REGEX);

        commandHandler = new CommandHandler(realMemory, supervizorMemory);
        for (String command :
                commands) {
            commandHandler.handleCommand(command);
        }
        DataTextBox.setText("");
    }

    public void DSreadOne(ActionEvent actionEvent) {
        String dataSegment = DataTextBox.getText();
        int newLineIndex = dataSegment.indexOf(NEW_LINE_REGEX);
        String singleCommand = dataSegment.substring(0, newLineIndex);

        commandHandler = new CommandHandler(realMemory, supervizorMemory);
        commandHandler.handleCommand(singleCommand);

        DataTextBox.setText(dataSegment.substring(newLineIndex));

    }

    public void CSreadAll(ActionEvent actionEvent) {

    }

    public void CSreadOne(ActionEvent actionEvent) {

    }

    private void InitClientMemory() {

        for (int i = 1; i < realMemory.size(); i++){
            clientMemory.add(new WordFX(i, 0));
        }
    }

    private void InitColumns() {
        ClientAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        ClientValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        HardDriveAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        HardDriveValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
    }
}
