package models.commands;

import javafx.collections.ObservableList;
import models.CPU;
import models.WordFX;

public class ArithmeticCommand {

    public static void ADD(CPU cpu, ObservableList<WordFX> memory) { //– sudeda du viršutinius steko elementus. Rezultatą padeda į stekoviršūnę ir steko rodyklę sumažina vienetu.ST[SP – 1] = ST [SP – 1] + ST [SP]; SP--;
        int temp = cpu.SP(); //gaunu 1 reiksme
        StackCommands.POP(cpu); //sp padidinu
        int ats = temp + cpu.SP();//sudedu reiksmes
        StackCommands.POP(cpu); //sp padidinu
        StackCommands.PUN(cpu, ats, memory );

    }

    public static void SUB(CPU cpu, ObservableList<WordFX> memory) { //atima steko viršūnėje esantį elementą iš antro nuo viršaus elemento.Rezultatą padeda į steko viršūnę .ST [SP – 1] = ST [ SP – 1] - ST [SP]; SP--;
        int temp = cpu.SP(); //gaunu 1 reiksme
        StackCommands.POP(cpu); //sp padidinu
        int ats = cpu.SP() - temp;//atimu is 2 reiksmes pirma
        StackCommands.POP(cpu); //sp padidinu
        StackCommands.PUN(cpu, ats, memory );
    }

    public static void MUL(CPU cpu, ObservableList<WordFX> memory) { //sudaugina du viršutinius steko elementus. Rezultatą padeda į stekoviršūnę ir steko rodyklę sumažina vienetu.ST [SP – 1] = ST [ SP – 1] * ST [SP]; SP--;
        int temp = cpu.SP(); //gaunu 1 reiksme
        StackCommands.POP(cpu); //sp padidinu
        int ats = cpu.SP() * temp;//atimu is 2 reiksmes pirma
        StackCommands.POP(cpu); //sp padidinu
        StackCommands.PUN(cpu, ats, memory );

    }

    public static void DIV(CPU cpu, ObservableList<WordFX> memory) { //padalina antrą nuo viršaus steke esantį elementą iš viršūnėje esančio.Rezultatą padeda į steko viršūnę ir steko rodyklę sumažina vienetu.[SP – 1] =  [ SP – 1] / [SP]; SP--;
        int temp = cpu.SP(); //gaunu 1 reiksme
        StackCommands.POP(cpu); //sp padidinu
        int ats = cpu.SP() / temp;//atimu is 2 reiksmes pirma
        StackCommands.POP(cpu); //sp padidinu
        StackCommands.PUN(cpu, ats, memory );

    }

    // cia nesuprantu koki dari logini tipo kad !true = false ar bitu inversija??
    //padariau inversija
    public static void NOT(CPU cpu, ObservableList<WordFX> memory) { //atlieka [SP] esančio žodžio loginį neigimą (inversiją).[SP] = !([SP])
        int a = cpu.SP(); //gaunu 1 reiksme
        int b = ~a; // this will reverts the bits 01000 which is 8 in decimal
        WordFX fx = new WordFX(cpu.SP(), b);
        memory.set(cpu.SP(), fx);

    }


}
