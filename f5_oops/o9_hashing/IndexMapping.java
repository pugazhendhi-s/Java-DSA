package com.f5_oops.o9_hashing;

public class IndexMapping {

    final static int MAX = 1000;

    // Since array is global, it
    // is initialized as 0.
    static boolean[][] has = new boolean[MAX + 1][2];

    // searching if X is Present in
    // the given array or not.
    static boolean search(int X) {
        if (X >= 0) return has[X][0];

        // if X is negative take the
        // absolute value of X
        // .
        X = Math.abs(X);
        return has[X][1];
    }

    static void insert(int[] a, int n) {
        for (int i = 0; i < n; i++) {
            if (a[i] >= 0) {
                has[a[i]][0] = true;
            } else {
                int abs_i = Math.abs(a[i]);
                has[abs_i][1] = true;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {-1, 9, -5, -8, -5, -2};
        int n = a.length;
        insert(a, n);
        int X = -5;
        if (search(X)) {
            System.out.println("Present");
        } else {
            System.out.println("Not Present");
        }
    }
}
