public class SieveThread extends Thread {

    private int startingIndex = 0;
    private int limit = 0;
    private boolean primes[];
    private long totalTime = 0;
    
    public void run() {
        long startTime = System.currentTimeMillis();

        if (startingIndex == 0 || limit == 0) {
            System.out.println(getName() + " doesn't have valeus specified");
            return;
        }

        for (int i = startingIndex; i < limit; i += 8) {
            if (primes[i-1]) {
                markMultiples(i, limit);
            }
        }

        long endTime = System.currentTimeMillis();
        totalTime = endTime - startTime;
        System.out.printf(getName() + " time: %f seconds\n", totalTime / 1000.0);
    }

    public void setValues(int index, int limit, boolean primes[]) {
        this.startingIndex = index;
        this.limit = limit;
        this.primes = primes;
    }

    private void markMultiples(int num, int limit) {
        int c = (int) Math.pow(num, 2) - 1;

        while (c < limit) {
            // change array in main
            primes[c] = false;
            c += num;
        }
    }
}