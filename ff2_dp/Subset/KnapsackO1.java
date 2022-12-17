package com.ff2_dp.Subset;

public class KnapsackO1 {

    public static void main(String[] args) {

    }

    public static int knapsack(int[] weight, int[] value, int n, int maxWeight) {
        
        int[][] dp = new int[n][maxWeight + 1];
        return func(n-1, maxWeight, weight, value, dp);
    }

    public static int func(int i, int W, int[] wt, int[] val, int[][] dp) {

        if (i == 0) return (wt[0] <= W) ? val[0] : 0;
        
        if (dp[i][W] != 0) return dp[i][W];
        
        int notpick = func(i-1, W, wt, val, dp);
        
        int pick = 0;
        if (wt[i] <= W) pick = val[i] + func(i-1, W - wt[i], wt, val, dp);

        return dp[i][W] = Math.max(notpick, pick);
        
        // tc -> O(N * W)  // for recursion without memo tc -> 2 ^ N;
        // sc -> O(N) + O(N*W)
    }
    
    static int tab(int n, int maxWeight, int[] wt, int[] val, int[][] dp) {

        for (int W = wt[0]; W <= maxWeight; W++) {   // W - > maxWeight
            dp[0][W] = val[0];
        }

        for (int ind = 1; ind < n; ind++) {
            for (int W = 0; W <= maxWeight; W++) {

                int notpick = dp[ind-1][W];
                int pick = 0;
                if (wt[ind] <= W)
                    pick = val[ind] + dp[ind-1][W - wt[ind]];

                dp[ind][W] = Math.max(notpick, pick);
            }
        }
        return dp[n-1][maxWeight];
    }

    static int opti(int n, int maxWeight, int[] wt, int[] val) {

        int[] prev = new int[maxWeight+1];
        for (int W = wt[0]; W <= maxWeight; W++) {
            prev[W] = val[0];
        }
        for (int ind = 1; ind < n; ind++) {

            int[] curr = new int[maxWeight+1];
            for (int W = 0; W <= maxWeight; W++) { // w = maxWeight to 0 also works because it depends on prev row values
                int notpick = prev[W];
                int pick = 0;
                if (wt[ind] <= W)
                    pick = val[ind] + prev[W - wt[ind]];

                curr[W] = Math.max(notpick, pick);
            }
            prev = curr;
        }
        return prev[maxWeight];   // sp -> 2 O(W)
    }

    static int func(int n, int maxWeight, int[] wt, int[] val) {

        int[] prev = new int[maxWeight+1];
        for (int W = wt[0]; W <= maxWeight; W++) {
            prev[W] = val[0];
        }
        for (int ind = 1; ind < n; ind++) {

            for (int W = maxWeight; W >= 0; W--) {   // if we start from  0 to maxWeight , we can't update prev because
                int notpick = prev[W];               // that value can affect , some w - wt for that current iteration
                int pick = 0;                       // if we traverse from maxWeight to 0, wt - W always go lesser index,
                if (wt[ind] <= W)                   // so higher index, doesn't affect anything
                    pick = val[ind] + prev[W - wt[ind]];

                prev[W] = Math.max(notpick, pick);
            }
        }
        return prev[maxWeight];   // O(W)
    }

    public static int unboundedKnapsack(int n, int W, int[] cost, int[] wt) {

        int[][] dp = new int[n][W+1];

        for (int i=wt[0]; i<=W; i++) {
            dp[0][i] = (i / wt[0]) * cost[0];
        }
        for (int ind = 1; ind < n; ind++) {

            for (int w = 0; w<=W; w++) {
                int notpick = dp[ind-1][w];
                int pick = (wt[ind] <= w) ? cost[ind] + dp[ind][w-wt[ind]] : 0;

                dp[ind][w] = Math.max(notpick, pick);
            }
        }
        return dp[n-1][W];
    }
    public static int unboundedKnapsackEff(int n, int W, int[] cost, int[] wt) {

        int[] dp = new int[W+1];

        for (int i=wt[0]; i<=W; i++) {
            dp[i] = (i / wt[0]) * cost[0];
        }
        for (int ind = 1; ind < n; ind++) {

            for (int w = 0; w<=W; w++) {
                int notpick = dp[w];

                int pick = 0;
                if (wt[ind] <= w) {
                    pick = cost[ind] + dp[w-wt[ind]];
                }
                //int pick = (wt[ind] <= w) ? cost[ind] + dp[w-wt[ind]] : 0;
                dp[w] = Math.max(notpick, pick);
            }
        }
        return dp[W];
    }

    public static int cutRod(int[] price, int n) {

        int[] dp = new int[n+1];

        for (int i=0; i<=n; i++) {
            dp[i] = i * price[0];
        }
        for (int ind = 1; ind < n; ind++) {

            for (int len = 0; len<=n; len++) {
                int notpick = dp[len];

                int pick = 0;
                int rodLen = ind+1;
                if (rodLen <= len) {
                    pick = price[ind] + dp[len-rodLen];
                }
                dp[len] = Math.max(notpick, pick);
            }
        }
        return dp[n];
    }
}
