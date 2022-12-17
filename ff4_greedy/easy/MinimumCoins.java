package com.ff4_greedy.easy;
import java.util.*;

public class MinimumCoins {

    public static void main(String[] args) {

        int N = 1818;
        List<Integer> list = minPartition(N);
        System.out.println(list);
    }

    public static List<Integer> minPartition(int N) {
        int[] currency = { 2000, 500, 200, 100, 50, 20, 10, 5, 2, 1 };

        List<Integer> list = new ArrayList<>();

        for (int val : currency) {

            int cnt = N / val;
            N -= (cnt * val);
            while (cnt-- > 0) { // cnt is 0, is val > N
                list.add(val);
            }
        }
        return list;

    }
}
