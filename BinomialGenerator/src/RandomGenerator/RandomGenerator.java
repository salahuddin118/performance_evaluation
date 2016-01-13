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
    static int bioInterval[] = new int[10];
    static double bioValues[] = new double[21];
    static int normalInterval[] = new int[10];
    
    
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
    
    static void checkNumberBio(int a, int[] interval)
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
    
    static void checkNumberNormal(double a, int[] interval)
    {
        if(a < -1)
            interval[0]++;
        else if(a < -0.5)
            interval[1]++;
        else if(a < 0.0)
            interval[2]++;
        else if(a < 0.5)
            interval[3]++;
        else if(a < 1.0)
            interval[4]++;
        else if(a < 1.5)
            interval[5]++;
        else if(a < 2.0)
            interval[6]++;
        else if(a < 2.5)
            interval[7]++;
        else if(a < 3.0)
            interval[8]++;
        else
            interval[9]++;
    }
    
    static BigInteger binomial(final int N, final int K) 
    {
        BigInteger ret = BigInteger.ONE;
        for (int k = 0; k < K; k++) {
            ret = ret.multiply(BigInteger.valueOf(N - k))
                    .divide(BigInteger.valueOf(k + 1));
        }
        return ret;
    }
    
    static double perComponent(int i)
    {
        double value;
        
        value = binomial(20, i).intValue() * Math.pow(0.3, i) * Math.pow(0.7, 20 - i);
        
        return  value;
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
        BigInteger modBy = new BigInteger("92233720368547758079183");
        BigInteger constA = new BigInteger("69621");
        int seed = 3571;
        Vector randomNumber = new Vector();
        Vector rd = new Vector();
        Vector expoRandomNumber = new Vector();
        Vector geoRandomNumber = new Vector();
        Vector normalRandomNumber = new Vector();
        BigDecimal modByD = new BigDecimal(modBy);
        
        BigInteger currentNumber, nextNumber;
        currentNumber = new BigInteger(String.valueOf(seed));
        
        for (int i = 0; i < 2100000; i++) 
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
        
        /*
        for (int i = 0; i < numberInterval.length; i++) {
            System.out.println(i + " - " + numberInterval[i] + " - " + Math.pow(numberInterval[i] - 50000, 2) / 50000);            
        }
        System.out.println("Done -->" + randomNumber.size() + "\n");

        for (int i = 0; i < rdInterval.length; i++) {
            System.out.println(i + " - " + rdInterval[i] + " - " + Math.pow(rdInterval[i] - 49999, 2) / 49999);            
        }
        System.out.println("Done -->" + rd.size() + "\n");
        
        for (int i = 0; i < expoInterval.length; i++) {
            System.out.println(i + " - " + expoInterval[i]);            
        }
        System.out.println("Done -->" + expoRandomNumber.size() + "\n");
        
        for (int i = 0; i < geoInterval.length; i++) {
            System.out.println(i + " - " + geoInterval[i]);            
        }
        System.out.println("Done -->" + geoRandomNumber.size() + "\n");
        */
        /*
        double fx = 0.0;
        for (int i = 0; i < 21; i++) 
        {
            fx += perComponent(i);
            bioValues[i] = fx;
            System.out.println(" > " + fx );
        }
        
        for (int i = 0; i < randomNumber.size(); i++) 
        {
            double uniformRandomNumber = (double) randomNumber.elementAt(i);
            int j = 0;
            for ( j = 0; j < bioValues.length; j++) 
            {
                if(uniformRandomNumber < bioValues[j])
                    break;                
            }
            checkNumberBio(j, bioInterval);
        }
        
        for (int i = 0; i < bioInterval.length; i++) {
            System.out.println(i + " - " + bioInterval[i]);            
        }
                */
        
        int totalNumber = 0;
        int index = 0;
        double normalNumber = 0.0;
        
        while(totalNumber < 500000)
        {
            double value;
            
            value = Math.pow(Math.E, (-1 * Math.pow((double)expoRandomNumber.elementAt(index), 2)/2));
            if(((double)randomNumber.elementAt(index+1)) > value)
            {
                index += 2;
                continue;
            }
            else
            {
                if(((double)randomNumber.elementAt(index+2)) > 0.5)
                    normalNumber = 1 + (double)expoRandomNumber.elementAt(index);
                else
                    normalNumber = 1 - (double)expoRandomNumber.elementAt(index);
                index+=3;
            }
            
            normalRandomNumber.add(normalNumber);
            checkNumberNormal(normalNumber, normalInterval);
            //System.out.println(" > " + normalNumber );
            totalNumber++;
        }
        
         for (int i = 0; i < normalInterval.length; i++) {
            System.out.println(i + " - " + normalInterval[i]);            
        }
        System.out.println("index >" + index);
        
    }
}
