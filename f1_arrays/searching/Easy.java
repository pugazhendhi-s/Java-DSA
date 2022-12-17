package com.f1_arrays.searching;

import java.util.*;
import java.util.stream.IntStream;

public class Easy{
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        fairSwapCandy();
    }
    public static int sqrtBs(int n){
        int start = 1; int end = (n/2)+1;
        while (start <= end){
            int mid = start + (end-start)/2;
            if(mid > n/mid)
                end = mid-1;
            else
                start = mid+1;
        }
        return end;
        // by newton approach
        /*int r = (n/2)+1;
        while (r > n/r){
            r = (r + n/r)/2;
        }
        System.out.println(r);*/
    }
    static void arrangingCoins(){
        int n = sc.nextInt();
        /*
         * 1. n = k(k+1)/2
         * 2. k(k+1) <= 2n
         * apply completing square rule
         *          (k+1/2)^2-1/4 = (k^2+k)
         * 3. (k+1/2)^2-1/4 <= 2n
         * 4. (k+1/2)^2 <= (2n+1/4)
         * 5. (k + 1/2) = (2n+1/4)^1/2
         * 6. (2n+1/4)^1/2 -1/2 = k
         * 7. Math.sqrt(2n + 0.25)-0.5;
         */
        int k = (int)(Math.sqrt((2 * (long)n)+0.25)-0.5);
        System.out.println("k = "+k);
        // Binary search
        // sum of n numbers given , n = k(k+1)/2, find k,
        long start = 1;
        long end = n;
        while(start <= end){
            long mid = start + (end-start)/2;
            long val = mid*(mid+1)/2;
            if(val == n) {
                System.out.println((int)mid);
                return;
            }
            else if(val > n)
                end = mid-1;
            else
                start = mid+1;
        }// T -> O(logN), S -> O(1)
        System.out.println(end);

    }
    public static int kthMissingNumber(int[] a, int k)  {
        /*int[] a = {2,4,5,7,11};  // 1, 3, 6, 8, 9
        int k = 5;*/
        int start = 0;
        int end = a.length;
        while (start <= end){
            int mid = start + (end - start)/2;
            if(a[mid] - (mid+1) < k)
                start = mid+1;
            else
                end = mid-1;
        }
        return k+start;
    }
    static void countNegValSortedMatrix(){
        int[][] grid = {
                {4,3,2,-1},
                {3,2,1,-1},
                {1,1,-1,-2},
                {-1,-1,-2,-3}
        };
        int cntOfNeg = 0;
        int row = grid.length-1; int col = 0;
        while (row >= 0 && col < grid[row].length){
            if(grid[row][col] < 0) {
                cntOfNeg += (grid[row].length - col);
                row--;
            }
            else
                col++;
        }
        System.out.println(cntOfNeg);
    }
    static void intersectionOfTwoArrays(){
        int[] a = {4,9,5,9};
        int[] b = {9,4,9,8,4};
        // Binary Search
        Arrays.sort(a);
        Arrays.sort(b);
        ArrayList<Integer> list = new ArrayList<>();
        for (int j : b) {
            if (bSForIntersection(a, j) && !list.contains(j))
                list.add(j);
        }
        System.out.println(Arrays.toString(list.stream().mapToInt(i -> i).toArray()));
        /* Using Hash
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : a) {
            map.put(i, 1);
        }
        for (int i : b) {
            if(map.containsKey(i)){
                map.put(i, map.get(i)+1);
            }
        }
        HashSet<Integer> set = new HashSet<>();
        for (Map.Entry<Integer, Integer> it: map.entrySet()) {
            if(it.getValue() > 1)
                set.add(it.getKey());
        }
        System.out.println(set);*/
        // Using HashSet inbuilt function
        HashSet<Integer> set1 = new HashSet<>();
        for (int x:a) {
            set1.add(x);
        }
        HashSet<Integer> set2 = new HashSet<>();
        for (int x:b) {
            set2.add(x);
        }
        set1.retainAll(set2);
        System.out.println(set1);
    }
    static void intersectionOfTwoArraysII(){
        int[] a = {1,2,2,1,9};
        int[] b = {2,1,2,4,5,1,9};
        Arrays.sort(a);
        Arrays.sort(b);
        // two pointer technique
        /*Arrays.sort(a);
        Arrays.sort(b);
        int i = 0;
        int j = 0;
        ArrayList<Integer> list = new ArrayList<>();

        while(i < a.length && j < b.length){
            if(a[i] == b[j]){
                list.add(a[i]);
                i++; j++;
            }
            else if(a[i] > b[j])
                j++;
            else
                i++;
        }
        System.out.println(list);*/
        ArrayList<Integer> list = new ArrayList<>();
        int startIndex=0;
        for (int val : a) {
            int index = bSForIntersection(b, val, startIndex,b.length);
            if(index >= 0){
                list.add(val);
                startIndex = index + 1;
            }
        }
        System.out.println(Arrays.toString(list.stream().mapToInt(k -> k).toArray()));
    }
    static int bSForIntersection(int[] b, int target, int s, int e){
        int start = s; int end = e;
        int index = -1;
        while(start < end){
            int mid = start + (end-start)/2;
            if(b[mid] == target) {
                end = mid;
                index = mid;
            }
            else if(b[mid] < target)
                start = mid+1;
            else
                end = mid;
        }
        return index;
    }
    static boolean bSForIntersection(int[] a, int target){
        int start = 0;
        int end = a.length;
        while(start < end){
            int mid = start + (end-start)/2;
            if(a[mid] == target)
                return true;
            else if(a[mid] < target)
                start = mid+1;
            else
                end = mid;
        }
        return false;
    }
    static void fairSwapCandy(){
        int[] a = {1,1};
        int[] b = {2,2};
        /*
         * a give x candy to b and get y candy from b
         * a-x+y = b-y+x;
         * y = x + (sum(a) - sum(b))/2;
         */
        int dif = (IntStream.of(a).sum()-IntStream.of(b).sum())/2;
        HashSet<Integer> set = new HashSet<>();
        for (int val : a) {
            set.add(val);
        }
        for (int val : b) {
            if(set.contains(val + dif)){
                System.out.println(val+dif+", "+val);
                return;
            }
        }
        System.out.println("null");
    }
    static void checkNAndItsDoubleExits(){
        /*
         * Input: arr = [10,2,5,3]
         * Output: true
         * Explanation: N = 10 is the double of M = 5,that is, 10 = 2 * 5.
         * Input: arr = [7,1,14,11]
           Output: true
           Explanation: N = 14 is the double of M = 7,that is, 14 = 2 * 7.
         */
        int[] a = {7,1,14,3};

        Set<Integer> seen = new HashSet<>();
        for (int val : a) {
            if (seen.contains(2 * val) || val % 2 == 0 && seen.contains(val / 2)) {
                System.out.println(true);
                System.exit(0);
            }
            seen.add(val);
        }
        System.out.println(false);
    }
}