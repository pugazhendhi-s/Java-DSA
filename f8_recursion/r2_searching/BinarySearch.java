package com.f8_recursion.r2_searching;

public class BinarySearch {
    public static void main(String[] args) {

    }
    public static int binarySearch(int[] a, int start, int end, int target) {
        // recurrence relation F(N) = O(1)+ F(N/2);
        if (start > end)
            return -1;
        int mid = start + (end - start) / 2;
        if (target == a[mid])
            return mid;
        if (target < a[mid])
            return binarySearch(a, start, mid - 1, target);
        return binarySearch(a, mid + 1, end, target);
    }
    public static int bSRotated(int[] a, int s, int e, int target){
        if(s > e)
            return -1;
        int m = s + (e - s)/2;
        if(a[m] == target)
            return m;
        // if left half is sorted, this will execute
        if(a[s] <= a[m]){    // 5,6,7,8,1,2,3,4
            if(a[s] <= target && target < a[m])
                return bSRotated(a, s, m-1, target);
            return bSRotated(a, m+1, e, target);
        }
        if(a[m] < target && target <= a[e])
            return bSRotated(a, m+1, e, target);
        return bSRotated(a,s,m-1, target);
    }


}
