package models.commands;

import javafx.collections.ObservableList;
import models.CPU;
import models.WordFX;

public class DataLoadingCommands {

    public static void DD(String value) {

    }

    //duota reiksme ikelia i ic+1 (VM kur datasegment)
    public static void DW(String value, ObservableList<WordFX> memory) {
        CPU cpu = CPU.getInstance();
        if(cpu.IC() == 0 && cpu.IC() == cpu.PRG()) {//jei pirmas dw
            cpu.IC(cpu.PRG()+1);
        }
        else {
            cpu.IC(cpu.IC()+1);
        }


        System.out.println(cpu.IC());
        memory.get(cpu.IC()).setValue(Integer.valueOf(value)); //pavyko RM, dabar reikia VM updeitint

    }
}
