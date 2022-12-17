package com.f8_recursion.r4_string;

import java.util.*;

public class SubSet {
    public static void main(String[] args) {
        int []arr = {2, 2, 3};
        List<List<Integer>> set = subsetEff(arr);
        for (List<Integer> list : set) {
            System.out.println(list);
        }
    }
    public static List<String> subSeq(String ans, String s,List<String> list){
        if(s.length() == 0) {
            list.add(ans);
            return list;
        }
        char ch = s.charAt(0);
        subSeq(ans+ch, s.substring(1), list);
        subSeq(ans, s.substring(1), list);
        return list;
    }
    // 2 methods ArrayList as argument or in body for this taking body
    public static ArrayList<String> subSeqAscii(String ans, String s){
        // u can do like previous problem
        if(s.isEmpty()){
            ArrayList<String> list = new ArrayList<>();
            list.add(ans);
            return list;
        }
        char ch = s.charAt(0);
        ArrayList<String> first = subSeqAscii(ans, s.substring(1));
        ArrayList<String> second = subSeqAscii(ans+ch, s.substring(1));
        ArrayList<String> third = subSeqAscii(ans + (int)ch, s.substring(1));
        first.addAll(second);
        first.addAll(third);
        return first;
    }
    // without changing the order of array

    public static List<List<Integer>> subset(int[] arr){
        List<List<Integer>> outer = new ArrayList<>();
        outer.add(new ArrayList<>());
        for (int num : arr) {
            int n = outer.size();
            for (int i = 0; i < n; i++) {
                List<Integer> inner = new ArrayList<>(outer.get(i)); // this creates copy pf outer array
                inner.add(num);
                if(!outer.contains(inner)) // removing duplicates
                    outer.add(inner);
            }
        }
        return outer;
        /*
        int []arr = {1, 2, 2};
        List<List<Integer>> set = subset(arr);
        for (List<Integer> list : set) {
            System.out.println(list);
        }
         */
    }
    public static List<List<Integer>> subsetEff(int[] arr){
        Arrays.sort(arr);
        List<List<Integer>> outer = new ArrayList<>();
        outer.add(new ArrayList<>());
        int start;
        int end = 0;
        for (int i=0; i < arr.length; i++) {
            start = 0;
            if(i > 0 && arr[i] == arr[i-1])
                start = end + 1;
            end = outer.size()-1;
            for (int j = start; j <= end; j++) {
                List<Integer> inner = new ArrayList<>(outer.get(j)); // this creates copy pf outer array
                inner.add(arr[i]);
                outer.add(inner);
            }
        }
        return outer;
    }

}
