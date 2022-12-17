package com.ff4_greedy.mediumHard.jumpgame;

public class JumpII {

    public static void main(String[] args) {

    }

    public static int minJumpII(int[] a) {

        int n = a.length;

        int farthest = 0;
        int current = 0;
        int jump = 0;

        for(int i=0; i<n-1; i++){

            farthest = Math.max(farthest, i+a[i]);

            if(i == current){
                current = farthest;
                jump++;
            }
        }
        return jump;
    }

    public static int minJump(int[] nums) {

        int[] dp = new int[nums.length];
        return fn(0, nums, dp);
    }

    public static int fn(int i, int[] nums, int[] dp) {

        if (i >= nums.length-1) return 0; // after reaching u no need to jump further
        if (dp[i] != 0) return dp[i];

        int jump = (int)1e9;
        for (int j = 1; j <= nums[i]; j++) {
            jump = Math.min(jump, 1 + fn(i+j, nums, dp));
        }
        return dp[i] = jump;
    }

    public static int fn(int[] nums) {

        int n = nums.length;
        int[] dp = new int[n];
        dp[n-1] = 0;

        /*for (int i = n-2; i >= 0; i--) {
            int jump = (int) 1e9;
            for (int j = 1; j <= nums[i]; j++) {
                if (i+j < n) {
                    jump = Math.min(jump, 1 + dp[i+j]);
                }
            }
            dp[i] = jump;
        }*/ //

        for (int i = n-2; i >= 0; i--) {
            int jump = (int) 1e9;
            int reach = i + nums[i];
            for (int j = i + 1; j <= Math.min(n-1, reach); j++) {
                jump = Math.min(jump, 1 + dp[j]);
            }
            dp[i] = jump;
        }
        return dp[0];
    }
}
