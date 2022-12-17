package com.f4_mathematics.formula;

public class CatalanNumbers {
    // 1, 1, 2, 5, 14, 42, 132, 429, 1430, 4862,...
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.print(catalanDP(i)+", ");
        }
    }
    static long catalan(int n){
        long cat = binomialCoefficient(2*n, n);
        // return 2nCn/(n+1)
        return cat / (n+1);
    }
    private static long binomialCoefficient(int n, int k) {
        int res = 1;

        // Since, C(n, k) = C(n, n-k)
        if(k > n - k)
            k = n - k;
        // Combination formula nCr = n! / (n-r)!r! => to simply Numerator, [N -> N-k+1], Denominator -> [k -> 1]
        // Calculate value of [n*(n-1)*---*(n-k+1)] / [k*(k-1)*---*1]
        for (int i = 0; i < k; i++) {
            res *= (n-i);
            res /= (i+1);
        }
        return res;
    }

    // using recursion
    static long catalanRecur(int n){
        if(n <= 1)
            return 1;
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += (catalanRecur(i) * catalanRecur(n-1 - i));
        }
        return result;
    }

    // using dynamic programming
    static int catalanDP(int n){
        int[] catalan = new int[n+2];
        catalan[0] = catalan[1] = 1;

        for (int i = 2; i <= n; i++) {
            catalan[i] = 0;  // previously assigning garbage value, so now assign 0
            for (int j = 0; j < i; j++) {
                catalan[i] += (catalan[j] * catalan[i- j -1]);
            }
        }
        return catalan[n];
    }
}
