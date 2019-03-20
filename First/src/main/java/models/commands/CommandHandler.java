package models.commands;

import models.CPU;
import models.RAM;

import java.util.ArrayList;

public class CommandHandler {

    private CPU cpu;
    private RAM ram;

    private final ArrayList<Command> arithmeticCommands;
    private final ArrayList<Command> comparisonCommands;
    private final ArrayList<Command> stackCommands;
    private final ArrayList<Command> controlCommands;
    private final ArrayList<Command> IOCommands;
    private final ArrayList<Command> dataLoading;

    public CommandHandler(CPU cpu, RAM ram){

        this.cpu = cpu;
        this.ram = ram;

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

        if (CPU.TI() == 0)
            System.out.println("Interupt should happen");

    }

}
