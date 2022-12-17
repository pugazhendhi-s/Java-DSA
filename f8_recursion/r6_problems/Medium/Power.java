package com.f8_recursion.r6_problems.Medium;

public class Power {
    public static void main(String[] args) {
        System.out.println(minNonZeroProduct(3));
    }
    private static final int mod  = (int) (1e9 + 7);
    public static int minNonZeroProduct(int p) {
        if(p == 1)
            return 1;
        int last = (1 << p) - 1;   // 2^p - 1
        int ans = (int) pow(last-1, (long) (last/2));
        return (ans * (last % mod)) % mod;
    }


    // power problems

    public int countGoodNumbers(long n) {
        /*
         * A digit string is good if the digits (0-indexed) at even indices are even
         * and the digits at odd indices are prime (2, 3, 5, or 7).
         *
         * For example, "2582" is good because the digits (2 and 8) at even positions are even
         * and the digits (5 and 2) at odd positions are prime.
         * However, "3245" is not good because 3 is at an even index but is not even.
         * Given an integer n, return the total number of good digit strings of length n.
         * Since the answer may be large, return it modulo 109 + 7.
         *
         * A digit string is a string consisting of digits 0 through 9 that may contain leading zeros.
         *
         * it's like permutation & combination problem, n=4, 0 based index,
         * indexesEven(0,2,4....) - contains only even values (0,2,4,6,8) - 5C1,
         * indexesOdd(1,3,5,7,9,11....) only prime numbers allowed (1,3,5,7) - 4C1
         *
         * when n = 4, 5C1 * 4C1 * 5C1 * 4C1 ,LIKE WISE
         * here we don't directly apply because of n comes as long , so values may be big, we can't do Math.pow.
         * so we create a custom pow, method and apply divide conquer approach (y/2) --> y == 0
         *
         */
        long even =  pow(5, (n+1)/2) % mod;
        long prime = pow(4, n/2) % mod;
        long goodNumbers = (even * prime) % mod;
        return (int) goodNumbers;
    }
    private static long pow(long x, long y){
        if(y == 0)
            return 1;
        long temp = pow(x, y/2) % mod; // recursion
        return (y % 2 == 0) ? (temp * temp) % mod: (x * temp * temp) % mod;
    }
    // pow(x,y)
    public double myPow(double x, int y) {
        return (y > 0) ? pow(x, y) : 1 / pow(x, y);
    }
    private static double pow(double x, int y){
        if(y == 0)
            return 1;
        double temp = pow(x, y/2); // recursion
        return (y % 2 == 0) ? temp * temp :  x * temp * temp;
    }
}
