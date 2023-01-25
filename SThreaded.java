// Starting with single threaded approach to gauge problem at hand

// TODO:
// "As a refinement, it is sufficient to mark the numbers in step 3 starting from p2, 
// as all the smaller multiples of p will have already been marked at that point. This 
// means that the algorithm is allowed to terminate in step 4 when p2 is greater than n.[1]"


public class SThreaded {

    static boolean primes[];
    static long count = 0;
    static long sum = 0;
    static int tenMaxPrimes[] = new int[10];
    
    public static void main(String[] args) {

        long t0 = System.currentTimeMillis();

        int n = Integer.parseInt(args[0]);
        primes = new boolean[n];

        // Assume numbers are true until confirmed otherwise
        for (int i = 0; i < n; ++i) {
            primes[i] = true;
        }

        // 
        for (int i = 2; i < n; ++i)
        {
            if (primes[i-1]) { 
                count++;
                sum += i;
                markMultiples(i, n);
            }
        }

        int subc = 0;
        int cc = 0;
        for (int i = 0; i < n; ++i) {
            if (primes[i] && count - subc < 10) {
                tenMaxPrimes[cc] = i+1;
                cc++;
            }
            if (primes[i]) {
                subc++;
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

    // Mark all multiples of num in array as non primes

    static void markMultiples(int num, int len) {
        int c = 2*num - 1;

        while (c < len) {
            primes[c] = false;
            c += num;
        }
    } 

    // ========================================================================
    // Functions used for validation purposes
    // ========================================================================

    static void printTableNum() {
        int c = 0; 
        while (c < primes.length) { 
            for (int i = 0; i < 10; ++i) {
                if (primes[c+i])
                    System.out.printf("%5d", c+i+1);
                else 
                    System.out.printf("     ");
            }

            System.out.println();
            c += 10;
        }
    }

    static void printTableBol() {
        int c = 0; 
        while (c < primes.length) { 
            for (int i = 0; i < 10; ++i)
                System.out.print(primes[c+i] + " ");

            System.out.println();
            c += 10;
        }
    }
}
