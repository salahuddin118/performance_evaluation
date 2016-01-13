/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventbasedsimulation;

/**
 *
 * @author Salahuddin
 */
public class EventBasedSimulation {

    static int N, LATENCY;
    static double SEEK;
    static double P = 0.1;
    static double DELAY, CLOCK;
    static int DEST, SOURCE, TRACKS;
    
    static int EVENT;
    static double ADELAY = 0.0;
        
    static void setInputParam()
    {
        N = 1000;
        LATENCY = 4;
        SEEK = 0.5;
        SOURCE = Math.round((float)Math.random() * 75);
        
        CLOCK = 0.0;   
   }
    
    static void doSimulation() 
    {
        double value;
        EVENT++;
        
        value = Math.random();
        if(value < P)
        {
            DEST = SOURCE;
            DELAY = LATENCY/2;
        }
        else
        {
            value = Math.random();
            DEST = Math.round((float)value * 75);
            if(DEST == SOURCE)
            {
                DEST++;
            }
            TRACKS = Math.abs(SOURCE - DEST);
            DELAY = TRACKS * SEEK + LATENCY / 2;
        }
        
        CLOCK += DELAY;
        SOURCE = DEST;
    } 
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        for (int j = 1; j < 10; j++) 
        {
            P = (double)j/10.0;
            System.out.println("\n" + P + "\n");
            for (int i = 0; i < 5; i++) 
            {
                setInputParam();
                EVENT = 0;
                while(EVENT < N)
                    doSimulation();

                ADELAY = CLOCK / N;
                System.out.println("Print - " + ADELAY);
            }
        }
    }
}
