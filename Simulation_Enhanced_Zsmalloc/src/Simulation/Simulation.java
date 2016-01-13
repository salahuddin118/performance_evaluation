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
        
        int zpageSize = 0;
        
        for (int i = 0; i < 100000; i++) 
        {
            zpageSize = (int)(Math.random() * 4096);  
            //System.out.println("" + zpageSize);
            zsmalloc.getInstance().storePage(new zpage(zpageSize));
        }
        
        System.out.println("Density = " + zsmalloc.getInstance().density());
    }
}
