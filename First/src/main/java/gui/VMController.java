package gui;

import enums.Command;
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

import java.lang.reflect.Array;
import java.nio.file.*;;

import java.net.URL;
import java.util.ArrayList;
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
    private TextArea MonitorTextBox;

    //endregion

    CommandHandler commandHandler;

    private ObservableList<WordFX> realMemory = FXCollections.observableArrayList();
    private ObservableList<WordFX> clientMemory = FXCollections.observableArrayList();
    private ObservableList<WordFX> supervizorMemory = FXCollections.observableArrayList();
    private ObservableList<WordFX> hardDriveMemory = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        InitColumns();
        InitHardDrive();
    }

    public void InitData(List vMemory, ObservableList<WordFX> supervizorMemory) {

        CPU cpu = CPU.getInstance();
        short vmSize = cpu.vmSize();
        //Match real memory
        this.realMemory.addAll(vMemory);
        InitClientMemory();

        //Add size to the right the place
        clientMemory.get(0).setValue(vmSize);
        realMemory.get(0).setValue(vmSize);

        ClientMemoryTable.getItems().setAll(clientMemory);
        this.supervizorMemory = supervizorMemory;

        //Set CPU values
        cpu.SP(vmSize - 1);

        commandHandler = new CommandHandler(clientMemory, supervizorMemory, MonitorTextBox, hardDriveMemory);
    }

    public void DSreadAll(ActionEvent actionEvent) {
        String dataSegment = getDataSegment();

        if (dataSegment.isEmpty())
            return;

        String[] commands = dataSegment.split("\n");

        for (String command : commands) {
            commandHandler.handleCommand(command);
        }
        DataTextBox.setText("");
        RefreshRM();
    }

    public void DSreadOne(ActionEvent actionEvent) {
        String dataSegment = getDataSegment();

        if (dataSegment.isEmpty())
            return;

        //Get new line index and handle command
        int newLineIndex = dataSegment.indexOf("\n");
        String singleCommand = dataSegment.substring(0, newLineIndex);

        commandHandler.handleCommand(singleCommand);

        DataTextBox.setText(dataSegment.substring(newLineIndex+1));
        RefreshRM();
    }

    public void CSreadAll(ActionEvent actionEvent) {
        String codeSegment = getCodeSegment();

        if (codeSegment.isEmpty())
            return;

        String[] commands = codeSegment.split("\n");
        commandHandler.AddCommandsToMemory(commands);

        CodeTextBox.setText("");
        RefreshRM();
    }

    public void CSreadOne(ActionEvent actionEvent) {
        String codeSegment = getCodeSegment();

        if (codeSegment.isEmpty())
            return;

        //Get new line index and handle command
        int newLineIndex = codeSegment.indexOf("\n");
        String singleCommand = codeSegment.substring(0, newLineIndex);

        commandHandler.handleCommand(singleCommand);

        CodeTextBox.setText(codeSegment.substring(newLineIndex+1));
        RefreshRM();
    }

    private String getCodeSegment() {
        String codeSegment = CodeTextBox.getText();

        if (codeSegment.isEmpty())
            return "";

        //Validate if the string has a new line at the end if not append it
        if (!(codeSegment.charAt(codeSegment.length() - 1) == '\n'))
            codeSegment += '\n';

        return codeSegment;
    }

    private String getDataSegment() {
        String dataSegment = DataTextBox.getText();

        if (dataSegment.isEmpty())
            return "";

        //Validate if the string has a new line at the end if not append it
        if (!(dataSegment.charAt(dataSegment.length() - 1) == '\n'))
            dataSegment += '\n';

        return dataSegment;
    }

    private void InitHardDrive() {
        for(int i = 0; i < 256; i++){
            hardDriveMemory.add(new WordFX(i, 0));
        }

        HardDriveTable.getItems().setAll(hardDriveMemory);
    }

    private void InitClientMemory() {

        for (short i = 0; i < realMemory.size(); i++){
            clientMemory.add(new WordFX(i, (short)0));
        }
    }

    private void InitColumns() {
        ClientAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        ClientValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        HardDriveAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        HardDriveValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
    }

    private void RefreshRM() {
        //Refresh Client's memory values to RM values
        for (int i = 0; i < realMemory.size(); i++){
            String clientValue = clientMemory.get(i).getValue();
            realMemory.get(i).setValue(clientValue);
        }
    }


    public void ExecuteCommands(ActionEvent actionEvent) {
        commandHandler.executeCommandsFromMemory();
    }



    public void ReadFile(ActionEvent actionEvent) {
        String data = readFileAsString("C:\\Users\\Vytas\\Desktop\\os\\OSbendras\\OperatingSystems\\First\\textFiles\\1.txt");
        //nuskaitytas visas failas kaip stringas
        System.out.println(data+ '\n');

        String[] trimmed = data.split("\r\n");

        //keliu reiksmes i datasegment kol sutinku Codesegment
        //String[] trimmed2 = new String[trimmed.length-1];
        //System.arraycopy(trimmed,1,trimmed2,0,trimmed.length-1);

        ArrayList<String> temp = new ArrayList<>();
        ArrayList<String> forWork = new ArrayList<>();

        //sudedu trimed comandas i aray lista kad lengviau dirbti
        for(String element: trimmed) {
            temp.add(element);
        }

        //removes dataseg
        temp.remove(0);

        //sudeda reiksmes iki codeseg ir trina jau panaudotas
        for(String element: temp) {

            if(element.equals("CODESEG"))
               break;

            forWork.add(element);
            //temp.remove(element);
        }

        //istrina is temp jau panaudotas komandas
        temp.removeAll(forWork);

        String[] DataCommand = new String[forWork.size()];
        DataCommand = forWork.toArray(DataCommand);

        //dataseg komandas atpazista ir sudeda
        commandHandler.AddCommandsToMemory(DataCommand);

        //istrina codeseg
        temp.remove(0);
        forWork.clear();

        //sudeda reiksmes iki STOP
        for(String element: temp) {

            if(element.equals("STOP"))
                break;

            forWork.add(element);
        }

        // i atminti sudeda codeseg komandas
        String[] DataCommand2 = new String[forWork.size()];
        DataCommand2 = forWork.toArray(DataCommand2);
        commandHandler.AddCommandsToMemory(DataCommand2);
    }

    public static String readFileAsString(String fileName) {
        try{
            String data = "";
            data = new String(Files.readAllBytes(Paths.get(fileName)));
            return data;

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "Reading from file didnt work";
    }
}
