package models;

import java.util.Arrays;
import java.util.Optional;

public enum Command {

    //Arithmetic commands
    ADD("AA", 2),
    SUB("AB", 2),
    MUL("AC", 2),
    DIV("AD", 2),
    NOT("AE", 2),

    //Comparison commands
    CMP("C0",2),

    //Stack commands
    LD("80", 4),
    PT("81", 4),
    PUN("82", 4),
    PUS("83", 4),

    //Controll
    JP("F0", 4),
    JE("F1", 4),
    JL("F2", 4),
    JG("F3", 4),
    STOP("FFFF", 4),

    // I/O
    PRTS("50", 4),
    PRTN("51", 4),
    P("11", 4),
    R("12", 4),
    READ("52", 4),
    RDH("20", 4),
    WRH("51", 4),
    STARTIO("00", 4);

    private final String hexCode;
    private final int commandLength;

    Command(String hexCode, int commandLength)
    {
        this.hexCode = hexCode;
        this.commandLength = commandLength;
    }

    /**
     * Determines if the command is valid
     * @param command
     * @return
     */
    public boolean isValidCoomand(String command){
        if (command == null)
            return false;

        Command com = findCommand(command).orElse(null);

        return com == null;
    }

    private Optional<Command> findCommand(String command){
        return Arrays.stream(values())
                .filter(com -> com.toString().equals(command)).findFirst();
    }

}
