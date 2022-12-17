package com.f1_arrays.rotation;

import java.util.Arrays;

public class BruteForce {
    public static void main(String[] args) {

    }

    // 1 => using temp[] array
    public static void leftRotate(int[] a, int left){
        int n = a.length;

        int[] temp = Arrays.copyOf(a,n);
        for (int i = 0; i < n; i++) {
            if(i < left)
                a[n-left+i] = temp[i];
            else
                a[i-left] = temp[i];
        }
    }
    // 2 => temp variable
    public static void leftRotate1(int[] a, int left){
        int n = a.length;
        while (left-- > 0){
            int copy = a[0];
            for (int j = 0; j < n-1; j++) {
                a[j] = a[j+1];
            }
            a[n-1] = copy;
        }
    }

    // 1 => using temp[] array
    public static void rightRotate(int[] a, int left){
        int n = a.length; int k = 2;
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            c[i] = a[(n-k+i) % n];
        }
    }
    // 2 => temp variable
    public static void rightRotate1(int[] a, int right){
        int n = a.length;
        while (right-- > 0){
            int temp = a[n-1];
            for (int i = n-1; i > 0; i--) {
                a[i] = a[i-1];
            }
            a[0] = temp;
        }
    }
}
