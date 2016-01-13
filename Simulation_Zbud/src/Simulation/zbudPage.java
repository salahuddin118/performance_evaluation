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
public class zbudPage implements Comparable<zbudPage>{
    private int size = Simulation.PAGE_SIZE;
    private int leftBuddySize;
    private int rightBuddySize;
    private int wastage;
    private int slackSpace;
    private boolean hasLeftBuddy;
    private boolean hasRightBuddy;
    
    /**
     * @return the size
     */
    public int getSize() {
        return Simulation.PAGE_SIZE;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return the leftBuddySize
     */
    public int getLeftBuddySize() {
        return leftBuddySize;
    }

    /**
     * @param leftBuddySize the leftBuddySize to set
     */
    public void setLeftBuddySize(int leftBuddySize) {
        this.leftBuddySize = leftBuddySize;
        this.hasLeftBuddy = true;
        this.slackSpace = size - leftBuddySize;
    }
    
    public void setLeftBuddy(zpage leftBuddy) {
        this.leftBuddySize = leftBuddy.getSize();
        this.hasLeftBuddy = true;
        this.slackSpace = size - leftBuddy.getSize();
    }

    /**
     * @return the rightBuddySize
     */
    public int getRightBuddySize() {
        return rightBuddySize;
    }

    /**
     * @param rightBuddySize the rightBuddySize to set
     */
    public void setRightBuddySize(int rightBuddySize) {
        this.rightBuddySize = rightBuddySize;
        this.hasRightBuddy = true;
        this.slackSpace -= rightBuddySize;
        this.wastage = this.slackSpace;
    }
    
    public void setRightBuddy(zpage rightBuddy) {
        this.rightBuddySize = rightBuddy.getSize();
        this.hasRightBuddy = true;
        this.slackSpace -= rightBuddy.getSize();
        this.wastage = this.slackSpace;
    }

    /**
     * @return the wastage
     */
    public int getWastage() {
        return wastage;
    }

    /**
     * @return the slackSpace
     */
    public int getSlackSpace() {
        return slackSpace;
    }

    /**
     * @return the hasLeftBuddy
     */
    public boolean isHasLeftBuddy() {
        return hasLeftBuddy;
    }

    /**
     * @param hasLeftBuddy the hasLeftBuddy to set
     */
    public void setHasLeftBuddy(boolean hasLeftBuddy) {
        this.hasLeftBuddy = hasLeftBuddy;
    }

    /**
     * @return the hasRightBuddy
     */
    public boolean isHasRightBuddy() {
        return hasRightBuddy;
    }

    /**
     * @param hasRightBuddy the hasRightBuddy to set
     */
    public void setHasRightBuddy(boolean hasRightBuddy) {
        this.hasRightBuddy = hasRightBuddy;
    }

    @Override
    public int compareTo(zbudPage o) {
        if(o.getSlackSpace() > getSlackSpace())
            return o.getSlackSpace();
        else
            return getSlackSpace();
    }  
}
