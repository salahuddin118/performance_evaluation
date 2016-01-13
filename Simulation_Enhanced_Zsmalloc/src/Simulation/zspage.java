/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulation;

import java.util.ArrayList;

/**
 *
 * @author Salahuddin
 */
public class zspage {

    private int capacity;
    private int objectCount;
    private ArrayList<zpage> zpageList = new ArrayList();

    zspage(int aCapacity) {
        this.capacity = aCapacity;
        this.objectCount = 0;
    }

    /**
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * @return the objectCount
     */
    public int getObjectCount() {
        return objectCount;
    }

    /**
     * @param objectCount the objectCount to set
     */
    public void setObjectCount(int objectCount) {
        this.objectCount = objectCount;
    }

    public void storeZpage(zpage aZpage) {
        zpageList.add(aZpage);
        objectCount++;
    }

    public void addObject(zpage aZpage) {
        if (objectCount < capacity) {
            zpageList.add(aZpage);
            objectCount++;
        } else {
            System.out.println("Full");
        }
    }

    public boolean isFull() {
        return (objectCount == capacity);
    }
}
