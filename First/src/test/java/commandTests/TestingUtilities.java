package commandTests;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.WordFX;
import models.commands.DataLoadingCommands;

import java.util.Random;

public class TestingUtilities {

    final static String[] hex = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };

    /**
     * Creates an empty VM
     * @param size - VM size
     * @return
     */
    public static ObservableList<WordFX> getVirtualMachineMockUp(int size){
        ObservableList<WordFX> vmRam = FXCollections.observableArrayList();

        for (int i = 0; i < size; i++){
            vmRam.add(new WordFX(i, 0));
        }

        return vmRam;
    }

    /**
     * Creates and fills <b>valueAmount</b>of the VM with random values
     * @param valueAmount
     */
    public static void loadRandomValuesToDS(ObservableList<WordFX> vRam, int valueAmount){
        Random rand = new Random(System.nanoTime());

        for (int i = 0; i < valueAmount; i++){
            //Semi random index value generation
            int firstValue = rand.nextInt(20) % 15;
            int secondValue = (firstValue + rand.nextInt(31)) % 15;

            DataLoadingCommands.DW(hex[firstValue] + hex[secondValue], vRam);
        }
    }
}
