package com.f8_recursion.r6_problems.Medium;

import java.util.ArrayList;

public class FindTargetSumWays {
    public static void main(String[] args) {
        findTargetSumWays();
    }

    public static void findTargetSumWays(){
        int[] nums= {6};
        int targetSum = 1;
        findTargetSumWays(nums, 0, targetSum, 0, "");
        System.out.println(count);
        System.out.println(list);
    }
    static int count = 0;
    static ArrayList<String> list = new ArrayList<>();
    private static void findTargetSumWays(int[] nums, int index, int target, int cur, String p){
        if(index == nums.length){
            if(cur == target) {
                list.add(p);
                count++;
            }
        }

        else{
            findTargetSumWays(nums, index+1, target, cur+nums[index], p+"+"+nums[index]);
            findTargetSumWays(nums, index+1, target, cur-nums[index], p+"-"+nums[index]);
        }
    }
}
