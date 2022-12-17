package com.ff2_dp.mcm;

import java.util.Arrays;

public class BooleanEvaluation {

    public static void main(String[] args) {

    }

    private final static int mod = 1_000_000_007;

    public static int evaluateExp(String exp) {
        int n = exp.length();
        long[][][] dp = new long[n][n][2];
        for (long[][] a : dp) {
            for (long[] d : a) Arrays.fill(d, -1);
        }
        return (int) fn(0, exp.length() - 1, 1, exp, dp);
    }

    public static long fn(int i, int j, int isTrue, String exp, long[][][] dp) {

        if (i > j) return 0;
        if (i == j) {
            if (isTrue == 1) return exp.charAt(i) == 'T' ? 1 : 0;
            else return exp.charAt(i) == 'F' ? 1 : 0;
        }
        if (dp[i][j][isTrue] != -1) return dp[i][j][isTrue];

        long ways = 0;
        for (int k = i + 1; k <= j - 1; k += 2) {
            long lT = fn(i, k - 1, 1, exp, dp) % mod;  // left True
            long lF = fn(i, k - 1, 0, exp, dp) % mod;  // left False
            long rT = fn(k + 1, j, 1, exp, dp) % mod;  // right True
            long rF = fn(k + 1, j, 0, exp, dp) % mod;  // right False

            char ch = exp.charAt(k);
            if (ch == '&') {
                if (isTrue == 1)
                    ways = (ways + lT * rT) % mod;
                else
                    ways = (ways + (lF * rF) % mod + (lT * rF) % mod + (lF * rT)) % mod;
            } else if (ch == '|') {
                if (isTrue == 1)
                    ways = (ways + (lT * rT) % mod + (lT * rF) % mod + (lF * rT)) % mod;
                else
                    ways = (ways + lF * rF) % mod;
            } else {
                if (isTrue == 1)
                    ways = (ways + (lT * rF) % mod + (lF * rT) % mod) % mod;
                else
                    ways = (ways + (lT * rT) % mod + (lF * rF) % mod) % mod;
            }
        }
        return dp[i][j][isTrue] = ways;
    }

    public static int Tabulation(String exp) {

        int n = exp.length();
        long[][][] dp = new long[n][n][2];

        for (int i=0; i<n; i++) {
            char ch = exp.charAt(i);
            dp[i][i][0] = (ch == 'F') ? 1 : 0;
            dp[i][i][1] = (ch == 'T') ? 1 : 0;
        }
        for (int i = n-1; i >= 0; i--) {
            for (int j = i+1; j < n; j++) { // i > j && i == j are base cases,so start j from i+1
                for (int isTrue = 0; isTrue < 2; isTrue++) {
                    long ways = 0;
                    for (int k=i+1; k<=j-1; k+=2) {
                        long lT = dp[i][k-1][1] % mod;  // left True
                        long lF = dp[i][k-1][0] % mod;  // left False
                        long rT = dp[k+1][j][1] % mod;  // right True
                        long rF = dp[k+1][j][0] % mod;  // right False

                        char ch = exp.charAt(k);
                        if (ch == '&') {
                            if (isTrue == 1)
                                ways = (ways + lT * rT)%mod;
                            else
                                ways = (ways + (lF * rF)%mod + (lT * rF)%mod + (lF * rT)) % mod;
                        }
                        else if (ch == '|') {
                            if (isTrue == 1)
                                ways = (ways + (lT * rT)%mod + (lT * rF)%mod + (lF * rT)) % mod;
                            else
                                ways = (ways + lF * rF)%mod;
                        }
                        else {
                            if (isTrue == 1)
                                ways = (ways + (lT * rF)%mod + (lF * rT)%mod)%mod;
                            else
                                ways = (ways + (lT * rT)%mod + (lF * rF)%mod)%mod;
                        }
                    }
                    dp[i][j][isTrue] = ways;
                }
            }
        }
        return (int)dp[0][n-1][1];
    }
}
