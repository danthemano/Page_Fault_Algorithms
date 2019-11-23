package company;

public class Main {

    public static void main(String[] args)
    {
        Algorithm a = new Algorithm();
        //Status: Complete
        //Necessary Value: 17
        //Actual Value: 17
        System.out.println("FIFO");
        a.FIFO();
        System.out.println(a.getPageFaults());

        //Status: Fixing issues where LRU isn't recognizing a couple of page faults
        //Necessary value: 18
        //Actual returned: 15
        System.out.println("LRU");
        a.LRU();
        System.out.println(a.getPageFaults());
        
        //Status Complete
        //Necessary Value: 13
        //Actual Value: 13
        System.out.println("Optimal");
        a.Optimal();
        System.out.println(a.getPageFaults());
    }
}
