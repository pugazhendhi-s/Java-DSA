package com.ff3_slidingWindow.hard;

import java.util.*;

public class p2_KDistinct {

    public static void main(String[] args) {

        String s = "aabacbebebe";
        int k = 3;
        int longest = longest_K_Substring(s, k);
        System.out.println(longest);

        // exactly K unique characters
    }

    public static int longest_K_Substring(String s, int k) {

        Map<Character, Integer> map = new HashMap<>();
        int maxLen = 0;

        for (int j = 0, i = 0; j < s.length(); j++) {
            map.put(s.charAt(j), j);
            if (map.size() > k) {
                int minIndex = Collections.min(map.values());
                map.remove(s.charAt(minIndex));
                i = minIndex + 1;
            }
            if (map.size() == k) {   // exactly k distinct , if atmost means remove the if condition only
                maxLen = Math.max(maxLen, j + 1 - i);
            }
        }
        return maxLen;
    }
}


