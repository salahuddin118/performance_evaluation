/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcsimulation;

/**
 *
 * @author Salahuddin
 */
public class MCSimulation {

    static int calls = 0, event = 0;
    // in hour
    static double callrate = 0.5;
    static double holdtime = 0.25;
    static int phones = 4;
    static int trunks = 3;
    static double newcall = 0;
    static double hangup = 0.0;
    static double para = 0.0;
    static double etime = 0.0;
    static double AVGCALL = 0.0;
    static double VARCALL = 0.0;
    static double BLOCK_VALUE = 0.0;
    static double clock = 0.0;
    static int totcall = 0;
    static double block = 0;
    static int limit = 10000;

    static void doSimulation() {
        event++;

        double PROB;

        PROB = Math.random();

        newcall = callrate * (phones - calls);
        hangup = calls / holdtime;
        para = newcall + hangup;

        etime = -1 / para;
        etime = etime * Math.log(PROB);

        AVGCALL = calls * etime + AVGCALL;
        VARCALL = calls * calls * etime + VARCALL;

        clock += etime;
        PROB = Math.random();

        if (PROB >= (newcall / para)) {
            calls--;
        } else {
            totcall++;
            if (calls < trunks) {
                calls++;
            } else {
                block++;
            }
        }
    }

    static void printResult() {
        AVGCALL = AVGCALL / clock;
        VARCALL = Math.sqrt(VARCALL / clock - AVGCALL * AVGCALL);
        BLOCK_VALUE = block / totcall;

        System.out.println("Avgcall: " + AVGCALL);
        System.out.println("VARCALL: " + VARCALL);
        System.out.println("Block: " + BLOCK_VALUE);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        while (event < limit) {
            doSimulation();
        }
        printResult();
    }
}
