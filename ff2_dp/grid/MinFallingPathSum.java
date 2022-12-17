package com.ff2_dp.grid;
import java.util.*;

public class MinFallingPathSum {

    public static void main(String[] args) {

        int[][] mat = { {2,1,3},{6,5,4},{7,8,9} }; // ans : 13  {{-19,57},{-40,-5}} ans = -59

    }

    public static int minFallingPathSum(int[][] matrix) {

        int n = matrix.length;
        int minSum = (int)1e9;

        int[][] dp = new int[n][n];

        for (int[] d : dp) Arrays.fill(d, minSum);

        for (int j=0; j<n; j++) {

            minSum = Math.min(minSum, memo(0, j, dp, matrix, n));
        }
        return minSum;
    }

    public static int memo(int i, int j, int[][] dp, int[][] mat, int n) {

        if (j >= n || j < 0) return (int)1e9;
        if (i == n-1) return mat[i][j];

        if (dp[i][j] != (int)(1e9)) return dp[i][j];

        int D = memo(i+1, j, dp, mat, n);
        int DR = memo(i+1, j+1, dp, mat, n);
        int DL = memo(i+1, j-1, dp, mat, n);

        return dp[i][j] = mat[i][j] + Math.min(D, Math.min(DR, DL));

        // TC -> O(N*N) // for normal recursion 3 ^ N -> Exponential
        // sc -> O(N) + O(N*N)
    }

    public static int spaceOpti(int[][] matrix) {

        int n = matrix.length;
        int[] prev = new int[n];

        System.arraycopy(matrix[0], 0, prev, 0, n);

        for (int i = 1; i < n; i++) {
            int[] curr = new int[n];
            for (int j = 0; j < n; j++) {

                int D = prev[j];
                int DR = (j+1 < n) ? prev[j+1] : (int)1e9;
                int DL = (j > 0) ? prev[j-1] : (int)1e9;

                curr[j] = matrix[i][j] + Math.min(D, Math.min(DR, DL));
            }
            prev = curr;
        }
        int minSum = prev[0];

        for (int val : prev) if(val < minSum) minSum = val;

        return minSum;

        // tc -> O(N*M)+O(M)   -> O(M) to find max val;
        // space -> O(M)
    }

    public static int getMaxPathSum(int[][] matrix) {

        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];

        System.arraycopy(matrix[0], 0, dp[0], 0, m);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {

                int D = dp[i-1][j];
                int DR = (j+1 < m) ? dp[i-1][j+1] : -(int)1e9;
                int DL = (j > 0) ? dp[i-1][j-1] : -(int)1e9;

                dp[i][j] = matrix[i][j] + Math.max(D, Math.max(DR, DL));
            }
        }
        int maxSum = dp[n-1][0];

        for (int val : dp[n-1]) if(val > maxSum) maxSum = val;

        return maxSum;

        // tc -> O(N*M)+O(M)   -> O(M) to find max val;
        // space -> O(N*M)
    }
}
