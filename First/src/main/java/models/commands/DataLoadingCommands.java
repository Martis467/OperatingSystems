package models.commands;

import javafx.collections.ObservableList;
import models.CPU;
import models.WordFX;

public class DataLoadingCommands {

    //duota reiksme ikelia i ic+1 (VM kur datasegment)
    public static void DW(String value, ObservableList<WordFX> memory) {

        CPU cpu = CPU.getInstance();
        cpu.IC(cpu.IC()+1);

        if(cpu.IC() > cpu.PRG() + 111) //jei bando rasyt i code segmenta
            cpu.IC(cpu.PRG()+1);

        memory.get( cpu.IC() - cpu.PRG() ).setValue(Integer.valueOf(value));
    }

    public static void DD(String value, ObservableList<WordFX> memory){
        CPU cpu = CPU.getInstance();
        cpu.IC(cpu.IC()+1);

        if(cpu.IC() > cpu.PRG() + 111) //jei bando rasyt i code segmenta
            cpu.IC(cpu.PRG()+1);

        memory.get( cpu.IC() - cpu.PRG() ).setValue(value);
    }

    public static void DN(ObservableList<WordFX> memory){
        CPU cpu = CPU.getInstance();
        int IC = cpu.IC() + 1;

        memory.get(IC).setValue("\\n");

        cpu.IC(IC);
    }
}
