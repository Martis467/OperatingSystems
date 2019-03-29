package models.commands;

import enums.Interrupt;
import javafx.collections.ObservableList;
import models.CPU;
import models.WordFX;
import utillities.BaseConverter;

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
        if (dsAddress > cpu.vmSize()-1)
            return;

        String dsValue = memory.get(dsAddress).getValue();
        String stackHead = memory.get(255).getValue();

        //If the first stack element is zeros
        //We don't need to increase SP because our head is empty
        //We just have to add the element
        if((stackHead.equals("0000"))) {
            memory.get(SP).setValue(dsValue);
            return;
        }

        SP--;
        memory.get(SP).setValue(dsValue);
        cpu.SP(SP);
    }

    /**
     * Put stack head value into the given data segment address
     * @param memory
     * @param dsAddress
     */
    public static void PT(ObservableList<WordFX> memory, int dsAddress) {
        CPU cpu = CPU.getInstance();

        //Increment instruction counter
        cpu.IC(cpu.IC() + 1);
        int SP = cpu.SP();

        //Current DS size is 112,
        //We shouldn't add anything above that
        if (dsAddress > cpu.vmSize()-1)
            return;

        String value = memory.get(SP).getValue();
        memory.get(dsAddress).setValue(value);
    }

    /**
     * Put value in stack head
     * @param memory
     * @param value
     */
    public static void PUN(ObservableList<WordFX> memory, String value) {
        CPU cpu = CPU.getInstance();

        //Increment instruction counter
        cpu.IC(cpu.IC() + 1);
        int SP = cpu.SP();

        //Check if the value is hex
        if (!value.matches(BaseConverter.getHexRegex()))
            return;

        String stackHead = memory.get(cpu.vmSize()-1).getValue();

        //If the first stack element is zeros
        //We don't need to increase SP because our head is empty
        //We just have to add the element
        if((stackHead.equals("0000"))) {
            memory.get(SP).setValue(value);
            return;
        }

        SP--;
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

        //2bytes can hold 2 symbols
        if (value.length() > 2)
            return;

        String stackHead = memory.get(cpu.vmSize()-1).getValue();

        //If the first stack element is zeros
        //We don't need to increase SP because our head is empty
        //We just have to add the element
        if((stackHead.equals("0000"))) {
            memory.get(SP).setValue(value);
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

        if(cpu.SP() == cpu.vmSize()-1) {
            memory.get(SP).setValue((short) 0);
            return;
        }

        memory.get(SP).setValue((short)0);
        cpu.SP(SP+1);


    }

    /**
     * i steko viršūnę patalpina simboli „\n“.
     * @param memory
     */
    public static void PN(ObservableList<WordFX> memory) {
        CPU cpu = CPU.getInstance();

        //Increment instruction counter
        cpu.IC(cpu.IC() + 1);
        int SP = cpu.SP();

        String stackHead = memory.get(cpu.vmSize()-1).getValue();

        //If the first stack element is zeros
        //We don't need to increase SP because our head is empty
        //We just have to add the element
        if((stackHead.equals("0000"))) {
            memory.get(SP).setValue("\\n");
            return;
        }

        SP--;
        memory.get(SP).setValue("\\n");
        cpu.SP(SP);

    }
}
