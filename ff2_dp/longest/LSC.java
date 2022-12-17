package com.ff2_dp.longest;

import java.util.*;

public class LSC {

    public static void main(String[] args) {

        String[] words = {"a","b","ba","bca","bda","bdca"};
    }
    // Time Complexity -> O( (N^2 * I) + NLogN)  // I -> compare two strings , it depends on ith String

    public static int longestStrChain(String[] words) {

        int n = words.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        
        Arrays.sort(words, Comparator.comparingInt(String::length));
        int maxi = 1;
        for (int ind = 0; ind < n; ind++) {
            for (int pre = 0; pre < ind; pre++) {
                if (compare(words[ind], words[pre]) && 1 + dp[pre] > dp[ind]){
                    dp[ind] = 1 + dp[pre];
                }
            }
            if (dp[ind] > maxi) maxi = dp[ind];
        }
        return maxi;
    }

    private static boolean compare(String ind, String pre) {

        int n = ind.length();
        int m = pre.length();

        if (n != m+1) return false;
        int first = 0, second = 0;

        while (first < n) {
            if (second < m && ind.charAt(first) == pre.charAt(second)) {
                first ++; second ++;
            }
            else{
                first ++;
            }
        }
        return first == n && second == m;
    }

    private static int longestChain(String[] words) {

        Map<String, Integer> dp = new HashMap<>();
        Arrays.sort(words, (a, b)-> a.length() - b.length());
        int res = 0;
        for (String word : words) {
            int best = 0;
            for (int i = 0; i < word.length(); ++i) {
                String prev = word.substring(0, i) + word.substring(i + 1);
                best = Math.max(best, dp.getOrDefault(prev, 0) + 1);
            }
            dp.put(word, best);
            res = Math.max(res, best);
        }
        return res;
    }
}
