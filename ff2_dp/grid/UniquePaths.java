package com.ff2_dp.grid;
import java.util.*;

public class UniquePaths {

    public static void main(String[] args) {

        int paths = opti(3,3);
        System.out.println(paths);
    }

    public static int uniquePaths(int n, int m) {

        int[][] dp = new int[n][m];

        for (int[] d : dp) Arrays.fill(d, -1);

        return tabulation(n, m, dp);
    }

    public static int recur(int i, int j) {

        if (i == 0 && j == 0) return 1;
        if (i < 0 || j < 0) return 0;

        int up = recur(i-1, j);
        int down = recur(i, j-1);

        return up + down;

        // tc -> O(2 power n*m) exponential
        // sc -> O(n-1 + m-1) path length
    }

    public static int memo(int i, int j, int[][] dp) {

        if (i == 0 && j == 0) return 1;
        if (i < 0 || j < 0) return 0;
        if (dp[i][j] != -1) return dp[i][j];

        int up = memo(i-1, j, dp);
        int down = memo(i, j-1, dp);

        return dp[i][j] = up + down;

        // tc -> O(n*m)
        // sc -> O(n-1 + m-1) + O(n * m) path length + dp
    }

    public static int tabulation(int n, int m, int[][] dp) {

        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {

                if (i == 0 && j == 0) dp[0][0] = 1;
                else {
                    dp[i][j] += (i > 0) ? dp[i-1][j] : 0;  // up
                    dp[i][j] += (j > 0) ? dp[i][j-1] : 0;  // left
                }
            }
        }
        return dp[n-1][m-1];

        // tc -> O(n*m)
        // sc -> O(n * m) dp
    }

    public static int opti(int n, int m) {

        int[] prev = new int[m]; // col times

        for (int i=0; i<n; i++) {

            int[] curr = new int[m];
            for (int j=0; j<m; j++) {

                if (i == 0 && j == 0) curr[j] = 1;
                else {
                    int up = 0, left = 0;
                    if (i > 0) up = prev[j];
                    if (j > 0) left = curr[j-1];
                    curr[j] = up + left;
                }
            }
            prev = curr;
        }
        return prev[m-1];

        // tc -> O(n*m)
        // sc -> O(m) + O(m) dp + temp
    }

    public static int combinatorics(int n, int m) {

        int steps = n + m -2;
        int right = m-1;
        double res = 0;

        for (int i = 1; i <= right; i++) {
            res = res * (steps - right + i) / i;
        }
        return (int)res;
        // tc -> O(m)
        // sc -> O(1)
    }


    // unique paths with obstacle

    public int uniquePathsWithObstacles(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        int[][] dp = new int[n][m];

        for (int[] d : dp) Arrays.fill(d, -1);

        return memo(n-1, m-1, grid, dp);
    }

    public int memo(int i, int j, int[][] grid, int[][] dp) {

        if (i < 0 || j < 0 || grid[i][j] == 1) return 0;

        if (i == 0 && j == 0) return 1;
        if (dp[i][j] != -1) return dp[i][j];

        int up = memo(i-1, j, grid, dp);
        int down = memo(i, j-1, grid, dp);

        return dp[i][j] = up + down;
    }

    public int tabulation(int n, int m, int[][] grid, int[][] dp) {

        dp[0][0] = 1;

        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (grid[i][j] != 1) {
                    dp[i][j] += (i > 0) ? dp[i-1][j] : 0;  // up
                    dp[i][j] += (j > 0) ? dp[i][j-1] : 0;  // left
                }
            }
        }
        return dp[n-1][m-1];
    }

    public static int spaceOpti(int n, int m, int[][] grid) {

        int[] prev = new int[m]; // col times

        for (int i=0; i<n; i++) {

            int[] curr = new int[m];
            for (int j=0; j<m; j++) {

                if(grid[i][j] == 1) continue; // or temp[i] = 0;

                if (i == 0 && j == 0) curr[j] = 1;
                else {
                    int up = 0, left = 0;
                    if (i > 0) up = prev[j];
                    if (j > 0) left = curr[j-1];
                    curr[j] = up + left;
                }
            }
            prev = curr;
        }
        return prev[m-1];
    }

}
