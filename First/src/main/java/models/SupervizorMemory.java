package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;

public class SupervizorMemory {

    private HashMap<Integer, Integer> memory;
    private ObservableList<WordFX> supMemorylist = FXCollections.observableArrayList();

    CPU cpu;

    //Supvervizorines atminties komandos
    public void SVW(int adress, int value) { //adresu x idedama reiksme y
        memory.put(adress, value);
    }

    public int SVR(int adress) { //is adreso x grazinama reiksme
        return memory.get(adress);
    }

    public void MOV(CPU cpu ) { // sukurti funkcija kur reikiama reiksme ideda i reg. kaip nurodyti registra???//2 vyr. bait komandos kodas, 3 reg, 4 value
        cpu.SP(0);
        cpu.IC(0);
    }

    public static void fillMemory(ObservableList<WordFX> memory){

        CPU cpu = CPU.getInstance();

        //TI register value
        memory.add(new WordFX(0, cpu.TI()));

        // 0001-0010. Ar VM – aktyvi ar ne
        for(int i = 1; i < 17; i++) {
            memory.add(new WordFX(i, 0));
        }

        /*
        // 12 interuptu kiekvienam po 16 zodziu
        // paskutinis adresas isskirtas interuptas db
        for(int interupt = 1; interupt <13; interupt++) { //0011 *x; 0011*x + 0010
            for(int i = 17*interupt; i < 17*interupt+16; i++) {
                if(interupt==12) {
                    SI12(memory);
                    break;
                }
                else
                    memory.add(new WordFX(i, 0));
            }
        }*/

        for(int i = 17; i < 204; i++) {
            memory.add(new WordFX(i, 0));
        }

        SI12(memory);

        // stackui iskirta 36 zodziai
        for(int i = 220; i < 256; i++) {
            memory.add(new WordFX(i,0));
        }


    }

    private static void SI12(ObservableList<WordFX> memory) {

        /* senas, bet dabar supushinam vm'e.
        memory.add(new WordFX(204, "B33")); //push sp
        memory.add(new WordFX(205, "B31")); //push IC
        memory.add(new WordFX(206, "4732")); // MOV TI = 50       //MOV komanda 7 registras(TI) reiksme = 50(16hex)
        memory.add(new WordFX(207, "B41")); //pop IC
        memory.add(new WordFX(208, "B43")); //pop SP
        memory.add(new WordFX(209, "FFFF")); //STOP
        for(int i = 210; i < 220; i++) {
            memory.add(new WordFX(i, 0));
        }
        */
        memory.add(new WordFX(204, "4732")); // MOV TI = 50
        memory.add(new WordFX(205, "FFFF")); //STOP
        for(int i = 206; i < 220; i++) {
            memory.add(new WordFX(i, 0));
        }

    }

    private static void SI11() {
    }

    private static void SI10() {
    }

    private static void SI9() {
    }

    private static void SI8() {
    }

    private static void SI7() {
    }

    private static void SI6() {
    }

    private static void SI5() {
    }



    private static void SI3() {
    }



    public static void SI1() {
        CPU cpu = CPU.getInstance();
        cpu.SI(1);
    }

    private static void SI4() {
        /*
        //PRTN
        memory.add(new WordFX(1, Command.PUSH.getCode() + cpu.SPnumber())); //cia i steka reikia push
        memory.add(new WordFX(2, Command.PUSH.getCode() + cpu.ICnumber()));

        memory.add(new WordFX(3, Command.MOV.getCode() + cpu.MODEnumber() + 0)); //pakeicia mode i supervizoriaus (0)
        memory.add(new WordFX(4, Command.TRNF.getCode() )); //i supervizor steko virsu ikelia is ram steko virsaus value
        memory.add(new WordFX(5, Command.REGW.getCode())); //i stacko virsu ikelia org reiksme
        memory.add(new WordFX(6, Command.PUN.getCode() + 1)); //i steko virsu patalpina 1
        memory.add(new WordFX(7, Command.CMP.getCode())); //compare 0 ir org

        memory.add(new WordFX(8, Command.JE.getCode())); // jei nelygus(ORG = 1) soka i adresa x ir y(uzimtas ORG) nurodyti x ir y bet cia veliau kai uzpildysim lentele!!!!!!!

        memory.add(new WordFX(9, Command.POP.getCode())); //ismeta cmp reiksme
        memory.add(new WordFX(10, Command.POP.getCode())); //ismeta 0 reiksme
        memory.add(new WordFX(11, Command.POP.getCode())); //ismeta ORG reiksme //steke liko tik reiksme paimta is ram steko virsaus
        memory.add(new WordFX(12, Command.PRTS.getCode())); //jei laisvas tai atprintina i ekrana stacko virsu

        //jei kanalas uzimtas ir jump padare ka reikia daryt?



        memory.add(new WordFX(4, Command.POP.getCode() + cpu.ICnumber())); // is steko pop reikia
        memory.add(new WordFX(4, Command.POP.getCode() + cpu.SPnumber()));
*/
    }


}
