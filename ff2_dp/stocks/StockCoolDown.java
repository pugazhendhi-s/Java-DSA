package com.ff2_dp.stocks;

import java.util.Arrays;

public class StockCoolDown {

    public static void main(String[] args) {

    }
    public static int maxProfit(int[] prices) {

        int n = prices.length;
        int[][] dp = new int[n][2];

        for (int[] d : dp) Arrays.fill(d, -1);

        return func(0, 0, prices, dp);
    }

    public static int func(int ind, int buy, int[] prices, int[][] dp) {

        if (ind >= prices.length) return 0;
        if (dp[ind][buy] != -1) return dp[ind][buy];

        int profit;
        if (buy == 0) {
            profit = Math.max(func(ind+1, 0, prices, dp),
                    -prices[ind] + func(ind+1, 1, prices ,dp));
        }

        else {
            profit = Math.max(func(ind+1, 1, prices, dp),
                    prices[ind] + func(ind+2, 0, prices, dp));  // ind + 2 whenever sold stock, else same as stockII
        }

        return dp[ind][buy] = profit;
    }

    public static int tabulation(int[] prices) {

        int n = prices.length;
        int[][] dp = new int[n+2][2];

        dp[n][0] = 0;

        for (int ind = n-1; ind >= 0; ind--) {
            for (int buy=0; buy <=1; buy++) {
                int profit = 0;
                if (buy == 0) {
                    profit = Math.max(dp[ind+1][0],-prices[ind] + dp[ind+1][1]);
                }
                else {
                    profit = Math.max(dp[ind+1][1], prices[ind] + dp[ind+2][0]);
                }
                dp[ind][buy] = profit;
            }
        }
        return dp[0][0];
    }

    public static int opti(int[] prices) {

        int n = prices.length;
        int[] aft1 = new int[2];
        int[] aft2 = new int[2];

        for (int ind = n-1; ind >= 0; ind--) {
            int[] curr = new int[2];
            for (int buy=0; buy <=1; buy++) {
                int profit;
                if (buy == 0) {
                    profit = Math.max(aft1[0],-prices[ind] + aft1[1]);
                }
                else {
                    profit = Math.max(aft1[1], prices[ind] + aft2[0]);
                }
                curr[buy] = profit;
            }
            aft2 = aft1;
            aft1 = curr;
        }
        return aft1[0];
    }
}
