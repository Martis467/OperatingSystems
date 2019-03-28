package commandTests;

import javafx.collections.ObservableList;
import models.CPU;
import models.WordFX;
import models.commands.ArithmeticCommand;
import models.commands.DataLoadingCommands;
import models.commands.StackCommands;
import org.junit.Assert;
import org.junit.Test;

public class ArithmeticTests {

    /* SUDETIS
    DS:
        DW20
        DW10
    CS:
        LD01
        LD02
    ADD
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

    /*
    DS
DW 30
DW 10

    CS
LD 01
LD 02
SUB
SUB
     */
    @Test
    public void testSub(){
        CPU cpu = CPU.getInstance();
        cpu.SP(255);

        ObservableList<WordFX> vRam = TestingUtilities.getVirtualMachineMockUp(256);

        //If there is only one element nothing should happen
        DataLoadingCommands.DW("30", vRam);
        StackCommands.LD(vRam, 1);

        ArithmeticCommand.Subtract(vRam);

        Assert.assertEquals(0x30, vRam.get(cpu.SP()).getValueShort());

        //if there is two, the head should change
        DataLoadingCommands.DW("10", vRam);
        //4 because we are jumping back and forth from DS and CS so our IC gets increased by each command
        //This should not be done in LIVE
        StackCommands.LD(vRam, 4);

        //Subtract values
        ArithmeticCommand.Subtract(vRam);

        //0x10 - 0x30
        Assert.assertEquals(-0x20,vRam.get(cpu.SP()).getValueShort());

        int SP = cpu.SP();
        //Subsctract 0
        ArithmeticCommand.Subtract(vRam);

        //-0x20 - 0x10
        Assert.assertEquals(-0x30, vRam.get(cpu.SP()).getValueShort());
    }

    /*
DS
DW 30
DW 10

CS
LD 01
LD 02
MUL
 */
    @Test
    public void testMul(){
        CPU cpu = CPU.getInstance();
        cpu.SP(255);

        ObservableList<WordFX> vRam = TestingUtilities.getVirtualMachineMockUp(256);

        //If there is only one element nothing should happen
        DataLoadingCommands.DW("5", vRam);
        StackCommands.LD(vRam, 1);

        ArithmeticCommand.Multiply(vRam);

        Assert.assertEquals(0x5, vRam.get(cpu.SP()).getValueShort());

        //if there is two, the head should change
        DataLoadingCommands.DW("7", vRam);
        //4 because we are jumping back and forth from DS and CS so our IC gets increased by each command
        //This should not be done in LIVE
        StackCommands.LD(vRam, 4);

        //Multiply values
        ArithmeticCommand.Multiply(vRam);

        //0x7 * 0x5
        Assert.assertEquals(0x23,vRam.get(cpu.SP()).getValueShort());
    }

    @Test
    public void dafuck(){
        //How in the world is this normal
        short okay = Short.parseShort("7FF0", 16);
        //AND THIS IS NOT? WHAT IN THE GODS HEAVEN...
        short NOT_OKAY = Short.parseShort("FFF0", 16);
        System.out.println(NOT_OKAY);
    }

    /* DALYBA
     DS:
        DW20
        DW10
    CS:
        LD01
        LD02
    DIV
     */

}
