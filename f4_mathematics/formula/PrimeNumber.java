package com.f4_mathematics.formula;

public class PrimeNumber {
    public static void main(String[] args) {

    }
    public static void sieve(int n, boolean[] primes){
        // false in array is number is prime
        for (int i = 2; i*i <= n; i++) {
            if(!primes[i]){
                for (int j = i*2; j <= n; j += i) { // i * 2
                    primes[j] = true;
                }
            }
        }
        for (int i = 2; i <= n; i++) {
            if(!primes[i])
                System.out.print(i+" ");
        }
        // Space Complexity = O(N) (only Auxiliary space)
        // Time complexity = O(N * log(logN)) // watch tailor series and harmonic series on primes
    }
    public static boolean isPrime(int n){
        if( n <= 1)
            return false;
        int i = 2;
        while (i * i <= n){  // i < Math.sqrt(n)
            if(n % i == 0)
                return false;
            i++;
        }
        return true;
    }
}
