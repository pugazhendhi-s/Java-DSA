package com.ff3_slidingWindow.hard;

public class p4_MinSubSequences {

    public static void main(String[] args) {

        String s = "geeksforgeeks";
        String t = "eksrg";
        String seq = minWindow(s, t);
        System.out.println(seq);
    }

    public static String minWindow(String s, String t) {

        int n = s.length();
        int m = t.length();

        int minLen = (int)1e9;
        int start = -1;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != t.charAt(0))
                continue;
            int ind = 1; int j = i+1;
            while (j < n && ind < m) {
                if (s.charAt(j) == t.charAt(ind)) {
                    j++; ind++;
                }
                else j++;
            }
            if (ind == m && minLen > (j-i)) {
                minLen = j-i;
                start = i;
            }
        }
        return (start == -1) ? "" : s.substring(start, start + minLen);
    }
}
