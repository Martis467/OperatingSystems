package commandTests;

import enums.VMSize;
import javafx.collections.ObservableList;
import models.CPU;
import models.WordFX;
import models.commands.StackCommands;
import org.junit.Assert;
import org.junit.Test;

public class StackTests {

    private CPU cpu;
    private VMSize vmSize;

    public  void initValues() {
        vmSize = VMSize.Big;
        cpu = CPU.getInstance();
        cpu.setVM(vmSize);
        cpu.SP(vmSize.getVmSize()-1);

    }

    /* Live test
    DS:
DW 1A
DW 2A
DW 3A

    CS:
LD 01
LD 02
LD 03
POP
PUS BA
PUS BAD
PUN CA
PUN 1K
PT 10

     */
    @Test
    public void testStackCommands(){
        initValues();
        ObservableList<WordFX> vRam = TestingUtilities.getVirtualMachineMockUp(vmSize.getVmSize());

        TestingUtilities.loadRandomValuesToDS(vRam, 5);

        StackCommands.LD(vRam, 1);
        StackCommands.LD(vRam, 2);
        StackCommands.LD(vRam, 3);
        StackCommands.LD(vRam, 4);
        StackCommands.LD(vRam, 5);

        //Check if values loaded to stack properly
        String stackHead = vRam.get(cpu.SP()).getValue();
        String dsValue = vRam.get(5).getValue();

        Assert.assertTrue(stackHead.equals(dsValue));
        Assert.assertEquals(10, cpu.IC());

        //Check if POP works
        StackCommands.POP(vRam);

        stackHead = vRam.get(cpu.SP()).getValue();
        dsValue = vRam.get(4).getValue();

        Assert.assertTrue(stackHead.equals(dsValue));

        //Test putting random symbols to stack
        String symbol = "BA";
        String badSymbol = "BAD";

        StackCommands.PUS(vRam, symbol);
        StackCommands.PUS(vRam, badSymbol);

        Assert.assertTrue(vRam.get(cpu.SP()).getValue().equals(symbol));
        Assert.assertEquals(13, cpu.IC());

        //Test putting random hex values to stack
        String hex = "CA";
        String badHex = "1K";

        StackCommands.PUN(vRam, hex);
        StackCommands.PUN(vRam, badHex);

        Assert.assertTrue(vRam.get(cpu.SP()).getValue().equals(hex));
        Assert.assertEquals(15, cpu.IC());

        //Testing putting stack head to DS
        StackCommands.PT(vRam, 10);

        stackHead = vRam.get(cpu.SP()).getValue();
        dsValue = vRam.get(10).getValue();

        Assert.assertTrue(stackHead.equals(dsValue));
    }
}
