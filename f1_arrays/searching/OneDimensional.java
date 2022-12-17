package com.f1_arrays.searching;
import java.util.*;

public class OneDimensional {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        firstAndLastIndexOfElement();
    }
    public static int binarySearch(int[] a, int start, int end, int target){
        while(start <= end){
            int mid = start + (end-start)/2;
            if(a[mid] == target)
                return mid;
            else if(a[mid] < target)
                start = mid+1;
            else
                end = mid-1;
        }
        return -1;
    }
    public static int orderAgnosticBinarySearch(int[] a, int target){
        // Ascending sort and descending sort
        /*main
          int[] a = {99, 56, 34, 0, -67, -108, -195};
          int target = -108;
          System.out.println(orderAgnosticBinarySearch(a, target));
         */
        int start = 0;
        int end = a.length-1;
        boolean isAsc = a[start] < a[end];
        while (start <= end){
            int mid = start + (end-start)/2;
            if(a[mid] == target){
                return mid;
            }
            if (isAsc) {
                if (target < a[mid])
                    end = mid - 1;
                else
                    start = mid + 1;
            }
            else{
                if (target > a[mid])
                    end = mid - 1;
                else
                    start = mid + 1;
            }
        }
        return -1;
    }
    // 1
    public static int ceilBS(int[] a, int key) {
        /**main
         * int[] a = new int[]{1,2,3,6,7,8,11,12};
         * int key = 10;
         * System.out.println("Ceil Index : "+ceilBS(a, key));
         */
        int start = 0;
        int end = a.length - 1;
        if(key > a[end])
            return -1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (key == a[mid])
                return mid;
            else if (key < a[mid])
                end = mid-1;
            else
                start = mid + 1;
        }
        return start;
    }
    public static int floorBS(int[] a, int key) {
        //System.out.println("Floor Index : "+floorBS(a, key));
        int start = 0;
        int end = a.length - 1;
        if(key < a[0])
            return -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (key == a[mid])
                return mid;
            else if (key < a[mid])
                end = mid - 1;
            else
                start = mid + 1;
        }
        return end;
    }
    //2
    public static char nextGreatestLetter(char[] a, char key) {
        /**main non- decreasing order
           char[] c = {'a','e','e','e','e','e','e','n','n','n','n'};
           char key = 'e';  // key may in c[] or not
           System.out.println(nglBS(c, key));
         */
        int n = a.length;
        int start = 0;
        int end = n - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (key == a[mid])
                start = mid+1;
            else if (key < a[mid])
                end = mid - 1;
            else
                start = mid + 1;
        }
        return a[start%n];
    }
    //3
    static void firstAndLastIndexOfElement(){
        int[] a = {1,2,3,3,4,4,4,4,6,7,8};
        int target = 4;
        int[] ans = searchRange(a,target);
        System.out.println(Arrays.toString(ans));
    }
    public static int[] searchRange(int[] a, int target) {
        int[] ans = {-1, -1};
        ans[0] = firstLastIndex(a,target, true);
        // if ans[0] = -1 then no need for a[1]
        ans[1] = (ans[0] != -1) ? firstLastIndex(a,target, false) : -1;
        return ans;
    }
    public static int firstLastIndex(int[] a, int target, boolean findingFirstIndex){
        int start = 0;
        int end = a.length-1;
        int ans = -1;
        while(start < end){
            int mid = start + (end-start)/2;
            if(a[mid] == target){
                ans = mid;
                if(findingFirstIndex)
                    end = mid;
                    // if first occurrence for 1,2,2,3,3,3,3,4,5,6
                    // mid = key at index 5, now change end to mid for first index
                else
                    start = mid;
            }
            else if(a[mid] < target)
                start = mid+1;
            else
                end = mid-1;
        }
        return ans;
    }

    //4
    static void elementPositionInInfiniteArray(){
        int[] a = {1,2,3,4,5,6,7,8,9,10,202,500,10000,40000};
        int target = 10000;
        System.out.println(findingRange(a,target));
    }
    public static int findingRange(int[] a, int target){
        int start = 0;
        int end = 1;
        while (target > a[end]){
            int newStart = end+1;
            end = end + (end-start)*2;
            start = newStart;
        }
        return binarySearch(a, start, end, target);
    }

    //5
    public static int peekInMountainArray(int[] a){
        /*main
        int[] a = {1,2,8,7,6,5,4,3,0};
        System.out.println(peekInMountainArray(a));
         */
        int start = 0;
        int end = a.length-1;
        while (start < end){
            int mid = start+(end-start)/2;
            if(a[mid] > a[mid+1])
                end = mid;
            else
                start = mid+1;
        }
        // start and end both are equal
        return a[end]; //or start]
    }

    // 6
    public static void findTargetInMountainArray(){
        int[] a = {0,5,3,1};
        int target = 1;
        System.out.println(findElement(a,target));
    }
    public static int peek(int[] a){

        int start = 0;
        int end = a.length-1;
        while (start < end){
            int mid = start+(end-start)/2;
            if(a[mid] > a[mid+1])
                end = mid;
            else
                start = mid+1;
        }
        // start end and both are equal
        return end;
    }
    public static int findElement(int[] a, int target){
        int peek = peek(a);
        if(a[peek] == target)
            return peek;
        int index = bS(a,0,peek-1,target, true);
        if(index != -1)
            return index;
        return bS(a,peek+1,a.length-1,target, false);
    }
    public static int bS(int[] a, int start, int end, int target, boolean isAsc){
        while(start <= end){
            int mid = start + (end-start)/2;
            if(a[mid] == target)
                return mid;
            else if(a[mid] < target)
                if(isAsc)
                    start = mid+1;   // for ascending
                else
                    end = mid-1;  // for descending sort
            else
            if(isAsc)
                end = mid-1;
            else
                start = mid+1; // for descending sort
        }
        return -1;
    }

    // 7
    static void rotationCount(){
        int[] a = {7,8,8,1,2,3,3,4,5,6};
        int s = 0;
        int e = a.length-1;
        int pivot = findPivot(a,s,e);
        System.out.println(pivot);
    }
    static int findPivot(int[] a, int s, int e) {
        while(s <= e){
            int m = s+(e-s)/2;
            if(m > 0 && a[m] < a[m-1])
                return m-1;
            if(m < e && a[m] > a[m+1])
                return m;
            if(a[s] > a[m])    // 7,8,1,2,3,4,5,6 => a[start] > a[mid] (7,2)
                e = m-1;
            else
                s = m+1;
        }
        return -1;
    }

    // 8
    static void splitArrayLargestSum(){  // leetcode hard
        /*Input: nums = [7,2,5,10,8], m = 2
            Output: 18
            Explanation:
            There are four ways to split nums into two sub arrays.
            The best way is to split it into [7,2,5] and [10,8],
            where the largest sum among the two sub arrays is only 18.
         */
        int[] a = {7, 2, 5, 8, 10};
        //int max = IntStream.of(a).sum();  //  sum of array
        int m = 2;
        int start = 0;
        int end = 0;
        for (int x: a) {
            start = Math.max(start,x);
            end += x;
        }
        while (start < end) {
            int mid = start+ (end-start)/2;
            int sum = 0; int pieces = 1;
            for (int x : a) {
                if(sum+x > mid){
                    sum = x;
                    pieces++;
                }
                else
                    sum += x;
            }
            if(pieces <= m)
                end = mid;
            else
                start = mid+1;
        }
        System.out.println(start);
    }
}
