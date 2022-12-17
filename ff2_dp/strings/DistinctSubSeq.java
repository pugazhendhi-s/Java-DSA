package com.ff2_dp.strings;
import java.util.*;

public class DistinctSubSeq {

    public static void main(String[] args) {

        String s = "babgbag"; String t = "bag";
        int distinct = tabulation(s, t);
        System.out.println(distinct);
    }

    public static int subsequenceCounting(String s, String t, int n, int m) {

        int[][] dp = new int[n+1][m+1];
        for (int[] d : dp) Arrays.fill(d, -1);
        return func(n-1, m-1, s, t, dp);
    }

    public static int func(int i, int j, String s, String t, int[][] dp) {

        if (j < 0) return 1;  // we completed get t string, so add 1 count;
        if (i < 0) return 0;
        if (dp[i][j] != -1) return dp[i][j];

        if (s.charAt(i) == t.charAt(j)) {
            int leaveOne = func(i-1, j-1, s, t, dp);
            int stay = func(i-1, j, s, t, dp);
            return dp[i][j] = (leaveOne + stay);
        }
        return dp[i][j] = func(i-1, j, s, t, dp);
    }

    public static int tabulation(String s, String t) {

        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n+1][m+1];

        for (int i=0; i<=n; i++) dp[i][0] = 1; // j=0, t is completed processed
        for (int j=1; j<=m; j++) dp[0][j] = 0;

        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++) {
                if (s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];  // leaveOne and stay one
                }
                else dp[i][j] = dp[i-1][j];
            }
        }
        return dp[n][m];
    }

    public static int opti(String s, String t) {

        int n = s.length();
        int m = t.length();

        int[] prev = new int[m+1];
        prev[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = m; j > 0; j--) {    // we are traverse from end so lesser won't get affected
                if (s.charAt(i-1) == t.charAt(j-1)) {
                    prev[j] = prev[j-1] + prev[j];
                }
                else prev[j] = prev[j];
            }
        }
        return prev[m];
    }
}
