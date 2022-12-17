package com.ff2_dp.longest;

import java.util.*;

public class LIS {

    public static void main(String[] args) {

        int[] arr = {5, 4, 11, 1, 16, 8};
        printLIS(arr);
    }

    public static int longestIncreasingSubsequence(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n+1];
        for (int[] d : dp ) Arrays.fill(d, -1);
        return func(0, -1, arr, dp);
    }

    private static int func(int ind, int prev_ind, int[] arr, int[][] dp) {

        if (ind == arr.length) return 0;
        if (dp[ind][prev_ind + 1] != -1) return dp[ind][prev_ind + 1];

        int notpick = func(ind+1, prev_ind, arr, dp);
        int pick = 0;
        if (prev_ind == -1 || arr[ind] > arr[prev_ind]) {
            pick = 1 + func(ind+1, ind, arr, dp);
        }
        return dp[ind][prev_ind + 1] = Math.max(notpick, pick);
    }

    public static int tabulation(int[] arr) {

        int n = arr.length;
        int[][] dp = new int[n+1][n+1];

        for (int ind = n-1; ind >= 0; ind--) {

            for (int pre = ind-1; pre >= -1; pre--) {
                int notpick = dp[ind+1][pre+1];
                int pick = 0;
                if (pre == -1 || arr[ind] > arr[pre]) {
                    pick = 1 + dp[ind+1][ind+1];
                }
                dp[ind][pre+1] = Math.max(notpick, pick);
            }
        }
        return dp[0][0];
    }

    public static int spaceOpti(int[] arr) {

        int n = arr.length;            // tc -> O(n^2) , space -> O(N + N)
        int[] next = new int[n+1];

        for (int ind = n-1; ind >= 0; ind--) {
            int[] curr = new int[n+1];   // we can use ind+1 also,

            for (int pre = ind-1; pre >= -1; pre--) {
                int notpick = next[pre+1];
                int pick = 0;
                if (pre == -1 || arr[ind] > arr[pre]) {
                    pick = 1 + next[ind+1];
                }
                curr[pre+1] = Math.max(notpick, pick);
            }
            next = curr;
        }
        return next[0];
    }
    
    // new method algorithm approach
    
    public static int newTab(int[] arr) {
        
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // min LIS

        int maxi = 1;     // tc -> O(N^2)    this method is to trace back the lis for all ith index
        for (int ind = 0; ind < n; ind++) {
            for (int pre = 0; pre < ind; pre++) {
                if (arr[ind] > arr[pre]) {
                    dp[ind] = Math.max(dp[ind], 1 + dp[pre]);
                }
                maxi = Math.max(maxi, dp[ind]);
            }
        }
        return maxi;
    }

    public static void printLIS(int[] arr) {

        int n = arr.length;
        int[] dp = new int[n];
        int[] hash = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(hash, 1);

        int len = 0;
        int lastIndex = -1;
        for (int i = 0; i < n; i++) {
            hash[i] = i;
            for (int pre = 0; pre < i; pre++) {
                if (arr[i] > arr[pre] && 1 + dp[pre] > dp[i]) {
                    dp[i] = 1 + dp[pre];
                    hash[i] = pre;
                }
            }
            if (dp[i] > len) {
                len = dp[i];
                lastIndex = i;
            }
        }

        int[] res = new int[len];
        res[--len] = arr[lastIndex];

        while (hash[lastIndex] != lastIndex) {
            lastIndex = hash[lastIndex];
            res[--len] = arr[lastIndex];
        }
        System.out.println(Arrays.toString(res));
    }

    public static int lengthLIS(int[] nums) {

        int n = nums.length;
        int[] tails = new int[n];
        int size = 0;

        for (int val : nums) {
            int lo = 0, hi = size;
            while (lo < hi) {
                int mid = (lo + hi)/2;
                if (val > tails[mid]) {
                    lo = mid + 1;
                }
                else hi = mid;
            }
            tails[lo] = val;
            if (lo == size) size++;
        }
        return size;
    }

    public static int lengthList(int[] arr) {

        int n = arr.length;
        List<Integer> tails = new ArrayList<>(n);

        for (int val : arr) {
            int tail = Collections.binarySearch(tails, val);
            if (tail < 0)
                tail = ~tail;  // if element not found
            if (tail == tails.size())
                tails.add(val);
            else
                tails.set(tail, val);
        }
        return tails.size();
    }
}
