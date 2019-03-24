package models.commands;

import javafx.collections.ObservableList;
import models.CPU;
import models.WordFX;

public class JumpCommands {

    StackCommands stack;

    public void CMP(CPU cpu, ObservableList<WordFX> memory) { //lygina steko viršūnėje esančius du žodžius. Ir rezultatą padeda įsteko viršūnę. 1 – jei lygūs, 0 – jei viršutinis mažesnis, 2 – jei didesnis.
        int temp = cpu.SP();
        stack.POP(cpu);
        int temp2 = cpu.SP();
        int t = CPU.SP();
        t--;
        cpu.SP(t);
        if(temp == temp2) {
            stack.PUN(cpu,1,memory);
        }
        if(temp < temp2) {
            stack.PUN(cpu,0, memory);
        }
        if(temp > temp2) {
            stack.PUN(cpu,2,memory);
        }
    }

    public void JP(CPU cpu, int x, int y) { //nesąlyginio valdymo perdavimo komanda. Valdymas perduodamas kodo sričiai nurodytam adresui. IC = 16*x+y 0 < x,y < 0xF
        cpu.IC(16*x+y);
    }

    public void JE(CPU cpu, int x, int y, ObservableList<WordFX> memory) { //jei steko viršūnėje yra 1 valdymas perduodamas adresu 16*x + y. 0 < x,y < 0xF IF( [SP] == 1) IC = 16 * x + y; SP--;
        WordFX fx = memory.get(cpu.SP()); // gaunu wordfx is sp
        if(fx.getValueInt() == 1) {
            JP(cpu, x, y);
        }
    }

    public void JL(CPU cpu, int x, int y, ObservableList<WordFX> memory) { //jei steko viršūnėje yra 0 valdymas perduodamas adresu 16*x + y. 0 < x,y < 0xF IF( [SP] == 1) IC = 16 * x + y; SP--;
        WordFX fx = memory.get(cpu.SP()); // gaunu wordfx is sp
        if(fx.getValueInt() == 0) {
            JP(cpu, x, y);
        }
    }

    public void JG(CPU cpu, int x, int y, ObservableList<WordFX> memory) { //jei steko viršūnėje yra 2 valdymas perduodamas adresu 16*x + y. 0 < x,y < 0xF IF( [SP] == 1) IC = 16 * x + y; SP--;
        WordFX fx = memory.get(cpu.SP()); // gaunu wordfx is sp
        if(fx.getValueInt() == 2) {
            JP(cpu, x, y);
        }
    }

    //!! nezinau ka cia daryt, kaip ta programa sustoja???
    public void STOP() { //programos sustojimo komanda.

    }
}
