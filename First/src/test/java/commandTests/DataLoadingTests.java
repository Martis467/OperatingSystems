package commandTests;

import javafx.collections.ObservableList;
import models.CPU;
import models.WordFX;
import models.commands.DataLoadingCommands;
import org.junit.Assert;
import org.junit.Test;

public class DataLoadingTests {


    /* Live tests
DW FA
DW GA
DD XO
DD XOX
DN
     */
    @Test
    public void testDataLoadingCommands(){
        CPU cpu = CPU.getInstance();

        ObservableList<WordFX> vRam = TestingUtilities.getVirtualMachineMockUp(256);

        String hex = "FA";
        String badHex = "GA";

        String symbols = "XO";
        String badSymbols = "XOX";


        DataLoadingCommands.DW(hex, vRam);
        DataLoadingCommands.DW(badHex, vRam);

        DataLoadingCommands.DD(symbols, vRam);
        DataLoadingCommands.DD(badSymbols, vRam);

        DataLoadingCommands.DN(vRam);

        //Ram assertions
        Assert.assertTrue(vRam.get(1).getValue().equals("00FA"));
        Assert.assertFalse(vRam.get(2).getValue().equals("00GA"));

        Assert.assertTrue(vRam.get(3).getValue().equals("XO"));
        Assert.assertFalse(vRam.get(4).getValue().equals("XOX"));

        Assert.assertTrue(vRam.get(5).getValue().equals("\\n"));

        //Cpu assertions
        Assert.assertEquals(5, cpu.IC());
    }

    /**
     * Testing if we exceed data segments size if we keep adding values or we stop
     */
    @Test
    public void testOverTheBoundries(){
        CPU cpu = CPU.getInstance();
        ObservableList<WordFX> vRam = TestingUtilities.getVirtualMachineMockUp(256);

        for (int i = 0; i < 256; i++){
            DataLoadingCommands.DW("FA", vRam);
        }

        Assert.assertEquals(256, cpu.IC());

        Assert.assertTrue(vRam.get(111).getValue().equals("00FA"));

        Assert.assertFalse(vRam.get(112).getValue().equals("00FA"));
    }
}
