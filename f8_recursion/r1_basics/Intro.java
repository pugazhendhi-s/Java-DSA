package com.f8_recursion.r1_basics;

public class Intro {
    /* 1. two types of recursion
        i). Linear complexity,  ii). Divide & Conquer
        take fibonacci recursive tree diagram,
        Space complexity = height of the recursive tree.
        only one function can execute , which are in same level of tree.
     */
    public static void main(String[] args) {
    }
    static int sum = 0;

    static void reverse1(int n) {
        /*
         * reverse1(3456);
         * System.out.println(sum);*/
        if (n == 0)
            return;
        sum = sum * 10 + n % 10;
        reverse1(n / 10);
    }

    private static int reverse2(int n) {
        int digits = (int) Math.log10(n) + 1;
        return helper(n, digits);
    }
    private static int helper(int n, int digits) {
        if (n % 10 == n)
            return n;
        int rem = n % 10;
        return rem * (int) Math.pow(10, digits - 1) + helper(n / 10, digits - 1);
    }
    public static boolean isPalindromeNumber(int n){
        return n == reverse2(n);
        /*Reverse.reverse1(n);
        return n == Reverse.sum;*/
    }
    static int countZeroes(int n, int count){
        if(n % 10 == n)
            return count;
        return (n % 10 == 0) ? countZeroes(n / 10, ++count) : countZeroes(n / 10, count);
    }

    static void printN(int n) {
        if (n == 0)
            return;
        System.out.println(n);
        printN(--n); // printN(n-1);
        // (n--) gives infinite recursion, because after the function call
        // only n values decreases, same value will be return infinite times*/
    }

    static int fiboFormula(int n) {
        double sqrt = Math.sqrt(5);
        double fiboN = (Math.pow((1+sqrt)/2, n) - Math.pow( (1-sqrt)/2, n))/sqrt;
        return (int)(fiboN);
    }

    static void fibo() {
        System.out.println("Using Formula");
        for (int i = 1; i < 51; i++) {
            System.out.println(fiboFormula(i));
        }
    }

    static int fibo(int n) {
        if (n < 2) {
            return n;
        }
        return fibo(n - 1) + fibo(n - 2);
        // it's an exponential time complexity , n increases,
        // time increases exponential, high time required of n
    }

    static int factorial(int n) {
        if (n <= 2)
            return n;
        return n * factorial(n - 1);
    }

    static int sumOfN(int n) {
        if (n <= 1)
            return 1;
        return n + sumOfN(n - 1);
    }

    static int sumOfDigits(int n) {
        if (n == 0)
            return 0;
        return n % 10 + sumOfDigits(n / 10);
    }
}

