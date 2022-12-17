package com.f1_arrays.sorting.algo;

import java.util.Arrays;

public class Merge {
    public static void main(String[] args) {
        int[] a = {3,7,14,2,5,9,0};
        a = mergeSort(a);
        System.out.println(Arrays.toString(a));

        int[] b = {5,7,1,6,3,2,0,9};
        mergeSort(b, 0, b.length-1);
        System.out.println(Arrays.toString(b));
    }
    public static int[] mergeSort(int[] a){
        if(a.length == 1)
            return a;
        int mid = a.length/2;
        int[] left = mergeSort(Arrays.copyOfRange(a, 0, mid));
        int[] right = mergeSort(Arrays.copyOfRange(a, mid, a.length));

        return merge(left, right);
    }
    private static int[] merge(int[] first, int[] second) {
        int[] mix = new int[first.length + second.length];
        int i=0, j=0, k=0;
        while (i < first.length && j < second.length){
            if(first[i] <= second[j])
                mix[k++] = first[i++];
            else
                mix[k++] = second[j++];
        }
        while (i < first.length)
            mix[k++] = first[i++];
        while (j < second.length)
            mix[k++] = second[j++];
        return mix;
    }
    // in place merge sort
    public static void mergeSort(int[] a, int lo, int hi){
        if(lo < hi){
            int mid = lo + (hi - lo)/2;
            mergeSort(a, lo, mid);
            mergeSort(a, mid+1, hi);
            merge(a, lo, mid, hi);
        }
    }
    private static void merge(int[] a, int lo, int mid, int hi){
        int[] temp = new int[a.length];
        int i = lo, j = mid+1, k = lo;
        while (i <= mid && j <= hi){
            if(a[i] <= a[j])
                temp[k++] = a[i++];
            else
                temp[k++] = a[j++];
        }
        while(i <= mid)
            temp[k++] = a[i++];
        while(j <= hi)
            temp[k++] = a[j++];
        if (hi + 1 - lo >= 0)
            System.arraycopy(temp, lo, a, lo, hi + 1 - lo);

        /*for (int l = lo; l <= hi; l++)
            a[l] = temp[l];*/
    }
}
