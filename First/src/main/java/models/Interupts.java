package models;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.HashMap;

public class Interupts {

    SupervizorMem supervizorMem;

    Interupts() {
        supervizorMem = new SupervizorMem();
    }

    public void checkInterupt(HashMap<Integer,Integer> memory, CPU cpu) {

        int interupt = supervizorMem.SVR(204); //sp 0 grazinamas ir tikrinama reiksme koks interuptas

        supervizorMem.SVW( 255,cpu.SP() );
        supervizorMem.SVW(254, cpu.IC());

        switch (interupt) {//cpu.SI()

            //a.    programiniai interuptai
            case 1: //Atminties saugos pažeidimas

                break;
            case 2: //Blogas operacijos kodas
                //BadOperationCode();
                break;
            //b.	Sisteminiai:
            case 3: //Komanda PRTS
                PRTS();
                break;
            case 4: //Komanda PRTN

                break;
            case 5: //Komanda P

                break;
            case 6: //Komanda READ

                break;
            case 7: //Komanda RDH

                break;
            case 8: //Komanda STOP

                break;
            case 9: //Komanda LC

                break;
            case 10: //Komanda UC

                break;
            //c.	Timerio:
            case 11: //SI = B – TI = 0

                break;
        }

        cpu.SP( supervizorMem.SVR(255) );
        cpu.IC( supervizorMem.SVR(254) );
    }

    public void PRTS() {
        System.out.println("PRTS pavyko");

    }

    private void Timer(CPU cpu, SupervizorMem supervizorMem) {

        cpu.TI(50);
    }

    private void BadOperationCode(CPU cpu, SupervizorMem supervizorMem) {


    }
}
