import java.lang.Thread.State;

public class Runner {

    public static void main(String[] args) {

        long t0 = System.currentTimeMillis();
        
        boolean primes[];
        long count = 0;
        long sum = 0;
        int tenMaxPrimes[] = new int[10]; 

        int limit = Integer.parseInt(args[0]);
        primes = new boolean[limit];

        for (int i = 0; i < limit; ++i) {
            primes[i] = true;
        }

        // start jobs here
        int threadc = 8;
        SieveThread threads[] = new SieveThread[threadc];
        for (int i = 0; i < threadc; ++i) {
            threads[i] = new SieveThread();
            threads[i].setValues(i+2, limit, primes);
            // System.out.println(threads[i].getState());
            threads[i].start();
        }

        // SieveThread t = new SieveThread();
        // t.setValues(2, limit, primes);
        // t.start();
        // boolean done = false;
        while (true) {
            try {
                Thread.sleep(100);
                boolean done = true;
                for (int i = 0; i < threadc; ++i) {
                    if (threads[i].getState() != State.TERMINATED)
                        done = false;
                }
                if (done) break;
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }


        for (int i = 1; i < limit; ++i) {
            if (primes[i]) {
                count++;
                sum += i+1;
            }
        }
        int temp = 0;
        int tenIndex = 0;
        for (int i = 0; i < limit; ++i) {
            if (primes[i] && count - temp < 10) {
                tenMaxPrimes[tenIndex] = i+1;
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
            System.out.printf("%10d", tenMaxPrimes[i]);
        System.out.println();
    }
}
