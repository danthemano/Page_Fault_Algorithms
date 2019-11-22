package pagefault;

import java.util.*;

public class Algorithm
{
    ArrayList<Integer> pgTable =  new ArrayList<Integer>(30);
    ArrayList<Integer> pageRefString = new ArrayList<Integer>(100);
    boolean isInTable = false;
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
        for(int j = 0; j < 30; j++) pgTable.add(-1);
    }
    public void FIFO()
    {
        resetPageTable();
        pageFaults = 0;
        int firstIn = 0;
        isInTable = false;
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
    public void LRU()
    {   
        resetPageTable();
    	//Boolean to check if the value is the table
        isInTable = false;
        pageFaults = 0;
        //counter to keep track of priority
        int counter = 0;
        
        int leastPriorityIndex = 0;
        int leastPriority = 0;
        //instantiating LRU table
        ArrayList<LRU> LRUpgTable = new ArrayList<LRU>();
        
        //Filling in the LRU table with dummy values
        for(int j = 0; j < 30; j++) LRUpgTable.add(new LRU(-1, -1));
        
        for(int i = 0; i < 100; i++){
        	isInTable = false;
        	
        	for (LRU c : LRUpgTable) {
        		if(c.getValue() == pageRefString.get(i)) {
        			isInTable = true;
        		    c.setPriority(counter++);
        			break;
        		}
        	}
        	
        	//if the page is not in the table
        	if(!isInTable) 
        	{
        		//resets the variables to search for the element with the least priority
        		leastPriorityIndex = 0;
        		leastPriority= 0;
        		
        		//this loop finds the index of the page is the table that was least recently used
        		for(int j = 0; j < 30; j++) 
        		{
        			if(LRUpgTable.get(j).getPriority() >=0 && 
        			   LRUpgTable.get(j).getPriority() < leastPriority)
        			{
        				leastPriorityIndex = j;
        				leastPriority = LRUpgTable.get(j).getPriority();
        			}
        		}
        		
        		//Setting the frame to an item with new information from the page ref string.
        		LRUpgTable.set(leastPriorityIndex, new LRU(pageRefString.get(i),counter++));
        		
        		//incrementing the amount of page faults
        		pageFaults++;
        	}
        }
    }
    
    public void Optimal() {
    	resetPageTable();
        pageFaults = 0;
    	//Boolean to check if the value is the table
        isInTable = false;
        
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
            	int late = -1, indexReplace=0,count=0;
            	for(int j = 0; j < 30; j++) {
            		for(int k = i+1; k < 100; k++){
            			if(pgTable.get(j) == pageRefString.get(i)&&pgTable.get(i) != -1)
            				late = k-i;
            		}
            		
            		if(late>=indexReplace)
            			indexReplace = j;
            		else indexReplace = count;
            		
            		count++;
            	}
            	
                pgTable.set(indexReplace, pageRefString.get(i));
                pageFaults++;
            }
        }
    }

    public int getPageFaults()
    {
        return pageFaults;
    }
}
