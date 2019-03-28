package models.commands;

import gui.VMController;
import javafx.scene.control.TextArea;
import models.CPU;
import models.WordFX;
import javafx.collections.ObservableList;
import utillities.BaseConverter;


public class IOCommand {

    /**
     * steko viršūnėje esantį žodį traktuoja kaip simbolius ir išveda į išvedimo įrenginį.
     * @param memory
     * @param monitor
     */
    public static void PRTS(ObservableList< WordFX > memory, TextArea monitor) {
        CPU cpu = CPU.getInstance();

        cpu.IC(cpu.IC() +1 );

        //jei kanalas uzimtas
        if(cpu.ORG() == 1)
            return;
        //pradedamas isvedimas
        cpu.ORG(1);

        String a = memory.get(cpu.SP()).getValue();
        monitor.setText(a);

        //baigia isvedima
        cpu.ORG(0);
    }

    /**
     * ji kaip vieną iš argumentų ima kanalo numerį.
     * Kanalų užimtumas nustatomas pagal kanalų (HRG,IRG,ORG) registrus.
     * StartIO į steko viršūnę patalpina, 0 - laisvas arba 1- užimtas, reikšmę.
     * @param memory
     */
    public static void STARTIO(ObservableList<WordFX> memory, String register) {
        CPU cpu = CPU.getInstance();

        if(register == "ORG")
            memory.get( cpu.SP()).setValue( (short) cpu.ORG());

        if(register == "HRG")
            memory.get( cpu.SP()).setValue( (short) cpu.HRG());

        if(register == "IRG")
            memory.get( cpu.SP()).setValue( (short) cpu.IRG());

        //sumazinu sp nes idejau nauja reiksme
        cpu.SP(cpu.SP() -1);

        cpu.IC(cpu.IC() +1 );

    }

    /**
     * steko viršūnėje esantį žodį traktuoja kaip skaitinę reikšmę ir
     * išveda į išvedimo įrenginį.
     * @param memory
     * @param monitor
     */
    public static void PRTN(ObservableList< WordFX > memory, TextArea monitor) {
        CPU cpu = CPU.getInstance();

        cpu.IC(cpu.IC() +1 );

        //jei kanalas uzimtas
        if(cpu.ORG() == 1)
            return;
        //pradedamas isvedimas
        cpu.ORG(1);

        String a = memory.get(cpu.SP()).getValue();
        monitor.setText(a);


        //isveda kaip 10taini
        //Short a = memory.get(cpu.SP()).getValueShort();
        //String tmp = a.toString();
        //monitor.setText(tmp);

        //baigia isvedima
        cpu.ORG(0);
    }

    /**
     * į išvedimo įrenginį išveda x numeriu nurodyto atminties srities
     * bloko nuo pradžios iki simbolio \n.
     * @param memory
     * @param monitor
     */
    public static void P( ObservableList<WordFX> memory,TextArea monitor, String adress) {
        CPU cpu = CPU.getInstance();
        int shortAdress = BaseConverter.converToDecimal(adress,BaseConverter.Hexadecimal);

        cpu.IC(cpu.IC() +1 );

        //jei kanalas uzimtas
        if(cpu.ORG() == 1)
            return;
        //pradedamas isvedimas
        cpu.ORG(1);

        String tmp = "";
        String print = "";
        while( !tmp.equals("\\n")) {
            print = print + tmp;
            tmp = memory.get( shortAdress).getValue();
            shortAdress++;
        }

        monitor.setText( print);
        //baigia isvedima
        cpu.ORG(0);
    }

    public static void R() {
        
    }
}
