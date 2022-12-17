package com.f1_arrays.problems;

import java.util.Scanner;

public class Declaration {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        //ArrayPrint();
        //ArrayTypes();
    }
    static void ArrayPrint() {
        int[] arr = {1,2,3,9,8,6,7,4,5,0,1};
        int size = arr.length;
        int[] result = new int[size];
        for (int i = size - 1; i >= 0; i--)
        {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
        for (int i = 0; i < size; i++)
        {
            result[size-i-1] = arr[i];
        }
        for (int j : result) {
            System.out.print(j + " ");
        }
    }
    static void ArrayTypes(){
        System.out.println("Single Dimensional Array..");
        int []a; // Declaration
        int[] b = new int[5]; // Declaration and Initialization
        int c[] = {1,2,3,4,5,9}; // Declaration, Initiation and instantiation

        for (int i = 0; i < b.length; i++)
            System.out.print(c[i]+" ");

        System.out.println("\n________x_______x_________x___________x_________________x_______________\n");

        System.out.println("Multidimensional Array");
        int[][] d = { {1,2,3,4,5}, {6,7,8,9,0} };    // d[2][5]
        int []e[] = new int[5][5];
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[i].length; j++) {
                System.out.print(d[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("\n______________x_________________x_______________________x________________\n");

        System.out.println("Jagged Array");
        /*int a[][] = new int[][]{
                {1,2,3,4},
                {5,6},
                {7,8,9}
        };
              or
        int b[][] = {
                {1,2,3,4},
                {5,6},
                {7,8,9}
        };
              or
        int[][] c = new int[][]{
                new int[]{1,2,3,4},
                new int[]{5,6},
                new int[]{7,8,9}
        };*/
        int f[][] = new int[3][];
        f[0] = new int[4];
        f[1] = new int[2];
        f[2] = new int[5];
        System.out.println("Enter values");
        for (int i = 0; i < f.length; i++) {
            for (int j = 0; j < f[i].length; j++) {
                f[i][j] = sc.nextInt();
            }
            System.out.println();
        }
        for (int i = 0; i < f.length; i++) {
            for (int j = 0; j < f[i].length; j++) {
                System.out.print(f[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("_______________________The End_________________________________________________");
    }
}
