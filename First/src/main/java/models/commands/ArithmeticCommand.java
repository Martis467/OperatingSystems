package models.commands;

import javafx.collections.ObservableList;
import models.CPU;
import models.WordFX;

public class ArithmeticCommand {

    /**
     * Adds two top elements in the stack and places the result [SP-1] address
     * @param memory
     */
    public static void Add(ObservableList<WordFX> memory){
        GenericArithmeticCommand(memory, Operation.ADD);
    }

    /**
     * Subtracts the the the top element from the element below and places the result [SP-1] address
     * @param memory
     */
    public static void Subtract(ObservableList<WordFX> memory) {
        GenericArithmeticCommand(memory, Operation.SUB);
    }

    /**
     * Multiplies two top elements in the stack and places the result [SP-1] address
     * @param memory
     */
    public static void Multiply(ObservableList<WordFX> memory){
        GenericArithmeticCommand(memory, Operation.MUL);
    }

    public static void Divide(ObservableList<WordFX> memory){
      GenericArithmeticCommand(memory, Operation.DIV);
    }

    public static void Not(ObservableList<WordFX> memory) {
        CPU cpu = CPU.getInstance();

        //Increment instruction counter
        cpu.IC(cpu.IC() + 1);
        int SP = cpu.SP();

        //Get [SP] value and set head to zero
        int value = memory.get(SP).getValueInt();
        memory.get(SP).setValue(value * -1);
    }

    private static void GenericArithmeticCommand(ObservableList<WordFX> memory, Operation operation) {
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

        //Check if the divisor is not 0 if it is cause interruption
        if(operation == Operation.DIV && value1 == 0){
            cpu.SI(Interrupt.DivisionFromZero.toInt());
            return;
        }

        //Decrease SP
        cpu.SP(SP - 1);

        switch (operation){
            case ADD:
                //put the sum to stack
                memory.get(SP - 1).setValue(value2+value1);
                break;
            case SUB:
                //put the difference to stack
                cpu.SP(SP - 1);
                memory.get(SP - 1).setValue(value2-value1);
                break;
            case MUL:
                //put the product to stack
                memory.get(SP - 1).setValue(value2*value1);
                break;
            case DIV:
                //put the quotient to stack
                memory.get(SP - 1).setValue(value2/value1);
                break;
            default:
                    break;
        }

    }

    private enum Operation{
        ADD,
        SUB,
        MUL,
        DIV,
        NOT
    }
}
