/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulation;

import static Simulation.sizeClass.ZS_MAX_ALLOC_SIZE;
import static Simulation.sizeClass.ZS_MIN_ALLOC_SIZE;
import static Simulation.sizeClass.ZS_SIZE_CLASS_DELTA;
import java.util.ArrayList;

/**
 *
 * @author Salahuddin
 */
public class zsmalloc {

    // 20MB pool size
    int maxSize = 20 * 1024 * 1024;
    int currentSize = 0;
    int storedPageCount = 0;
    ArrayList<sizeClass> memoryPool = new ArrayList(255);
    private static zsmalloc instance = null;

    /**
     * @return the instance
     */
    public static zsmalloc getInstance() {
        if (instance == null) {
            instance = new zsmalloc();
            instance.configure();
        }
        return instance;
    }

    public void evict() {
        // Not implemented
        int i = 0;

        i++;
        System.out.println("Density - " + density());
    }

    public ArrayList<sizeClass> reverse(ArrayList<sizeClass> list) {
        if (list.size() > 1) {
            sizeClass value = list.remove(0);
            reverse(list);
            list.add(value);
        }
        return list;
    }

    public void configure() {
        int i;
        int zs_size_classes = 255;
        int unique = 1;

        for (i = zs_size_classes - 1; i >= 0; i--) {
            int size;
            int pages_per_zspage;

            size = ZS_MIN_ALLOC_SIZE + i * ZS_SIZE_CLASS_DELTA;
            if (size > ZS_MAX_ALLOC_SIZE) {
                size = ZS_MAX_ALLOC_SIZE;
            }

            pages_per_zspage = sizeClass.get_pages_per_zspage(size);

            sizeClass asizeClass = new sizeClass(size, i, pages_per_zspage);
            memoryPool.add(asizeClass);
        }

        memoryPool = reverse(memoryPool);
        for (i = zs_size_classes - 1; i > 0; i--) {
            int size;
            int pages_per_zspage;

            size = ZS_MIN_ALLOC_SIZE + i * ZS_SIZE_CLASS_DELTA;
            if (size > ZS_MAX_ALLOC_SIZE) {
                size = ZS_MAX_ALLOC_SIZE;
            }

            pages_per_zspage = sizeClass.get_pages_per_zspage(size);
            if (sizeClass.can_merge(memoryPool.get(i - 1), size, pages_per_zspage)) {
                memoryPool.get(i - 1).setSize(memoryPool.get(i).getSize());
            } else {
                unique++;
            }
        }
        //System.out.println("U = " + unique);
    }

    public int storedPageCount() {
        return storedPageCount;//fullList.size() * 2 + halfEmptyList.size();
    }

    public double density() {
        return storedPageCount() * 1024 / (currentSize / (1024));
    }

    public void storePage(zpage aZpage) {
        int classIndex = 0;
        sizeClass targetClass;
        boolean fullness = false;
        int temp = 0;

        if (currentSize >= maxSize) {
            fullness = true;
        }

        classIndex = sizeClass.get_size_class_index(aZpage.getSize());
        //System.out.println("Old - " + classIndex);
        for (int i = classIndex; i > 0; i--) {

            if (memoryPool.get(i - 1).getSize() != memoryPool.get(i).getSize()) {
                classIndex = i;
                break;
            }
        }
        //System.out.println("New - " + classIndex);
        try {
            targetClass = memoryPool.get(classIndex);

            temp = targetClass.storeZpage(aZpage, fullness);
            if (temp != -1) {
                currentSize += temp;
                storedPageCount++;
            }

        } catch (Exception e) {
        }
    }
}
