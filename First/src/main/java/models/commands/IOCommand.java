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

        String stackHead = memory.get(cpu.vmSize()-1).getValue();

        //If the first stack element is zeros
        //We don't need to increase SP because our head is empty
        //We just have to add the element
        if((stackHead.equals("0000")) && cpu.SP() == cpu.vmSize()-1 ) {

            if(register == "ORG")
                memory.get( cpu.SP()).setValue( BaseConverter.convertValue(cpu.ORG(), BaseConverter.Hexadecimal));

            if(register.equals("HRG") )
                memory.get(cpu.SP()).setValue(BaseConverter.convertValue(cpu.HRG(), BaseConverter.Hexadecimal));

            if(register == "IRG")
                memory.get( cpu.SP()).setValue( BaseConverter.convertValue(cpu.IRG(), BaseConverter.Hexadecimal));
            return;
        }

        //sumazinu sp nes idejau nauja reiksme
        cpu.SP(cpu.SP() -1);

        if(register == "ORG")
            memory.get( cpu.SP()).setValue( BaseConverter.convertValue(cpu.ORG(), BaseConverter.Hexadecimal));

        if(register.equals("HRG") )
            memory.get(cpu.SP()).setValue(BaseConverter.convertValue(cpu.HRG(), BaseConverter.Hexadecimal));

        if(register == "IRG")
            memory.get( cpu.SP()).setValue( BaseConverter.convertValue(cpu.IRG(), BaseConverter.Hexadecimal));


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
     * į išvedimo įrenginį(monitoriu) išveda x numeriu nurodyto atminties srities
     * bloko nuo pradžios iki simbolio \n.
     * @param memory
     * @param monitor
     */
    public static void PB( ObservableList<WordFX> memory,TextArea monitor, String adress) {
        CPU cpu = CPU.getInstance();
        int shortAdress = BaseConverter.converToDecimal(adress,BaseConverter.Hexadecimal);

        cpu.IC(cpu.IC() +1 );

        //jei kanalas uzimtas
        if(cpu.ORG() == 1)
            return;

        System.out.println(adress);
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

    /**
     * į išorinį įrenginį adresu x išveda stack‘e esančias reikšmes nuo pradžios iki simbolio \n.
     * @param memory
     * @param hdd
     * @param adress
     */
    //pvz:
    //PUSA
    //PUSB
    //PN
    //R0000
    public static void RHD(ObservableList<WordFX> memory,ObservableList<WordFX> hdd, String adress) {
        CPU cpu = CPU.getInstance();

        //gaunu adresa 10 tainiu o ne string
        int shortAdress = BaseConverter.converToDecimal(adress,BaseConverter.Hexadecimal);

        cpu.IC(cpu.IC() +1 );

        //jei kanalas uzimtas
        if(cpu.HRG() == 1)
            return;
        //pradedamas isvedimas i hdd kanala
        cpu.HRG(1);

        String tmp = "";

        int spOld = cpu.SP();
        int spNew = 255;//rodo i pacia steko pradzia //ff cia

        while( true) {
            //nuskaitau reiksme is stack
            tmp = memory.get( spNew).getValue();

            if(tmp.equals("\\n")) {
                break;
            }

            //padidnu sp reiksme (einu i steka aukstyn ir renku reiksmes
            spNew--;
            //irasau i hdd
            hdd.get(shortAdress).setValue(tmp);

            shortAdress++;
        }

        //baigia isvedima
        cpu.HRG(0);
    }

    /**
     * išorinėje atmintyje(hdd) esantį žodį x adresu, įrašo į steko viršūnę.
     * @param memory
     * @param hdd
     * @param adress
     */
    public static void HDD(ObservableList<WordFX> memory,ObservableList<WordFX> hdd, String adress) {
        CPU cpu = CPU.getInstance();

        //gaunu adresa 10 tainiu o ne string(16liktaini)
        int shortAdress = BaseConverter.converToDecimal(adress,BaseConverter.Hexadecimal);

        cpu.IC(cpu.IC() +1 );

        //jei kanalas uzimtas
        if(cpu.HRG() == 1)
            return;
        //pradedamas isvedimas i stack
        cpu.HRG(1);

        //nuskaitau reiksme is hdd
        String tmp = hdd.get( shortAdress).getValue();

        StackCommands.PUS(memory,tmp);

        //baigia isvedima
        cpu.HRG(0);
    }

    /**
     * steko viršūnėje esantį žodį įrašo į išorinę atmintį adresu x.
     * @param memory
     * @param hdd
     * @param adress
     */
    public static void WHD(ObservableList<WordFX> memory,ObservableList<WordFX> hdd, String adress) {
        CPU cpu = CPU.getInstance();

        //gaunu adresa 10 tainiu o ne string(16liktaini)
        int shortAdress = BaseConverter.converToDecimal(adress,BaseConverter.Hexadecimal);

        cpu.IC(cpu.IC() +1 );

        //jei kanalas uzimtas
        if(cpu.HRG() == 1)
            return;
        //pradedamas isvedimas i hdd
        cpu.HRG(1);

        //nuskaitau reiksme is stack
        String tmp = memory.get( cpu.SP()).getValue();

        //i hdd adresu shortAdress idedu reiksme is stacko virsunes
        hdd.get(shortAdress).setValue(tmp);

        //baigia isvedima
        cpu.HRG(0);

    }
}
