package com.ff2_dp.OneD;
import java.util.*;
public class FrogJump {

    public static void main(String[] args) {

        int[] hts = {30, 10, 60, 10, 60, 50};
        int n = hts.length;

        int s1 =  recursive(n-1, hts);

        int[] dp = new int[n+1];
        //int s2 = memoR(n-1, hts, dp);

        int s3 = tabulation(n, hts);

        int[] heights = {40, 10, 20, 70, 80, 10, 20, 70, 80, 60};
        int N = heights.length;
        int K = 4;
        int[] dp1 = new int[N];
        int steps = frogJumpAtKEff(N, heights, dp1, K);
        System.out.println(steps);
    }

    // 1 or 2 jumps
    public static int frogJump(int n, int[] hts) {  // this is efficient

        int p1 = 0;
        int p2 = 0;

        for (int i=1; i<n; i++) {

            int fs = p1 + Math.abs(hts[i] - hts[i-1]);
            int ss = Integer.MAX_VALUE;
            if(i > 1) ss = p2 + Math.abs(hts[i] - hts[i-2]);

            p2 = p1;
            p1 = Math.min(fs, ss);
        }
        return p1;
    }

    public static int recursive(int i, int[] hts) { // normal recursive

        if (i == 0) return 0;

        int left = recursive(i-1, hts) + Math.abs(hts[i-1] - hts[i]);
        int right = Integer.MAX_VALUE;

        if (i > 1) {
            right = recursive(i-2, hts) + Math.abs(hts[i-2] - hts[i]);
        }
        return Math.min(left, right);
    }

    public static int memoR(int i, int[] hts, int[] dp) {  // recursive memoization (top -> down)

        if (i == 0) return 0;
        if (dp[i] != 0) return dp[i];

        int left = memoR(i-1, hts, dp) + Math.abs(hts[i-1] - hts[i]);
        int right = Integer.MAX_VALUE;

        if (i > 1) {
            right = memoR(i-2, hts, dp) + Math.abs(hts[i-2] - hts[i]);
        }
        return dp[i]  = Math.min(left, right);
    }

    public static int tabulation(int n, int[] hts) {
        
        int[] dp = new int[n];
        dp[0] = 0;

        for (int i=1; i<n; i++) {

            int fs = dp[i-1] + Math.abs(hts[i] - hts[i-1]);
            int ss = Integer.MAX_VALUE;
            
            if(i > 1) ss = dp[i-2] + Math.abs(hts[i] - hts[i-2]);
            dp[i] = Math.min(fs, ss);
        }

        return dp[n-1];
    }

    // k jumps
    public static int frogJumpAtK(int i, int[] hts, int[] dp, int k) {  // recursive memoization (top -> down)

        if (i == 0) return 0;
        if (dp[i] != 0) return dp[i];

        int minSteps = Integer.MAX_VALUE;

        for (int j = 1; j <= k && i-j >= 0; j++) {
            int steps = frogJumpAtK(i-j, hts, dp, k) + Math.abs(hts[i] - hts[i-j]);
            if (steps < minSteps) minSteps = steps;
        }
        return dp[i]  = minSteps;
    }

    public static int frogJumpAtKEff(int n, int[] hts, int[] dp, int k) {  // bottom up approach

        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            int minSteps = Integer.MAX_VALUE;

            for (int j = 1; j <= k && i-j >= 0; j++) {
                int steps = dp[i-j] + Math.abs(hts[i] - hts[i-j]);
                minSteps = Math.min(steps, minSteps);
            }
            dp[i] = minSteps;
        }
        return dp[n-1];
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
}
