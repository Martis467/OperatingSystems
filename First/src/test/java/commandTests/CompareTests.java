package commandTests;

import javafx.collections.ObservableList;
import models.CPU;
import models.WordFX;
import models.commands.ComparisonCommand;
import models.commands.DataLoadingCommands;
import models.commands.StackCommands;
import org.junit.Assert;
import org.junit.Test;

public class CompareTests {

    @Test
    public void testInvalid(){
        CPU cpu = CPU.getInstance();
        cpu.SP(255);

        ObservableList<WordFX> vRam = TestingUtilities.getVirtualMachineMockUp(256);

        //If there is only one element nothing should happen
        DataLoadingCommands.DW("10", vRam);
        StackCommands.LD(vRam, 1);

        ComparisonCommand.Compare(vRam);
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
    public void testGreater(){
        /*
         * Compare, increase SP value and add the result to [SP-1]:
         * if [SP + 1] > [SP] = 1
         * if [SP + 1] < [SP] = 2
         * else 0
         */
        CPU cpu = CPU.getInstance();
        cpu.SP(255);

        ObservableList<WordFX> vRam = TestingUtilities.getVirtualMachineMockUp(256);

        //If there is only one element nothing should happen
        DataLoadingCommands.DW("20", vRam);
        DataLoadingCommands.DW("10", vRam);
        StackCommands.LD(vRam, 1);
        StackCommands.LD(vRam, 2);

        ComparisonCommand.Compare(vRam);

        // 20 > 10
        Assert.assertEquals(1, vRam.get(cpu.SP()).getValueShort());
    }

    @Test
    public void testLess(){
        /*
         * Compare, increase SP value and add the result to [SP-1]:
         * if [SP + 1] > [SP] = 1
         * if [SP + 1] < [SP] = 2
         * else 0
         */
        CPU cpu = CPU.getInstance();
        cpu.SP(255);

        ObservableList<WordFX> vRam = TestingUtilities.getVirtualMachineMockUp(256);

        //If there is only one element nothing should happen
        DataLoadingCommands.DW("10", vRam);
        DataLoadingCommands.DW("20", vRam);
        StackCommands.LD(vRam, 1);
        StackCommands.LD(vRam, 2);

        ComparisonCommand.Compare(vRam);

        // 20 > 10
        Assert.assertEquals(2, vRam.get(cpu.SP()).getValueShort());
    }

    @Test
    public void testEquals(){
        /*
         * Compare, increase SP value and add the result to [SP-1]:
         * if [SP + 1] > [SP] = 1
         * if [SP + 1] < [SP] = 2
         * else 0
         */
        CPU cpu = CPU.getInstance();
        cpu.SP(255);

        ObservableList<WordFX> vRam = TestingUtilities.getVirtualMachineMockUp(256);

        //If there is only one element nothing should happen
        DataLoadingCommands.DW("10", vRam);
        DataLoadingCommands.DW("10", vRam);
        StackCommands.LD(vRam, 1);
        StackCommands.LD(vRam, 2);

        ComparisonCommand.Compare(vRam);

        // 20 > 10
        Assert.assertEquals(0, vRam.get(cpu.SP()).getValueShort());
    }
}
