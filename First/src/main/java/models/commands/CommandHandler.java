package models.commands;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.CPU;
import models.RAM;
import models.WordFX;

import java.util.ArrayList;

public class CommandHandler {

    private final ArrayList<Command> arithmeticCommands;
    private final ArrayList<Command> comparisonCommands;
    private final ArrayList<Command> stackCommands;
    private final ArrayList<Command> controlCommands;
    private final ArrayList<Command> IOCommands;
    private final ArrayList<Command> dataLoading;

    private ObservableList<WordFX> vMemory;
    private ObservableList<WordFX> supervizorMemory = FXCollections.observableArrayList();

    public CommandHandler(ObservableList<WordFX> vMemory, ObservableList<WordFX> supervisorMemory){
        this.vMemory = vMemory;
        this.supervizorMemory = supervisorMemory;

        //Load all commands
        arithmeticCommands = new ArrayList<>();
        arithmeticCommands.add(Command.ADD);
        arithmeticCommands.add(Command.SUB);
        arithmeticCommands.add(Command.MUL);
        arithmeticCommands.add(Command.DIV);
        arithmeticCommands.add(Command.NOT);

        comparisonCommands = new ArrayList<>();
        comparisonCommands.add(Command.CMP);

        stackCommands = new ArrayList<>();
        stackCommands.add(Command.LD);
        stackCommands.add(Command.PT);
        stackCommands.add(Command.PUN);
        stackCommands.add(Command.PUS);

        controlCommands = new ArrayList<>();
        controlCommands.add(Command.JP);
        controlCommands.add(Command.JE);
        controlCommands.add(Command.JG);
        controlCommands.add(Command.JL);
        controlCommands.add(Command.STOP);

        IOCommands = new ArrayList<>();
        IOCommands.add(Command.PRTS);
        IOCommands.add(Command.PRTN);
        IOCommands.add(Command.P);
        IOCommands.add(Command.R);
        IOCommands.add(Command.READ);
        IOCommands.add(Command.RDH);
        IOCommands.add(Command.WRH);
        IOCommands.add(Command.STARTIO);

        dataLoading = new ArrayList<>();
        dataLoading.add(Command.DW);
        dataLoading.add(Command.DN);
        dataLoading.add(Command.DD);
    }

    /**
     * Executes given command
     * @param command
     */
    public void handleCommand(String command){
        CPU cpu = CPU.getInstance();
        Command parsedCommand = Command.getCommand(command);


        if (command == null)
            System.out.println("SI = 2 Interupt should happen");

        if (cpu.TI() == 0)
            System.out.println("SI = B Interupt should happen");


        if (arithmeticCommands.contains(parsedCommand)){
            handleArithmeticCommand(parsedCommand);
            return;
        }

        if (comparisonCommands.contains(parsedCommand)){
            handleComparisonCommand(parsedCommand);
            return;
        }

        if (stackCommands.contains(parsedCommand)){
            handleStackCommand(parsedCommand, command);
            return;
        }

        if (controlCommands.contains(parsedCommand)){
            handleControlCommand(parsedCommand, command);
            return;
        }

        if (IOCommands.contains(parsedCommand)){
            handleIOCommands(parsedCommand, command);
            return;
        }

        if (dataLoading.contains(parsedCommand)){
            handleDataLoading(parsedCommand, command);
            return;
        }
    }

    private void handleArithmeticCommand(Command parsedCommand) {

    }

    private void handleDataLoading(Command parsedCommand, String command) {

    }

    private void handleIOCommands(Command parsedCommand, String command) {

    }

    private void handleControlCommand(Command parsedCommand, String command) {

    }

    private void handleStackCommand(Command parsedCommand, String command) {
    }

    private void handleComparisonCommand(Command parsedCommand) {

    }

}
