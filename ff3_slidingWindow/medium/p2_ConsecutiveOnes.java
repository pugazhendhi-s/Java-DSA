package com.ff3_slidingWindow.medium;

public class p2_ConsecutiveOnes {

    public static void main(String[] args) {

    }

    public static int longestOnes(int[] nums, int k) {

        int n = nums.length;
        int s = 0, zeros = 0;
        int longest = 0;

        for (int e = 0; e < n; e++) {
            if (nums[e] == 0) {
                zeros++;
            }
            while (zeros > k) {
                if (nums[s] == 0)
                    zeros--;
                s++;
            }
            longest = Math.max(longest, e + 1 - s);
        }
        return longest;
    }
}

/*
Count Number of Nice Subarrays
Replace the Substring for Balanced String
Max Consecutive Ones III
Binary Subarrays With Sum
Subarrays with K Different Integers
Fruit Into Baskets
Shortest Subarray with Sum at Least K
Minimum Size Subarray Sum

 */
