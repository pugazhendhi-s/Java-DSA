package com.ff4_greedy.mediumHard.jumpgame;

public class Jump {

    public static void main(String[] args) {

    }

    public static boolean canJump(int[] nums) {

        int n = nums.length;

        int farthest = 0;
        int current = 0;

        for (int i = 0; i < n-1; i++) {

            farthest = Math.max(farthest, i + nums[i]);

            if (i >= farthest) return false;
            if (i == current) {
                current = farthest;
            }
        }
        return true;
    }

    // brute force dp recursion

    public static boolean canJumpI(int[] nums) {
        int[] dp = new int[nums.length];
        return fn(0, nums, dp);
    }

    public static boolean fn(int i, int[] nums, int[] dp) {

        if (i >= nums.length-1) return true;
        if (dp[i] != 0) return dp[i] == 1;

        for (int j = 1; j <= nums[i]; j++) {
            boolean reach = fn(i + j, nums, dp);
            if (reach) {
                dp[i] = 1;
                return true;
            }
        }
        dp[i] = -1;
        return false;
    }

    // tabulation

    public static boolean fn(int[] nums) {

        int n = nums.length;
        boolean[] dp = new boolean[n];
        dp[n-1] = true;

        for (int i = n-2; i >= 0; i--) {
            // edge case , nums[i] = 0 -> dp[i] = false; continue;
            int reach = i + nums[i];
            for (int j = i+1; j <= Math.min(n-1 ,reach); j++) {
                if (dp[j]) {
                    dp[i] = true;
                    break;
                }

            }
        }
        return dp[0];
    }
}
