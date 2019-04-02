package models.commands;

import enums.Command;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;
import models.CPU;
import enums.Interrupt;
import models.WordFX;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandHandler {

    private final ArrayList<Command> arithmeticCommands;
    private final ArrayList<Command> comparisonCommands;
    private final ArrayList<Command> stackCommands;
    private final ArrayList<Command> controlCommands;
    private final ArrayList<Command> IOCommands;
    private final ArrayList<Command> dataLoading;

    private ObservableList<WordFX> vMemory;
    private ObservableList<WordFX> supervizorMemory;
    private ObservableList<WordFX> hdd;
    private TextArea monitor;

    public CommandHandler(ObservableList<WordFX> vMemory, ObservableList<WordFX> supervisorMemory, TextArea monitor, ObservableList<WordFX> hdd){
        this.vMemory = vMemory;
        this.supervizorMemory = supervisorMemory;
        this.monitor = monitor;
        this.hdd = hdd;

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
        stackCommands.add(Command.PN);

        controlCommands = new ArrayList<>();
        controlCommands.add(Command.JP);
        controlCommands.add(Command.JE);
        controlCommands.add(Command.JG);
        controlCommands.add(Command.JL);
        controlCommands.add(Command.STOP);

        IOCommands = new ArrayList<>();
        IOCommands.add(Command.PRTS);
        IOCommands.add(Command.PRTN);
        IOCommands.add(Command.PB);
        IOCommands.add(Command.RHD);
        IOCommands.add(Command.READ);
        IOCommands.add(Command.HDD);
        IOCommands.add(Command.WHD);
        IOCommands.add(Command.STARTIO);

        dataLoading = new ArrayList<>();
        dataLoading.add(Command.DW);
        dataLoading.add(Command.DN);
        dataLoading.add(Command.DD);
    }

    /**
     * Adds commands to memory for IC jumps
     * @param commands
     */
    public void AddCommandsToMemory(String[] commands) {
        CPU cpu = CPU.getInstance();

        //To iterate with ease turn this array into an arraylist
        ArrayList<String> commandArrayList = new ArrayList<>(Arrays.asList(commands));
        FillSegmentCommands(commandArrayList, cpu.vmSegmentSize());
    }

    /**
     * Executes commands that are in CS
     */
    public void executeCommandsFromMemory(){
        CPU cpu = CPU.getInstance();
        int CS = cpu.vmSegmentSize();
        int csSize = 2*CS - 1;

        for (int i = CS; i < csSize; i++ ){
            String command = vMemory.get(i).getValue();
            handleCommand(toStringCommand(command));
        }
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
        int commandInteger = 0;
        if( !command.equals("") )  {
            commandInteger = Integer.parseInt(command,16);
        }
        switch (parsedCommand){
            case LD:
                StackCommands.LD(vMemory, commandInteger);
                break;
            case PT:
                StackCommands.PT(vMemory, commandInteger);
                break;
            case PUN:
                StackCommands.PUN(vMemory, command);
                break;
            case PUS:
                StackCommands.PUS(vMemory, command);
                break;
            case POP:
                StackCommands.POP(vMemory);
                break;
            case PN:
                StackCommands.PN(vMemory);
                break;
            default:

        }
    }

    private void handleComparisonCommand(Command parsedCommand) {
        ComparisonCommand.Compare(vMemory);
    }

    private void handleIOCommands(Command parsedCommand, String command) {
        switch (parsedCommand) {
            case PRTS:
                IOCommand.PRTS(vMemory, monitor);
                break;
            case PRTN:
                IOCommand.PRTN(vMemory,monitor);
                break;
            case PB:
                IOCommand.PB(vMemory,monitor,command);
                break;
            case HDD:
                //veikia
                IOCommand.HDD(vMemory,hdd,command);
                break;
            case WHD:
                IOCommand.WHD(vMemory,hdd,command);
                break;
            case STARTIO:
                IOCommand.STARTIO(vMemory, command);
                break;
            case RHD:
                //veikia
                IOCommand.RHD(vMemory,hdd,command);
                break;
        }

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

    private String toStringCommand(String commandHexCode) {
        String com = Command.getCommandString(commandHexCode);
        return com + " " + commandHexCode.substring(2);
    }

    public void parseCommandsFromString(String command) {
        CPU cpu = CPU.getInstance();

        //Parse data segment
        int dsBeginning = command.indexOf('{') + 1;
        int dsEnding = command.indexOf('}');
        String dataSegment = command.substring(dsBeginning, dsEnding);

        //Parse data segment commands
        String[] dataSegmentCommands = dataSegment.split("\r\n");
        ArrayList<String> dsc = new ArrayList<>(Arrays.asList(dataSegmentCommands));

        //Get the remaining string from ds
        command = command.substring(dsEnding);

        //Parse code segment
        int csBeginning = command.indexOf('{') +1;
        String codeSegment = command.substring(csBeginning);

        //Parse code segment commands
        String[] codeSegmentCommands = codeSegment.split("\r\n");
        ArrayList<String> csc = new ArrayList<>(Arrays.asList(codeSegmentCommands));

        //Parse commands
        FillSegmentCommands(dsc, 0);
        FillSegmentCommands(csc, cpu.vmSegmentSize());
    }

    /**
     * Fill commands to segment
     * @param commandArrayList command string array list
     * @param index starting place to write commands, for e.x if we want to start writing to CS, we pass 112(Beginning of CS)
     */
    private void FillSegmentCommands(ArrayList<String> commandArrayList, int index) {

        int memoryBeginning = 0;

        //This has to be done because DS 0000 address has always a value pre-written
        if (index == 0)
            memoryBeginning++;

        for (int i = memoryBeginning; i < commandArrayList.size(); i++){
            String cleanCommand = commandArrayList.get(i).trim();

            if (!cleanCommand.matches("^[a-zA-Z0-9]+$"))
                continue;

            Command parsedCommand = Command.getCommand(cleanCommand);
            String commandValue = parsedCommand.stripCommand(cleanCommand);

            vMemory.get(index + i).setValue(parsedCommand.getCode() + commandValue);
        }
    }
}
