package com.company;

import java.util.*;

public class Algorithm
{
    ArrayList<Integer> pgTable =  new ArrayList<Integer>(30);
    ArrayList<Integer> pageRefString = new ArrayList<Integer>(100);
    int pageFaults;

    Random rand = new Random();
    public Algorithm()
    {
        for(int i = 0; i < 100; i++)
            pageRefString.add(rand.nextInt(50));
        pageFaults = 0;
    }
    public void resetPageTable()
    {
        pgTable.clear();
        for(int j = 0; j < 30; j++)
        {
            pgTable.add(-1);
        }
    }
    public void FIFO()
    {
        resetPageTable();
        int firstIn = 0;
        boolean isInTable = false;
        //clears the page table upon calling the FIFO algorithm
        for(int i = 0; i < 100; i++) {
            isInTable = false;
            for (Integer x : pgTable) {
                //If found in the table continue loop, no eviction happens
                if (pageRefString.get(i) == x) {
                    isInTable = true;
                    break;
                }
            }
            if (!isInTable)
            {
                pgTable.set(firstIn, pageRefString.get(i));
                firstIn = (firstIn + 1) % 30;
                pageFaults++;
            }
        }
    }
    public int getPageFaults()
    {
        return pageFaults;
    }
    public void LRU()
    {
        resetPageTable();
        
    }


}
