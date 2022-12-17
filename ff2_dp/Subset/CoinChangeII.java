package com.ff2_dp.Subset;
import java.util.*;

public class CoinChangeII {

    public static void main(String[] args) {

    }
    public int change(int amount, int[] coins) {

        int n = coins.length;
        int[][] dp = new int[n][amount+1];
        for (int[] d : dp) Arrays.fill(d, -1);
        return func(n-1, amount, coins, dp);
    }

    public int func(int ind, int tar, int[] coins, int[][] dp) {

        if (tar == 0) return 1;
        if (ind == 0) return tar % coins[0] == 0 ? 1 : 0;
        if (dp[ind][tar] != -1) return dp[ind][tar];

        int notpick = func(ind-1, tar, coins, dp);
        int pick = (tar >= coins[ind]) ? func(ind, tar-coins[ind], coins, dp) : 0;

        return dp[ind][tar] = pick + notpick;
    }
    public int opti(int T, int[] coins) {

        int n = coins.length;
        int[] prev = new int[T+1];

        for (int i=0; i<=T; i++) {
            if (i % coins[0] == 0) prev[i] = 1;
        }

        for (int ind=1; ind<n; ind++) {
            int[] curr = new int[T+1];
            for (int tar = 0; tar <= T; tar++) {
                int notpick = prev[tar];
                int pick = (coins[ind] <= tar) ? curr[tar-coins[ind]] : 0;

                curr[tar] = notpick + pick;
            }
            prev = curr;
        }
        return prev[T];
    }
    public int changeEff(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i-coin];
            }
        }
        return dp[amount];
    }
}
