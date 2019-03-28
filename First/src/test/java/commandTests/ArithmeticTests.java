package commandTests;

import javafx.collections.ObservableList;
import models.CPU;
import models.WordFX;
import models.commands.ArithmeticCommand;
import models.commands.DataLoadingCommands;
import org.junit.Assert;
import org.junit.Test;

public class ArithmeticTests {

    /* SUDETIS
    DS:
    DW20
    DW10
    CS:
    LD0001
    LD0002
    ADD
    */

    /* DALYBA
     DS:
    DW20
    DW10
    CS:
    LD0001
    LD0002
    DIV
     */



    @Test
    public void testAdd(){
        CPU cpu = CPU.getInstance();

        ObservableList<WordFX> vRam = TestingUtilities.getVirtualMachineMockUp(256);

        DataLoadingCommands.DW("20", vRam);
        DataLoadingCommands.DW("10", vRam);



        ArithmeticCommand.Add(vRam);
        //System.out.println(vRam.get( cpu.SP() ).getValue() );

    }
}
