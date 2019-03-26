package models.commands;

import javafx.collections.ObservableList;
import models.CPU;
import models.WordFX;

public class DataLoadingCommands {

    //duota reiksme ikelia i ic+1 (VM kur datasegment)
    public static void DW(String value, ObservableList<WordFX> memory) {
        CPU cpu = CPU.getInstance();
        int IC = cpu.IC() + 1;

        memory.get(IC).setValue(Integer.valueOf(value));

        cpu.IC(IC);
    }

    public static void DD(String value, ObservableList<WordFX> memory){
        CPU cpu = CPU.getInstance();
        int IC = cpu.IC() + 1;

        memory.get(IC).setValue(value);

        cpu.IC(IC);
    }

    public static void DN(ObservableList<WordFX> memory){
        CPU cpu = CPU.getInstance();
        int IC = cpu.IC() + 1;

        memory.get(IC).setValue("\\n");

        cpu.IC(IC);
    }
}
