package com.f8_recursion.r5_backTrackking;

import java.util.*;

public class PalindromePartition {

    public static void main(String[] args) {

    }

    public static List<List<String>> partition(String s) {

        List<List<String>> res = new ArrayList<>();
        List<String> list = new ArrayList<>();
        fn(s, list, res);
        return res;
    }

    private static void fn(String s, List<String> list, List<List<String>> res) {
        if (s.length() == 0) {
            res.add(new ArrayList<String>(list));
            return;
        }
        int n = s.length();
        for (int i=0; i<n; i++) {
            if(isPalin(0, i, s)){
                list.add(s.substring(0, i+1));
                fn(s.substring(i+1), list, res);
                list.remove(list.size()-1);
            }
        }
    }

    private static boolean isPalin(int i, int j, String s) {
        while (i <= j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++; j--;
        }
        return true;
    }
}
