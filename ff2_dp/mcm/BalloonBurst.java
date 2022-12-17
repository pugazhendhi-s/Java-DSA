package com.ff2_dp.mcm;

import java.util.Arrays;

public class BalloonBurst {

    public static void main(String[] args) {

        int[] nums = {3,1,5,8};
        int maxi = maxCoins(nums);
        System.out.println(maxi);
    }

    public static int maxCoins(int[] balloon) {

        int n = balloon.length;
        int[] A = new int[n+2];
        System.arraycopy(balloon, 0, A, 1, n);
        A[0] = 1; A[n+1] = 1;

        int[][] dp = new int[n+1][n+1];
        for (int[] d : dp) Arrays.fill(d, -1);

        return fn(1, n, A, dp);
    }

    private static int fn(int i, int j, int[] A, int[][] dp) {

        if (i > j) return 0;
        if (dp[i][j] != -1) return dp[i][j];
        int maxCoins = 0;

        for (int k=i; k<=j; k++) {
            int coins = (A[i-1] * A[k] * A[j+1])
                    + fn(i, k-1, A, dp) + fn(k+1, j, A, dp);
            maxCoins = Math.max(maxCoins, coins);
        }
        return dp[i][j] = maxCoins;
    }

    public static int tabulation(int[] nums) {

        int n = nums.length;
        int[] A = new int[n+2];
        A[0] = 1; A[n+1] = 1;

        System.arraycopy(nums, 0, A, 1, n);
        int[][] dp = new int[n+2][n+2];

        for (int i = n; i >= 1; i--) {
            for (int j = 1; j <= n; j++) {
                if (i > j) continue;
                int maxCoins = 0;

                for (int k=i; k<=j; k++) {
                    int coins = (A[i-1] * A[k] * A[j+1]) + dp[i][k-1] + dp[k+1][j];
                    maxCoins = Math.max(maxCoins, coins);
                }
                dp[i][j] = maxCoins;
            }
        }
        return dp[1][n];
    }
}
