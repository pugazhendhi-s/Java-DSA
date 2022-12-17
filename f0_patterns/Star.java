package com.f0_patterns;

import java.util.Scanner;

public class Star {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
    }
    static void diamond(){
        System.out.print("Diamond size = ");
        int n = sc.nextInt();

        for (int row = 0; row < 2 * n; row++) {
            int totColsInRow = row > n ? 2 * n - row : row;
            int totSpaces = n - totColsInRow;
            for (int sp = 0; sp < totSpaces; sp++) {
                System.out.print(" ");
            }
            for (int col = 0; col < totColsInRow; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
    static void halfDiamond(){
        /*
         *
         * *
         * * *
         * * * *
         * * * * *
         * * * *
         * * *
         * *
         *
         */
        System.out.print("Half Diamond size = ");
        int n = sc.nextInt();
        for(int row = 0; row < 2 * n; row++){
            int totColsInRow = (row <= n) ? row : 2 * n - row;     //  n-row%n
            for (int col = 0; col < totColsInRow; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
    static void butterflyPat(){
        /*

         *        *
         **      **
         ***    ***
         ****  ****
         **********
         ****  ****
         ***    ***
         **      **
         *        *

         */
        System.out.print("Butterfly pattern = ");
        int size = sc.nextInt();
        int n = 2*size;
        for (int row = 1; row < n; row++) {

            int totCol = (row > size) ?  n-row : row;
            int tolSpace = size-totCol;
            for (int col = 1; col <= totCol; col++) {
                System.out.print("*");
            }
            for (int sp = 0; sp < tolSpace; sp++) {
                System.out.print("  "); //  "  " or " " , totSpace*2
            }
            for (int col = 1; col <= totCol; col++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    static void sandClock(){
        /*

         * * * * *
          * * * *
           * * *
            * *
             *
             *
            * *
           * * *
          * * * *
         * * * * *

         */
        int size = 5;
        int n = size * 2;
        for (int row = 0; row < n; row++) {
            int totCols = row >= size ? row-size+1 : size-row;
            int totSpace = n - totCols;
            for (int sp = 0; sp < totSpace; sp++) {
                System.out.print(" ");
            }
            for (int col = 0; col < totCols; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
}
