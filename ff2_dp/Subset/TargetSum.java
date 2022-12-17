package com.ff2_dp.Subset;


public class TargetSum {

    public static void main(String[] args) {

    }
    public static int targetSum(int n, int target, int[] arr) {

        return func(n-1, target, arr);
    }

    public static int func(int ind, int tar, int[] arr) {  // this is native approach

        if (ind == 0) {
            if (tar == 0 && arr[0] == 0) return 2;
            return (arr[0] == Math.abs(tar)) ? 1 : 0;
        }
        int add = func(ind-1, tar+arr[ind], arr);
        int sub = func(ind-1, tar-arr[ind], arr);

        return add+sub;
    }
    public int tabulation(int[] nums, int target) {  // efficient

        int n = nums.length;

        int totSum = 0;
        for (int val : nums) totSum +=  val;

        int k = totSum - target;
        if (k % 2 != 0 || k < 0) return 0;
        k /= 2;

        int[][] dp = new int[n][k+1];
        dp[0][0] = (nums[0] == 0) ? 2 : 1;
        if (nums[0] != 0 && nums[0] <= k) dp[0][nums[0]] = 1;

        for (int ind=1; ind<n; ind++) {

            for (int tar=0; tar <= k; tar++) {
                int notpick = dp[ind-1][tar];
                int pick = (nums[ind] <= tar) ? dp[ind-1][tar - nums[ind]] : 0;

                dp[ind][tar] = notpick + pick;
            }
        }
        return dp[n-1][k];
    }

    public int opti(int[] nums, int target) {

        int n = nums.length;

        int totSum = 0;
        for (int val : nums) totSum +=  val;

        int k = totSum - target;
        if (k % 2 != 0 || k < 0) return 0;
        k /= 2;

        int[] dp = new int[k+1];
        dp[0] = (nums[0] == 0) ? 2 : 1;
        if (nums[0] != 0 && nums[0] <= k) dp[nums[0]] = 1;

        for (int ind=1; ind<n; ind++) {

            for (int tar=k; tar >= 0; tar--) {
                int notpick = dp[tar];
                int pick = (nums[ind] <= tar) ? dp[tar - nums[ind]] : 0;

                dp[tar] = notpick + pick;
            }
        }
        return dp[k];
    }
}
