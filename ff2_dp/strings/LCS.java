package com.ff2_dp.strings;
import java.util.*;

public class LCS {

    public static void main(String[] args) {

        //int val = lcs("adebc", "dcadb");
        //System.out.println(val);

        longestSubSeqPrint();
    }

    // brute force
    public static int lcs(String s, String t) {

        List<String> ls = powerSet(s);
        List<String> lt = powerSet(t);

        int maxi = 0;
        for (String seq : ls) {
            if (seq.length() <= maxi) continue;
            if (lt.contains(seq)) {
                maxi = seq.length();
            }
        }
        return maxi;
    }
    public static List<String> powerSet(String s)  {
        int n = s.length();
        List<String> list = new ArrayList<>();

        for (int i=1; i<Math.pow(2,n); i++) {
            StringBuilder temp = new StringBuilder();
            for (int j=0; j<n; j++) {
                if ( (i & (1 << j)) != 0)
                    temp.append(s.charAt(j));
            }
            list.add(temp.toString());
        }
        return list;
    }


    public static int lcsDP(String s, String t) {

        int n = s.length(), m = t.length();
        int[][] dp = new int[n][m];
        for  (int[] d : dp) Arrays.fill(d, -1);
        return func(n-1, m-1, s, t, dp);
    }

    public static int func(int i, int j, String s, String t, int[][] dp) {

        if (i < 0 || j < 0) return 0;
        if (dp[i][j] != -1) return dp[i][j];

        if (s.charAt(i) == t.charAt(j))
            return dp[i][j] = 1 + func(i-1, j-1, s, t, dp);

        return dp[i][j] = Math.max(func(i-1, j, s, t, dp),func(i, j-1, s, t, dp));
    }

    public static int tabulation(String s, String t, int[][] dp) {

        int n = s.length(), m = t.length();
        // base case
        for (int j=0; j<=m; j++) dp[0][j] = 0;
        for (int i=0; i<=n; i++) dp[i][0] = 0;

        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++) {
                if (s.charAt(i-1) == t.charAt(j-1))
                    dp[i][j] = 1 + dp[i-1][j-1];
                else
                    dp[i][j] = Math.max(dp[i-1][j] ,dp[i][j-1]);
            }
        }
        return dp[n][m];
    }

    public static int opti(String s, String t) {

        int n = s.length(), m = t.length();

        int[] prev = new int[m+1];
        int[] curr = new int[m+1];
        // base case
        for (int j=0; j<=m; j++) prev[j] = 0; // default it will be zero

        for (int i=1; i<=n; i++) {

            for (int j=1; j<=m; j++) {
                if (s.charAt(i-1) == t.charAt(j-1))
                    curr[j] = 1 + prev[j-1];
                else
                    curr[j] = Math.max(prev[j], curr[j-1]);
            }
            prev = curr.clone();  // or u can create curr inside i loop
        }
        return prev[m];
    }

    // print longest subsequence

    public String all_longest_common_subsequences(String s, String t)
    {
        int n = s.length(), m = t.length();
        String[][] dp = new String[n][m];

        for  (String[] d : dp) Arrays.fill(d, "-");
        return funcString(n-1, m-1, s, t, dp);
    }

    public static String funcString(int i, int j, String s, String t, String[][] dp) {

        if (i == -1 || j == -1) return "";
        if (! dp[i][j].equals("-")) return dp[i][j];

        if (s.charAt(i) == t.charAt(j))
            return dp[i][j] = funcString(i-1, j-1, s, t, dp) + s.charAt(i);

        String x = funcString(i-1, j, s, t, dp);
        String y = funcString(i, j-1, s, t, dp);

        return dp[i][j] = (x.length() >= y.length()) ? x : y;
    }

    public static void longestSubSeqPrint() {
        String s = "abcde";
        String t = "bdgek";
        int n = s.length(), m = t.length();
        int[][] dp = new int[n+1][m+1];

        int len = tabulation(s, t, dp);

        for (int[] d : dp) {
            System.out.println(Arrays.toString(d));
        }
        StringBuilder builder = new StringBuilder();
        int i = n, j = m;

        while (i > 0 && j > 0 && len > 0) {

            if (s.charAt(i-1) == t.charAt(j-1)) {
                builder.insert(0, s.charAt(i-1));
                i--; j--; len--;
            }
            else if (dp[i-1][j] > dp[i][j-1]) {
                i--;
            }
            else j--;
        }
        System.out.println(builder);
    }

    public static int lcSubString(String s, String t) {

        int n=s.length(), m=t.length();
        int[][] dp = new int[n+1][m+1];

        for (int j=0; j<=m; j++) dp[0][j] = 0;
        for (int i=0; i<=n; i++) dp[i][0] = 0;

        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++) {
                if (s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else {
                    dp[i][j] = 0; // string broke in new string
                }
            }
        }
        int maxi = 0;  // we can find this in previous for loop, and we can use prev curr to optimize
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++) {
                if (maxi < dp[i][j]) maxi = dp[i][j];
            }
        }
        return maxi;
    }

}
