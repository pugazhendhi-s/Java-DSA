package com.f1_arrays.sorting.algo;

public class Quick {
    public static void main(String[] args) {

    }
    // quick sort method 1
    public static void quickSort(int[] a, int lo, int hi){
        if(lo >= hi)
            return;
        int s = lo;
        int e = hi;
        int m = lo + (hi - lo)/2;
        int p = a[m];
        while (s <= e){
            while (a[s] < p)
                s++;
            while (a[e] > p)
                e--;
            if(s <= e){
                swap(a, s, e);
                s++;
                e--;
            }
        }
        // pivot is place at the right place, so split array below like wise
        // this while violates after s > e, so left part be (lo, e) and
        // right part be (s, hi)
        quickSort(a, lo, e);
        quickSort(a, s, hi);
    }
    // quick sort method 2

    public static void qSort(int[] a, int lo, int hi){
        if(lo < hi){
            int p = partition(a, lo, hi);
            qSort(a, lo, p-1);
            qSort(a, p+1, hi);
        }
    }
    private static int partition(int []a, int s, int e){
        int i = s; // lower index
        int pivot = a[e];
        for (int j = s; j < e; j++){
            if(a[j] <= pivot)
                swap(a, i++, j);
        }
        swap(a, i, e);
        return i;
    }
    private static void swap(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
