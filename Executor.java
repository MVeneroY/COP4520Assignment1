// Miguel Venero Yupanqui
// Assignment 1
// Professor Parra
// COP4520
// January 27, 2023

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Executor {
  
    static boolean primes[];

    public static void main(String[] args) {
        long t0 = System.currentTimeMillis();

        if (args.length == 0) return;
        int limit = Integer.parseInt(args[0]);

        primes = new boolean[limit];
        for (int i = 0; i < limit; ++i) {
            primes[i] = true;
        }
        
        ExecutorService executorService = Executors.newFixedThreadPool(8);

        for (int i = 2; i < (int) Math.sqrt(limit)+1; ++i) {
            Runnable runnable = new SieveRunnable(i, limit, primes);
            executorService.execute(runnable);
        }
        executorService.shutdown();

        while (!executorService.isTerminated()) { 
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }

        long count = 0;
        long sum = 0;
        for (int i = 1; i < limit; ++i) {
            if (primes[i]) {
                count++;
                sum += i+1;
            }
        }

        int maxTenPrimes[] = new int[10];
        int temp = 0;
        int tenIndex = 0;
        for (int i = 0; i < limit; ++i) {
            if (primes[i] && count - temp < 10) {
                maxTenPrimes[tenIndex] = i+1;
                tenIndex++;
            }
            if (primes[i]) {
                temp++;
            }
        }

        long t1 = System.currentTimeMillis();

        System.out.printf("execution time: %f\n", (t1-t0) / 1000.0);
        System.out.printf("%d primes found\n", count);
        System.out.printf("sum of all primes found: %d\n", sum);

        for (int i = 0; i < 10; ++i)
            System.out.printf("%10d", maxTenPrimes[i]);
        System.out.println();
    }
}
