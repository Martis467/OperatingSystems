package models.commands;

import javafx.collections.ObservableList;
import models.CPU;
import models.WordFX;

public class ArithmeticCommand {

    /**
     * Adds two top elements in the stack and places them [SP+1] address
     * @param memory
     */
    public static void Add(ObservableList<WordFX> memory){
        CPU cpu = CPU.getInstance();

        //Increment instruction counter
        cpu.IC(cpu.IC() + 1);
        int SP = cpu.SP();

        //If our stack has only one variable or none, we do nothing
        if (SP <= memory.size() - 32)
            return;

        //Get [SP] value and set head to zero
        int value1 = memory.get(SP).getValueInt();
        memory.get(SP).setValue(0);

        //Get [SP-1] value
        int value2 = memory.get(SP-1).getValueInt();

        //decrease SP and put the new value
        cpu.SP(SP - 1);
        memory.get(SP - 1).setValue(value1+value2);
    }

    public static void Subtract(ObservableList<WordFX> memory){
        CPU cpu = CPU.getInstance();

        //Increment instruction counter
        cpu.IC(cpu.IC() + 1);
        int SP = cpu.SP();
    }

    public static void Multiply(ObservableList<WordFX> memory){
        CPU cpu = CPU.getInstance();

        //Increment instruction counter
        cpu.IC(cpu.IC() + 1);
        int SP = cpu.SP();
    }

    public static void Divide(ObservableList<WordFX> memory){
        CPU cpu = CPU.getInstance();
        int SP = cpu.SP();

    }

    public static void Not(ObservableList<WordFX> memory){
        CPU cpu = CPU.getInstance();

        //Increment instruction counter
        cpu.IC(cpu.IC() + 1);
        int SP = cpu.SP();
    }
}
