package com.f1_arrays.rotation;

public class Reversal {
    public static void main(String[] args) {

    }
    public static void reverse(int[] a, int start, int end){
        while (start < end){
            int temp = a[start];
            a[start] = a[end];
            a[end] = temp;
            start++; end--;
        }
    }
    public static void reversalRight(int[] a, int d){
        int n = a.length;
        //if(n%d == 0) no rotation
        reverse(a,n-d,n-1);
        reverse(a,0,n-d-1);
        reverse(a,0,n-1);
    }
    public static void reversalLeft(int[] a, int d){
        int n = a.length;
        reverse(a,0,d-1);
        reverse(a,d,n-1);
        reverse(a,0,n-1);
    }
}
