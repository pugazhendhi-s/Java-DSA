package com.ff2_dp.Subset;
import java.util.*;

public class Partition {

    public static void main(String[] args) {

        int[] arr = {2, 3, 9, 1};
        int n = arr.length;
        int k = 4;

        boolean tab = spaceOpti(n, k, arr);
        System.out.println(tab);
    }

    public static boolean subsetSumToK(int n, int k, int[] arr) {

        int[][] dp = new int[n][k + 1];

        return memo(n - 1, k, arr, dp);
    }

    public static boolean recur(int i, int k, int[] arr) {

        if (k == 0) return true;
        if (i == 0) return arr[0] == k;

        boolean notpick = recur(i - 1, k, arr);
        boolean pick = (k >= arr[i]) && recur(i - 1, k - arr[i], arr);
        return notpick || pick;

        //return func(i-1, k, arr) || func(i-1, k-arr[i], arr);
    }

    public static boolean memo(int i, int k, int[] arr, int[][] dp) {

        if (k == 0) return true;
        if (i == 0) return arr[0] == k;
        if (dp[i][k] != 0) return dp[i][k] == 1;

        boolean notpick = memo(i - 1, k, arr, dp);
        boolean pick = (k >= arr[i]) && memo(i - 1, k - arr[i], arr, dp);

        dp[i][k] = (notpick || pick) ? 1 : -1;
        return dp[i][k] == 1;
    }

    public static boolean tabulation(int n, int k, int[] arr) {

        boolean[][] dp = new boolean[n][k + 1];

        for (int i = 0; i < n; i++) dp[i][0] = true;

        if (arr[0] <= k) dp[0][arr[0]] = true;

        for (int ind = 1; ind < n; ind++) {
            for (int target = 1; target <= k; target++) {

                boolean notpick = dp[ind - 1][target];
                boolean pick = (target >= arr[ind]) && dp[ind - 1][target - arr[ind]];

                dp[ind][target] = notpick || pick;
            }
        }
        return dp[n - 1][k];
    }

    public static boolean spaceOpti(int n, int k, int[] arr) {

        boolean[] prev = new boolean[k + 1];
        prev[0] = true;

        if (arr[0] <= k) prev[arr[0]] = true;

        for (int ind = 1; ind < n; ind++) {
            boolean[] curr = new boolean[k + 1];
            curr[0] = true;

            for (int target = 1; target <= k; target++) {

                boolean notpick = prev[target];
                boolean pick = (target >= arr[ind]) && prev[target - arr[ind]];
                curr[target] = notpick || pick;
            }
            prev = curr;
        }
        return prev[k];
    }

    public static boolean canPartition(int[] nums) {

        int n = nums.length;
        int sum = 0;
        for (int val : nums) sum += val;

        if (sum % 2 == 1) return false;

        int m = sum / 2;
        int[][] dp = new int[n][m + 1];

        return memo(n - 1, sum / 2, nums, dp);
    }

    public static int minSubsetSumDifference(int[] nums, int n) {

        int k = 0;

        for (int val : nums) k += val;
        // tabulation
        boolean[][] dp = new boolean[n][k + 1];

        for (int i = 0; i < n; i++) dp[i][0] = true;

        if (nums[0] <= k) dp[0][nums[0]] = true;

        for (int ind = 1; ind < n; ind++) {
            for (int target = 1; target <= k; target++) {

                boolean notpick = dp[ind - 1][target];
                boolean pick = (target >= nums[ind]) && dp[ind - 1][target - nums[ind]];

                dp[ind][target] = notpick || pick;
            }
        }

        int minDiff = (int) 1e9;

        for (int i = 0; i <= k / 2; i++) {

            if (dp[n - 1][i]) {
                int s1 = i;
                int s2 = k - i;

                minDiff = Math.min(minDiff, Math.abs(s1 - s2));

            }
        }
        return minDiff;
    }
    // count subsets with k sum
    public static int perfectSum(int[] arr, int n, int sum) {

        int[][] dp = new int[n][sum+1];

        for (int[] d : dp) Arrays.fill(d, -1);

        return func(n-1, sum, arr, dp);
    }

    public static int func(int i, int k, int[] arr, int[][] dp) {

        if (i == 0) {
            if (k == 0 && arr[0] == 0) return 2;
            if (k == 0 || arr[0] == k) return 1;
            return 0;
        }

        if (dp[i][k] != -1) return dp[i][k];

        int notpick = func(i - 1, k, arr, dp);
        int pick = (k >= arr[i]) ? func(i - 1, k - arr[i], arr, dp) : 0;

        return dp[i][k] = notpick + pick;
    }

    public static int tabulation(int n, int k, int[] arr, int[][] dp) {

        dp[0][0] = (arr[0] == 0) ? 2 : 1; // arr[0] = 0, we can pick or not pick both will be same target so count 2

        if (arr[0] != 0) dp[0][arr[0]] = 1; // if arr[0] = 0 , refers dp[0][0] , so that arr[0] != 0

        for (int i=1; i<n; i++) {
            for (int target = 0; target <= k; target++) {

                int notpick = dp[i-1][target];
                int pick = (target >= arr[i]) ? dp[i-1][target-arr[i]] : 0;

                dp[i][target] = (notpick + pick) % 1_000_000_007;
            }
        }
        return dp[n-1][k];
    }

   // count partitions with diff d
    public static int countPartitions(int n, int d, int[] arr) {

        int totSum = 0;
        for (int val : arr) totSum += val;
         /*
            1. s1 - s2 = d
            2. s1 >= s2
            eq ->  s1 = totSum - s2

             s2 = (totSum - d) / 2;  s2 always less than s1
         */
        if (totSum - d < 0) return 0;
        if ( (totSum-d) % 2 == 1) return 0;

        int k = (totSum - d)/2; // s2 = k

        int[][] dp = new int[n][k + 1];

        dp[0][0] = (arr[0] == 0) ? 2 : 1;
        if (arr[0] != 0 && arr[0] <= k) dp[0][arr[0]] = 1;

        for (int i=1; i<n; i++) {
            for (int target = 0; target <= k; target++) {

                int notpick = dp[i-1][target];
                int pick = (target >= arr[i]) ? dp[i-1][target-arr[i]] : 0;

                dp[i][target] = (notpick + pick) % 1_000_000_007;
            }
        }
        return dp[n-1][k];
    }
}
