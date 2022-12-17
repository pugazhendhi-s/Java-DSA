package com.ff2_dp.longest;

import java.util.*;

public class LDS {

    public static void main(String[] args) {

    }

    public static List<Integer> largestDivisibleSubset(int[] nums) {

        Arrays.sort(nums);
        List<Integer> res = Eff(nums);
        System.out.println(res);
        return func(0, -1, nums);
    }
    // TLE
    private static List<Integer> func(int ind, int pre_ind, int[] nums) {

        if (ind == nums.length) return new ArrayList<>();

        List<Integer> notpick = func(ind+1, pre_ind, nums);
        List<Integer> pick = new ArrayList<>();
        if (pre_ind == -1 || nums[ind] % nums[pre_ind] == 0) {
            pick = func(ind+1, ind, nums);
            pick.add(nums[ind]);
        }
        if(notpick.size() > pick.size()) return notpick;
        return pick;
    }

    public static List<Integer> Eff(int[] nums) {

        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int[] hash = new int[n];
        Arrays.fill(hash, 1);

        Arrays.sort(nums);

        int maxLen = 0;
        int maxIndex = -1;

        for (int i=0; i<n;i++) {
            hash[i] = i;
            for (int pre=0; pre<i; pre++) {

                if (nums[i] % nums[pre] == 0 && 1 + dp[pre] > dp[i]) {
                    dp[i] = 1 + dp[pre];
                    hash[i] = pre;
                }

            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxIndex = i;
            }
        }

        List<Integer> list = new ArrayList<>();
        list.add(nums[maxIndex]);

        while (hash[maxIndex] != maxIndex) {
            maxIndex = hash[maxIndex];
            list.add(nums[maxIndex]);
        }
        return list;
    }
}
