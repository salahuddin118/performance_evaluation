/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAAlgorithm;

import java.awt.Point;

/**
 *
 * @author Salahuddin
 */
public class PAAlgorithm {

    static Point b1 = new Point();
    static Point b2 = new Point();
    static Point b3 = new Point();
    static Point t1 = new Point();
    static Point t2 = new Point();
    static double angle1, angle2, angle3, distance1, distance2, distance3;
    static double beaconPower[][] = new double[3][2];
    static double finalPower[] = new double[3];

    public static void configure() {
        b1.setLocation(0, 0);
        b2.setLocation(10, 0);
        b3.setLocation(0, 10);
        t1.setLocation(Math.random() * 10.0, Math.random() * 10.0);
        t2.setLocation(Math.random() * 10.0, Math.random() * 10.0);

        System.out.println(">> " + b1.toString().substring(9));
        System.out.println(">> " + b2.toString().substring(9));
        System.out.println(">> " + b3.toString().substring(9));
        System.out.println(">> " + t1.toString().substring(9));
        System.out.println(">> " + t2.toString().substring(9));
    }

    public static void preprocessing(Point p) {
        angle1 = calcRotationAngleInDegrees(p, b1);
        System.out.println("Angle t1 - b1: " + angle1);
        angle2 = calcRotationAngleInDegrees(p, b2);
        System.out.println("Angle t1 - b2: " + angle2);
        angle3 = calcRotationAngleInDegrees(p, b3);
        System.out.println("Angle t1 - b3: " + angle3);

        distance1 = p.distance(b1);
        System.out.println("Distance t1 - b1: " + distance1);
        distance2 = p.distance(b2);
        System.out.println("Distance t1 - b2: " + distance2);
        distance3 = p.distance(b3);
        System.out.println("Distance t1 - b3: " + distance3);
    }

    public static void algorithmPhase1() {
        // Phase 1
        // Calculate power for each pair (b1-b2, b2-b3, b1-b3)
        // For b1-b2
        beaconPower[0][0] = distance1 / (distance1 + distance2);
        beaconPower[1][0] = distance2 / (distance1 + distance2);

        // For b2-b3
        beaconPower[1][1] = distance2 / (distance2 + distance3);
        beaconPower[2][0] = distance3 / (distance2 + distance3);

        // For b1-b3
        beaconPower[0][1] = distance1 / (distance1 + distance3);
        beaconPower[2][1] = distance3 / (distance1 + distance3);
    }

    public static void algorithmPhase2() {
        // Phase 2
        double sum = 0.0;
        for (int i = 0; i < beaconPower.length; i++) {
            double temp = beaconPower[i][0];
            for (int j = 0; j < beaconPower[0].length; j++) {
                if (beaconPower[i][j] > temp) {
                    temp = beaconPower[i][j];
                }
                System.out.println("[" + i + "," + j + "] = " + beaconPower[i][j]);
            }
            finalPower[i] = temp;
            sum += temp;
        }

        System.out.println("Selecting among multiple allocated power");
        for (int i = 0; i < finalPower.length; i++) {
            System.out.println("Power " + i + ": " + finalPower[i]);
        }

        System.out.println("Scale to fit total power");
        for (int i = 0; i < finalPower.length; i++) {
            finalPower[i] /= sum;
            System.out.println("Power " + i + ": " + finalPower[i]);
        }
    }
    
    public static void calculateLikelihoodArea() {
        double theta1 = Math.toRadians(angle1);
        double theta2 = Math.toRadians(angle2);
        double theta3 = Math.toRadians(angle3);
        double area, area1, area2, area3;
        
        // For uniform distribution all beacon power is 0.333
        area = ((Math.sin(theta1)/Math.sqrt(0.333)) + (Math.sin(theta2)/Math.sqrt(0.333)))
                * ((Math.cos(theta1)/Math.sqrt(0.333)) + (Math.cos(theta2)/Math.sqrt(0.333)));
        if(area < 0)
            area = -area;
        System.out.println("Area (U): " + area);
        // for 0-1
        area1 = ((Math.sin(theta1)/Math.sqrt(finalPower[0])) + (Math.sin(theta2)/Math.sqrt(finalPower[1])))
                * ((Math.cos(theta1)/Math.sqrt(finalPower[0])) + (Math.cos(theta2)/Math.sqrt(finalPower[1])));
        if(area1 < 0)
            area1 = -area1;
//        System.out.println("Area1: " + area1);
        // for 1-2
        area2 = ((Math.sin(theta2)/Math.sqrt(finalPower[1])) + (Math.sin(theta3)/Math.sqrt(finalPower[2])))
                * ((Math.cos(theta2)/Math.sqrt(finalPower[1])) + (Math.cos(theta3)/Math.sqrt(finalPower[2])));
        if(area2 < 0)
            area2 = -area2;
//        System.out.println("Area2: " + area2);
        // for 0-2
        area3 = ((Math.sin(theta1)/Math.sqrt(finalPower[0])) + (Math.sin(theta3)/Math.sqrt(finalPower[2])))
                * ((Math.cos(theta1)/Math.sqrt(finalPower[0])) + (Math.cos(theta3)/Math.sqrt(finalPower[2])));
        if(area3 < 0)
            area3 = -area3;
//        System.out.println("Area3: " + area3); 
        if(area1 < area2)
        {
            if(area1 < area3)
                System.out.println("PA Area: " + area1);
            else 
                System.out.println("PA Area: " + area3);
        }
        else
        {
            if(area2 < area3)
                System.out.println("PA Area: " + area2);
            else
                System.out.println("PA Area: " + area3);
        }
    }
    
    public static void PAalgorithm(Point p) {
        preprocessing(p);
        algorithmPhase1();
        algorithmPhase2();
        calculateLikelihoodArea();
        System.out.println(" ");
    }

    /**
     * Calculates the angle from centerPt to targetPt in degrees. The return
     * should range from [0,360), rotating CLOCKWISE, 0 and 360 degrees
     * represents NORTH, 90 degrees represents EAST, etc...
     *
     * Assumes all points are in the same coordinate space. If they are not, you
     * will need to call SwingUtilities.convertPointToScreen or equivalent on
     * all arguments before passing them to this function.
     *
     * @param centerPt Point we are rotating around.
     * @param targetPt Point we want to calculate the angle to.
     * @return angle in degrees. This is the angle from centerPt to targetPt.
     */
    public static double calcRotationAngleInDegrees(Point centerPt, Point targetPt) {
        // calculate the angle theta from the deltaY and deltaX values
        // (atan2 returns radians values from [-PI,PI])
        // 0 currently points EAST.  
        // NOTE: By preserving Y and X param order to atan2,  we are expecting 
        // a CLOCKWISE angle direction.  
        double theta = Math.atan2(targetPt.y - centerPt.y, targetPt.x - centerPt.x);

        // rotate the theta angle clockwise by 90 degrees 
        // (this makes 0 point NORTH)
        // NOTE: adding to an angle rotates it clockwise.  
        // subtracting would rotate it counter-clockwise
        theta += Math.PI / 2.0;

        // convert from radians to degrees
        // this will give you an angle from [0->270],[-180,0]
        double angle = Math.toDegrees(theta);

        // convert to positive range [0-360)
        // since we want to prevent negative angles, adjust them now.
        // we can assume that atan2 will not return a negative value
        // greater than one partial rotation
        if (angle < 0) {
            angle += 360;
        }

        return angle;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        configure();
        PAalgorithm(t1);
        PAalgorithm(t2);
    }
}
