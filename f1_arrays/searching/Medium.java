package com.f1_arrays.searching;

import java.util.*;

public class Medium{
    public static void main(String[] args) {
        singleElement();
    }
        //  1 not for duplicates
    static int searchInRotatedArray(int[] a, int target){

        int n = a.length;
        int minIdx = findMinIdx(a);

        if(minIdx == -1)
            return -1;
        else if(target == a[minIdx]){
            return minIdx;
        }
        else {
            int start = (target <= a[n - 1]) ? minIdx : 0;
            int end = (target > a[n - 1]) ? minIdx : n - 1;
            return binarySearch(a, start, end, target);
        }
        /*
        int[] a = {7,8,9,10,12,3,4,6};
        int target = 4;
        System.out.println(searchInRotatedArray(a, target));
         */
    }
    static int binarySearch(int[] a, int s, int e, int t){
        while (s <= e){
            int m = s + (e-s)/2;
            if(a[m] == t) return m;
            else if(a[m] < t)  s = m+1;
            else e = m-1;
        }
        return -1;
    }
    static int findMinIdx(int[] a){
        int s = 0;
        int e = a.length-1;
        while (s < e){
            int m = s + (e-s)/2;
             if(a[m] > a[e]) s = m+1;
             else if(a[m] < a[e]) e = m;
             else e--;  // for remove duplicates
        }
        return s;
    }
    //2  duplicate and not duplicates both valid
    static int bSDuplicate(int[] a, int start, int end, int target){
        if(end < start)
            return -1;
        int mid = start + (end-start)/2;
        if(a[mid] == target)
            return mid;
        // left is sorted and right may be unsorted
        if(a[mid] > a[start] || a[mid] > a[end]){
            if(target < a[mid] && target >= a[start])
                return binarySearch(a, start, mid-1, target);
            return binarySearch(a, mid+1, end, target);
        }
        // right is sorted and left is unsorted
        else if(a[mid] < a[end] || a[mid] < start){
            if(target > a[mid] && target <= a[end])
                return binarySearch(a, mid+1, end, target);
            return binarySearch(a, start, mid-1, target);
        }
        // In this condition a[start], a[end], a[mid] are same ,
        // so increment (++start) or (--end) to remove duplicate gradually
        else
            return binarySearch(a, start, --end, target);  // or start++ remove duplicate
    }
    static void searchInDuplicateArray(){
        // sorted
        int[] a = {1,1,1,1,1,1,1,1,1,1,2,1,1,1,1};
        int target = 2;
        int index = bSDuplicate(a, 0, a.length-1, target);
        if(index != -1){
            System.out.printf("Index = %d ," +
                    "Value = %d ", index, a[index]);
        }
        else
            System.out.println("Target not found");
    }
    static void singleElement(){
        int[] a = {2,2,3,3,4,5,5};
        System.out.println(singleElement(a, 0, a.length-1));
    }
    static int singleElement(int[] a, int s, int e){
        int n = a.length;
        while (s < e){
            if(s < n-1 && a[s] != a[s+1])
                return a[s];
            if(e > 0 && a[e] != a[e-1])
                return a[e];
            else{
                s = s + 2; e -= 2;
            }
        }
        return -1;
    }
    // 3 minimum value
    static void findMinimumVal(){
        int[] a = {7, 8, 9, 12, 13, 2, 3};
        System.out.println(findMinVal(a));
    }
    // not remove duplicates
    static int findMinVal(int[] a){  // efficient approach then findMinIdx
        int n = a.length;
        if(a.length == 1)
            return a[0];
        // if array is already sorted
        if(a[n-1] > a[0])
            return a[0];
        int start = 0;
        int end = n-1;
        while (start <= end){
            int mid = start + (end - start)/2;
            if(a[mid] > a[mid+1])
                return a[mid+1];
            if(a[mid] < a[mid-1])
                return a[mid];
            if(a[mid] > a[0])
                start = mid+1;
            else
                end = mid-1;
        }
        return Integer.MAX_VALUE;
    }
    // 4 find peek
    static void findPeek(){
        // Value which is greater than its neighbour
        /* native approach Linear method
        int[] a = {2,1,3};   // 2 > 1 , ans : 2
        for (int i = 0; i < a.length-1; i++) {
            if(a[i] > a[i+1]) {
                System.out.printf("Index = %d, Value = %d",i,a[i]);
                return;
            }
        }
        System.out.println(a.length-1);*/
        // Recursive binary search
        int[] a = {2,1,3,5,6,7,1};
        int index = recursiveBSPeek(a, 0, a.length-1);
        System.out.printf("Index = %d, Value = %d",index,a[index]);
    }
    static int recursiveBSPeek(int[] a, int start, int end){
        if(start == end)
            return start;
        int mid = start + (end - start)/2;
        if(a[mid] > a[mid+1])
            return recursiveBSPeek(a, start, mid);
        return recursiveBSPeek(a, mid+1, end);
    }
    static void findDuplicate(){
        int[] a = {4,4,1,2,3};
        // using HashSet Time = O(n), Space = O(n)
        /*HashSet<Integer> set = new HashSet<>();
        for (int i:a) {
            if(set.contains(i)){
                System.out.println(i +" is duplicate number");break;
            }
            set.add(i);
        }*/
        // Negative marking, Time = O(n), Space = O(1)
        /*
         * array elements are in range of (1,n-1)
         */
        /*int cur = -1;
        for (int i = 0; i < a.length; i++) {
            cur = Math.abs(a[i]);
            if(a[cur] <= 0){
                System.out.println(cur+" is duplicate number");
                break;
            }
            a[cur] *= -1;
        }*/

            int start = 1;
            int end = a.length-1;
            int duplicate = -1;
            while(start <= end){
                int count=0;
                int cur = (start+end)/2;
                for(int val : a){
                    if(val <= cur)
                        count++;
                }
                if(count > cur){
                    duplicate = cur;
                    end = cur-1;
                }
                else
                    start = cur+1;
            }
            System.out.println(duplicate);
    }
    public static int minCapacity(int[] a, int n){
            Arrays.sort(a);
            int s = a[0]; int e = a[n-1];
            while(s <= e){
                int m = s + (e - s)/2;
                int k = 0; boolean flag = false;
                for (int i = 0; i < n; i++, k++) {
                    if(a[i] - m - k > 0) {
                        flag = true;
                        break;
                    }
                }
                if(flag)
                    s = m + 1;
                else
                    e = m-1;
            }

            return s;
        }

}