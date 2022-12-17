package com.ff2_dp.strings;

public class LCSproblems {

    public static void main(String[] args) {

        // String palin = longestPalindrome("lactijvsodsvjgnlnhshqyuivgwxrluoshfkhlsb");
        //System.out.println(palin);

//        String s = "brute";
//        String t = "groot";
//        String ans = superSequence(s, t);
//        System.out.println(ans);

        String subs = "etitl";
        String substring = substringDP(subs);
        System.out.println(substring);
    }
    // subsequence
    public static int longestPalindromeSubsequence(String s) {

        StringBuilder sb = new StringBuilder(s);
        String rev = sb.reverse().toString();

        int n = s.length();

        int[][] dp = new int[n+1][n+1];

        for (int j=0; j<=n; j++) dp[0][j] = 0;
        for (int i=0; i<=n; i++) dp[i][0] = 0;

        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                if (s.charAt(i-1) == rev.charAt(j-1))
                    dp[i][j] = 1 + dp[i-1][j-1];
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[n][n];
    }

    public static int minInsertion(String s) {  // min insertion to make palindrome string

        int n = s.length();
        StringBuilder sb = new StringBuilder(s).reverse();
        String r = sb.toString();

        int[][] dp = new int[n+1][n+1];

        // base case 0 index is treated as -1 index
        for (int j=0; j<=n; j++) dp[0][j] = 0;  // j = 0 refers j=-1
        for (int i=0; i<=n; i++) dp[i][0] = 0;  // u need not make it as zero , default it will be zero , just for procedure we do

        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                if (s.charAt(i-1) == r.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return n - dp[n][n];
    }

    public int minDistance(String s, String t) {

        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n+1][m+1];

        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++) {
                if (s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return (n + m)- (2 * dp[n][m]);  // n-dp[n][m] + m - dp[n][m]
    }

    public static String superSequence(String s, String t) {

        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n+1][m+1];

        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++) {
                if (s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        int length_of_superSequence = (n+m) - dp[n][m];

        int i=n; int j=m;
        StringBuilder res = new StringBuilder();

        while (i > 0 && j > 0) {
            if (s.charAt(i-1) == t.charAt(j-1)) {
                res.insert(0, s.charAt(i - 1));
                i--; j--;
            }
            else if (dp[i-1][j] > dp[i][j-1]) {
                res.insert(0, s.charAt(i - 1));
                i--;
            }
            else {
                res.insert(0, t.charAt(j - 1));
                j--;
            }
        }

        while (i > 0) {
            res.insert(0, s.charAt(i - 1)); i--;
        }
        while (j > 0) {
            res.insert(0, t.charAt(j - 1)); j--;
        }
        return res.toString();  // insert or append and reverse
    }



    // substring
    public static String longestPalindrome(String s) {

        int n = s.length();
        int start= 0; int end = 0;

        for (int i=0; i<n ; i++) {

            int l = i, r = i;  // l -> search in  left half, r -> right
            char ch = s.charAt(i);

            while (l >= 0 && s.charAt(l) == ch) l--;  // get duplicate

            while (r < n && s.charAt(r) == ch) r++;  // get duplicate

            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                l--; r++;
            }
            l++; // last val of l & r is not equal, so get previous l, r val
            // r-- is not needed because we again add to convert r to 1 based that's

            if (end - start < r - l) {
                start = l;
                end = r;
            }
        }
        return s.substring(start, end);
    }
    // substring using dp
    public static String substringDP(String s) {

        int n=s.length();
        boolean[][] dp = new boolean[n][n];
        int start = 0, end = 0;

//        for (int i=n-1; i>=0; i--) {
//            for (int j=i; j<n; j++) {
//
//                dp[i][j] = (s.charAt(i) == s.charAt(j)) && (j-i < 2 || dp[i+1][j-1]);
//
//                if (dp[i][j] && j-i > end-start) {
//                    start = i;
//                    end = j;
//                }
//            }
//        }

        for (int i = 0; i < n; i++) {
            for (int j = i; j >= 0; j--) {

                if (s.charAt(i) == s.charAt(j) && (j-i < 2 || dp[i-1][j+1])){
                    dp[i][j] = true;
                }
                if (dp[i][j] && i-j > end-start) {
                    end = i;
                    start = j;
                }
            }
        }
        return s.substring(start, end+1);
    }
}
