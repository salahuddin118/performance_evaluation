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
public class zbud {
    // 20MB pool size
    int maxSize = 20 * 1024 * 1024;
    int currentSize = 0;
    ArrayList fullList = new ArrayList();
    ArrayList<zbudPage> halfEmptyList = new ArrayList<>();
    private static zbud instance = null;

    /**
     * @return the instance
     */
    public static zbud getInstance() {
        if(instance == null)
            instance = new zbud();
        return instance;
    }
    
    public void evict()
    {
        //System.out.println("Density - " + density());
        if(!fullList.isEmpty())
        {
            fullList.remove(0);
            currentSize -= Simulation.PAGE_SIZE;
        }
    }
    
    public void addToHalfList(zbudPage aZbudPage)
    {
        boolean addLast = true;
        int i = 0;
        for (zbudPage zbudpage1 : halfEmptyList) 
        {
            if(aZbudPage.getSlackSpace() < zbudpage1.getSlackSpace())
            {
                halfEmptyList.add(i, aZbudPage);
                addLast = false;
                break;
            }
            i++;
        }
        if(addLast)
            halfEmptyList.add(aZbudPage);
    }
    
    public int storedPageCount()
    {
        return fullList.size() * 2 + halfEmptyList.size();
    }
    
    public double density()
    {
        return storedPageCount()/(currentSize/ (1024 * 1024));
    }
    
    public void storePage(zpage aZpage)
    {
        if(halfEmptyList.isEmpty())
        {
            // Allocate new one if possible
            if(currentSize < maxSize)
            {
                zbudPage aZbudPage = new zbudPage();
                aZbudPage.setLeftBuddy(aZpage);
                addToHalfList(aZbudPage);
                currentSize += Simulation.PAGE_SIZE;
            }
            else //evict
            {
                evict();
                zbudPage aZbudPage = new zbudPage();
                aZbudPage.setLeftBuddy(aZpage);
                addToHalfList(aZbudPage);
                currentSize += Simulation.PAGE_SIZE;
            }
        }
        else
        {
            boolean noSuitableZbudPage = true;
            for (zbudPage zbudpage1 : halfEmptyList) 
            {
                // fit
                if(zbudpage1.getSlackSpace() > aZpage.getSize())
                {
                    zbudpage1.setRightBuddy(aZpage);
                    fullList.add(zbudpage1);
                    halfEmptyList.remove(zbudpage1);
                    noSuitableZbudPage = false;
                    break;
                }
            }
            
            if(noSuitableZbudPage)
            {
                if(currentSize == maxSize)
                {
                    evict();
                }
                zbudPage aZbudPage = new zbudPage();
                aZbudPage.setLeftBuddy(aZpage);
                addToHalfList(aZbudPage);
                currentSize += Simulation.PAGE_SIZE;
            }
        }
    }
}
