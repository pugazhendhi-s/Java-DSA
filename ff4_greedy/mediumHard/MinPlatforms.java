package com.ff4_greedy.mediumHard;

import java.util.Arrays;

public class MinPlatforms {

    public static void main(String[] args) {
        int []arr = {900, 940, 950, 1100, 1500, 1800};
        int []dep = {910, 1200, 1120, 1130, 1900, 2000};

        int minPlatforms = findPlatform(arr, dep, arr.length);

        System.out.println(minPlatforms);

    }

    public static int findPlatform(int[] arr, int[] dep, int n) {

        Arrays.sort(arr);
        Arrays.sort(dep);

        int res = 0;
        int platform = 1;

        int i = 1, j = 0;

        while (i < n && j < n) {
            if (arr[i] <= dep[j]) {
                platform++;
                i++;
            }

            else if (arr[i] > dep[j]) {  // train departed
                platform--;
                j++;
            }

            if (platform > res) res = platform;
        }

        return res;
    }
}
