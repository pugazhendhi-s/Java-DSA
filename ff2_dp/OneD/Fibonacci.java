package com.ff2_dp.OneD;


public class Fibonacci {

    public static void main(String[] args) {
        fibonacci();
    }

    public static void fibonacci(){

        int n = 15;
        int[] dp = new int[n+1];

        int recursion = recur(n, dp); // tc -> O(N), sc -> 2O(N)
        int tabulation = tab(n, dp); // tc -> O(N), sc -> O(N)
        int efficient = eff(n);     // tc -> O(N), sc -> O(1)

        System.out.println(recursion + " " + tabulation + " " + efficient);
    }

    private static int recur(int n, int[] dp) {

        if (n <= 1) return n;
        if (dp[n] != 0) return dp[n];

        return dp[n] = recur(n-1, dp) + recur(n-2, dp);
    }

    public static int tab(int n, int[] dp){

        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    private static int eff(int n) {

        int prevLast = 0;
        int prev = 1;
        int curr = 0;

        for (int i = 2; i <= n; i++) {
            curr = prev + prevLast;
            prevLast = prev;
            prev = curr;
        }
        return curr;
    }
}
