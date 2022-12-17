package com.f4_mathematics.formula;

public class SquareRoot {
    public static void main(String[] args) {
        System.out.printf("%.5f",binarySearchSQRT(40, 5));
    }
    static double newtonRaphsonSQRT(int n, double accuracy){
        double x = n;
        double root;
        while (true){
            root = 0.5 * (x + n/x);
            if(Math.abs(root-x) < accuracy) {
                break;
            }
            x = root;
        }
        return root;
        /*
         * int n = 40;
         * double accuracy = 0.5;
         * System.out.printf("%.3f",newtonRaphsonSQRT(n, accuracy)); // ans = 6.325
         */
    }
    static double binarySearchSQRT(int n, int precision){
        int s = 0;
        int e = n;
        double root = 0.0;
        while (s <= e){
            int m= s + (e-s)/2;
            if(m * m == n){
                return m;
            }
            if(m * m > n)
                e = m - 1;
            else
                s = m + 1;
        }
        double incr = 0.1;
        for (int i = 0; i < precision; i++) {
            while (root * root <= n){
                root += incr;
            }
            root -= incr;
            incr /= 10;
        }
        return root;
    }
}
