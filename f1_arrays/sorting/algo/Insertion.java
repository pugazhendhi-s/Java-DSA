package com.f1_arrays.sorting.algo;

import java.util.Arrays;

public class Insertion {

    public static void insertionSort(int[] a){
        int len = a.length;
        for (int i = 1; i < len; i++) {
            int j = i-1; int curVal = a[i];
            while ( j >= 0 && a[j] > curVal){
                a[j+1] = a[j];
                j--;
            }
            a[j+1] = curVal;
        }
    }
    public static void insertion(int[] nums){
        for (int i = 1; i < nums.length; i++) {
            int j; int curVal = nums[i];
            for (j = i-1; j >= 0 && nums[j] > curVal; j--) {
                nums[j+1] = nums[j];
            }
            nums[j+1] = curVal;
        }
        System.out.println(Arrays.toString(nums));
    }
    public static void main(String[] args) {
        int[] nums = {3,2,5,4,0,4,1,1,1,1,1,-2,-3,-5,-6};
        recursiveI(nums, 1);
        System.out.println(Arrays.toString(nums));
    }
    public static void recursiveInsertion(int[] nums, int r, int c, int curVal){
        if(c >= 0 && curVal < nums[c]){
            nums[c+1] = nums[c];
            recursiveInsertion(nums, r, c-1, curVal);
        }
        else{
            nums[c+1] = curVal;
            if(r+1 < nums.length)
                recursiveInsertion(nums, r+1, r, nums[r+1]);
        }
    }
    public static void recursiveI(int[] nums, int i){
        if(i == nums.length)
            return;
        int last = nums[i];
        int j = i-1;
        while (j >= 0 && nums[j] > last){
            nums[j+1] = nums[j];
            j--;
        }
        nums[j+1] = last;
        recursiveI(nums, i+1);
    }
}
