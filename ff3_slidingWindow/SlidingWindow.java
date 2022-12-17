package com.ff3_slidingWindow;

import java.util.Arrays;

public class SlidingWindow {
    public static void main(String[] args) {
        maxFrequencyByIncrementK();
    }

    static void longestSubArrayFlip() {
        int[] a = {1, 0, 0, 1, 0, 1, 0, 1};
        int n = a.length;
        int flip = 2;
        /*
         * finding the longest subarray of 0
         * wil traverse array till (1) count > flip. i at some index
         * now left = 0;
         * then a[left] == 0, count --  > flip ,now we found start position of zero.
         * maxsub = max( maxsum,  i-left+1);
         */

        int left = 0;  // two pointer techinque
        int count = 0;
        int maxLength0 = 0;
        int maxLength1 = 0;
        System.out.println("SubArray with max 0");
        int s0 = 0;
        int e0 = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] == 1)
                count++;
            while (count > flip) {
                if (a[left] == 1)
                    count--;
                left++;
            }
            int curLength = i - left + 1;
            if (maxLength0 < curLength) {
                maxLength0 = curLength;
                s0 = left;
                e0 = i;
            }
        }
        System.out.println("Length 0 = " + maxLength0 + ", Start = " + s0 + ", End = " + e0);
        System.out.println("SubArray with max 1");
        left = 0;
        count = 0;
        int s1 = 0;
        int e1 = 0;

        for (int i = 0; i < n; i++) {
            if (a[i] == 0)
                count++;
            while (count > flip) {
                if (a[left] == 1)
                    count--;
                left++;
            }
            int curLength = i - left + 1;
            if (maxLength1 < curLength) {
                maxLength1 = curLength;
                s1 = left;
                e1 = i;
            }
        }
        System.out.println("Length 1 = " + maxLength1 + ", Start = " + s1 + ", End = " + e1);
        if (maxLength0 > maxLength1) {
            for (int i = s0; i <= e0; i++) {
                System.out.print(a[i] + " ");
            }
            System.out.println("= " + maxLength0);
        } else {
            for (int i = s1; i <= e1; i++) {
                System.out.print(a[i] + " ");
            }
            System.out.println("= " + maxLength1);
        }
    }

    static void maxSubArray() {  // Sliding window
        /**
         *  Input  : arr[] = {100, 200, 300, 400}, k = 2
         * Output : 700
         *
         * Input  : arr[] = {1, 4, 2, 10, 23, 3, 1, 0, 20}, k = 4
         * Output : 39
         * We get maximum sum by adding subarray {4, 2, 10, 23} of size 4.
         *
         * Input  : arr[] = {2, 3}, k = 3
         * Output : Invalid
         * There is no subarray of size 3 as size of whole array is 2.
         *
         */

        int[] a = {1, 4, 2, 10, 23, 3, 1, 0, 20};
        int k = 4;
        int n = a.length;
        int maxSum = 0;
        for (int i = 0; i < k; i++) {
            maxSum += a[i];
        }
        int start = 0;
        int end = 0;
        for (int i = k; i < n; i++) {
            int windnowSum = maxSum - a[i - k] + a[i];
            if (windnowSum > maxSum) {
                maxSum = windnowSum;
                start = i - k + 1;
                end = i;
            }
        }
        for (int i = start; i <= end; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println("= " + maxSum);
    }

    static void maxFrequencyByIncrementK() {
        int[] a = {1, 4, 8, 10, 12};
        Arrays.sort(a);
        long k = 6;
        int curSum = 0;
        int max = -1;
        int right;
        int left = 0;
        /*
         * if you want to make a window to same element , then its
         * total sum of window is (size of window * element) ,
         * eg. size = 3, element = 5, like[5,5,5] means, 3*5 = 15
         * [1,3,5] to change to [5,5,5], do (3*5) < curSum+k , k additional added
         * curSum is window sum (1+3+5)+k = 3*5 achieve this.
         */
        for (right = 0; right < a.length; right++) {
            curSum += a[right];
            while ((long) (right + 1 - left) * a[right] > curSum + k) {
                curSum -= a[left++];
            }
            max = Math.max(max, (right + 1 - left));
        }
        System.out.println("Window Size = " + max);
        for (int i = left; i < right; i++) {
            System.out.print(a[i] + " ");
        }

        // Efficient approach
        System.out.println("\nEfficient approach");
        k = 6;
        left = 0;
        for (right = 0; right < a.length; right++) {
            k += a[right]; // directly adding k to curSum (k+curSum)
            if (k < (long) (right + 1 - left) * a[right])
                k -= a[left++];
        }
        System.out.println("Window Size = " + (right - left));

    }

}
