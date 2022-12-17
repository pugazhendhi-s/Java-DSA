package com.ff3_slidingWindow.medium;

import java.util.*;

public class p7_AbcChars {

    public static void main(String[] args) {
        String s = "abcabc";
        int count = numberOfSubstrings(s);
        int c1 = byArray(s);
        System.out.println(c1);
        System.out.println(count);
    }

    public static int numberOfSubstrings(String s) {

        int n = s.length();
        int count = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (int j=0, i=0; j<n; j++) {

            map.put(s.charAt(j), map.getOrDefault(s.charAt(j), 0)+1);

            while (map.size() == 3 && i < n) {
                count += (n - j); // elements after
                char ch = s.charAt(i);
                if(map.get(ch) <= 1){
                    map.remove(ch);
                    i++;
                    break;
                }
                map.put(ch, map.getOrDefault(ch, 0)-1);
                i++;
            }
        }
        return count;
    }

    public static int byArray(String s) {

        int[] count = {0, 0, 0};
        int res = 0;
        int i = 0;
        int n = s.length();
        for (int j = 0; j < n; ++j) {
            count[s.charAt(j) - 'a']++;
            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                count[s.charAt(i++) - 'a']--;
            }
            res += i; // element before
        }
        return res;
    }

    public int noSliding(String s) {

        int[] last = {-1, -1, -1};
        int res = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            last[s.charAt(i) - 'a'] = i;
            res += 1 + Math.min(last[0], Math.min(last[1], last[2])); // taking smallest index tell that, we have that possible of combination before that index
        }
        return res;
    }
}
