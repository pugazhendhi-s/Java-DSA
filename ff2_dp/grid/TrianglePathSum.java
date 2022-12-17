package com.ff2_dp.grid;

import java.util.Arrays;
import java.util.List;

public class TrianglePathSum {

    public static void main(String[] args) {

        int[][] grid = {{2}, {3, 4}, {6, 5, 7}, {4, 1, 8, 3}};
        int n = 4;
        int tab = tabulation(n, grid);
        System.out.println(tab);
    }

    public static int minimumPathSum(int[][] triangle, int n) {
        return recur(0, 0, triangle);
    }

    public static int recur(int i, int j, int[][] grid) {

        if (i == grid.length - 1) return grid[i][j];

        int down = grid[i][j] + recur(i + 1, j, grid);
        int adj = grid[i][j] + recur(i + 1, j + 1, grid);

        return Math.min(down, adj);

        // tc -> 2 ^ sum of all numbers int grid,, -> 2 because for every number go down and adjacent
        // sc -> O(N) -> length of n , stack
    }
    // leet code
    public int minimumTotal(List<List<Integer>> triangle) {

        int n = triangle.size();

        int[][] dp = new int[n][n];

        for (int[] d : dp) Arrays.fill(d, -1);

        return memo(0, 0, dp, triangle);

    }

    public static int memo(int i, int j, int[][] dp, List<List<Integer>> grid) {

        if (i == grid.size() - 1) return grid.get(i).get(j);
        if (dp[i][j] != -1) return dp[i][j];

        int down = grid.get(i).get(j) + memo(i + 1, j, dp, grid);
        int adj = grid.get(i).get(j) + memo(i + 1, j + 1, dp, grid);

        return dp[i][j] = Math.min(down, adj);

        // tc -> O(N*N)  -> triangle this is less than n*n complexity
        // sc -> O(N) + O(N*N)  -> recursive stack + dp
    }

    public static int tabulation(int n, int[][] grid) {

        int[][] dp = new int[n][n];

        System.arraycopy(grid[n - 1], 0, dp[n - 1], 0, n);
        // for (int j=0; j<n; j++) dp[n-1][j] = grid[n-1][j];

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                int down = grid[i][j] + dp[i + 1][j];
                int adj = grid[i][j] + dp[i + 1][j + 1];
                dp[i][j] = Math.min(down, adj);
            }
        }
        return dp[0][0];
    }

    public static int spaceOpti(int n, int[][] grid) {

        int[] prev = new int[n];
        System.arraycopy(grid[n - 1], 0, prev, 0, n);

        for (int i = n-2; i >= 0; i--) {
            int[] curr = new int[grid[i].length];

            for (int j = i; j >= 0; j--) {
                int down = grid[i][j] + prev[j];
                int adj = grid[i][j] + prev[j+1];
                curr[j] = Math.min(down, adj);
                //curr[j] = grid[i][j] + Math.min(prev[j], prev[j+1]);
            }
            prev = curr;
        }
        return prev[0];
    }
}
