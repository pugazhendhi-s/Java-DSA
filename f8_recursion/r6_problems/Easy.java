package com.f8_recursion.r6_problems;

import java.util.Arrays;

public class Easy {
    public static void main(String[] args) {
        int[] nums = {1,-2,-3,70,-4,-7,688};
        System.out.println(maximum(nums, 0));
    }
    // 1. triangle Print Sum
    private static void printTriangle(int[] arr) {
        if(arr.length == 0) {
            return;
        }
        int[] temp = new int[arr.length-1];
        for (int i = 0; i < arr.length-1; i++) {
            temp[i] = arr[i] + arr[i+1];
        }
        printTriangle(temp);
        System.out.println(Arrays.toString(arr));
        /*
        int[] nums = {1,1,1,1,1};
        printTriangle(nums);
        [16]
        [8, 8]
        [4, 4, 4]
        [2, 2, 2, 2]
        [1, 1, 1, 1, 1]
         */
    }
    // using recursive Call
    private static void recursivePrint(int[] arr) {
        if(arr.length == 0) {
            return;
        }
        int[] temp = new int[arr.length-1];
        helper(arr, temp, 0);
        recursivePrint(temp);
        System.out.println(Arrays.toString(arr));
    }
    private static void helper(int[] arr,  int[] temp, int index){
        if(index == arr.length-1)
            return;
        temp[index] = arr[index] + arr[index+1];
        helper(arr, temp, index+1 );
    }

    // 2. max and min
    public static int maximum(int[] arr, int i){
        if(i == arr.length-1) // traverse from 0
            return arr[arr.length-1];
        return Math.max(arr[i], maximum(arr, ++i));
    }
    public static int min(int[] arr, int n){
        if(n == 1)   // another method traverse arr.length
            return arr[0];
        return Math.min(arr[n-1], min(arr, n-1));
    }

    // 3. first capital in string
    public static char firstCapitalStr(String s){
        if(s.isEmpty())
            return ' ';
        char ch = s.charAt(0);
        if(ch >= 'A' && ch <= 'Z')
            return ch;
        else
            return firstCapitalStr(s.substring(1));
    }

    // 4. length of string
    public static int len(String s){
        if(s.isEmpty())
            return 0;
        return 1 + len(s.substring(1));
    }

    // 5. digit sum
    public static int digitSum(int n){
        if(n % 10 == n)
            return n;
        return n % 10 + digitSum(n/10);
    }

    // 6. product of two numbers
    public static int product(int a, int b){
        if(b == 1)
            return a;
        if(a < b)
            return product(b, a);
        return a + product(a, b-1);
    }

    // 7. boolean is Prime
    public static boolean isPrime(int n, int i){
        if(n < 2)
            return false;
        return i * i > n || n % i != 0 && isPrime(n, i+1);
    }

    // 8. sum of natural numbers
    public static int sumOfN(int n){
        if(n == 1)
            return 1; // or n
        return n + sumOfN(n-1);
    }

    // 9. is Power of 2
    public static boolean isPowerOf2(int n){
        //return n > 0 && Integer.bitCount(n) == 1;
        return n == 1 || (n > 0 && ((n & 1) != 1) && isPowerOf2(n/2));
    }

    // 10.is Power of 3
    public static boolean isPowerOf3(int n) {
        return n == 1 || (n > 0 && n % 3 == 0 && isPowerOf3(n/3));
        //return Integer.toString(n, 3).matches("^10*$");
        // convert the base 10 to base 3, if its power of 3 means, its will be like 1,10,100,1000,
        // 9 -> 100 (9 in base 3) , matches checks whether string like 10....any number of zeroes
    }

    public static boolean isSorted(int[] nums, int i){
        if(i == nums.length-1)
            return true;
        return nums[i] < nums[i+1] && isSorted(nums, i+1);
    }
}
