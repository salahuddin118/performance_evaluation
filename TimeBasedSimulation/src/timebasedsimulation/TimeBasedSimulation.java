/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timebasedsimulation;

/**
 *
 * @author Salahuddin
 */
public class TimeBasedSimulation {

    static double PROBIN;
    static double PROBMEM[] = new double[3];
    static int MEMSTEP = 6;
    static int EXECI = 0;
    static int MAXSTEP = 1000000000;
    static int STEP, INSTR = 0, MEMRD = 0;
        
    static void setInputParam()
    {
        double value;
        
        value = Math.random();
        
        if(value < 0.2)
            PROBIN = 1;
        else if(value < 0.7)
            PROBIN = 2;
        else
            PROBIN = 3;
        
        PROBMEM[0] = 0.3;
        PROBMEM[1] = 0.5;
        PROBMEM[2] = 0.8;
        
        EXECI = 0;
        INSTR = 0;
        MEMRD = 0;
        //System.out.println("> " + value);
    }
    
    static void doSimulation() 
    {
        double value;
        STEP++;
        
        if(INSTR == 0)
        {
            if(MEMRD == 0)
            {
                EXECI++;
                value = Math.random();
                if(value < 0.2)
                    INSTR = 1;
                else if(value < 0.7)
                    INSTR = 2;
                else
                    INSTR = 3;
                
                value = Math.random();
                if(PROBMEM[INSTR-1] < value)
                    MEMRD = 0;
                else
                    MEMRD = MEMSTEP;
            }
            else
                MEMRD--;
        }
        else
            INSTR--;
    } 
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        for (int j = 5; j < 11; j++) 
        {
            MEMSTEP = j;
            System.out.println("\n" + j + "\n");
            for (int i = 0; i < 5; i++) 
            {
                setInputParam();
                STEP = 0;
                while(STEP < MAXSTEP)
                    doSimulation();

                System.out.println("Print - " + EXECI);
            }
        }
    }
}
