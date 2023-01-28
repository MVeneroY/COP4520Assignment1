// Miguel Venero Yupanqui
// Assignment 1
// Professor Parra
// COP4520
// January 27, 2023

public class SieveRunnable implements Runnable {

    private int startingIndex = 0;
    private int limit = 0;
    private boolean primes[];

    public SieveRunnable(int index, int limit, boolean[] primes) {
        this.startingIndex = index;
        this.limit = limit;
        this.primes = primes;
    }

    @Override
    public void run() {

        if (primes[startingIndex-1]) {
            markMultiples(startingIndex);
        }
    }

    
    private void markMultiples(int num) {
        int c = (int) Math.pow(num, 2) - 1;

        while (c < limit) {
            primes[c] = false;
            c += num;
        }
    }
}
 