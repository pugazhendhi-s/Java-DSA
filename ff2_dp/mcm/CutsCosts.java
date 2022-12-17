package com.ff2_dp.mcm;

import java.util.Arrays;

public class CutsCosts {

    public static void main(String[] args) {

    }

    public static int minCost(int n, int[] cuts) {

        int c = cuts.length;
        int[] A = new int[c+2];
        A[0] = 0; A[c+1] = n;

        for (int i=0; i<c; i++) A[i+1] = cuts[i];

        Arrays.sort(A);

        int[][] dp = new int[c+2][c+2];

        return fn(1, c, A, dp);
    }

    private static int fn(int i, int j, int[] cuts, int[][] dp) {

        if (i > j) return 0;
        if (dp[i][j] != -1) return dp[i][j];

        int minCost = (int) 1e9;

        for (int k=i; k<=j; k++) {
            int cost = cuts[j+1] - cuts[i-1];
            cost += (fn(i, k-1, cuts, dp) + fn(k+1, j, cuts, dp));
            minCost = Math.min(minCost, cost);
        }
        return dp[i][j] = minCost;
    }

    public static int cost(int n, int c, int[] cuts) {

        int[] A = new int[c+2];
        A[0] = 0; A[c+1] = n;
        System.arraycopy(cuts, 0, A, 1, c);
        Arrays.sort(A);

        int[][] dp = new int[c+2] [c+2];

        for (int i = c; i >= 1; i--) {
            for (int j = 1; j <= c; j++) {
                if(i > j) continue;
                int minCost = (int) 1e9;
                for (int k = i; k <= j; k++) {
                    int cost = A[j+1] - A[i-1] + dp[i][k-1] + dp[k+1][j];
                    minCost = Math.min(cost, minCost);
                }
                dp[i][j] = minCost;
            }
        }
        return dp[1][c];
    }
}


