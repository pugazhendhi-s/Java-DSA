package com.ff4_greedy.mediumHard.intervals;


import java.util.ArrayList;
import java.util.Arrays;

public class InsertInterval {

    public static void main(String[] args) {

        int[][] intervals = {{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[] newInterval = {4, 8};
        int[][] mergeInterval = insert(intervals, newInterval);

        for (int[] temp : mergeInterval) {
            System.out.println(Arrays.toString(temp));
        }
    }

    public static int[][] insert(int[][] it, int[] newIt) {

        ArrayList<int[]> list = new ArrayList<>();
        boolean flag = true;
        for (int[] ints : it) {
            if (newIt[0] < ints[0] && flag) {
                list.add(newIt);
                flag = false;
            }
            list.add(ints);
        }
        if (flag) {
            list.add(newIt);
        }
        int prev = list.get(0)[1];
        int i = 1;
        while (i  < list.size()) {

            if (prev >= list.get(i)[0]) {
                prev = Math.max(prev, list.get(i)[1]);
                list.get(i-1)[1] = prev;
                list.remove(i);
            }
            else {
                prev = list.get(i)[1];
                i++;
            }
        }
        int size = list.size();
        int[][] mergeIntervals = new int[size][2];
        i = 0;
        for (int[] temp : list) {
            mergeIntervals[i++] =  temp;
        }
        return mergeIntervals;
    }
}
