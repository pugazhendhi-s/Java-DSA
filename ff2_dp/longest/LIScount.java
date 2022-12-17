package com.ff2_dp.longest;

public class LIScount {

    public static void main(String[] args) {

        int[] nums = {1,3,5,4,7};
        int count = findNumberOfLIS(nums);
        System.out.println(count);
    }

    public static int findNumberOfLIS(int[] nums) {

        int n = nums.length;
        int[] dp = new int[n];
        int[] cnt = new int[n];

        int maxi = 1;
        for (int ind = 0; ind < n; ind++) {
            dp[ind] = cnt[ind] = 1;

            for (int pre = 0; pre < ind; pre++) {
                if (nums[ind] > nums[pre]) {
                    if (1 + dp[pre] > dp[ind]){
                        dp[ind] = 1 + dp[pre];
                        cnt[ind] = cnt[pre];
                    }
                    else if (dp[ind] == 1 + dp[pre]) {
                        cnt[ind] += cnt[pre];
                    }
                }
            }
            maxi = Math.max(maxi, dp[ind]);
        }

        int nos = 0;
        for (int i=0; i<n; i++) {
            if (dp[i] == maxi) nos += cnt[i];
        }
        return nos;
    }
}
