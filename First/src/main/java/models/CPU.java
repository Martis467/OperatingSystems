package models;

/**
 * Singleton
 */
public class CPU {

    private static CPU cpu;

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

    /**
     * Private constructor
     */
    private CPU() {
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

    public static CPU getInstance(){
        if (cpu == null)
            cpu = new CPU();

        return cpu;
    }

    //Getters and setters
    public static int   IC() {return IC;}
    public static void  IC(int val) {IC = val;}
    public static String ICnumber() {return "1";}

    public static int   PRG() {return PRG;}
    public static void  PRG(int val) {PRG = val;}
    public static String PRGnumber() {return "2";}

    public static int   SP() {return SP;}
    public static void  SP(int val) {SP = val;}
    public static String SPnumber() {return "3";}

    public static int   HRG() {return HRG;}
    public static void  HRG(int val) {HRG = val;}
    public static String HRGnumber() {return "4";}

    public static int   ORG() {return ORG;}
    public static void  ORG(int val) {ORG = val;}
    public static String ORGnumber() {return "5";}

    public static int   IRG() {return IRG;}
    public static void  IRG(int val) {IRG = val;}
    public static String IRGnumber() {return "6";}

    public static int   SI() {return SI;}
    public static void  SI(int val) {SI = val;}
    public static String SInumber() {return "7";}

    public static int   TI() {return TI;}
    public static void  TI(int val) {TI = val;}
    public static String TInumber() {return "8";}

    public static int   SM() {return SM;}
    public static void  SM(int val) {SM = val;}
    public static String SMnumber() {return "9";}

    public static int   MODE() {return MODE;}
    public static void  MODE(int val) {MODE = val;}
    public static String MODEnumber() {return "A";}


}
