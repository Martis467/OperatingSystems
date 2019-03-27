package gui;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.CPU;
import models.SupervizorMemory;
import models.WordFX;
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
    private ObservableList<WordFX> ramMemorylist = FXCollections.observableArrayList();
    @FXML
    private TableView<WordFX> RamTableView;
    @FXML
    private TableColumn<WordFX, String> RamAddressColumn;
    @FXML
    private TableColumn<WordFX, String> RamValueColumn;


    //Supervisor table
    private ObservableList<WordFX> supMemorylist = FXCollections.observableArrayList();
    @FXML
    private TableView<WordFX> SupervisorTableView;
    @FXML
    private TableColumn<WordFX, String> SupAddressColumn;
    @FXML
    private TableColumn<WordFX, String> SupValueColumn;

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

    @FXML
    private Button ToggleRefreshButton;

    //endregion

    private Timeline timer;

    private final String TOGLE_ON_REFRESH = "Toggle on refresh";
    private final String TOGLE_OFF_REFRESH = "Toggle off refresh";

    public void initialize(URL location, ResourceBundle resources) {
        VMSizeComboBox.setItems(JFXUtillities.getVirtualMachineSizes());
        InitTableColumns();
        InitTableValues();
        InitRegisterUpdate();
        ToggleRefreshButton.setText(TOGLE_OFF_REFRESH);
    }

    public void CreateNewVM(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = JFXLoader.getLoader("VM");
            Stage stage = JFXLoader.loadWindow(loader, "Virtual computer");

            VMController controller = loader.<VMController>getController();

            int sublistFrom = Integer.valueOf("400", 16);
            int sublistTo = Integer.valueOf("500", 16);

            CPU cpu = CPU.getInstance();
            cpu.PRG(sublistFrom);
            cpu.IC(sublistFrom);

            //i supervizoriu iraso masinoos numeriu ar aktyvi ir nuo kurios vietos atmintis prasideda
            supMemorylist.get(1).setValue(1*4096+cpu.PRG());

            SupervisorTableView.refresh();

            //padaro pagal prg registra vm memory
            controller.InitData(ramMemorylist.subList(sublistFrom, sublistTo), supMemorylist, 256);

            stage.show();

        } catch (IOException e) {
            JFXUtillities.showAlert("VM creation", "Could not create new VM", Alert.AlertType.ERROR);
        }
    }

    public void ToggleRegisterRefresh(ActionEvent actionEvent) throws InterruptedException {
        //If thread is running stop the thread and set the text to
        //Turn on thread
        //If thread is not running turn it on and set the text
        //Turn off thread
        if (timer.getStatus() == Animation.Status.RUNNING) {
            ToggleRefreshButton.setText(TOGLE_ON_REFRESH);
            timer.pause();
            System.out.println("Stopped");
            return;
        }

        ToggleRefreshButton.setText(TOGLE_OFF_REFRESH);
        timer.play();
        System.out.println("Resumed");
    }

    public void ResetRegisterValues(ActionEvent actionEvent) {
        CPU cpu = CPU.getInstance();

        cpu.ICHex(ICregister.getText());
        cpu.PRGHex(PRGregister.getText());
        cpu.SPHex(SPregister.getText());
        cpu.HRG(Integer.parseInt(HRGregister.getText()));
        cpu.ORG(Integer.parseInt(ORGregister.getText()));
        cpu.IRG(Integer.parseInt(IRGregister.getText()));
        cpu.SIHex(SIregister.getText());
        cpu.TIHex(TIregister.getText());
        cpu.SMHex(SMregister.getText());
        cpu.MODE(Integer.parseInt(MODEregister.getText()));
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
                   ramMemorylist.add(new WordFX(i,0));
               }
            }
        });
        clientRamThread.start();

        Thread supervisorRamThread = new Thread(new Runnable() {
            @Override
            public void run() {
                SupervizorMemory.fillMemory(supMemorylist);
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
    private void refreshRegisterValues() {
        CPU cpu = CPU.getInstance();
        ICregister.setText(BaseConverter.convertValue(cpu.IC(), BaseConverter.Hexadecimal));
        PRGregister.setText(BaseConverter.convertValue(cpu.PRG(), BaseConverter.Hexadecimal));
        SPregister.setText(BaseConverter.convertValue(cpu.SP(), BaseConverter.Hexadecimal));
        HRGregister.setText(BaseConverter.convertValue(cpu.HRG(), BaseConverter.Hexadecimal));
        ORGregister.setText(BaseConverter.convertValue(cpu.ORG(), BaseConverter.Hexadecimal));
        IRGregister.setText(BaseConverter.convertValue(cpu.IRG(), BaseConverter.Hexadecimal));
        SIregister.setText(BaseConverter.convertValue(cpu.SI(), BaseConverter.Hexadecimal));
        TIregister.setText(BaseConverter.convertValue(cpu.TI(), BaseConverter.Hexadecimal));
        SMregister.setText(BaseConverter.convertValue(cpu.SM(), BaseConverter.Hexadecimal));
        MODEregister.setText(BaseConverter.convertValue(cpu.MODE(), BaseConverter.Hexadecimal));
    }

    private void InitRegisterUpdate() {
        timer = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            refreshRegisterValues();
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }
}
