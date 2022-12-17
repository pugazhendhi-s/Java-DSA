package com.ff2_dp.OneD;
import java.util.ArrayList;

public class NonAdjacent {

    public static void main(String[] args) {

    }

    public static int maximumNonAdjacentSum(ArrayList<Integer> nums) {

        int n = nums.size();
        int[] dp = new int[n];

        int recu = recur(n-1, nums);
        int memo = memo(n-1, dp, nums);
        int tab = tab(dp, nums);
        return tab;
    }

    public static int recur(int ind, ArrayList<Integer> nums) {

        if(ind == 0) return nums.get(0);
        if(ind < 0) return 0;

        int pick = nums.get(ind) + recur(ind-2, nums);
        int notpick = recur(ind - 1, nums);

        return Math.max(pick, notpick);
    }

    public static int memo(int ind, int[] dp, ArrayList<Integer> nums) {

        if(ind == 0) return nums.get(0);
        if(ind < 0) return 0;
        if(dp[ind] != 0) return dp[ind];

        int pick = nums.get(ind) + memo(ind-2, dp, nums);
        int notpick = memo(ind - 1, dp, nums);

        return dp[ind] = Math.max(pick, notpick);
    }

    public static int tab(int[] dp, ArrayList<Integer> nums) {

        int n = nums.size();
        dp[0] = nums.get(0);

        for (int i=1; i<n; i++) {

            int pick = (i > 1) ? nums.get(i) + dp[i-2] : nums.get(i);
            int notpick = dp[i-1];

            dp[i] = Math.max(pick, notpick);
        }
        return dp[n-1];
    }

    public static int opti(ArrayList<Integer> nums) {

        int n = nums.size();
        int prev = nums.get(0);  // previous 1
        int prev2 = 0;

        for (int i=1; i<n; i++) {

            int pick = nums.get(i);
            if (i > 1) pick += prev2;

            int notpick = 0 + prev;

            int curr = Math.max(pick, notpick);
            prev2 = prev;
            prev = curr;
        }
        return prev;
    }

    // https://leetcode.com/problems/house-robber/

    public int rob(int[] nums) {

        int prev1 = nums[0];
        int prev2 = 0;

        for(int val : nums) {
            int temp = prev1;
            prev1 = Math.max(val + prev2, prev1);
            prev2 = temp;
        }
        return prev2;
    }

    // https://leetcode.com/problems/house-robber-ii/

    public int robII(int[] nums) {

        int n = nums.length;
        if(n == 1) return nums[0];
        return Math.max(func(nums, 0, n-1), func(nums, 1, n));
    }

    public int func(int[] nums, int lo, int hi) {

        int prev2 = 0;
        int prev1 = nums[lo];

        for (int i=lo+1; i<hi; i++) {
            int temp = prev1;
            prev1 = Math.max(nums[i]+prev2, prev1);
            prev2 = temp;
        }

        return prev1;
    }
}
