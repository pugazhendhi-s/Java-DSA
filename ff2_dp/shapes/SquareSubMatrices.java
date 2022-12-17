package com.ff2_dp.shapes;

public class SquareSubMatrices {

    public static void main(String[] args) {

    }

    public static int countSquares(int[][] mat) {

        int n = mat.length;
        int m = mat[0].length;
        int res = 0;
        int[][] dp = new int[n+1][m+1];

        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++) {
                if (mat[i-1][j-1] > 0) {
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1], 
                                   Math.min(dp[i-1][j], dp[i][j-1]));
                    res += dp[i][j];
                }
            }
        }
        return res;
    }

    public static int expandedSolution(int[][] mat) {

        int n = mat.length;
        int m = mat[0].length;
        int[][] dp = new int[n][m];

        for (int j = 0; j < m; j++) dp[0][j] = mat[0][j]; // System.arraycopy(mat[0], 0, dp[0], 0, m); 
        for (int i = 0; i < n; i++) dp[i][0] = mat[i][0];
        
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {

                if (mat[i][j] == 0){
                    dp[i][j] = 0;
                }
                else if (mat[i][j] == 1 ) {
                    if(dp[i-1][j-1] > 0 && dp[i-1][j] > 0 && dp[i][j-1] > 0) {
                        dp[i][j] = 1 + Math.min(dp[i - 1][j - 1],
                                       Math.min(dp[i - 1][j], dp[i][j - 1]));
                    }
                    else dp[i][j] = 1;
                }
            }
        }
        
        int res = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res += dp[i][j];
            }
        }
        
        return res;
    }
}
