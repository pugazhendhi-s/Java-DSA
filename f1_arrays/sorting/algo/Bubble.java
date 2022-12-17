package com.f1_arrays.sorting.algo;

import java.util.Arrays;

public class Bubble { // // also known Sinking Sort / Exchanging sort / T - 0(n^2), S-> O(1)
    public static void main(String[] args) {
        int[] a = {4,3,2,4,6,3,8,4,5};
        bubble(a, a.length);
        System.out.println(Arrays.toString(a));
    }
    public static void bubble(int[] a, int r, int c){
        if(r == 0)
            return;
        if(c < r){
            if(a[c] > a[c+1]){
                int temp = a[c];
                a[c] = a[c+1];
                a[c+1] = temp;
            }
            bubble(a, r, c+1);
        }
        else
            bubble(a, r-1, 0);
    }
    public static void bubble(int[] a, int len){
        if(len == 0 || len == 1)
            return;
        for (int i = 0; i < len-1; i++) {
            if(a[i] > a[i+1])
                swap(a, i, i+1);
        }
        bubble(a,len-1);
    }
    public static void bubble(int[] a){
        int len = a.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len-1-i; j++) {
                if(a[j] > a[j+1])
                    swap(a, j, j+1);
            }
        }
    }
    public static void swap(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


}
