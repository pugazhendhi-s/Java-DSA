package com.ff2_dp.mcm;

import java.util.Arrays;

public class MatrixChainMultiplication {

    public static void main(String[] args) {

    }

    public static int matrixMultiplication(int[] arr, int N) {

        int[][] dp = new int[N][N];
        for (int[] d : dp) Arrays.fill(d, -1);

        return fn(1, N - 1, arr, dp);
    }

    private static int fn(int i, int j, int[] A, int[][] dp) {

        if (i == j) return 0;
        if (dp[i][j] != -1) return dp[i][j];

        int minSteps = (int) 1e9;

        for (int k = i; k <= j - 1; k++) {
            int steps = (A[i - 1] * A[k] * A[j]) + fn(i, k, A, dp) + fn(k + 1, j, A, dp);
            minSteps = Math.min(steps, minSteps);
        }
        return dp[i][j] = minSteps;
    }

    public static int tabulation(int[] A, int N) {

        int[][] dp = new int[N][N];

        for (int i = 0; i < N; i++) dp[i][i] = 0;

        for (int i = N - 1; i >= 1; i--) {

            for (int j = i + 1; j < N; j++) { // j is always right to i
                int minSteps = (int) 1e9;

                for (int k = i; k < j; k++) {
                    int steps = A[i - 1] * A[k] * A[j] + dp[i][k] + dp[k + 1][j];
                    minSteps = Math.min(steps, minSteps);
                }
                dp[i][j] = minSteps;
            }
        }
        return dp[1][N - 1];
    }
}
