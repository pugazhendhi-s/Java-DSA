package com.ff3_slidingWindow.medium;

import java.util.*;

public class p5_BinarySum {

    public static void main(String[] args) {

        int[] nums = {1,0,1,0,1};
        int goal = 2;
        int count = numSubArraysWithSum(nums, goal);
        System.out.println(count);
    }

    public static int numSubArraysWithSum(int[] nums, int goal) {

        int prevSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0;

        for (int bin : nums) {
            prevSum += bin;
            if (map.containsKey(prevSum - goal)) {
                count += map.get(prevSum - goal);
            }
            map.put(prevSum, map.getOrDefault(prevSum, 0)+1);
        }
        return count;
    }

    public static int usingArray(int[] nums, int goal) {
        int[] count = new int[nums.length+1];
        int prevSum = 0;
        count[0] = 1;
        int res = 0;

        for (int bin : nums) {
            prevSum += bin;
            if (prevSum >= goal) {
                res += count[prevSum-goal];
            }
            count[prevSum]++;
        }
        return res;
    }
}
