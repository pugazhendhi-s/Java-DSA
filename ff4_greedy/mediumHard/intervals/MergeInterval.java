package com.ff4_greedy.mediumHard.intervals;
import java.util.*;

public class MergeInterval {

    public static void main(String[] args) {

    }

    public int[][] merge(int[][] intervals) {
        // i[0] sort by intervals[i][0] start
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
        List<int[]> res = new ArrayList<>();
        int[] newInterval = intervals[0];
        res.add(newInterval);
        for (int[] interval : intervals) {
            if(interval[0] <= newInterval[1])
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            else {
                newInterval = interval;  // adding new interval, previous interval ended
                res.add(newInterval);
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
