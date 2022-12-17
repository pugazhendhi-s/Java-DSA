package com.ff3_slidingWindow.medium;
import java.util.*;

public class p3_FruitBaskets {

    public static void main(String[] args) {

    }

    public int totalFruit(int[] fruits) {

        int n = fruits.length;
        Map<Integer, Integer> map = new HashMap<>();

        int start = 0; int maxFruits = 0;

        for (int end = 0; end < n; end++) {
            map.put(fruits[end], end);
            if (map.size() > 2) {
                int minIndex = Collections.min(map.values());
                map.remove(fruits[minIndex]);
                start = minIndex+1;
            }
            maxFruits = Math.max(maxFruits, end + 1- start);
        }
        return maxFruits;
    }

    public int totalFruitEff(int[] fruits) {

        int maxLen = 0, len = 0;
        int a = 0, b = 0;
        int cntB = 0;

        for (int c : fruits) {
            len = (c == a || c == b) ? len+1 : cntB + 1;
            cntB = (c == b) ? cntB+1 : 1;
            if (b != c) {
                a = b;
                b = c;
            }
            maxLen = Math.max(len, maxLen);
        }
        return maxLen;
    }
}
