/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrixmultiply;

/**
 *
 * @author Salahuddin
 */
public class MatrixMultiply {

    public static double[][] multiply(double[][] A, double[][] B) {
        int mA = A.length;
        int nA = A[0].length;
        int mB = B.length;
        int nB = B[0].length;
        if (nA != mB) {
            throw new RuntimeException("Illegal matrix dimensions.");
        }
        double[][] C = new double[mA][nB];
        for (int i = 0; i < mA; i++) {
            for (int j = 0; j < nB; j++) {
                for (int k = 0; k < nA; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    public static void printMatrix(double[][] A) {
        int mA = A.length;
        int nA = A[0].length;

        for (int i = 0; i < mA; i++) {
            for (int j = 0; j < nA; j++) {
                System.out.printf("%.4f ", A[i][j]);
            }
            System.out.println("");
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        double[][] c = {{1.0/4.0, 1.0/4.0, 0, 1.0/2.0}, 
                        {1.0/3.0, 1.0/3.0, 1.0/3.0, 0}, 
                        {0, 1.0/4.0, 1.0/2.0, 1.0/4.0},
                        {1.0/2.0, 0, 1.0/4.0, 1.0/4.0}}; 
        
        double[][] a = {{0.5, 0.5, 0}, 
                        {0, 0, 1.0}, 
                        {1.0, 0, 0}};
        double[][] d = {{3.0/10.0, 1.0/5.0, 1.0/2.0}, 
                        {1.0/10.0, 4.0/5.0, 1.0/10.0}, 
                        {2.0/5.0, 2.0/5.0, 1.0/5.0}};
        
        double[][] result;
        result = d;
        System.out.println("P = ");
        printMatrix(result);
        
        for (int i = 0; i < 100; i++) 
        {
            System.out.println("P^" + (i+2) + " = ");
            result = multiply(result, d);
            printMatrix(result);
            
        }   
    }
}
