package com.ff4_greedy.easy;

public class Lemon {

    public static void main(String[] args) {

        int[] bills = {5, 5, 5, 5, 10, 20, 10};
        boolean isServed = lemonadeChange(bills);
        System.out.println(isServed);
    }

    public static boolean lemonadeChange(int[] bills) {

        int five = 0, ten = 0;

        for (int val : bills) {

            if (val == 5) { five++; }
            else if (val == 10) { five--; ten++; }
            else if (ten > 0) { ten--; five--; }
            else five -= 3;
            if (five < 0) return false;
        }
        return true;
    }
}
