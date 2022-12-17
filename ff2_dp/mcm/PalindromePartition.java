package com.ff2_dp.mcm;

import java.util.Arrays;
import java.util.stream.IntStream;

public class PalindromePartition {

    public static void main(String[] args) {

    }

    public static int palindromePartitioning(String str) {

        int n = str.length();
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return fn(0, str, dp) - 1;
    }
    private static int fn(int i, String s, int[] dp) {

        if (i == s.length()) return 0;
        if (dp[i] != -1) return dp[i];

        int minCuts = (int)1e9;
        StringBuilder builder = new StringBuilder();
        for (int j = i; j < s.length(); j++) {
            builder.append(s.charAt(j));
            if (isPalin(builder)) {
                int cut = 1 + fn(j+1, s, dp);
                if(cut < minCuts) minCuts = cut;
            }
        }
        return dp[i] = minCuts;
    }

    private static boolean isPalin(StringBuilder sb) {
        int i = 0, j = sb.length()-1;
        while (i <= j) {
            if (sb.charAt(i) != sb.charAt(j)) return false;
            else { i++; j--;}
        }
        return true;
    }

    public static int tabulation(String s) {

        int n = s.length();
        int[] dp = new int[n+1];
        dp[n] = 0;

        for (int i=n-1; i>=0; i--) {
            int minCuts = (int)1e9;
            for (int j = i; j < n; j++) {
                if (isPalin(i, j, s)) {
                    int cut = 1 + dp[j+1];
                    if(cut < minCuts) minCuts = cut;
                }
            }
            dp[i] = minCuts;
        }
        return dp[0]-1;
    }
    private static boolean isPalin(int i, int j, String s) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++; j--;
        }
        return true;
    }

    public int minCut(String s) {

        // dp
        int N = s.length();
        int[] dp = IntStream.range(0, N).toArray(); // initial value: dp[i] = i

        for (int mid = 1; mid <  N; mid++) { // iterate through all chars as mid-point of palindrome
            // CASE 1. odd len: center is at index mid, expand on both sides
            for (int start = mid, end = mid; start >= 0 && end < N && s.charAt(start) == s.charAt(end); start--, end++) {
                int newCutAtEnd = (start == 0) ? 0 : dp[start - 1] + 1;
                dp[end] = Math.min(dp[end], newCutAtEnd);
            }
            // CASE 2: even len: center is between [mid-1,mid], expand on both sides
            for (int start = mid - 1, end = mid; start >= 0 && end < N && s.charAt(start) == s.charAt(end); start--, end++) {
                int newCutAtEnd = (start == 0) ? 0 : dp[start - 1] + 1;
                dp[end] = Math.min(dp[end], newCutAtEnd);
            }
        }
        return dp[N - 1];
    }
}
