package com.ff2_dp.stocks;

import java.util.Arrays;

public class StockFee {

    public static void main(String[] args) {

    }
    public int maxProfit(int[] prices, int fee) {

        int n = prices.length;
        int[][] dp = new int[n][2];

        for (int[] d : dp) Arrays.fill(d, -1);

        return func(0, 0, fee, prices, dp);
    }

    public static int func(int ind, int buy,int fee, int[] prices, int[][] dp) {

        if (ind == prices.length) return 0;
        if (dp[ind][buy] != -1) return dp[ind][buy];

        int profit;
        if (buy == 0) {
            profit = Math.max(func(ind+1, 0, fee, prices, dp),
                    -prices[ind] + func(ind+1, 1, fee, prices ,dp));
        }

        else {
            profit = Math.max(func(ind+1, 1, fee, prices, dp),
                    prices[ind] - fee + func(ind+1, 0, fee, prices, dp));  // - fee whenever sold stock, else same as stockII
        }

        return dp[ind][buy] = profit;
    }
    public int tabulation(int[] prices, int fee) {

        int n = prices.length;
        int[][] dp = new int[n+1][2];

        dp[n][0] = 0;

        for (int ind = n-1; ind >= 0; ind--) {
            for (int buy=0; buy <=1; buy++) {
                int profit;
                if (buy == 0) {
                    profit = Math.max(dp[ind+1][0],-prices[ind] + dp[ind+1][1]);
                }
                else {
                    profit = Math.max(dp[ind+1][1], prices[ind] + dp[ind+1][0] - fee);
                }
                dp[ind][buy] = profit;
            }
        }
        return dp[0][0];
    }
    public int opti(int[] prices, int fee) {

        int n = prices.length;
        int[] after = new int[2];

        for (int ind = n-1; ind >= 0; ind--) {
            int[] curr = new int[2];
            for (int buy=0; buy <=1; buy++) {
                int profit;
                if (buy == 0) {
                    profit = Math.max(after[0],-prices[ind] + after[1]);
                }
                else {
                    profit = Math.max(after[1], prices[ind] + after[0] - fee);
                }
                curr[buy] = profit;
            }
            after = curr;
        }
        return after[0];
    }
}
