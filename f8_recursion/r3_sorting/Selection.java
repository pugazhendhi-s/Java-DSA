package com.f8_recursion.r3_sorting;

public class Selection {
    public static void main(String[] args) {

    }
    public static void selection(int[] a, int r, int c, int max){
        if(r == 0)
            return;
        if(c < r){
            if(a[c] > a[max])
                max = c;
            selection(a, r, c+1,max);
        }
        else {
            int temp = a[r-1];
            a[r-1] = a[max];
            a[max] = temp;
            selection(a, r - 1, 0,0);
        }
    }
    public static void selection(int[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            int min_Index = i;
            for (int j = i + 1; j < n; j++)
                if (a[j] < a[min_Index])
                    min_Index = j;
            swap(a, i, min_Index);
        }
    }
    private static void swap(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
