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
public class sizeClass {

    public static final int ZS_ALIGN = 8;
    public static final int PAGE_SHIFT = 12;
    public static final int ZS_MIN_ALLOC_SIZE = 32;
    public static final int ZS_MAX_ALLOC_SIZE = 4096;
    public static final int ZS_SIZE_CLASS_DELTA = 16;
    public static final int ZS_MAX_ZSPAGE_ORDER = 2;
    public static final int ZS_MAX_PAGES_PER_ZSPAGE = (1 << ZS_MAX_ZSPAGE_ORDER);
    public static final int ZS_SIZE = 255;

    private int size;
    private int index;
    private int pages_per_zspage;
    private ArrayList<zspage> zspageList = new ArrayList();

    sizeClass(int asize, int anindex, int a_page_per_zspage) {
        this.size = asize;
        this.index = anindex;
        this.pages_per_zspage = a_page_per_zspage;
    }

    public static int get_pages_per_zspage(int class_size) {
        int i, max_usedpc = 0;
        /* zspage order which gives maximum used size per KB */
        int max_usedpc_order = 1;

        for (i = 1; i <= ZS_MAX_PAGES_PER_ZSPAGE; i++) {
            int zspage_size;
            int waste, usedpc;

            zspage_size = i * Simulation.PAGE_SIZE;
            waste = zspage_size % class_size;
            usedpc = (zspage_size - waste) * 100 / zspage_size;

            if (usedpc > max_usedpc) {
                max_usedpc = usedpc;
                max_usedpc_order = i;
            }
        }

        return max_usedpc_order;
    }

    public static int get_maxobj_per_zspage(int size, int pages_per_zspage) {
        return pages_per_zspage * Simulation.PAGE_SIZE / size;
    }

    public static boolean can_merge(sizeClass prev, int size, int pages_per_zspage) {
        if (prev.getPages_per_zspage() != pages_per_zspage) {
            return false;
        }

        if (get_maxobj_per_zspage(prev.getSize(), prev.getPages_per_zspage())
                != get_maxobj_per_zspage(size, pages_per_zspage)) {
            return false;
        }

        return true;
    }

    public static int get_size_class_index(int size) {
        int idx = 0;
        idx = (int) Math.ceil((size - ZS_MIN_ALLOC_SIZE) / ZS_SIZE_CLASS_DELTA);
        return idx;
    }

    public int storeZpage(zpage aZpage, boolean fullness) {
        int allocatedSize = 0;
        if (zspageList.isEmpty()) {
            zspage azspage = new zspage(this.pages_per_zspage * Simulation.PAGE_SIZE / this.size);
            azspage.storeZpage(aZpage);
            zspageList.add(azspage);
            allocatedSize = this.pages_per_zspage * Simulation.PAGE_SIZE;
        } else {
            zspage azspage = zspageList.get(zspageList.size() - 1);
            if (azspage.isFull()) {
                if (fullness) {
                    allocatedSize = -1;
                } else {
                    zspage anotherzspage = new zspage(this.pages_per_zspage * Simulation.PAGE_SIZE / this.size);
                    anotherzspage.storeZpage(aZpage);
                    zspageList.add(anotherzspage);
                    allocatedSize = this.pages_per_zspage * Simulation.PAGE_SIZE;
                }
            } else {
                azspage.addObject(aZpage);
            }
        }
        return allocatedSize;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @return the pages_per_zspage
     */
    public int getPages_per_zspage() {
        return pages_per_zspage;
    }

    /**
     * @param pages_per_zspage the pages_per_zspage to set
     */
    public void setPages_per_zspage(int pages_per_zspage) {
        this.pages_per_zspage = pages_per_zspage;
    }
}
