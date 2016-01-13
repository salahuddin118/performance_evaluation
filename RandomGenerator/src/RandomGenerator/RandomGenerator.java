/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RandomGenerator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Vector;

/**
 *
 * @author Salahuddin
 */
public class RandomGenerator {

    static int numberInterval[] = new int[10];
    static int rdInterval[] = new int[10];
    static int expoInterval[] = new int[10];
    static int geoInterval[] = new int[10];
    
    static void checkNumber(double a, int[] interval)
    {
        if(a < 0.1)
            interval[0]++;
        else if(a < 0.2)
            interval[1]++;
        else if(a < 0.3)
            interval[2]++;
        else if(a < 0.4)
            interval[3]++;
        else if(a < 0.5)
            interval[4]++;
        else if(a < 0.6)
            interval[5]++;
        else if(a < 0.7)
            interval[6]++;
        else if(a < 0.8)
            interval[7]++;
        else if(a < 0.9)
            interval[8]++;
        else
            interval[9]++;
    }
    
    static void checkNumberExpo(double a, int[] interval)
    {
        if(a < 1)
            interval[0]++;
        else if(a < 2)
            interval[1]++;
        else if(a < 3)
            interval[2]++;
        else if(a < 4)
            interval[3]++;
        else if(a < 5)
            interval[4]++;
        else if(a < 6)
            interval[5]++;
        else if(a < 7)
            interval[6]++;
        else if(a < 8)
            interval[7]++;
        else if(a < 9)
            interval[8]++;
        else
            interval[9]++;
    }
    
    static void checkNumberGeo(double a, int[] interval)
    {
        if(a <= 1)
            interval[0]++;
        else if(a <= 3)
            interval[1]++;
        else if(a <= 5)
            interval[2]++;
        else if(a <= 7)
            interval[3]++;
        else if(a <= 9)
            interval[4]++;
        else if(a <= 11)
            interval[5]++;
        else if(a <= 13)
            interval[6]++;
        else if(a <= 15)
            interval[7]++;
        else if(a <= 17)
            interval[8]++;
        else
            interval[9]++;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        double aNumber = 0.0;
        double preNumber = 0.0;
        double rdNumber = 0.0;
        double expoNumber = 0.0;
        double geoNumber = 0.0;
        //BigInteger modBy = new BigInteger("4294967295");
        BigInteger modBy = new BigInteger("9223372036854775807");
        BigInteger constA = new BigInteger("69621");
        int seed = 3571;
        Vector randomNumber = new Vector();
        Vector rd = new Vector();
        Vector expoRandomNumber = new Vector();
        Vector geoRandomNumber = new Vector();
        BigDecimal modByD = new BigDecimal(modBy);
        
        BigInteger currentNumber, nextNumber;
        currentNumber = new BigInteger(String.valueOf(seed));
        
        for (int i = 0; i < 500000; i++) 
        {
            currentNumber = currentNumber.multiply(constA);
            nextNumber = currentNumber.mod(modBy);     
            //System.out.println(i + " >> " + nextNumber.toString());
            currentNumber = nextNumber;
            BigDecimal test = new BigDecimal(nextNumber);
            test = test.divide(modByD, new MathContext(10));
            aNumber = test.doubleValue();
            //System.out.println(i + " >> " + aNumber);
            checkNumber(aNumber, numberInterval);
            randomNumber.add(aNumber);
            
            expoNumber = -1 * Math.log(aNumber);
            expoRandomNumber.add(expoNumber);
            checkNumberExpo(expoNumber, expoInterval);
            
            geoNumber = Math.ceil(Math.log(aNumber) / Math.log(0.9));
            geoRandomNumber.add(geoNumber);
            checkNumberGeo(geoNumber, geoInterval);
            
            if(i > 0)
            {
                preNumber = (double) randomNumber.get(i-1);
                if(aNumber >= preNumber)
                {
                    rdNumber = aNumber - preNumber;
                }
                else
                {
                    rdNumber = (1 - preNumber) + aNumber;
                }
                rd.add(rdNumber);
                checkNumber(rdNumber, rdInterval);
            }
        }
        
        for (int i = 0; i < numberInterval.length; i++) {
            System.out.println(i + "-->" + numberInterval[i] + "--" + Math.pow(numberInterval[i] - 50000, 2) / 50000);            
        }
        System.out.println("Done -->" + randomNumber.size() + "\n");

        for (int i = 0; i < rdInterval.length; i++) {
            System.out.println(i + "-->" + rdInterval[i] + "--" + Math.pow(rdInterval[i] - 49999, 2) / 49999);            
        }
        System.out.println("Done -->" + rd.size() + "\n");
        
        for (int i = 0; i < expoInterval.length; i++) {
            System.out.println(i + "-->" + expoInterval[i]);            
        }
        System.out.println("Done -->" + expoRandomNumber.size() + "\n");
        
        for (int i = 0; i < geoInterval.length; i++) {
            System.out.println(i + "-->" + geoInterval[i]);            
        }
        System.out.println("Done -->" + geoRandomNumber.size() + "\n");
    }
}
