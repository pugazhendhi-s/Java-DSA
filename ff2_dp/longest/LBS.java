package com.ff2_dp.longest;

import java.util.Arrays;

public class LBS {

    public static void main(String[] args) {

    }

    public static int longestBitonicSequence(int[] arr, int n) {

        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        Arrays.fill(dp1, 1);
        Arrays.fill(dp2, 1);

        // Strictly increasing from 0
        for (int ind = 0; ind < n; ind++) {
            for (int pre = 0; pre < ind; pre++) {
                if (arr[ind] > arr[pre] && 1 + dp1[pre] > dp1[ind]) {
                    dp1[ind] = 1 + dp1[pre];
                }
            }
        }
        // reverse -> strictly increasing from n-1
        for (int ind = n-1; ind >= 0; ind--) {
            for (int pre = n-1; pre > ind; pre--) {
                if (arr[ind] > arr[pre] && 1 + dp2[pre] > dp2[ind]) {
                    dp2[ind] = 1 + dp2[pre];
                }
            }
        }
        int maxBitonic = 0;

        for (int i = 0; i < n; i++) {
            int bitonic = dp1[i] + dp2[i] - 1;
            maxBitonic = Math.max(maxBitonic, bitonic);
        }
        return maxBitonic;
    }
}

