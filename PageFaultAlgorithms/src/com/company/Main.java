package pagefault;

public class Main {

    public static void main(String[] args)
    {
        Algorithm a = new Algorithm();
        //Status: Complete
        System.out.println("FIFO");
        a.FIFO();
//        System.out.println(a.getPageFaults());
//        
//        //TODO fix implementation on LRU
        System.out.println("LRU");
        a.LRU();
//        System.out.println(a.getPageFaults());
        
        //TODO fix implementation on Optimal
        System.out.println("Optimal");
        a.Optimal();
//        System.out.println(a.getPageFaults());
    }
}
