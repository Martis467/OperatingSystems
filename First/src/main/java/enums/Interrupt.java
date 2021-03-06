package enums;

public enum Interrupt {
    ForbiddenMemoryAccess(1),
    BadOperationCode(2),
    DivisionFromZero(3),
    PRTS(4),
    PRTN(5),
    P(6),
    READ(7),
    RDH(8),
    STOP(9),
    LC(10),
    UC(11),
    TimerZero(12); // adresu CC pradeda vykdyti

    private final int val;

    Interrupt(int val){
        this.val = val;
    }

    public int toInt() {return val;}

}
