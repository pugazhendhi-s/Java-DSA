package com.f4_mathematics.formula;

import java.util.*;

public class LCEasy {
    public static void main(String[] args) {
        System.out.println(selfDividingNumbers(47, 85));
    }
    public static boolean isHappy(int n) {
        // Hashset
        /*HashSet<Integer> set = new HashSet<>();
        while(n != 1 && !set.contains(n)){
            set.add(n);
            n = getNext(n);
        }
        return n == 1;*/
        // Floyd's Cycle finding algo - linked list
        int slowPointer = n;
        int fastPointer = getNext(n);
        while (fastPointer != 1 && slowPointer != fastPointer) {
            slowPointer = getNext(slowPointer);
            fastPointer = getNext(getNext(fastPointer));
        }
        return fastPointer == 1;
    }
    public static int getNext(int n){
        int sum = 0;
        while (n > 0){
            int r = n%10;
            sum += (r * r);
            n /= 10;
        }
        return sum;
    }
    public static boolean isPowerOfFour(int n) {
        if(n <= 0)
            return false;
        if((n & (n-1)) != 0)  // 4, 16 (100 ^ 011) == 0, (10000 ^ 1111) (16 ^ 15 == 0)
            return false;
        return (n-1) % 3 == 0;   // 4-1 % 3 = 0 , 16-1 % 3 == 0, all 4 exponential -1 must be divided by 3.
    }
    public int climbStairs(int n) {
        /**70. Climbing Stairs
         * You are climbing a staircase. It takes n steps to reach the top.
         *
         * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
         *
         *
         *
         * Example 1:
         *
         * Input: n = 2
         * Output: 2
         * Explanation: There are two ways to climb to the top.
         * 1. 1 step + 1 step
         * 2. 2 steps
         * Example 2:
         *
         * Input: n = 3
         * Output: 3
         * Explanation: There are three ways to climb to the top.
         * 1. 1 step + 1 step + 1 step
         * 2. 1 step + 2 steps
         * 3. 2 steps + 1 step
         */
        int[] dp = new int[n+2];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for(int i=3; i<=n; i++){
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
    public static List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> list = new ArrayList<>();

        while(left <= right){
            if(selfDivide(left))
                list.add(left);
            left++;
        }
        return list;
    }
    static boolean selfDivide(int n){
        int temp = n;
        while(temp > 0){
            int rem = temp % 10;
            temp /= 10;
            if( rem == 0 || n % rem != 0)
                return false;
        }
        return true;
    }
    public boolean isPowerOfTwo(int n) {
        //return n > 0 && (n & (n - 1)) == 0;
        return n > 0 && Integer.bitCount(n) == 1;
    }
}
