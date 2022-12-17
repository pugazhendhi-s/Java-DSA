package com.ff2_dp.mcm;

public class PartitionMaxSum {

    public static void main(String[] args) {

    }

    public static int maxSumAfterPartitioning(int[] nums, int k) {

        int[] dp = new int[nums.length];
        return fn(0, k, nums, dp);
    }
    private static int fn(int i, int k, int[] nums, int[] dp) {

        if (i == nums.length) return 0;
        if (dp[i] != 0) return dp[i];

        int maxSum = 0; int maxNum = 0; int len = 0;
        for (int j = i; j < Math.min(nums.length, i+k); j++) {  // j -> i+k  if i+k > n, out ouf bound so, min(i+k, n);
            maxNum = Math.max(maxNum, nums[j]);
            len++;
            int sum = len * maxNum + fn(j+1, k, nums, dp);
            maxSum = Math.max(maxSum, sum);
        }
        return dp[i] = maxSum;
    }

    public static int tabulation(int[] nums, int k) {

        int n = nums.length;
        int[] dp = new int[n+1];
        dp[n] = 0;

        for (int i = n-1; i >= 0; i--) {
            int maxSum = 0; int maxNum = 0;
            for (int j = i; j < Math.min(nums.length, i+k); j++) {
                maxNum = Math.max(maxNum, nums[j]);
                int sum = (j+1-i) * maxNum + dp[j+1];
                maxSum = Math.max(maxSum, sum);
            }
            dp[i] = maxSum;
        }
        return dp[0];
    }


    public static int tab(int[] nums, int k) {

        int n = nums.length;

        int[] dp = new int[n];
        dp[0] = nums[0];

        for (int i=1; i<n; i++) {
            int curr = nums[i];
            for (int j=i; j >= 0 && (i+1-j <= k); j--) {
                curr = Math.max(curr, nums[j]);
                int len = i+1-j;
                if (j == 0) {
                    dp[i] = Math.max(dp[i], curr * len);
                }
                else dp[i] = Math.max(dp[i], dp[j-1] + curr * len);
            }
        }
        return dp[n-1];
    }
}
