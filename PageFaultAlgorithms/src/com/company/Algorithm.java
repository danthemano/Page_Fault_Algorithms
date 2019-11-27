package pagefault;

import java.util.*;

public class Algorithm {
	ArrayList<Integer> pgTable = new ArrayList<Integer>(30);
	ArrayList<Integer> pageRefString = new ArrayList<Integer>(100);
	boolean isInTable = false;
	int pageFaults;

	Random rand = new Random();

	public Algorithm() {
		for (int i = 0; i < 100; i++)
			pageRefString.add(rand.nextInt(50));
		// Testing code against homework
//		int[] hwArray = new int[] { 7, 2, 3, 1, 2, 5, 3, 4, 6, 7, 7, 1, 0, 5, 4, 6, 2, 3, 0, 1 };
//		for (int i = 0; i < hwArray.length; i++)
//			pageRefString.add(hwArray[i]);
		pageFaults = 0;
	}

	public void resetPageTable() {
		pgTable.clear();
	}

	public void FIFO() {
		// variable to keep track of what will exist next
		int firstIn = 0;

		// Testing variable against homework
//		int capacity = 3;
		for (int capacity = 1; capacity <= 30; capacity++) {
		resetPageTable();
		pageFaults = 0;
		// clears the page table upon calling the FIFO algorithm
		for (int i = 0; i < pageRefString.size(); i++) {
			// Checking if the page table contains the item from the reference string
			if (!pgTable.contains(pageRefString.get(i))) {
				// if the table is not full, then the item will be appended to the end
				if (pgTable.size() < capacity)
					pgTable.add(pageRefString.get(i));

				// In the case that the table is at capacity
				else {
					// setting the next item to the first in to be replaced
					pgTable.set(firstIn, pageRefString.get(i));

					// incrementing the next to be replaced
					firstIn = (firstIn + 1) % capacity;
				}
				// incrementing the number of page faults
				pageFaults++;
			}
		}

			System.out.println("Capaacity: " + capacity + " PageFaults:" + getPageFaults());
		}
	}

	public void LRU() {
		// Code for testing against homework
//		int capacity = 3;
		for (int capacity = 1; capacity <= 30; capacity++) {
		resetPageTable();
		pageFaults = 0;

		for (int i = 0; i < pageRefString.size(); i++) {
			int valueToReplace = 0, previous = 0;
			// Testing variable to see current item
			int currentValue = pageRefString.get(i);

			// variable to hold the value that needs to be replaced and the least
			// recently used variable.

			// if the item is not contained already in the table
			if (!pgTable.contains(pageRefString.get(i))) {
//        		if(pgTable.size()<=capacity) {
//        			pageFaults++;
//        			pgTable.add(pageRefString.get(i));
//        		}
				// incrementing the number of page faults
				pageFaults++;
				// if the table can accommodate more entries, then it will be appended and
				// will continue to look at the next item in the string
				if (pgTable.size() < capacity) {
					pgTable.add(pageRefString.get(i));
					continue;
				}
				// Going through each item currently in the pgTable.
				for (int j = 0; j < pgTable.size(); j++) {

					// setting a counter to keep track of how recent an item was used in the table
					int counter = 0;
					// setting x to the page table size as we will be going backwards on the
					// reference string to see when a register was used
					int x = i;

					// While we have not reached the end of the table
					while (x >= 0) {
						// incrementing the counter per each iteration
						counter++;
						
						// If we find an instance where we find that an register was used in the
						// reference string
						if (pgTable.get(j) == pageRefString.get(x)) {
							if (counter > previous) {
								// if the counter of the string is greater than that of the previous
								// then set the previous to the counter as it is the least recently used.
								previous = counter;
								// holding the value so we know what value to look for to replace
								valueToReplace = pgTable.get(j);
								break;
							}
							break;
						}
						// If we reach the end of the list and have found nothing greater then previous
						// then we'll just set
						// the previous to the current counter.
						if (x == 0 && counter > previous) {
							previous = counter;
							valueToReplace = pgTable.get(j);
						}
						x--;
					}
				}

				for (int k = 0; k < pgTable.size(); k++) {
					if (pgTable.get(k) == valueToReplace) {
						pgTable.set(k, pageRefString.get(i));
						break;
					}
				}
			}
		}
			System.out.println("Capacity: " + capacity + " PageFaults:" + getPageFaults());
		}
	}

	public void Optimal() {
		// Code for testing against homework
//		int capacity = 3;
		for (int capacity = 1; capacity <= 30; capacity++) {
		resetPageTable();
		pageFaults = 0;

		for (int i = 0; i < pageRefString.size(); i++) {

			// if the current item in the page reference string is not in the page table
			if (!pgTable.contains(pageRefString.get(i))) {
				pageFaults++;
				if (pgTable.size() < capacity) {
					pgTable.add(pageRefString.get(i));
				}
				// counters to find which is the latest to be used
				int latest = -1, valueToReplace = -1;

				// going through each item and each item in the reference string
				for (int j = 0; j < pgTable.size(); j++) {
					int counter = 0;

					for (int x = i; x < pageRefString.size(); x++) {
						counter++;
						// if the number is found then
						if (pgTable.get(j) == pageRefString.get(x)) {
							if (counter > latest) {
								latest = counter;
								valueToReplace = pgTable.get(j);
								break;
							}
							break;
						}

						else if (x + 1 == pageRefString.size()) {
							latest = counter;
							valueToReplace = pgTable.get(j);
							break;
						}
					}
				}

				for (int z = 0; z < pgTable.size(); z++) {
					if (pgTable.get(z) == valueToReplace) {
						pgTable.set(z, pageRefString.get(i));
						break;
					}
				}
			}
		}
			System.out.println("Capacity: " + capacity + " PageFaults:" + getPageFaults());
		}
	}

	public int getPageFaults() {
		return pageFaults;
	}
}
