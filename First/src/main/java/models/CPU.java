package models;

public class CPU {
    private static int IC;
    private static int PRG;
    private static int SP;
    private static int HRG;
    private static int ORG;
    private static int IRG;
    private static int SI;
    private static int TI;
    private static int SM;
    private static int MODE;

    public static CPU initCPU()
    {
       CPU cpu = new CPU();
       cpu.IC(0);
       cpu.SP(0);
       cpu.PRG(0);
       cpu.HRG(0);
       cpu.ORG(0);
       cpu.IRG(0);
       cpu.SI(0);
       cpu.TI(50);
       cpu.SM(0);
       cpu.MODE(0);

       return cpu;
    }

    CPU() {
        IC(0);
        SP(0);
        PRG(0);
        HRG(0);
        ORG(0);
        IRG(0);
        SI(0);
        TI(50);
        SM(0);
        MODE(0);
    }

    //Getters and setters
    public static int   IC() {return IC;}
    public static void  IC(int val) {IC = val;}

    public static int   PRG() {return PRG;}
    public static void  PRG(int val) {PRG = val;}

    public static int   SP() {return SP;}
    public static void  SP(int val) {SP = val;}

    public static int   HRG() {return HRG;}
    public static void  HRG(int val) {HRG = val;}

    public static int   ORG() {return ORG;}
    public static void  ORG(int val) {ORG = val;}

    public static int   IRG() {return IRG;}
    public static void  IRG(int val) {IRG = val;}

    public static int   SI() {return SI;}
    public static void  SI(int val) {SI = val;}

    public static int   TI() {return TI;}
    public static void  TI(int val) {TI = val;}

    public static int   SM() {return SM;}
    public static void  SM(int val) {SM = val;}

    public static int   MODE() {return MODE;}
    public static void  MODE(int val) {MODE = val;}


}
