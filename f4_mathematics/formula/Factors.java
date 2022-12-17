package com.f4_mathematics.formula;

import java.util.ArrayList;

public class Factors {
    public static void main(String[] args) {
        sortedFactors(20);
    }
    static void sortedFactors(int n){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if(n % i == 0){
                if(n / i == i)
                    System.out.print(i+" ");
                else{
                    System.out.print(i+" ");
                    list.add(n/i);
                }
            }
        }
        for (int i = list.size()-1; i >= 0; i--) {
            System.out.print(list.get(i)+" ");
        }
    }
    static void factors(int n){
        // Time - > O(root(n)), Space -> O(1)
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if(n % i == 0){
                if(n/i == i)
                    System.out.print(i+" ");
                else
                    System.out.print(i +" "+ (n/i)+" ");
            }
            // ans : 1  20  2  10  4  5 -> observe this 1, 2, 4 are ascending 20, 10, 5 descending
        }

        // Brute Force Approach - O(n)
        /*for (int i = 1; i <= n; i++) {
            if(n % i == 0)
                System.out.print(i+" ");
        }*/
    }
}
