package models.commands;

import javafx.collections.ObservableList;
import models.CPU;
import models.WordFX;

public class StackCommands {

    /**
     * Put value to stack head from data segment address
     * @param memory
     * @param dsAddress
     */
    public static void LD(ObservableList<WordFX> memory, int dsAddress) {
        CPU cpu = CPU.getInstance();

        //Increment instruction counter
        cpu.IC(cpu.IC() + 1);
        int SP = cpu.SP();

        //Current DS size is 112,
        //We shouldn't add anything above that
        if (dsAddress > 111)
            return;

        //If this is the first stack element
        //We don't need to increase SP because our head is empty
        if((SP >= 255)) {
            memory.get(SP).setValue(dsAddress);
            SP--;
            cpu.SP(SP);
            return;
        }

        String dsValue = memory.get(dsAddress).getValue();
        memory.get(SP).setValue(dsValue);
    }

    /**
     * Put stack head value into the given data segment address
     * @param memory
     * @param dsAddress
     */
    public static void PTxy(ObservableList<WordFX> memory, int dsAddress) {
        CPU cpu = CPU.getInstance();

        //Increment instruction counter
        cpu.IC(cpu.IC() + 1);
        int SP = cpu.SP();

        //Current DS size is 112,
        //We shouldn't add anything above that
        if (dsAddress > 111)
            return;

        String value = memory.get(SP).getValue();
        memory.get(dsAddress).setValue(value);
    }

    /**
     * Put value in stack head
     * @param memory
     * @param value
     */
    public static void PUN(ObservableList<WordFX> memory, int value) {
        CPU cpu = CPU.getInstance();

        //Increment instruction counter
        cpu.IC(cpu.IC() + 1);
        int SP = cpu.SP();

        //If this is the first stack element
        //We don't need to increase SP because our head is empty
        if((SP >= 255)) {
            memory.get(SP).setValue(value);
            SP--;
            cpu.SP(SP);
            return;
        }

        memory.get(SP).setValue(value);
        cpu.SP(SP);
    }

    /**
     * Put symbol to stack head
     * @param memory
     * @param value
     */
    public static void PUS(ObservableList<WordFX> memory, String value) {
        CPU cpu = CPU.getInstance();

        //Increment instruction counter
        cpu.IC(cpu.IC() + 1);
        int SP = cpu.SP();

        //If this is the first stack element
        //We don't need to increase SP because our head is empty
        if((SP >= 255)) {
            memory.get(SP).setValue(value);
            SP--;
            cpu.SP(SP);
            return;
        }

        SP--;
        memory.get(SP).setValue(value);
        cpu.SP(SP);
    }

    public static void POP(ObservableList<WordFX> memory) {
        CPU cpu = CPU.getInstance();

        //Increment instruction counter
        cpu.IC(cpu.IC() + 1);
        int SP = cpu.SP();

        memory.get(SP).setValue(0);
        cpu.SP(SP + 1);
    }
}
