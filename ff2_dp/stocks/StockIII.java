package com.ff2_dp.stocks;

import java.util.Arrays;

public class StockIII {

    public static void main(String[] args) {

        int[] prices = {3,3,5,0,0,3,1,4};
        int profit = maxProfit(prices);
        System.out.println(profit);
    }

    public static int maxProfit(int[] prices) {

        int n = prices.length;
        int[][][] dp = new int[n][2][2];

        for (int[][] d : dp) {
            for (int[] s : d) {
                Arrays.fill(s, -1);
            }
        }

        return func(0, 0, 0, prices, dp);
    }

    public static int func(int ind, int buy, int cap, int[] prices, int[][][] dp) {

        if (ind == prices.length || cap == 2) return 0;
        if (dp[ind][buy][cap] != -1) return dp[ind][buy][cap];

        int profit;
        if (buy == 0) {
            profit = Math.max(func(ind+1, 0, cap, prices, dp),
                    - prices[ind] + func(ind+1, 1, cap, prices ,dp));
        }
        else {
            if (prices[ind] == 0) return dp[ind][buy][cap];
            profit = Math.max(func(ind+1, 1, cap, prices, dp),
                    prices[ind] + func(ind+1, 0, cap+1, prices, dp));
        }

        return dp[ind][buy][cap] = profit;
    }

    public static int tabulation(int[] prices) {

        int n = prices.length;
        int[][][] dp = new int[n+1][2][3];

        // base case cap == 2// buy and price can be 0
        for (int ind=0; ind<=n; ind++) {
            for (int buy = 0; buy <= 1; buy++) {
                dp[ind][buy][2] = 0;
            }
        }
        // base case i==n// buy and cap is 0, but no need to implement this, default it will be 0;

        for (int ind = n-1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap >= 0; cap--) {
                    if (buy == 0) {
                        dp[ind][buy][cap] = Math.max(dp[ind + 1][0][cap], -prices[ind] + dp[ind + 1][1][cap]);
                    }
                    if(buy == 1){
                        dp[ind][buy][cap] = Math.max(dp[ind + 1][1][cap], prices[ind] + dp[ind + 1][0][cap+1]);
                    }
                }
            }
        }
        return dp[0][0][0];
    }

    public static int opti(int[] prices) {

        int n = prices.length;
        int[][] after = new int[2][3];

        for (int ind = n-1; ind >= 0; ind--) {
            int[][] curr = new int[2][3];
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap >= 0; cap--) {    // instead of 2 transactions, it may give k transactions
                    if (buy == 0) {
                        curr[buy][cap] = Math.max(after[0][cap], -prices[ind] + after[1][cap]);
                    }
                    if(buy == 1){
                        curr[buy][cap] = Math.max(after[1][cap], prices[ind] + after[0][cap+1]);
                    }
                }
            }
            after = curr;
        }
        return after[0][0];
    }

    public static int KTransactions(int k, int[] prices) {

        int n = prices.length;   // cap -> transactions
        int[][] after = new int[2][k+1];

        for (int ind = n-1; ind >= 0; ind--) {
            int[][] curr = new int[2][k+1];
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = k-1; cap >= 0; cap--) {
                    if (buy == 0) {
                        curr[buy][cap] = Math.max(after[0][cap], -prices[ind] + after[1][cap]);
                    }
                    if(buy == 1){
                        curr[buy][cap] = Math.max(after[1][cap], prices[ind] + after[0][cap+1]);
                    }
                }
            }
            after = curr;
        }
        return after[0][0];
    }
}
