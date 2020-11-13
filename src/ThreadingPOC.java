import java.util.ArrayList;
import java.util.List;

public class ThreadingPOC
{
    private int sum;
    private int sumAmount  = 1000*1000;
    private int numThreads = 10;

    public void sumRaceCondition() throws InterruptedException {
        sum = 0;

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < numThreads; i++ ) {
            Thread t = new Thread(() -> {
                for ( int j = 0; j < sumAmount/numThreads; j ++ )
                {
                    // Race condition, needs a lock
                    sum += 1;
                }
            });
            threads.add(t);
        }

        for ( Thread t : threads ) t.start();
        for ( Thread t : threads ) t.join();

        System.out.println("Sum race condition result: " + sum + " expected " + sumAmount );
    }

    synchronized void incrementSum()
    {
        sum += 1;
    }

    public void sumAtomically() throws InterruptedException {
        sum = 0;

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < numThreads; i++ ) {
            Thread t = new Thread(() -> {
                for ( int j = 0; j < sumAmount/numThreads; j ++ )
                {
                    incrementSum();
                }
            });
            threads.add(t);
        }
        for ( Thread t : threads )  t.start();
        for ( Thread t : threads )  t.join();

        System.out.println("Sum atomic result: " + sum + " expected " + sumAmount );
    }
}

