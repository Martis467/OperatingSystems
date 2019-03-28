package models.commands;

import javafx.collections.ObservableList;
import models.CPU;
import models.WordFX;

public class ComparisonCommand {

    public static void Compare(ObservableList<WordFX> memory){
        CPU cpu = CPU.getInstance();

        //Increment instruction counter
        cpu.IC(cpu.IC() + 1);
        int SP = cpu.SP();

        //If our stack has only one variable or none, we don't do anything
        if (SP+1 >= memory.size())
            return;

        //Get first value and set head to zero
        short value1 = memory.get(SP).getValueShort();

        //Get second value
        short value2 = memory.get(SP+1).getValueShort();

        //Decrease SP
        cpu.SP(SP - 1);

        /*
         * Compare, increase SP value and add the result to [SP-1]:
         * if [SP - 1] > [SP] = 1
         * if [SP - 1] < [SP] = 2
         * else 0
         */

        short result = 0;

        if( value2 > value1) result = 1;
        if( value2 < value1) result = 2;

        //increase SP and put the new value
        memory.get(SP - 1).setValue(result);
    }
}
