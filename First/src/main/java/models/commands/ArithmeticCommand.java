package models.commands;

import javafx.collections.ObservableList;
import models.CPU;
import enums.Interrupt;
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

    /**
     * Divides two top elements in the stack and places the result [SP-1] address
     * @param memory
     */
    public static void Divide(ObservableList<WordFX> memory){
      GenericArithmeticCommand(memory, Operation.DIV);
    }

    /**
     * Invert sign of the value
     * @param memory
     */
    public static void Not(ObservableList<WordFX> memory) {
        CPU cpu = CPU.getInstance();

        //Increment instruction counter
        cpu.IC(cpu.IC() + 1);
        int SP = cpu.SP();
    }

    private static void GenericArithmeticCommand(ObservableList<WordFX> memory, Operation operation) {
        CPU cpu = CPU.getInstance();

        //Increment instruction counter
        cpu.IC(cpu.IC() + 1);
        int SP = cpu.SP();

        //If our stack has only one variable or none, we do nothing
        if (SP >= cpu.vmSize()-1)
            return;

        //Get [SP] value and set head to zero
        short value1 = memory.get(SP).getValueShort();

        //Get [SP+1] value
        short value2 = memory.get(SP+1).getValueShort();

        //Check if the divisor is not 0 if it is cause interruption
        if(operation == Operation.DIV && value2 == 0){
            cpu.SI(Interrupt.DivisionFromZero.toInt());
            return;
        }

        //Decrease SP
        cpu.SP(SP - 1);

        switch (operation){
            case ADD:
                //put the sum to stack
                memory.get(cpu.SP()).setValue((short) (value1 + value2));
                break;
            case SUB:
                //put the difference to stack
                memory.get(cpu.SP()).setValue((short) (value1 - value2));
                break;
            case MUL:
                //put the product to stack
                memory.get(cpu.SP()).setValue((short) (value1 * value2));
                break;
            case DIV:
                //put the quotient to stack
                memory.get(cpu.SP()).setValue((short) (value1 / value2));
                break;
            case NOT:
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
