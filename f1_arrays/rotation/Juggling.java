package com.f1_arrays.rotation;

import java.util.Arrays;

public class Juggling {
    public static void main(String[] args) {

    }
    public static void jugglingLeft() {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int n = a.length;
        int d = 3;

        // 1 => Juggling Algorithm
        int gcd = gcd(n, d);
        for (int i = 0; i < gcd; i++) {
            int j = 0; int temp = a[i];
            while(true){
                int k = (j+d) % n;
                if(k == i)
                    break;
                a[j] = a[k];
                j=k;
            }
            a[j] = temp;
        }
        System.out.println(Arrays.toString(a));
    }
    public static int gcd(int a, int b){
        if(b == 0)
            return a;
        else
            return gcd(b, a%b);
    }
}
