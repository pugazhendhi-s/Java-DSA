package com.ff2_dp.OneD;

import java.util.*;

public class NinjasTraining {

    public static void main(String[] args) {

        int[][] points = {{2,1,3}, {3,4,6}, {10,1,6}, {8,3,7}};
        int maxi = opti(points.length, points);
        System.out.println(maxi);
    }

    public static int ninjaTraining(int n, int[][] pts) {

        int[][] dp = new int[n][4];
        for(int[] it : dp) Arrays.fill(it, -1);
        int last = 3;
        return find(n-1, last, pts, dp);
    }

    // using memo , but TLE -> need to go for tabulation
    private static int find(int day, int last, int[][] pts, int[][] dp) {

        if (dp[day][last] != -1) return dp[day][last];

        if (day == 0) {
            int maxi = 0;
            for (int task = 0; task < 3; task++) {
                if (task != last)
                    maxi = Math.max(maxi, pts[day][task]);
            }
            return dp[0][last] = maxi;
        }

        int maxi = 0;

        for (int task = 0; task < 3; task++) {
            if (task != last) {
                int point = pts[day][task] + find(day-1, task, pts, dp);
                maxi = Math.max(maxi, point);
            }
        }
        return dp[day][last] = maxi;
    }

    private static int tab(int n, int[][] pts) {

        int[][] dp = new int[n][4];
        dp[0][0] = Math.max(pts[0][1], pts[0][2]);
        dp[0][1] = Math.max(pts[0][0], pts[0][2]);
        dp[0][2] = Math.max(pts[0][0], pts[0][1]);
        dp[0][3] = Math.max(pts[0][0], Math.max(pts[0][1], pts[0][2]));   // this is compare everything in that dp row

        for (int day = 1; day < n; day++) {   // tc -> O(N*4*3), sc -> O(N*4)
            for (int last = 0; last < 4; last++) {
                int maxi = 0;
                for (int task = 0; task < 3; task++) {
                    if (task != last) {
                        int point = pts[day][task] + dp[day - 1][task];
                        maxi = Math.max(maxi, point);
                    }
                }
                dp[day][last] = maxi;
            }
        }
        return dp[n-1][3];
    }

    private static int opti(int n, int[][] pts) {  // sp ->  O(4) nearly constant

        int[] prevDay = new int[4];  // prevDay = dp
        prevDay[0] = Math.max(pts[0][1], pts[0][2]);
        prevDay[1] = Math.max(pts[0][0], pts[0][2]);
        prevDay[2] = Math.max(pts[0][0], pts[0][1]);
        prevDay[3] = Math.max(pts[0][0], Math.max(pts[0][1], pts[0][2]));

        for (int day = 1; day < n; day++) {

            int[] temp = new int[4];
            for (int last = 0; last < 4; last++) {
                for (int task = 0; task < 3; task++) {
                    if (task != last) {
                        temp[last] = Math.max(temp[last], pts[day][task] + prevDay[task]);
                    }
                }
            }
            prevDay = temp;
        }
        return prevDay[3];
    }
}
