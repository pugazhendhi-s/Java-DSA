package com.f1_arrays.problems;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class ArrayInterview {
    public static void main(String[] args) {
    }

    static int minJumps(int[] a) {
        int n = a.length;
        if (n <= 1) return 0;
        if (a[0] == 0) return -1;
        int jump = 0;
        int farthest = 0;
        int current = 0;
        for (int i = 0; i < n - 1; i++) { // n-1 because if we last element means no need for further jump
            farthest = Math.max(farthest, a[i] + i);
            if (farthest == n)
                return jump + 1; // whenever reach last element
            if (i == current) {  // 1st jump over with element
                current = farthest;
                jump++;
                if (i >= farthest)
                    return -1;
            }
        }
        return jump;
    }

    static void minJumps() {
        System.out.println("1.Efficient");
        int[] a = {2, 0, 5, 2, 3, 0, 0, 1, 0, 8, 9};
        int jump = minJumps(a);
        System.out.println(jump);
    }

    static void minimizeHeight() {

        int[] a = {2, 6, 3, 4, 7, 2, 10, 3, 2, 1};
        int n = a.length;
        int k = 5;
        // a[i] or value less than k means a[i]+k only possible lowest minimum
        // a[i] or value greater than or equal to k means a[i]+k or a[i]-k possible.
        // we tend to find low possible maximum and minimum by adding with k or sub k
        Arrays.sort(a);
        int diff = a[n - 1] - a[0]; // or a[n-1]-k - a[0]+k
        int min = a[0] + k;
        int max = a[n - 1] - k;
        for (int i = 1; i < n; i++) {
            // a[0]+k is always minimum value which less than k but
            // if value greater than or equal to k in that condition
            // minimum value is possible which is less a[0]+k ie. a[i]-k condition value >= k
            // for eg. { 1,2,5,6}  k = 4; now minimum is (5-K) is minimum and max is 2+4 likewise.
            int curMin = Math.min(min, a[i] - k);
            int curMax = Math.max(max, a[i - 1] + k);
            System.out.println(curMax + " " + curMin);
            diff = Math.min(diff, curMax - curMin);
        }
        System.out.println(diff);

    }

    static void maxSumConfig() {
        System.out.println("2.Efficient Approach");
        int[] a = {8, 3, 1, 2};
        int n = a.length;
        int curVal = 0;
        int curSum = 0;
        for (int i = 0; i < n; i++) {
            curSum += a[i];
            curVal += (i * a[i]);
        }
        int maxsum = curVal;
        for (int i = 1; i < n; i++) {  // 11 - (6-8)+8*(n-1) = 11+2+24
            curVal = curVal - curSum + n * (a[i - 1]);
            if (curVal > maxsum)
                maxsum = curVal;
        }
        System.out.println(maxsum);
        /*System.out.println("1.Naive Approach: Time Complexity = O(n^2) ,Space = O(1) ");
        int[] a = {340,112,884,604,662,826,543,217,340,175,345,597,331,
                    268,295,14,758,520,861,651,293,833,877,280,991,954,
                    636,296,599,108,742,938,571,977,541,585,802,84,801,
                    493,610,497,441,940,764,736,305};
        int n = a.length;
        int maxSum = Integer.MIN_VALUE;
        int i=0;
        while(i < n){
            int sum=0;
            for(int j=0; j<n; j++){
                int index = (i+j)%n;
                sum += (j*a[index]);
            }
            maxSum = Math.max(maxSum,sum);
            i++;
        }
        System.out.println(maxSum);*/
    }

    static void longestConsecutiveAnyOrder() {
        /*System.out.println("By Sorting : Time Complexity = O(NlogN), Space Complexity = O(N)");
        int a[] = {2,6,1,9,4,5,3};
        int n = a.length;
        Arrays.sort(a); int count=1; int maxCount=1;
        for (int i = 0; i < n-1; i++) {
            //1,2,3,4,5,6,9
            if(a[i] == a[i+1])continue;  // for same number;
            if(a[i] - a[i+1] == -1){
                count++;
                maxCount = Math.max(maxCount,count);
            }
            else
                count=1;
        }
        System.out.println(maxCount);*/
        int[] a = {2, 6, 1, 9, 4, 5, 3};
        int max = 0;
        HashSet<Integer> hs = new HashSet<>();
        for (int i : a)
            hs.add(i);
        for (int i = 0; i < hs.size(); i++) {
            // 1,2,3,4,5,6,9
            if (!hs.contains(a[i] - 1)) {
                int current = a[i];
                while (hs.contains(current)) {
                    current++; // a[i]=2 and cur=7 (len = 7-2);
                }
                max = Math.max(max, current - a[i]); // or use count in while loop count length
            }
        }
        System.out.println(max);

    }

    static void maxSubArrayProd() {

        int[] a = {8, -3, -2, 0, 0, 0, -8, -8, -9};
        int n = a.length;
        int start = 0, end = 0;
        int min = a[0];
        int max = a[0];
        int prodmax = a[0];
        for (int i = 1; i < n; i++) {
            if (a[i] < 0) {
                int temp = max;
                max = min;
                min = temp;
            }
            if (a[i] > max * a[i]) {
                max = a[i];
            } else {
                max = max * a[i];
            }
            if (a[i] < min * a[i]) {
                min = a[i];
                start = i;
            } else {
                min = min * a[i];
            }
            if (max > prodmax) {
                prodmax = max;
                end = i;
            }
        }
        System.out.println(prodmax + " (" + start + ", " + end + ")");
        System.out.println("If only prod value is asked , not index means");
        min = a[0];
        max = a[0];
        prodmax = a[0];
        for (int i = 1; i < n; i++) {
            if (a[i] < 0) {
                int temp = max;
                max = min;
                min = temp;
            }
            max = Math.max(a[i], max * a[i]);
            min = Math.min(a[i], min * a[i]);
            prodmax = Math.max(prodmax, max);
        }
        System.out.println(prodmax);
    }

    static void bigIntegerFactBySplit() {
        int N = 5;
        ArrayList<Integer> li = new ArrayList<>();
        BigInteger fac = BigInteger.ONE;
        if (N <= 1) {
            li.add(1);
        } else {
            for (int count = N; count >= 2; count--) {
                fac = fac.multiply(BigInteger.valueOf(count));
            }
            String s = fac.toString();
            System.out.println(s);
            for (int i = 0; i < s.length(); i++) {
                li.add(Character.getNumericValue(s.charAt(i)));
            }
        }
        for (int x : li) {
            System.out.print(x);
        }
    }

    static void maxSubArrayKadaneAlgo() {
        int[] a = {-2, -5, 6, -2, -3, 1, 5, -6};
        int n = a.length;
        int start = 0;
        int end = 0;
        int curVal = 0;
        int maxVal = 0;
        for (int i = 0; i < n; i++) {
            curVal += a[i];
            if (curVal < a[i]) {
                curVal = a[i];
                start = i;
            }
            if (maxVal < curVal) {
                maxVal = curVal;
                end = i;
            }
        }
        System.out.println(maxVal);
        for (int i = start; i <= end; i++) {
            System.out.print(a[i] + " ");
        }
    }

    static void maxSubarraySum() {
        int[] a = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int n = a.length;
        int start = 0, end = 0;
        long max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += a[j];
                if (sum > max) {
                    max = sum;
                    start = i;
                    end = j;
                }
            }
        }
        for (int i = start; i < end; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println(max);
    }

    static void subArraySum0() {
        /*int[] a = {4,2,-3,1,6};
        int n = a.length;
        int sum = 0;
        int j = 0; boolean flag = true;
        for(int i=0; i<n; i++){
            sum += a[i];
            System.out.println(sum+" sum");
            if(sum == 0){
                flag = false;
                break;
            }
            if(i == n-1){
                sum -= a[j];
                i=j++;
                System.out.println(i+" i");

            }
        }
        if(flag)
           System.out.println("No");
        else
            System.out.println("Yes");*/
        System.out.println("Using HashSet");
        int[] a = {4, 2, -3, 1, 6};
        int sum = 0;
        boolean flag = true;
        HashSet<Integer> set = new HashSet<>();
        set.add(0);
        for (int j : a) {
            sum += j;
            if (set.contains(sum)) {
                flag = false;
                break;
            } else {
                set.add(sum);
            }

        }

        if (flag)
            System.out.println("No");
        else
            System.out.println("Yes");
    }

    static void noOfSubarrayWith0and1() {
        /*
         * n = 7
         * A[] = {1,0,0,1,0,1,1}
         * Output: 8
         * Explanation: The index range for the 8
         * sub-arrays are: (0, 1), (2, 3), (0, 3), (3, 4),
         * (4, 5) ,(2, 5), (0, 5), (1, 6)
         */
        /* Brute Force Approach O(N^2);
        int a[] = {1,0,0,1,0,1,1};
        int n= a.length;
        int result = 0;
        for (int i = 0; i < n; i++) {
            int countZero = 0, countOne = 0;
            for (int j = i; j < n; j++) {
                if(a[j] == 0)
                    countZero++;
                else
                    countOne++;
                if(countOne == countZero)
                    result++;
            }
        }
        System.out.println(result);*/

        int[] a = {1, 0, 0, 1, 0, 1, 1};
        HashMap<Integer, Integer> hs = new HashMap<>();
        hs.put(0, 1);  // because sum=0, already there
        int res = 0, sum = 0;

        for (int value : a) {
            sum += (value == 0) ? -1 : 1;  // -1, 1 to make sum 0 , so that sum will be neutral
            if (hs.containsKey(sum))   // is already value contained means , now repeated means there is 0,1 combo occur
                res += hs.get(sum);
            hs.put(sum, hs.getOrDefault(sum, 0) + 1);
        }
        System.out.println(res);
    }

    static void noOfSubArraySum() {
        /*
         * Given an unsorted array of integers, find the number
         * of sub arrays having a sum exactly equal to a given number k.
         *
         * Examples:
         *
         * Input : arr[] = {10, 2, -2, -20, 10}, k = -10
         * Output : 3
         * Explanation: SubArrays: arr[0…3], arr[1…4], arr[3..4]
         *  have a sum exactly equal to -10.
         *
         * Input : arr[] = {9, 4, 20, 3, 10, 5}, k = 33
         * Output : 2
         * Explanation: SubArrays : arr[0…2], arr[2…4] have a sum exactly equal to 33.
         */
        int[] a = {2, -2, -20, 10};
        // store previous sum in map
        HashMap<Integer, Integer> prevSum = new HashMap<>();
        int desiredSum = -10;
        int curSum = 0;
        int cnt = 0;
        for (int j : a) {
            curSum += j;
            cnt += (curSum == desiredSum) ? 1 : 0;

            int removeSum = curSum - desiredSum;
            if (prevSum.containsKey(removeSum))
                cnt += prevSum.get(removeSum);
            prevSum.put(curSum, prevSum.getOrDefault(curSum, 0) + 1);
        }
        System.out.println(cnt);

    }

    static void alternatePosNegArray() {
        /*
         * Given an unsorted array Arr of N positive and negative numbers.
         * Your task is to create an array of alternate positive and negative
         * numbers without changing the relative order of positive and negative numbers.
         * Note: Array should start with positive number.
         * Example 1:
         *
         * Input:
         * N = 9
         * Arr[] = {9, 4, -2, -1, 5, 0, -5, -3, 2}
         * Output:
         * 9 -2 4 -1 5 -5 0 -3 2
         * Example 2:
         *
         * Input:
         * N = 10
         * Arr[] = {-5, -2, 5, 2, 4, 7, 1, 8, 0, -8}
         * Output:
         * 5 -5 2 -2 4 -8 7 1 8 0
         */

        ArrayList<Integer> pos = new ArrayList<>();
        ArrayList<Integer> neg = new ArrayList<>();
        int[] a = {-1, 5, 4, 3, -2, 9, -8, -7, -6, -4};
        int n = a.length;
        for (int i : a) {
            if (i >= 0)
                pos.add(i);
            if (i < 0)
                neg.add(i);
        }
        int i = 0, j = 0, k = 0;
        while (i < n) {
            if (j < pos.size())
                a[i++] = pos.get(j++);
            if (k < neg.size())
                a[i++] = neg.get(k++);
        }
        System.out.println(Arrays.toString(a));
    }

    public static int firstRepeated(int[] a, int n) {
        /*
         * Given an array arr[] of size n, find the first repeating element.
         * The element should occur more than once and the index of its
         * first occurrence should be the smallest.
         *
         * Input:
         * n = 7
         * arr[] = {1, 5, 3, 4, 3, 5, 6}
         * Output: 1
         * Explanation:
         * 5 is appearing twice and
         * its first appearance is at index 1
         * which is less than 3 whose first
         * occurring index is 2.
         */
        HashMap<Integer, Integer> hashmap = new HashMap<>();
        int index = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (hashmap.containsKey(a[i])) {
                if (hashmap.get(a[i]) < index)
                    index = hashmap.get(a[i]);
            } else
                hashmap.put(a[i], i);

        }
        return (index == Integer.MAX_VALUE) ? -1 : index;
    }

    static void unionOfArrays() {
        int[] a = {1, 2, 2, 2, 2, 3, 7, 8, 9};
        int[] b = {0, 4, 5, 5, 5, 5, 5, 9, 10};
        HashSet<Integer> hs = new HashSet<>();
        for (int i : a)
            hs.add(i);
        for (int i : b)
            hs.add(i);
        System.out.println(hs.size());
        System.out.println(hs);
    }

    static void negativeEndArray() {
        /*
         * Move all negative elements to end....
         * Given an unsorted array arr[] of size N
         * having both negative and positive integers.
         * The task is place all negative element at the end of array
         * without changing the order of positive element and negative element.
         * Input :
         * N = 8
         * arr[] = {1, -1, 3, 2, -7, -5, 11, 6 }
         * Output :
         * 1  3  2  11  6  -1  -7  -5
         * Input :
         * N=8
         * arr[] = {-5, 7, -3, -4, 9, 10, -1, 11}
         * Output :
         * 7  9  10  11  -5  -3  -4  -1
         */
        int[] a = {1, -1, 3, 2, -7, -5, 11, 6};
        int n = a.length;
        int[] temp = a.clone();
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (temp[i] > 0)
                a[j++] = temp[i];
        }
        for (int i = 0; i < n; i++) {
            if (temp[i] < 0)
                a[j++] = temp[i];
        }
        System.out.println(Arrays.toString(a));
    }

    public static void duplicates() {
        /*
         * Input:
         * N = 5
         * a[] = {2,3,1,2,3}
         * Output: 2 3
         * Explanation: 2 and 3 occur more than once
         * in the given array.
         */
        int[] a = {1, 2, 3, 1, 7, 3, 9};
        /*int n = a.length;
        ArrayList<Integer> al = new ArrayList<>();

        Arrays.sort(a);
        for(int i=0; i<n-1; i++){
            if(a[i] == a[i+1] && !al.contains(a[i]))
                al.add(a[i]);
        }
        if(al.isEmpty()){
            al.add(-1);
        }
        System.out.println(al.toString());*/
        System.out.println("Using hashmap time complexity reduced");
        ArrayList<Integer> al = new ArrayList<>();
        HashMap<Integer, Integer> hashmap = new HashMap<>();
        for (int i : a)
            hashmap.put(i, hashmap.getOrDefault(i, 0) + 1);
        for (int key : hashmap.keySet()) {
            if (hashmap.get(key) > 1)
                al.add(key);
        }
        if (al.size() == 0)
            al.add(-1);
        System.out.println(al);

    }

    static void greaterNextElement() {
        int[] a = {54, 2, 11, -7, 1, 21, 1, 8};  // ans : [54, 11, 11, 1, 1, 21, 8, 8]
        int[] res = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (i != 0 && a[i] > a[i - 1]) {
                    res[i] = a[i];
                    break;
                } else if (a[i] < a[j]) {
                    res[i] = a[j];
                    break;
                } else
                    res[i] = a[i];
            }
        }
        res[a.length - 1] = a[a.length - 1];
        System.out.println(Arrays.toString(res));
    }
}
