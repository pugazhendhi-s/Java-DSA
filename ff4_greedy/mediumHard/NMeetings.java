package com.ff4_greedy.mediumHard;

import java.util.Arrays;
import java.util.Comparator;

public class NMeetings {

    public static void main(String[] args) {
        
        int[] start = {0, 1, 3, 5, 5, 8};
        int[] end   = {6, 2, 4, 7, 9, 9};

        int maxMeetings = maxMeetings(start, end, end.length);
        System.out.println(maxMeetings);
    }

    public static int maxMeetings(int[] start, int[] end, int n) {

        int[][] interval = new int[n][2];

        for (int i = 0; i < n; i++) {
            interval[i][0] = start[i];
            interval[i][1] = end[i];
        }

        Arrays.sort(interval, Comparator.comparingInt(a -> a[1])); // sort by end

        int prevEnd = 0;
        int meet = 1;

        for (int currStart = 1; currStart < n; currStart++) {

            if (interval[currStart][0] > interval[prevEnd][1]) {
                meet++;
                prevEnd = currStart;
            }
        }
        return meet;
    }
}
