package com.ff3_slidingWindow.hard;
import java.util.*;

public class p1_CountKDistinct {

    public static void main(String[] args) {

        int[] nums = {1, 1, 2, 1, 1, 3, 3, 2, 4, 2};
        int k = 3;
        int[] temp = {1, 2, 1, 2, 3};
        int count = subarraysWithKDistinct(temp, 2);
        System.out.println(count);
    }

    public static int subarraysWithKDistinct(int[] nums, int k) {

        return atmost(nums, k) - atmost(nums, k-1);
    }

    public static int atmost(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;

        for (int j = 0, i = 0; j < nums.length; j++) {
            if (map.getOrDefault(nums[j], 0) == 0) {
                k--;
            }
            map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
            while (k < 0) {
                map.put(nums[i], map.get(nums[i])-1);
                if (map.get(nums[i]) == 0) k++;  // we are not removing character, instead we increase k,
                i++;
            }
            count += (j + 1 -i);
        }
        return count;
    }

    public static int atmostRemove(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;

        for (int j = 0, i = 0; j < nums.length; j++) {
            map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
            while (map.size() > k) {
                map.put(nums[i], map.get(nums[i])-1);
                if (map.get(nums[i]) == 0) map.remove(nums[i]);  // here we are removing char
                i++;
            }
            count += (j + 1 -i);
        }
        return count;
    }
    // new approach
    public static int kDistinct(int[] nums, int k) {

        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int maxInd = 0; // max index with exact k distinct
        int i = 0, j = 0;
        int count = 0;

        while (j < n) {
            map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
            if (map.size() == k) {
                maxInd = j;
                while (maxInd + 1 < n) {
                    if ( ! map.containsKey(nums[maxInd + 1]))
                        break;
                    maxInd++;
                }
            }
            while (map.size() == k) {
                if (map.get(nums[i]) == 1) {
                    map.remove(nums[i]);
                }
                else map.put(nums[i], map.get(nums[i]) - 1);
                count += (maxInd + 1 - j);
                i++;
            }
            j++;
        }
        return count;
    }
}
