package com.ff2_dp.grid;

public class MinPathSum {

    public static void main(String[] args) {

        /*
            [[1,3,1],[1,5,1],[4,2,1]]
            [[1,2,3],[4,5,6]]
            [[1,2],[1,1]]
            [[1,2,5],[3,2,1]]  - > 6
         */
        int[][] grid = {{1,2,5},{3,2,1}};
        int minSum = minPathSum(grid);
        System.out.println(minSum);
    }
    public static int minPathSum(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        int[][] dp = new int[n][m];
        //return tabulation(n, m, grid, dp);
        return recur(n-1, m-1, grid);
    }

    private static int recur(int i, int j, int[][] grid) {

        if (i < 0 || j < 0) return (int)1e9;
        if (i == 0 && j == 0) return grid[0][0];

        int up = grid[i][j] + recur(i-1, j, grid);
        int left = grid[i][j] + recur(i, j-1, grid);

        return Math.min(up, left);
    }

    private static int memo(int i, int j, int[][] grid, int[][] dp) {

        if (i < 0 || j < 0) return (int)1e9;
        if (i == 0 && j == 0) return grid[0][0];
        if (dp[i][j] != 0) return dp[i][j];

        int up = grid[i][j] + memo(i-1, j, grid, dp);
        int left = grid[i][j] + memo(i, j-1, grid, dp);

        return dp[i][j] = Math.min(up, left);
    }

    private static int tabulation(int n, int m, int[][] grid, int[][] dp) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (i == 0 && j == 0) dp[i][j] = grid[i][j];
                else {
                    int up = (i > 0) ? grid[i][j] + dp[i-1][j] : (int)1e9;
                    int left = (j > 0) ? grid[i][j] + dp[i][j-1] : (int)1e9;

                    dp[i][j] = Math.min(up, left);
                }
            }
        }
        return dp[n-1][m-1];
    }

    private static int spaceOpti(int n, int m, int[][] grid) {

        int[] prev = new int[m];

        for (int i = 0; i < n; i++) {

            int[] curr = new int[m];
            for (int j = 0; j < m; j++) {

                if (i == 0 && j == 0) curr[j] = grid[i][j];
                else {
                    int up = (i > 0) ? grid[i][j] + prev[j] : (int)1e9;
                    int left = (j > 0) ? grid[i][j] + curr[j-1] : (int)1e9;
                    curr[j] = Math.min(up, left);
                }
            }
            prev = curr;
        }
        return prev[m-1];
    }




}
