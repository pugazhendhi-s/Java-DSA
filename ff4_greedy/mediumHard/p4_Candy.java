package com.ff4_greedy.mediumHard;

import java.util.Arrays;

public class p4_Candy {

    /*
        Do it in two directions.
        The first loop makes sure children with a higher rating get more candy than its left neighbor;
        The second loop makes sure children with a higher rating get more candy than its right neighbor.
     */

    public static void main(String[] args) {

    }

    public static int candy(int[] ratings) {

        int n = ratings.length;
        int[] candy = new int[n];
        Arrays.fill(candy, 1);

        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i-1]) {
                candy[i] = candy[i-1] + 1;
            }
        }

        for (int i = n-2; i >= 0; i--) {
            if (ratings[i] > ratings[i+1]) {
                candy[i] = Math.max(candy[i], 1 + candy[i+1]);
            }
        }

        int candies = 0;
        for (int val : candy) {
            candies += val;
        }

        return candies;
    }
}
