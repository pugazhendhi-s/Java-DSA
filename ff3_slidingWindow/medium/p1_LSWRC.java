package com.ff3_slidingWindow.medium;

import java.util.*;

public class p1_LSWRC {

    public static void main(String[] args) {

        String str = "abcabefc"; // ans : edfgr
        int maxLen = lengthOfLongestSubstring(str);
        System.out.println(maxLen);
    }

    public static int lengthOfLongestSubstring(String s) {

        Map<Character, Integer> map = new HashMap<>();
        int maxLen = 0, n = s.length();
        int i=0;
        for (int j=0; j<n; j++) {
            char ch = s.charAt(j);
            if (map.containsKey(ch)) {
                i = Math.max(i, map.get(ch)+1);
            }
            map.put(ch, j);
            maxLen = Math.max(maxLen, j+1-i);
        }
        // using array
        int result = 0;
        int[] cache = new int[256];
        i=0;
        for (int j = 0; j < s.length(); j++) {
            char ch = s.charAt(j);
            if (cache[ch] > 0) {
                i = Math.max(i, cache[ch]);
            }
            cache[ch] = i + 1; // right shifting
            result = Math.max(result, j+1 - i);
        }

        return maxLen;
    }

    public static int llsByList(String s) {

        ArrayList<Character> list = new ArrayList<>();
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!list.contains(ch)) {
                list.add(ch);
                maxLen = Math.max(maxLen, list.size());
            }
            else {
                while (list.contains(ch)) {
                    list.remove(0);
                }
                list.add(ch);
            }
        }
        return maxLen;
    }


}
