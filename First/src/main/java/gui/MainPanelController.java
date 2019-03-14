package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.Memory;
import utillities.BaseConverter;
import utillities.JFXLoader;
import utillities.JFXUtillities;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPanelController implements Initializable {

    //region FXML variables

    //Constants
    private final int CLIENT_RAM_SIZE = 4096;
    private final int SUPERVISOR_RAM_SIZE = 256;

    //Parent border pane
    @FXML
    private BorderPane RootPane;

    //Child anchor panes
    @FXML
    private AnchorPane CPUPane;
    @FXML
    private AnchorPane CUPane;
    @FXML
    private AnchorPane VMPane;

    //Ram table
    private ObservableList<Memory> ramMemorylist = FXCollections.observableArrayList();
    @FXML
    private TableView<Memory> RamTableView;
    @FXML
    private TableColumn<Memory, String> RamAddressColumn;
    @FXML
    private TableColumn<Memory, String> RamValueColumn;


    //Supervisor table
    private ObservableList<Memory> supMemorylist = FXCollections.observableArrayList();
    @FXML
    private TableView<Memory> SupervisorTableView;
    @FXML
    private TableColumn<Memory, String> SupAddressColumn;
    @FXML
    private TableColumn<Memory, String> SupValueColumn;

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

    //VM size selection
    @FXML
    private ComboBox<String> VMSizeComboBox;

    //endregion

    public void initialize(URL location, ResourceBundle resources) {
        VMSizeComboBox.setItems(JFXUtillities.getVirtualMachineSizes());
        InitTableColumns();
        InitTableValues();
        InitRegisters();
    }


    public void CreateNewVM(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = JFXLoader.getLoader("VM");
            Stage stage = JFXLoader.loadWindow(loader, "Virtual computer");

            VMController controller = loader.<VMController>getController();
            controller.InitData();
            stage.show();

        } catch (IOException e) {
            JFXUtillities.showAlert("VM creation", "Could not create new VM", Alert.AlertType.ERROR);
        }
    }

    public void ResetRegisterValues(ActionEvent actionEvent) {

    }

    /**
     * Sets map values to make columns editable
     */
    private void InitTableColumns() {
        RamAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        RamValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        SupAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        SupValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
    }

    /**
     * Initializes tables
     */
    private void InitTableValues() {
        Thread clientRamThread = new Thread(new Runnable() {
            @Override
            public void run() {
               for(int i = 0; i < CLIENT_RAM_SIZE; i++){
                   ramMemorylist.add(new Memory(i,0));
               }
            }
        });
        clientRamThread.start();

        Thread supervisorRamThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < SUPERVISOR_RAM_SIZE; i++){
                    supMemorylist.add(new Memory(i,0));
                }
            }
        });
        supervisorRamThread.start();

        try {
            clientRamThread.join();
            supervisorRamThread.join();

            RamTableView.getItems().setAll(ramMemorylist);
            SupervisorTableView.getItems().setAll(supMemorylist);
        } catch (InterruptedException e) {
            JFXUtillities.showAlert("Initialization", "Table intialization failed", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    /**
     * Initializes the registers to starting values in hex
     */
    private void InitRegisters() {
        ICregister.setText(BaseConverter.convertValue(0, BaseConverter.Hexadecimal));
        PRGregister.setText(BaseConverter.convertValue(0, BaseConverter.Hexadecimal));
        SPregister.setText(BaseConverter.convertValue(0, BaseConverter.Hexadecimal));
        HRGregister.setText(BaseConverter.convertValue(0, BaseConverter.Hexadecimal));
        ORGregister.setText(BaseConverter.convertValue(0, BaseConverter.Hexadecimal));
        IRGregister.setText(BaseConverter.convertValue(0, BaseConverter.Hexadecimal));
        SIregister.setText(BaseConverter.convertValue(0, BaseConverter.Hexadecimal));
        TIregister.setText(BaseConverter.convertValue(50, BaseConverter.Hexadecimal));
        SMregister.setText(BaseConverter.convertValue(0, BaseConverter.Hexadecimal));
        MODEregister.setText("Client"); //Change me to a enum please
    }
}
