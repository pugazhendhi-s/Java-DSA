package com.f0_patterns;

public class Recursive {
    public static void main(String[] args) {
        rightTriangle(3,0);
        //invertedRightTriangle(5,0);
    }
    // 1 method
    public static void invertedRightTriangle(int n){
        // lot of ways to do this
        if(n == 0)
            return;
        for (int i = 0; i < n; i++) {
            System.out.print("* ");
        }
        System.out.println();
        invertedRightTriangle(n-1);
    }
    public static void rightTriangle(int n){
        // lot of ways to do this
        if(n == 0)
            return;
        rightTriangle(n-1);
        for (int i = 0; i < n; i++) {
            System.out.print("* ");
        }
        System.out.println();
    }
    // 2 method
    public static void rightTriangle(int r, int c){
        if(r == 0)
            return;
        if(c < r) {
            rightTriangle(r, c+1);
            System.out.print("* ");
        }
        else{
            rightTriangle(r-1, 0);
            System.out.println();
        }
    }
    public static void invertedRightTriangle(int r,  int c){
        if(r == 0)
            return;
        if(c < r){
            System.out.print("* ");
            invertedRightTriangle(r, c+1);
        }
        else {
            System.out.println();
            invertedRightTriangle(r-1, 0);
        }
    }
}
