package models.commands;

import javafx.collections.ObservableList;
import models.CPU;
import models.WordFX;

public class JumpCommands {

    public static void JP( int x, int y) { //nesąlyginio valdymo perdavimo komanda. Valdymas perduodamas kodo sričiai nurodytam adresui. IC = 16*x+y 0 < x,y < 0xF
        CPU cpu = CPU.getInstance();
        cpu.IC(16*x+y);
    }

    public static void JE(int x, int y, ObservableList<WordFX> memory) { //jei steko viršūnėje yra 1 valdymas perduodamas adresu 16*x + y. 0 < x,y < 0xF IF( [SP] == 1) IC = 16 * x + y; SP--;
        CPU cpu = CPU.getInstance();
        WordFX fx = memory.get(cpu.SP()); // gaunu wordfx is sp
        if(fx.getValueShort() == 1) {
            JP(x, y);
        }
    }

    public static void JL( int x, int y, ObservableList<WordFX> memory) { //jei steko viršūnėje yra 0 valdymas perduodamas adresu 16*x + y. 0 < x,y < 0xF IF( [SP] == 1) IC = 16 * x + y; SP--;
        CPU cpu = CPU.getInstance();
        WordFX fx = memory.get(cpu.SP()); // gaunu wordfx is sp
        if(fx.getValueShort() == 0) {
            JP( x, y);
        }
    }

    public static void JG( int x, int y, ObservableList<WordFX> memory) { //jei steko viršūnėje yra 2 valdymas perduodamas adresu 16*x + y. 0 < x,y < 0xF IF( [SP] == 1) IC = 16 * x + y; SP--;
        CPU cpu = CPU.getInstance();
        WordFX fx = memory.get(cpu.SP()); // gaunu wordfx is sp
        if(fx.getValueShort() == 2) {
            JP( x, y);
        }
    }

    //!! nezinau ka cia daryt, kaip ta programa sustoja???
    public static void STOP() { //programos sustojimo komanda.

    }
}
