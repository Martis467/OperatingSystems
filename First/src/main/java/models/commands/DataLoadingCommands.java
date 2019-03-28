package models.commands;

import enums.Interrupt;
import javafx.collections.ObservableList;
import models.CPU;
import models.WordFX;
import utillities.BaseConverter;

public class DataLoadingCommands {

    /**
     * Inserts integer hex value to data segment
     * @param value
     * @param memory
     */
    public static void DW(String value, ObservableList<WordFX> memory) {
        CPU cpu = CPU.getInstance();

        int IC = cpu.IC() + 1;
        cpu.IC(IC);

        //Check if the address are inbound with data segment
        if (IC > 111){
            cpu.SI(Interrupt.ForbiddenMemoryAccess.toInt());
            return;
        }

        //Check if the value is hex
        if (!value.matches(BaseConverter.getHexRegex()))
            return;

        memory.get(IC).setValue(Short.parseShort(value, 16));
    }

    /**
     * Inserts symbols to data segment
     * @param value
     * @param memory
     */
    public static void DD(String value, ObservableList<WordFX> memory){
        CPU cpu = CPU.getInstance();

        int IC = cpu.IC() + 1;
        cpu.IC(IC);

        //Check if the address are inbound with data segment
        if (IC > 111){
            cpu.SI(Interrupt.ForbiddenMemoryAccess.toInt());
            return;
        }

        //2bytes can hold 2symbols
        if (value.length() > 2)
            return;

        memory.get(IC).setValue(value);
    }

    /**
     * Inserts new line symbol to data segment
     * @param memory
     */
    public static void DN(ObservableList<WordFX> memory){
        CPU cpu = CPU.getInstance();

        int IC = cpu.IC() + 1;
        cpu.IC(IC);

        //Check if the address are inbound with data segment
        if (IC > 111){
            cpu.SI(Interrupt.ForbiddenMemoryAccess.toInt());
            return;
        }

        memory.get(IC).setValue("\\n");
    }
}
