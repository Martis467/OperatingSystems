package models.commands;

import enums.Command;
import javafx.collections.ObservableList;
import models.CPU;
import enums.Interrupt;
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
    private ObservableList<WordFX> supervizorMemory;

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
        stackCommands.add(Command.POP);

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
        command = parsedCommand.stripCommand(command);

        if (command == null) {
            //Reset VM
            cpu.SI(Interrupt.TimerZero.toInt());
        }

        if (cpu.TI() == 0){
            cpu.SI(Interrupt.TimerZero.toInt());
            cpu.MODE(0);
            cpu.TI(32);
        } else //Decrement timer
            cpu.TI(cpu.TI() - 1);

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

        if(dataLoading.contains(parsedCommand)){
            handleDataLoading(parsedCommand, command);
            return;
        }
    }

    private void handleArithmeticCommand(Command parsedCommand) {
        switch (parsedCommand){
            case ADD:
                ArithmeticCommand.Add(vMemory);
                break;
            case SUB:
                ArithmeticCommand.Subtract(vMemory);
                break;
            case MUL:
                ArithmeticCommand.Multiply(vMemory);
            case DIV:
                ArithmeticCommand.Divide(vMemory);
                break;
            case NOT:
                ArithmeticCommand.Not(vMemory);
        }
    }

    private void handleStackCommand(Command parsedCommand, String command) {
        switch (parsedCommand){
            case LD:
                StackCommands.LD(vMemory, Integer.parseInt(command));
                break;
            case PT:
                StackCommands.PTxy(vMemory, Integer.parseInt(command));
                break;
            case PUN:
                StackCommands.PUN(vMemory, Integer.parseInt(command));
                break;
            case PUS:
                StackCommands.PUS(vMemory, command);
                break;
            case POP:
                StackCommands.POP(vMemory);
                break;
            default:

        }
    }

    private void handleComparisonCommand(Command parsedCommand) {
        ComparisonCommand.Compare(vMemory);
    }

    private void handleIOCommands(Command parsedCommand, String command) {

    }

    private void handleControlCommand(Command parsedCommand, String command) {

    }

    private void handleDataLoading(Command parsedCommand, String value) {
        switch (parsedCommand) {
            case DW:
                DataLoadingCommands.DW(value, vMemory);
                break;
            case DD:
                DataLoadingCommands.DD(value, vMemory);
                break;
            case DN:
                DataLoadingCommands.DN(vMemory);
                break;
            default:
                    break;
        }
    }
}
