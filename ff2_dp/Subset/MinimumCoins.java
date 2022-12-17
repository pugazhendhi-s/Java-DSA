package com.ff2_dp.Subset;

public class MinimumCoins {

    public static void main(String[] args) {

    }

    public static int minimumElements(int[] arr, int tar) {

        int n = arr.length;
        int[][] dp = new int[n][tar+1];

        int mini = func(n-1, tar, arr, dp);
        return  (mini >= (int)1e9) ? -1 : mini;
    }

    public static int func(int i, int tar, int[] arr, int[][] dp) {

        // arr[i] >= 1
        if (i == 0) {
            return (tar % arr[0] == 0) ? tar/arr[0] : (int)1e9;
        }
        if (dp[i][tar] != 0) return dp[i][tar];

        int notpick = func(i-1, tar, arr, dp);
        int pick = (int)1e9;
        if (arr[i] <= tar) {
            pick = 1 + func(i, tar-arr[i], arr, dp);
        }
        return dp[i][tar] = Math.min(notpick, pick);
    }

    public static int tab(int n, int T, int[] arr, int[][] dp) {

        // base case

        for (int i=0; i<=T; i++) {
            dp[0][i] = (i % arr[0] == 0) ? i / arr[0] : (int)1e9;
        }

        for (int ind = 1; ind < n; ind++) {
            for (int target = 0; target <= T; target++) {

                int notpick = dp[ind-1][target];
                int pick = (int)1e9;
                if (arr[ind] <= target) pick = 1 + dp[ind][target-arr[ind]];

                dp[ind][target] = Math.min(notpick, pick);
            }
        }
        return dp[n-1][T];
    }
}
