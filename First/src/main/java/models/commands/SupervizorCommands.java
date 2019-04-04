package models.commands;

import javafx.collections.ObservableList;
import models.CPU;
import models.WordFX;
import utillities.BaseConverter;

public class SupervizorCommands {


    /**
     * Įdėti x registrą į steko viršūnę
     * @param memory
     * @param value
     */
    public static void SPH(ObservableList<WordFX> memory, String value) {
        CPU cpu = CPU.getInstance();

        //Increment instruction counter
        cpu.IC(cpu.IC() + 1);
        int SP = cpu.SP();

        //Check if the value is hex
        if (!value.matches(BaseConverter.getHexRegex()))
            return;

        //blogas size memory ties 5 ir 7 kazkas blogai
        //pvz a5 , a6, a8... dingsta a7
        //turi but 255
        String stackHead = memory.get(255).getValue();

        int registerValue = 0;

        switch (value) {
            case "1":
                registerValue = cpu.IC();
                break;
            case "2":
                registerValue = cpu.PRG();
                break;
            case "3":
                registerValue = cpu.SP();
                break;
            case "4":
                registerValue = cpu.HRG();
                break;
            case "5":
                registerValue = cpu.ORG();
                break;
            case "6":
                registerValue = cpu.IRG();
                break;
            case "7":
                registerValue = cpu.TI();
                break;
            case "8":
                registerValue = cpu.SI();
                break;
            case "9":
                registerValue = cpu.MODE();
                break;
            case "10":
                registerValue = cpu.SM();
                break;
        }

        //If the first stack element is zeros
        //We don't need to increase SP because our head is empty
        //We just have to add the element

        if((stackHead.equals("0000"))) {
            memory.get(SP).setValue(BaseConverter.convertValue(registerValue, BaseConverter.Hexadecimal));
            return;
        }

        SP--;
        memory.get(SP).setValue(String.valueOf(registerValue));
        cpu.SP(SP);
    }


    /**
     * Išimti iš steko viršūnės reikšmę ir įdėti į registą x
     * @param memory
     * @param value
     */
    public static void PAP(ObservableList<WordFX> memory, String value) {
        CPU cpu = CPU.getInstance();

        //Increment instruction counter
        cpu.IC(cpu.IC() + 1);
        int SP = cpu.SP();

        //If this is the last stack element
        //Don't reduce stack
        if(SP == 255){
            switch (value) {
                case "1":
                    cpu.IC(memory.get(SP).getValueShort());
                    break;
                case "2":
                    cpu.PRG(memory.get(SP).getValueShort());
                    break;
                case "3":
                    cpu.SP(memory.get(SP).getValueShort());
                    break;
                case "4":
                    cpu.HRG(memory.get(SP).getValueShort());
                    break;
                case "5":
                    cpu.ORG(memory.get(SP).getValueShort());
                    break;
                case "6":
                    cpu.IRG(memory.get(SP).getValueShort());
                    break;
                case "7":
                    cpu.TI(memory.get(SP).getValueShort());
                    break;
                case "8":
                    cpu.SI(memory.get(SP).getValueShort());
                    break;
                case "9":
                    cpu.MODE(memory.get(SP).getValueShort());
                    break;
                case "10":
                    cpu.SM(memory.get(SP).getValueShort());
                    break;
            }
            return;
        }

        switch (value) {
            case "1":
                cpu.IC(memory.get(SP).getValueShort());
                break;
            case "2":
                cpu.PRG(memory.get(SP).getValueShort());
                break;
            case "3":
                cpu.SP(memory.get(SP).getValueShort());
                break;
            case "4":
                cpu.HRG(memory.get(SP).getValueShort());
                break;
            case "5":
                cpu.ORG(memory.get(SP).getValueShort());
                break;
            case "6":
                cpu.IRG(memory.get(SP).getValueShort());
                break;
            case "7":
                cpu.TI(memory.get(SP).getValueShort());
                break;
            case "8":
                cpu.SI(memory.get(SP).getValueShort());
                break;
            case "9":
                cpu.MODE(memory.get(SP).getValueShort());
                break;
            case "10":
                cpu.SM(memory.get(SP).getValueShort());
                break;
        }
        cpu.SP(SP+1);

    }

    /**
     * leidzia keisti registro reiskme.
     * value nurodyti: 715 pvz: 7 yra registras i bus jo nauja reiksme 15
     * @param value
     */
    public static void MOV0(String value) {
        CPU cpu = CPU.getInstance();

        int register = Integer.valueOf(value.substring(0,1));
        int newValue = Integer.valueOf(value.substring(1, value.length()));

        switch (register) {
            case 1:
                cpu.IC(newValue);
                break;
            case 2:
                cpu.PRG(newValue);
                break;
            case 3:
                cpu.SP(newValue);
                break;
            case 4:
                cpu.HRG(newValue);
                break;
            case 5:
                cpu.ORG(newValue);
                break;
            case 6:
                cpu.IRG(newValue);
                break;
            case 7:
                cpu.TI(newValue);
                break;
            case 8:
                cpu.SI(newValue);
                break;
            case 9:
                cpu.MODE(newValue);
                break;
            case 10:
                cpu.SM(newValue);
                break;
        }

    }

    /**
     * pertraukimo sustojimo komanda.
     * mode i 1, push ir pop sp ir ic
     */
    public static void END() {

        CPU cpu = CPU.getInstance();

        cpu.MODE(1);
        cpu.SP(CommandHandler.SPbeforeINTERUPT);
        cpu.IC(CommandHandler.ICbeforeINTERUPT);

    }
}
