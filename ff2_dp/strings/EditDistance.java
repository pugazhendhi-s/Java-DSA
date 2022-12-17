package com.ff2_dp.strings;

import java.util.Arrays;

public class EditDistance {

    public static void main(String[] args) {

    }

    public int minDistance(String s, String t) {

        int n = s.length();
        int m = t.length();

        int[][] dp = new int[n+1][m+1];
        for (int[] d : dp) Arrays.fill(d, -1);

        return func(n-1, m-1, s, t, dp);
    }

    public static int func(int i, int j, String s, String t, int[][] dp) {

        if (i < 0) return j+1;  // insert j+1 chars
        if (j < 0) return i+1;  // delete i+1 chars

        if (dp[i][j] != -1) return dp[i][j];

        if (s.charAt(i) == t.charAt(j)) {
            return dp[i][j] = func(i-1, j-1, s, t, dp); // both are same  no need for operations
        }
        // not equal
        int insert = func(i, j-1, s, t, dp);
        int delete = func(i-1, j, s, t, dp);
        int replace = func(i-1, j-1, s, t, dp);

        return dp[i][j] = 1 + Math.min(insert, Math.min(delete, replace));

    }

    public static int tabulation(String s, String t) {

        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n+1][m+1];

        for (int i=0; i<=n ; i++) {
            dp[i][0] = i;
        }
        for (int j=0; j<=m; j++) {
            dp[0][j] = j;
        }

        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++) {
                if (s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                }
                else {
                    int insert = dp[i][j-1];
                    int delete = dp[i-1][j];
                    int replace = dp[i-1][j-1];

                    dp[i][j] = 1 + Math.min(insert, Math.min(delete, replace));
                }
            }
        }
        return dp[n][m];
    }
}
