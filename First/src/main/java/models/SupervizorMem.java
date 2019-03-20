package models;

import javafx.collections.ObservableList;
import models.commands.Command;

import java.util.HashMap;
import java.util.Map;

public class SupervizorMem {

    private HashMap<Integer, Integer> memory;

    CPU cpu;

    public SupervizorMem(){

        memory = new HashMap<>(256); //nurodomas dydis supervizor mem

        CPU cpu = CPU.getInstance();
        memory.put(0, cpu.TI()); //gaunama ti reiksme ji cia bus modifukuojama

        for(int i = 1; i <= 16; i++) { //rodo ar aktyvi vm ar ne
            memory.put(i, 0); //jei aktyvi ka idet vietoj 0?
        }

        for(int interupt = 1; interupt <= 11; interupt++) { //yra 11 timeriu //supervizorines atminties suskirstymas blogai apskaiciuotas doke!!!
            for (int i = 17*interupt; i <= 17*interupt+16; i++) {//pertraukimo numeriai (gaunasi vienam pertraukimui 17 vietu skirta ne 16)

                if(interupt == 2) {

                    // mov registrus resetint +
                    //34 vietoj prasideda
                    SVW(34, 16*16*Command.MOV.getDecimal()+1*16+0 );//mov 2b komand kod., reg, value


                    interupt++;
                }
                else {
                    memory.put(i, interupt);
                }

            }
        }

        //baigiasi interuptai adresu 0xca vienam interuptui 17 zodziu gaunasi...
        //stekui lieka ff-ca =35 (desimt 53 vietos) zodziai

        for(int i = 204; i < 256; i++) { //cia stekas
            memory.put(i, 1);
        }
    }

    public void SVW(int adress, int value) { //adresu x idedama reiksme y
        memory.put(adress, value);
    }

    public int SVR(int adress) { //is adreso x grazinama reiksme
        return memory.get(adress);
    }

    private void MOV(CPU cpu ) { // sukurti funkcija kur reikiama reiksme ideda i reg. kaip nurodyti registra???//2 vyr. bait komandos kodas, 3 reg, 4 value
        cpu.SP(0);
        cpu.IC(0);
    }

    public static void supMemToJavaFx(SupervizorMem supervizorMem, ObservableList<WordFX> supervizorMemFx) {

        for (Map.Entry<Integer, Integer> entry:
                supervizorMem.memory.entrySet()) {
            supervizorMemFx.get(entry.getKey()).setValue(entry.getValue());
        }
    }



}
