package com.ff2_dp.grid;
import java.util.*;

public class Cherry3D {

    public static void main(String[] args) {

        int[][] grid = {{1,0,0,0,0,0,1},{2,0,0,0,0,3,0},{2,0,9,0,0,0,0}, {0,3,0,5,4,0,0},{1,0,2,3,0,0,6}};
        int n = grid.length;
        int m = grid[0].length;

        int chocolates = maximumChocolates(n, m, grid);

        System.out.println(chocolates);
    }

    public static int maximumChocolates(int n, int m, int[][] grid) {

        int[][][] dp = new int[n][m][m];
        for (int[][] a: dp) {
            for (int[] b : a) Arrays.fill(b, -1);
        }
        return func(0, 0, m-1, n, m, dp, grid);
        // tc -> O(N*M*M) * 9 , for without memo tc -> O(3^N * 3^N) ~ exponential
        // sc -> O(N*M*M) + O(N)
    }
    // memo dp  -->
    public static int func(int i, int j1, int j2, int n, int m, int[][][] dp, int[][] grid) {

        if (j1 < 0 || j2 < 0 || j1 >= m || j2 >= m) return 0;
        if (dp[i][j1][j2] != 0) return dp[i][j1][j2];
        if(i == n-1) return (j1 == j2) ? grid[i][j1] : grid[i][j1] + grid[i][j2];

        int maxi = 0;
        for (int dj1 = -1; dj1 <= 1; dj1++) {
            for (int dj2 = -1; dj2 <= 1; dj2++) {
                int val = grid[i][j1];
                if(j1 != j2) val += grid[i][j2];

                val += func(i+1, j1+dj1, j2+dj2, n, m, dp, grid);
                maxi = Math.max(maxi, val);
            }
        }
        return dp[i][j1][j2] = maxi;
    }
}
