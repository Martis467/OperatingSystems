package models.commands;

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
    POP("84", 4),

    //Control
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
    STARTIO("00", 4),

    //Data loading
    DW("DB", 4),
    DN("DC", 4),
    DD("DD", 4),

    //Interupt commands
    MOV("CA",4),
    SVW("CB", 4),
    SVR("CC", 4),
    PUSH("CD", 4),
    POP("CE", 4),
    TRNF("CF", 4 ),
    REGW("D0", 4);


    private final String hexCode;
    private final int commandLength;

    Command(String hexCode, int commandLength)
    {
        this.hexCode = hexCode;
        this.commandLength = commandLength;
    }

    /**
     * Gets a command by string or returns null
     * @param command
     * @return
     */
    public static Command getCommand(String command){
        if (command == null)
            return null;

        return findCommand(command).orElse(null);
    }

    private static Optional<Command> findCommand(String command){
        return Arrays.stream(values())
                .filter(com -> com.toString().equals(command)).findFirst();
    }

    public String getCode() {
        return hexCode;
    }

    public int getDecimal() {
        return Integer.valueOf(hexCode, 16);
    }

}
