package models;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RAM {
    private HashMap<Integer, Integer> memory;

    /**
     * Default constructor, sets all values to zero
     */
    public RAM(){
        memory = new HashMap<>(4096);
        for (int i = 0; i < memory.size(); i++){
            memory.put(i, 0);
        }
    }

    public void addValue(int address, int value) {memory.put(address, value);}

    public static void ramToJavaFx(RAM ram, ObservableList<WordFX> ramFx){

        for (Map.Entry<Integer, Integer> entry:
                ram.memory.entrySet()) {
            ramFx.get(entry.getKey()).setValue(entry.getValue());
        }
    }



}
