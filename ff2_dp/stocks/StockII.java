package com.ff2_dp.stocks;

import java.util.Arrays;

public class StockII {

    public static void main(String[] args) {

        int[] prices = {3,3,5,0,0,3,1,4};
        int profit = maxProfit(prices);
        System.out.println(profit);
    }


    public static int maxProfit(int[] prices) {

        int n = prices.length;
        int[][] dp = new int[n][2];

        for (int[] d : dp) Arrays.fill(d, -1);

        return func(0, 0, n, prices, dp);
    }

    public static int func(int ind, int buy, int n, int[] prices, int[][] dp) {

        if (ind == n) return 0;
        if (dp[ind][buy] != -1) return dp[ind][buy];

        int profit;
        if (buy == 0) {
            profit = Math.max(func(ind+1, 0, n, prices, dp),
                    -prices[ind] + func(ind+1, 1, n, prices ,dp));
        }

        else {
            profit = Math.max(func(ind+1, 1, n, prices, dp),
                    prices[ind] + func(ind+1, 0, n, prices, dp));
        }

        return dp[ind][buy] = profit;
    }

    public static int tabulation(int[] prices) {

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
                    profit = Math.max(dp[ind+1][1], prices[ind] + dp[ind+1][0]);
                }
                dp[ind][buy] = profit;
            }
        }
        return dp[0][0];
    }

    public int opti(int[] prices) {

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
                    profit = Math.max(after[1], prices[ind] + after[0]);
                }
                curr[buy] = profit;
            }
            after = curr;
        }
        return after[0];
    }

    public int terms(int[] prices) {

        int n = prices.length;
        int nextSell = 0;
        int nextBuy = 0;

        for (int ind = n-1; ind >= 0; ind--) {
            int currBuy;
            int currSell;

            currBuy = Math.max(nextBuy ,-prices[ind] + nextSell);

            currSell = Math.max(nextSell, prices[ind] + nextBuy);

            nextBuy = currBuy;
            nextSell = currSell;
        }
        return nextBuy;
    }
}
