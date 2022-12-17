package com.f0_patterns;

import java.util.Scanner;

public class Numbers {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

    }
    public static void main() {
        pascalTriangle();
    }
    static void snake(){
        int n = 5; int init = 10;
        for (int row = 0; row < n;) {
            for (int sp = 0; sp < n-row; sp++) {
                System.out.print("  ");
            }
            for (int col = 0; col < n; col++) {
                if (row % 2 == 0)
                    System.out.print(init++ + " ");
                else
                    System.out.print(init-- + " ");
            }
            init = (++row % 2 == 0) ? init + n+1 : init + n-1;
            System.out.println();
        }
    }
    static void verticalInc(){
        /*
        1
        2 6
        3 7 10
        4 8 11 13
        5 9 12 14 15
         */
        int n = 5;
        for (int row = 1; row <= n; row++) {
            int val = row;
            for (int col = 1; col <= row; col++) {
                System.out.print(val+" ");
                val += n-col;
            }
            System.out.println();
        }
    }
    static void halfDiamond(){
        /*
        1
        1 2
        1 2 3
        1 2 3 4
        1 2 3 4 5
        1 2 3 4
        1 2 3
        1 2
        1
         */
        int size = 5;
        int n = size * 2;
        for (int row = 0; row < n; row++) {
            int tolCols = row >= size ? n-2-row : row;
            for (int col = 0; col <= tolCols; col++) {
                System.out.print(col+1+" ");
            }
            System.out.println();
        }
    }
    static void sandClock(){
        /*
        1 2 3 4 5
         2 3 4 5
          3 4 5
           4 5
            5
           4 5
          3 4 5
         2 3 4 5
        1 2 3 4 5
         */
        int size = 5;
        int n = size * 2;
        for (int row = 0; row < n; row++) {
            int totCols = row >= size ? row-size+1 : size-row;
            int totSpace = n - totCols;
            for (int sp = 0; sp < totSpace; sp++) {
                System.out.print(" ");
            }
            int val = size-totCols+1; // +1 becoz index start at 0
            for (int col = 0; col < totCols; col++) {
                System.out.print(val+col+" ");
            }
            System.out.println();
        }
    }
    static void sandGlass(){
        int size = 5;
        int n = 2 * size;
        for (int row = 0; row < n; row++) {
            int totCols = row >= size ? row-size+1 : size-row;
            int totSpace = size-totCols;
            for (int cols = 0; cols < totCols; cols++) {
                System.out.print(totCols-cols+" ");
            }
            for (int sp = 0; sp < 2*totSpace; sp++) {
                System.out.print("- ");
            }
            for (int col = 0; col < totCols; col++) {
                System.out.print(col+1+" ");
            }
            System.out.println();
        }
    }
    static void reverseNumber2N(){
        System.out.print("Reverse number by 2n = ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n-i; j++) {
                System.out.print("  ");
            }
            for (int j = 0; j <= 2*i; j++) {
                if(j > i) {
                    System.out.print((j+1-i) + " ");
                }
                else
                    System.out.print(i+1-j +" ");
            }
            // for j one more method
        /*for (int col = i+1; col >= 1; col--) {
            System.out.print(col +" ");
        }
        for (int col = 2; col <= i+1; col++) {
            System.out.print(col+" ");
        }*/
            System.out.println();
        }
//        for (int row = 0; row < 2 * n; row++) {
//            int totColInRows = 2 * row;
//            int totSpaces = n - totColInRows;
//            for (int sp = 0; sp < totSpaces; sp++) {
//                System.out.print("  ");
//            }
//            for (int col = 0; col < totColInRows; col++) {
//                System.out.print("* ");
//            }
//            System.out.println();
//        }
    }
    static void diamondNumber(){
        System.out.print("diamond reverse number = ");
        int n = sc.nextInt();
        for (int row = 1; row <= 2 * n; row++) {
            int totCals = (row > n) ? 2 * n - row : row;
            int totSpace = n - totCals;
            for (int sp = 0; sp < totSpace; sp++) {
                System.out.print("  ");
            }
            for (int col = totCals; col >= 1; col--) {
                System.out.print(col +" ");
            }
            for (int col = 2; col <= totCals; col++) {
                System.out.print(col+" ");
            }
            System.out.println();
        }
    }
    static void squareBoxN() {
        System.out.print("Square box = ");
        /*
         * 1 1 1 1 1 1 1 1 1
         * 1 2 2 2 2 2 2 2 1
         * 1 2 3 3 3 3 3 2 1
         * 1 2 3 4 4 4 3 2 1
         * 1 2 3 4 5 4 3 2 1
         * 1 2 3 4 4 4 3 2 1
         * 1 2 3 3 3 3 3 2 1
         * 1 2 2 2 2 2 2 2 1
         * 1 1 1 1 1 1 1 1 1
         * this is done by considering square as walls, print the min values from edge of box.
         */
        int n = sc.nextInt();
        int n2 = 2 * n;
        for (int row = 1; row < n2; row++) {
            for (int col = 1; col < n2; col++) {
                int atEveryIndex = Math.min(Math.min(row, col), Math.min(n2 -row, n2 -col));
                System.out.print(atEveryIndex+" ");
            }
            System.out.println();
        }
        System.out.println("Square box");
        n2 = 2 * n;
        for (int row = 1; row < n2; row++) {
            /**
             * Square box = 4
             * 4 4 4 4 4 4 4
             * 4 3 3 3 3 3 4
             * 4 3 2 2 2 3 4
             * 4 3 2 1 2 3 4
             * 4 3 2 2 2 3 4
             * 4 3 3 3 3 3 4
             * 4 4 4 4 4 4 4
             *
             * this is same as previous one here we reduced the wall size and take max size from mid
             * just n- ()..
             */
            for (int col = 1; col < n2; col++) {
                int atEveryIndex = n+1 - Math.min(Math.min(row, col), Math.min(n2 -row, n2 -col));
                System.out.print(atEveryIndex+" ");
            }
            System.out.println();
        }
    }
    static void modN(){
    /*
    1 2 3 4 5
    2 3 4 5 1
    3 4 5 1 2
    4 5 1 2 3
    5 1 2 3 4
     */
        System.out.print("Mod n pattern = ");
        int n = sc.nextInt();
        for (int row = 0; row < n; row++) {
            int k = 1;
            for (int col = row; k++ <= n ; col++) {
                System.out.print(col % n + 1 +" ");
            }
            System.out.println();
        }
    }
    static void pattern1(){
        int n = 5;
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                if(col < n-row)  // row < n -col
                    System.out.print("1 ");
                else
                    System.out.print(row+" ");
            }
            System.out.println();
        }
    }
    static void pascalTriangle(){
        // formula = n!/(n-r)!r!
        int n = 5;
        for (int row = 0; row <= n; row++) {
            int totCols = row;
            int totSpace = n-totCols;
            for (int space = 0; space <= totSpace; space++) {
                System.out.print(" ");
            }
            for (int col = 0; col <= totCols; col++) {
                int fact = factorial(row) / (factorial(row - col) * factorial(col));
                System.out.print(fact+" ");
            }
            System.out.println();
        }
    }
    static int factorial(int n){
        if(n == 0)
            return 1;
        return n * factorial(n-1);
    }

}
