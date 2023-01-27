// import java.util.concurrent.locks.ReadWriteLock;

public class SieveRunnable implements Runnable {

    private int startingIndex = 0;
    private int limit = 0;
    private boolean primes[];
    private long totalTime = 0;
    // ReadWriteLock lock;

    public SieveRunnable(int index, int limit, boolean[] primes) {//,ReadWriteLock lock) {
        this.startingIndex = index;
        this.limit = limit;
        this.primes = primes;
        // this.lock = lock;
    }

    @Override
    public void run() {
        long t0 = System.currentTimeMillis();

        // while (!lock.readLock().tryLock()) {}
        if (primes[startingIndex-1]) {
            markMultiples(startingIndex);
        }

        long t1 = System.currentTimeMillis();
        totalTime = t1 - t0;
    }

    
    private void markMultiples(int num) {
        int c = (int) Math.pow(num, 2) - 1;

        while (c < limit) {
            // lock.readLock().lock();
            // try {
                primes[c] = false;
            // } finally {
                // lock.readLock().unlock();
            // }
            // lock.readLock().unlock();
            c += num;
        }
    }
}
 