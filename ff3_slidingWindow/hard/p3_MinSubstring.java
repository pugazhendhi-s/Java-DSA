package com.ff3_slidingWindow.hard;

import java.util.*;

public class p3_MinSubstring {

    public static void main(String[] args) {

        String s = "aaaaaab";
        String t = "aaaaaa";
        String res = minWindow(s, t);
        System.out.println(res);
    }

    public static String minWindow(String s, String t) {

        int n = s.length(), m = t.length();
        HashMap<Character, Integer> map = new HashMap<>();

        for (int j = 0; j < m; j++) {
            map.put(t.charAt(j), map.getOrDefault(t.charAt(j), 0) + 1);
        }
        int minLen = (int)1e9, start = -1 ,count = 0;

        for (int j = 0, i = 0; j < n; j++) {
            char ch  = s.charAt(j);
            if (map.containsKey(ch)) {
                if (map.get(ch) > 0) count++;
                map.put(ch, map.get(ch) - 1);
            }
            while (count == m) {
                if (minLen > (j + 1 - i)) {
                    minLen = j + 1 - i;
                    start = i;
                }
                char toRemove = s.charAt(i);
                i++;
                if (map.containsKey(toRemove)) {
                    if (map.get(toRemove) == 0) count--;
                    map.put(toRemove, map.get(toRemove) + 1);
                }
            }
        }
        return (start == -1) ? "" : s.substring(start, start + minLen);
    }

    public static String byArray(String s, String t) {

        int[] map = new int[128];
        int n = s.length(), m = t.length();

        for (int j = 0; j < m; j++) {
            map[t.charAt(j)]++;
        }
        int minLen = (int) 1e9 , start = -1, count = 0;

        for (int j = 0, i = 0; j < n; j++) {

            if(map[s.charAt(j)]-- > 0) count++;

            while (count == m) {
                if (minLen > (j + 1 - i)) {
                    minLen = j + 1 - i;
                    start = i;
                }
                char rm = s.charAt(i++);
                if (++map[rm] > 0) count--;
            }
        }
        return (start == -1) ? "" : s.substring(start, start + minLen);
    }
}
