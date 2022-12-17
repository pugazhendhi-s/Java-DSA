package com.ff4_greedy.mediumHard.intervals;

import java.util.*;

public class NonOverlapping{

    public static void main(String[] args) {

    }

    public static int eraseOverlapIntervals(int[][] ints) {

        Arrays.sort(ints, Comparator.comparingInt(i -> i[0])); // by start

        int prevEnd = ints[0][1];
        int remove = 0;

        int i = 1;
        while (i < ints.length) {

            if (prevEnd > ints[i][0]) {
                remove++;
                prevEnd = Math.min(prevEnd, ints[i][1]);
            }
            else {
                prevEnd = ints[i][1];
            }
            i++;
        }
        return remove;
    }

    public static int erase(int[][] it) {

        Arrays.sort(it, Comparator.comparingInt(i -> i[1])); // by end
        int nonOverlap = 1;

        int end = it[0][1];
        for (int i = 1; i < it.length; i++) {
            if (it[i][0] >= end) {
                nonOverlap++;
                end = it[i][1];
            }
        }
        return it.length - nonOverlap;
    }

}
