package com.ff3_slidingWindow.medium;

import java.util.HashMap;
import java.util.Map;

public class p6_NiceSubArrays {

    public static void main(String[] args) {

        int[] nums = {2,2,2,1,2,2,1,2,2,2};
        int count = numberOfSubarrays(nums, 2);
        System.out.println(count);
    }

    public static int numberOfSubarrays(int[] nums, int k) {

        int totalCount = 0;
        int currCount = 0;

        for (int j=0, i=0; j<nums.length; j++) {
            if (nums[j] % 2 == 1) {
                k--;
                currCount = 0;  // this will be when it found odd numbers,
                                // because odd numbers make new subarray
            }
            while (k == 0) {
                k = k + (nums[i++] % 2); // whenever it found any odd , loops ends
                currCount++;
            }

            totalCount += currCount;// currCount is continuously added , till it found another odd number
                                    // because it all are part for same subarray
        }

        return totalCount;
    }

    public static int byHash(int[] nums, int k) {

        int totCount = 0;
        int curCount = 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int num : nums) {
            curCount += num % 2;
            totCount += map.getOrDefault(curCount - k, 0);
            map.put(curCount, map.getOrDefault(curCount, 0)+1);
            // expanded
            /*
                if (num % 2 == 1) {
                    curCount += 1;  or curCount += num % 2;
                }
                // if it curCount % 2 odd means
                if (map.containsKey(curCount - k)) {
                    totCount += map.get(curCount - k);
                }
            */
        }
        return totCount;
    }
}
