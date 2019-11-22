package company;

public class Main {

    public static void main(String[] args)
    {
        Algorithm a = new Algorithm();
        a.FIFO();
        int pgFaults = a.getPageFaults();
        System.out.println(pgFaults);
    }
}
