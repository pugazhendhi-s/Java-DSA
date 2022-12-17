package com.f8_recursion.r6_problems.Medium;

import java.util.ArrayList;
import java.util.List;

public class CombineTarget {
    public static void main(String[] args) {

    }
    // Combination , check combination of arr values equals sum - BackTracking
    static void combinationI(){
        int[] a = {2,3,6,7};
        int target = 7;
        List<List<Integer>> list = combinationI(a, target, 0, new ArrayList<>(), new ArrayList<>());
        System.out.println(list);
    }

    private static List<List<Integer>> combinationI(int[] a, int target, int start, List<Integer> temp, List<List<Integer>> list) {
        if(target < 0)
            return list;
        if(target == 0){
            list.add(new ArrayList<>(temp));
            return list;
        }
        for (int i = 0; i < a.length; i++) {
            temp.add(a[i]);
            combinationI(a, target-a[i], i, temp, list);
            temp.remove(temp.size()-1);
        }
        return list;
    }

    static void combinationII(){
        int[] a = {2,2,3,3,4,5,7};
        // if array not sorted means , sort the array in order combine duplicate
        int target = 9;
        List<List<Integer>> list = combinationII(a, target, 0, new ArrayList<>(), new ArrayList<>());
        System.out.println(list);
    }
    private static List<List<Integer>> combinationII(int[] a, int tar, int start, List<Integer> temp, List<List<Integer>> list){
        if(tar == 0){
            list.add(new ArrayList<>(temp));
            return list;
        }
        for (int i = start; i < a.length; i++) {
            if(i > start && a[i] == a[i-1])
                continue;
            if(tar - a[i] < 0)
                break;
            temp.add(a[i]);
            combinationII(a, tar-a[i], i+1,temp, list);
            temp.remove(temp.size()-1);
        }
        return list;
    }
}
