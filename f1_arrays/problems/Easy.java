package com.f1_arrays.problems;

import java.util.*;

import static java.util.Arrays.copyOf;
import static java.util.Arrays.sort;

public class Easy {
    public static void main(String[] args) {
        sortByFrequency();
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    static Scanner sc = new Scanner(System.in);

    static void pairSumArray() {
        /**
         * Input : [15, 13, 11, 10, 12, 10, 9, 8, 7, 5]
         * Output : [8, 7, 5, 3, 2]
         */
        int[] pair = {15, 13, 11, 10, 12, 10, 9, 8, 7, 5};   //8 7 5 3 2
        int n = 5;  // n * (n-1)/2 = va1+1;
        int[] a = new int[n];
        a[0] = (pair[0] + pair[1] - pair[n - 1]) / 2;  //  8+7 + 8+5 -(7+5)
        //  15+13 - 12 = 16 (7,5 removed, only 8,8 = 16 /2 =8 [0])
        for (int i = 0; i < n - 1; i++) {
            a[i + 1] = pair[i] - a[0];
        }
        System.out.println("Output : " + Arrays.toString(a));
    }

    static void triangleArray() {
        int[] a = {10, 21, 22, 100, 101, 200, 300};
        Arrays.sort(a);
        int count = 0;
        for (int i = 0; i < a.length - 2; i++) {
            for (int j = i + 1; j < a.length - 1; j++) {
                if (a[i] + a[j] > a[j + 1])
                    count++;
            }
        }
        System.out.println("Number of Triangle formed : " + count);
    }

    static void medianSortSubArray() {
        int[] a = {5, 15, 1, 3, 2, 8, 7, 9, 10, 6, 11, 4};
        int n = a.length;
        int median = 0;
        for (int i = 1; i < n; i++) {
            int j;
            int current = a[i];
            for (j = i - 1; j >= 0 && a[j] > current; j--) {
                a[j + 1] = a[j];
            }
            a[j + 1] = current;
            if (i % 2 != 0) {
                median = (a[i / 2] + a[(i / 2) + 1]) / 2;
                System.out.print(median + " ");
            } else {
                median = a[(i / 2)];
                System.out.print(median + " ");
            }
        }
        System.out.println("\n" + Arrays.toString(a));
    }

    static void unionOfArrays() {
        int[] a = {1, 2, 2, 2, 2, 3, 7, 8, 9};
        int[] b = {0, 4, 5, 5, 5, 5, 5, 99, 10};
        HashSet<Integer> hs = new HashSet<>();
        for (int i : a)
            hs.add(i);
        for (int i : b)
            hs.add(i);
        System.out.println(hs);
    }

    static void minSwap() {
        int[] a = {9, 1, 3, 8};
        int[] temp = copyOf(a, a.length);
        sort(temp);
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if (temp[i] != a[i]) {
                count++;
                swap(a, i, indexOf(a, temp[i]));
            }
        }
        System.out.println(count);
    }

    static int indexOf(int[] arr, int element) {
        int i = 0;
        for (i = 0; i < arr.length; i++) {
            if (arr[i] == element)
                break;
        }
        return i;
    }

    static void minSwapHash() {
        int[] a = {4, 3, 2, 1};
        int[] temp = copyOf(a, a.length);
        sort(temp);
        int count = 0;

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            hashMap.put(a[i], i);
        }

        for (int i = 0; i < a.length; i++) {
            if (temp[i] != a[i]) {
                count++;
                int init = a[i];
                swap(a, i, hashMap.get(temp[i]));
                hashMap.put(init, hashMap.get(temp[i]));
                hashMap.put(temp[i], i);
            }
        }
        System.out.println(Arrays.toString(a));
        System.out.println("Swap count : " + count);
    }

    static void longestUnsortedSubarray() {
        int[] a = {2, 6, 4, 8, 10, 9, 15};
        int n = a.length;
        int[] temp = a.clone();
        Arrays.sort(temp);
        int start = n;
        int end = 0;
        for (int i = 0; i < n; i++) {
            if (temp[i] != a[i]) {
                start = Math.min(start, i); // i  = 2
                end = Math.max(end, i);  // i = 2
            }
        }
        System.out.println(start + " to " + end);
    }

    static void subArrayUnsorted_toSorted() {
        int[] a = {10, 12, 20, 30, 25, 40, 32, 31, 35, 50, 60};
        int n = a.length;
        System.out.println("Array Length = " + n);
        int s;
        int e;
        // (left to right)finding first element which greater than next element.
        for (s = 0; s < n - 1; s++) {
            if (a[s] > a[s + 1])
                break;
        }
        if (s == n - 1) {
            System.out.println("Array is already sorted");
            return;
        }
        // (right to left)finding element which small to previous element.
        for (e = n - 1; e >= 0; e--) {
            if (a[e] < a[e - 1])
                break;
        }
        // now traverse array from s+i < e to find minimum and maximum element.
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = s + 1; i < e; i++) { // till > s and e < are sorted, so s+1, e-1
            if (a[i] < min)
                min = a[i];
            if (a[i] > max)
                max = a[i];
        }
        for (int i = 0; i < s; i++) {
            if (a[i] > min) {
                s = i;
                break;
            }
        }
        for (int i = n - 1; i >= e; i--) {
            if (a[i] < max) {
                e = i;
                break;
            }
        }
        System.out.printf("The unsorted subarray which makes the given array \n" +
                "sorted lies between the indexes (%d, %d), Values (%d, %d)", s, e, a[s], a[e]);
    }

    static void subArrayEqualSum() {
        //Only for positive elements
        int[] a = {2, 1, 9, 4, 6, 40, 70};
        int n = a.length;
        int sum = 21;
        int curSum = 0;
        int start = 0;
        for (int i = 0; i < n; i++) {
            curSum += a[i];
            while (curSum > sum) {
                curSum -= a[start++];
            }
            if (curSum == sum) {
                System.out.println(
                        "Sum found between indexes " + start
                                + " and " + (i));
                return;
            }
        }
        System.out.println("Using HashMap for both positive and negative");
        int[] b = {2, 6, 7, 34, 45, 100, 5};
        sum = 179;
        curSum = 0;
        start = 0;
        int end = -1;
        int i;
        Map<Integer, Integer> hs = new HashMap<>();

        for (i = 0; i < n; i++) {
            curSum += b[i];
            if (curSum - sum == 0) {
                end = i;
                break;
            }
            if (hs.containsKey(curSum - sum)) {
                // curSum - sum gives starting of subArray. eg. curSum add upto 194 , i = 5 .
                // now if we do (curSum - sum) ans 15, if 15 means before this index we not need;
                start = hs.get(curSum - sum) + 1;
                end = i;
                break;
            }
            hs.put(curSum, i);
        }
        System.out.println("Sum is found to be index from " + start + " to " + end);
    }

    static void ceilingFlooring() {
        int[] A = {1, 2, 3, 4, 5, 6, 9};
        System.out.println("Enter number to find ceiling and flooring");
        int x = sc.nextInt();
        int len = A.length;
        if (x < A[0])
            System.out.println("Floor does't exits\nCiel = " + A[0]);
        else if (x > A[len - 1])
            System.out.println("Floor = " + A[len - 1] + "\nCiel doesn't exits");
        else {
            for (int i = 0; i < len; i++) {
                if (x == A[i])
                    System.out.println("Floor : " + A[i] + "\nCiel : " + A[i]);
                else if (A[i] < x && A[i + 1] > x) {
                    System.out.println("Floor : " + A[i] + "\nCiel : " + A[i + 1]);
                }
            }
        }
    }

    static void closestPairSum() {
        // Two Pointer Technique
        System.out.println("Closest pair to sum in two sorted arrays");
        int[] a = {1, 4, 5, 7};
        int[] b = {10, 20, 30, 40};
        int x = 32;
        int res_a = -1, res_b = -1;
        int diff = Integer.MAX_VALUE;
        int left = 0;
        int right = b.length - 1;  // left for array a and right for array b
        while (left < a.length && right >= 0) {
            if (Math.abs(a[left] + b[right] - x) < diff) {
                res_a = left;
                res_b = right;
                diff = Math.abs(a[left] + b[right] - x);
            }
            if (Math.abs(a[left] + b[right]) > x)
                right--;
            else
                left++;
        }
        System.out.printf("Closest pair to %d is (%d,%d) = %d", x, a[res_a], b[res_b], Math.abs(a[res_a] + b[res_b]));
    }

    static void tripletSum() {
        System.out.println("Triplet sum");
        int[] arr = {3, 2, 5, 0, 7, 1};
        int sum = 15;
        HashSet<Integer> set = new HashSet<>();
        outer:
        for (int i = 0; i < arr.length - 2; i++) {
            int cur_sum = sum - arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (set.contains(cur_sum - arr[j])) {
                    System.out.printf("Triplet sum %d is (%d, %d, %d)", sum, arr[i], arr[j], cur_sum - arr[j]);
                    break outer;
                }
                set.add(arr[j]);
            }
        }

    }

    static void pairSum() {
        int[] a = {1, 5, 2, 8, 6, 9};
        int sum = 10;
        HashSet<Integer> set = new HashSet<>();
        for (int j : a) {
            int temp = sum - j;
            if (set.contains(temp)) {
                System.out.printf("Pair with given sum %d is (%d, %d)", sum, temp, j);
            }
            set.add(j);
        }
    }

    static void pairCounts() {
        List<Integer> a = new ArrayList<>(Arrays.asList(1, 1, 3, 1, 2, 1, 3, 3, 3, 3));
        HashSet<Integer> hs = new HashSet<>();
        int pair = 0;
        for (Integer integer : a) {
            if (!hs.contains(integer))
                hs.add(integer);
            else {
                pair++;
                hs.remove(integer);
            }
        }
        System.out.println(pair);
    }

    static void countOfInversions() {
        System.out.println("Count of Inversions");
        int[] a = {8, 4, 2, 1};
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j])
                    count++;
            }
        }
        System.out.println("Count of inversions : " + count);
    }

    static void elementCount() {
        int[] arr = {2, 3, 4, 5, 6, 3, 3, 4, 22, 22, 2, 1};
        int n = arr.length;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            int count = 1;
            if (visited[i])
                continue;
            for (int j = i + 1; j < n; j++) {
                if (arr[i] == arr[j]) {
                    count++;
                    //{ 0, 4, 5, 5, 6, 7, 7, 7 }
                    visited[j] = true;
                }
            }
            System.out.printf("Element : %d => Occurring : %d\n", arr[i], count);
        }
    }

    static void sortByFrequency() {
        System.out.println("Sort by Frequency");
        int[] a = {7, 7, 7, 7, 7, 2, 2, 3, 3, 3, 3, 3, 3};
        Arrays.sort(a);
        int[][] b = new int[a.length][2];
        int index = 0;
        b[index][0] = a[0];
        // Finding Frequency
        for (int i = 1; i < a.length; i++) {
            if (a[i] == a[i - 1])
                b[index][1] += 1;
            else {
                index++;
                b[index][0] = a[i];
            }
        }
        index++;
        System.out.println("No of rows in B 2D array : " + index);
        // Sort by Frequencies
        int[] temp;
        for (int i = 0; i < index; i++) {
            for (int j = i + 1; j < index; j++) {
                if (b[i][1] < b[j][1]) {
                    temp = b[i];
                    b[i] = b[j];
                    b[j] = temp;
                }
            }
        }
        // Printing
        for (int i = 0; i < index; i++) {
            for (int j = 0; j <= b[i][1]; j++) {
                System.out.print(b[i][0]);
            }
            System.out.print(" ");
        }

        /*// Created 2D array to match element with its frequency
        // and no of row is a.length and column is two(element and its frequency (3, 4))
        int b[][] = new int[a.length][2];
        // index is required to traverse B array.
        int index = 0;
        // Sorted array {2, 2, 5, 5, 6, 8, 8, 8}
        b[index][0] = a[0];  // 2 element b[index][0] 0 for element / b[index][1] for frequency
        for (int i = 1; i < a.length; i++) {
            // Due to sorted array , if previous element is equal to current element ,then we increase frequency
            if(a[i] == a[i-1]){
                b[index][1] = b[index][1] + 1; // frequency is added
            }
            else {
                index++; // if previous element is not equal to current element ,then we increase index.
                b[index][0] = a[i];
            }
        }
        // here index = 3 (0-3), but size of array is 4, need to increment index+1 for size 4.
        index++;
        // now sort 2D array by frequencies b[index][1]
        // create temp array for swapping purpose
        int temp[];  // swapping of 2D array
        for (int i = 0; i < index; i++) {
            for (int j = i+1; j < index; j++) { // b[i][1] refers frequency , it will execute
                if(b[i][1] < b[j][1]){
                    temp = b[i]; // transferring a row;
                    b[i] = b[j];
                    b[j] = temp;
                }
            }
        }
        // Print
        for (int i = 0; i < index; i++) {
            // here j = 0 refers frequency also calculated from 0 to count of element (freq = 2 means element occur 3 times)
            for (int j = 0; j <= b[i][1]; j++) {
                System.out.print(b[i][0]);
            }
            System.out.print(" ");
        }
        */
    }

    static void waveFormArray() {
        System.out.println("Wave form array.");
        int[] a = {10, 5, 6, 3, 2, 20, 100, 80};
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
        for (int i = 0; i < a.length - 1; i += 2) {
            int temp = a[i];
            a[i] = a[i + 1];
            a[i + 1] = temp;
        }
        System.out.println(Arrays.toString(a));
    }

    static void alternativeSortSortedArray() {
        int[] a = {1, 9, 4, 2, 5, 7, 0, 3, 6};
        int[] b = new int[a.length];
        Arrays.sort(a);
        int i = 0, j = a.length - 1;
        int k = 0;
        while (i <= j) {
            b[k++] = a[j--];
            b[k++] = a[i++];
        }
        if (a.length % 2 != 0)
            b[k] = a[i];
        System.out.println(Arrays.toString(b));
    }

    static void alternativeSort() {
        // Bubble sort
        System.out.println("Alternative Sort");
        int[] a = {1, 6, 9, 4, 3, 7, 8, 2, 0, 5};
        System.out.println(Arrays.toString(a));
        int len = a.length;
        for (int i = 0; i < len; i += 2) {
            for (int j = 0; j < len - 2 - i; j += 2) {
                if (a[j] < a[j + 2]) {
                    int temp = a[j];
                    a[j] = a[j + 2];
                    a[j + 2] = temp;
                }
            }
        }
        for (int i = 1; i < len; i += 2) {
            for (int j = 1; j < len - 1 - i; j += 2) {
                if (a[j] > a[j + 2]) {
                    int temp = a[j];
                    a[j] = a[j + 2];
                    a[j + 2] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(a));
    }

    static void firstRepeatingChar() {
        System.out.println("First Repeating Element");
        int[] arr = {2, 1, 5, 3, 9, 8, 9, 0};
        int n = arr.length;
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        int result = sum - (n - 1) * n / 2;
        System.out.println("Repeated Element : " + result);
        System.out.println("Using Hashmap to find repeating element ");
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (hashMap.containsKey(arr[i])) {
                result = arr[i];
                break;
            }
            hashMap.put(arr[i], i);
        }
        System.out.println("Hashmap result : " + result);
    }

    static void evenDigits() {
        int evenCount = 0;
        int[] a = {12, 345, 2, 6, 7896};
        for (int x : a) {
            int count = countDigit(x);
            evenCount += checkEvenDigit(count) ? 1 : 0;
        }
        System.out.println(evenCount);
    }

    static boolean checkEvenDigit(int num) {
        return num % 2 == 0;
    }

    static int countDigit(int num) {
        num *= (num < 0) ? -1 : 1;  // edge case
        if (num == 0)
            return 1;
        int count = 0;
        while (num > 0) {
            count++;
            num /= 10;
        }
        return count;
    }

    static int countDigitLog(int num) {
        num *= (num < 0) ? -1 : 1;  // edge case
        return (int) (Math.log10(num)) + 1;
    }
}
