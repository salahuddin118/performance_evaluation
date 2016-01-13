/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulation;

/**
 *
 * @author Salahuddin
 */
public class Simulation {

    public static final int PAGE_SIZE = 4096;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        int zpageSize;
        
        for (int i = 0; i < 20000; i++) 
        {
            zpageSize = (int)(Math.random() * 4096) + 1;
            zbud.getInstance().storePage(new zpage(zpageSize));
        }
        
        System.out.println("Density = " + zbud.getInstance().density());
    }
}
