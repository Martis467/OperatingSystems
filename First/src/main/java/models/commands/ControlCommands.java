package models.commands;

import javafx.collections.ObservableList;
import models.CPU;
import models.WordFX;

public class ControlCommands {

    /**
     * Jumps to given instruction
     * @param icValue
     */
    public static void JP(int icValue) {
        GenericJump(icValue, null, Operation.JP);
    }

    /**
     * Jumps to given instruction if there is a 0 in the stack head
     * @param icValue
     * @param memory
     */
    public static void JL(int icValue, ObservableList<WordFX> memory) {
        GenericJump(icValue, memory, Operation.JL);
    }

    /**
     * Jumps to given instruction if there is a 1 in the stack head
     * @param icValue
     * @param memory
     */
    public static void JE(int icValue, ObservableList<WordFX> memory) {
        GenericJump(icValue, memory, Operation.JE);
    }

    /**
     * Jumps to given instruction if there is a 2 in the stack head
     * @param icValue
     * @param memory
     */
    public static void JG(int icValue, ObservableList<WordFX> memory) {
       GenericJump(icValue, memory, Operation.JG);
    }

    /**
     * Generic command to execute IC jump based on a given command
     * @param icValue
     * @param memory
     * @param operation
     */
    private static void GenericJump(int icValue, ObservableList<WordFX> memory, Operation operation){
        CPU cpu = CPU.getInstance();
        int SP = cpu.SP();

        if(icValue > cpu.vmSize() - 1)
            return;

        if (operation == Operation.JP){
            cpu.IC(icValue);
            return;
        }

        short stackHead = memory.get(SP).getValueShort();

        switch (operation){
            case JL:
                if(stackHead == 0) cpu.IC(icValue); break;
            case JE:
                if (stackHead == 1) cpu.IC(icValue); break;
            case JG:
                if (stackHead == 2) cpu.IC(icValue); break;
            default:
                break;
        }
    }

    //!! nezinau ka cia daryt, kaip ta programa sustoja???
    public static void STOP() { //programos sustojimo komanda.

    }

    private enum Operation{
        JP,
        JL,
        JE,
        JG
    }
}
