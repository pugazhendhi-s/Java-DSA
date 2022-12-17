package com.ff3_slidingWindow.medium;
import java.util.*;

public class p4_LongestRepeatChar {

    public static void main(String[] args) {


    }

    public static int characterReplacement(String s, int k) {

        int n = s.length();
        int freq = 0;
        int maxLen = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (int end = 0, start = 0; end <n; end++) {
            char ch = s.charAt(end);
            map.put(ch, map.getOrDefault(ch, 0)+1);
            freq = Math.max(freq, map.get(ch));

            int replace = (end + 1 - start) - freq;
            if (replace > k) {
                map.put(s.charAt(start), map.get(s.charAt(start)) - 1);
                start++;
            }
            maxLen = Math.max(maxLen, end + 1 - start);
        }

        return maxLen;
    }

    public static int charReplace(String s, int k) {

        int n = s.length();
        int[] freq = new int[26];
        int mostFreq = 0;
        int maxLen = 0;

        for (int j = 0, i = 0; j < n; j++) {
            char ch = s.charAt(j);
            freq[ch -'A']++;
            mostFreq = Math.max(mostFreq, freq[ch-'A']);

            int lettersToReplace = (j + 1 - i) - mostFreq;
            if (lettersToReplace > k) {
                char ii = s.charAt(i);
                freq[ii - 'A']--;
                i++;
            }
            maxLen = Math.max(maxLen, (j + 1 - i));
        }
        return maxLen;
    }
}
