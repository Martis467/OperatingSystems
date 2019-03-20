package models.commands;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import models.CPU;
import models.WordFX;

public class StackCommands {

    public void LD(int x, int y, CPU cpu, ObservableList<WordFX> memory) { //į steko viršūnę užkrauna reikšmę iš duomenų srities adresu 16 *x + y.  SP--; 0 < x,y < 16
        WordFX temp = memory.get(16*x+y);
        memory.set(CPU.SP(), temp);
        int sp = cpu.SP()-1;
        cpu.SP(sp);

    }

    public void PTxy(int x, int y, CPU cpu, ObservableList<WordFX> memory) { //steko viršūnėje esantį žodį deda į duomenų sritį nurodytu adresu SP++; 16 * x + y. 0 < x,y < 0xF
        WordFX temp = memory.get(cpu.SP());
        memory.set(16*x+y, temp);
        int sp = cpu.SP()-1;
        cpu.SP(sp);

    }

    public void PUN(CPU cpu, int value, ObservableList<WordFX> memory) { //– x kaip skaičių patalpina į steko viršūnę. SP++;[SP] = x.
        String tmp = String.valueOf(value);//value i string
        WordFX wordfx = new WordFX(CPU.SP(), tmp); // sukuriu nauja wordFX
        memory.set(CPU.SP(), wordfx); // i SP vieta idedu nauja WORDFX(adresa nauja ty ta pati sp ir value jau string
        int sp = cpu.SP()-1;
        cpu.SP(sp);
    }

    // !!!!!!!!!!! cia nezinau kaip tai tiesiog string ikeliau
    public void PUS(CPU cpu, String value, ObservableList<WordFX> memory) { //– x kaip simbolį patalpina į steko viršūnę. [SP] = x
        WordFX wordfx = new WordFX(CPU.SP(), value); // sukuriu nauja wordFX
        memory.set(CPU.SP(), wordfx); // i SP vieta idedu nauja WORDFX(adresa nauja ty ta pati sp ir value jau string
        int sp = cpu.SP()-1;
        cpu.SP(sp);

    }

    public void POP(CPU cpu) { //ismeta stacko virsuneje esancia reiksme
        int temp = cpu.SP();
        temp++;
        if( temp > 4096 ) {
            temp = 4046;
        }
        else if( temp < 4046 ) { // primetu kad RAM stackas 50 zodziu)  {
            temp = 4096;
        }
        cpu.SP(temp); //padidinu sp reiksme(pop)

    }



}
