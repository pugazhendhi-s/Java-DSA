package com.f8_recursion.r6_problems.Medium;


public class findKthBit {
    public static void main(String[] args) {
        System.out.println(kthGrammar(2, 1));
    }
    // 1.simple iterative solution
    private static char kthBit(int n, int k){
        if(n == 1)
            return '0';
        int l = (1 << n) - 1;  // 1 << 3 = (8)-1 = 7 = l
        if(k == (l/2)+1)
            return '1'; // always middle value is 1
        else if(k > (l/2)+1)
            return (char)(1 ^ kthBit(n-1, l + 1 - k));
        else
            return (kthBit(n-1, k));
    }
    // recursive solution
    private static char findKthBits(int n, int k) {
        String bits = findKthBits(n, "0");
        System.out.println(bits);
        return bits.charAt(k-1);
    }
    private static String findKthBits(int n, String bits){
        if(n == 0)
            return bits;
        char[] bitArray = reverseInverted(bits.toCharArray());
        String newString = bits + "1" + new String(bitArray);
        return findKthBits(n-1, newString);
    }

    private static char[] reverseInverted(char[] bits) {
        int i=0; int j=bits.length-1;
        while (i < j){
            if(bits[i] == bits[j]){
                bits[i] ^= 1;
                bits[j] ^= 1;
            }
            i++; j--;
        }
        if(bits.length % 2 != 0)
            bits[i] ^= 1;
        return bits;
    }

    // 2. kth Grammar - Recursion and bit manipulation and math
    public static int kthGrammar(int n, int k) {
        if(n == 1)
            return 0;
        int parent = kthGrammar(n-1, (k+1)/2);
        return k % 2 != 0 ? parent : parent ^ 1;
        // return Integer.bitCount(K - 1) & 1; // one line solution
        /**
         * We build a table of n rows (1-indexed). We start by writing 0 in the 1st row.
         * Now in every subsequent row, we look at the previous row and replace
         * each occurrence of 0 with 01, and each occurrence of 1 with 10.
         *
         * For example, for n = 3, the 1st row is 0, the 2nd row is 01, and the 3rd row is 0110.
         * Given two integer n and k, return the kth (1-indexed) symbol in the nth row of a table of n rows.
         *
         *
         *
         * Example 1:
         *
         * Input: n = 1, k = 1
         * Output: 0
         * Explanation: row 1: 0
         * Example 2:
         *
         * Input: n = 2, k = 1
         * Output: 0
         *
         * Explanation :
         *
         * Row1	        			  0
         * 						  /       \
         * Row2					0          1
         * 					   /   \      /    \
         * Row3				  0     1     1      0
         * 					 / \    / \   / \   / \
         * Row4				0  1   1   0  1  0  0  1
         *
         * Index(for Row 4)->  1  2   3   4  5  6  7  8
         * Notice that:
         *
         * the parent of kth index in nth row is
         * k/2 index in the n-1 row for even numbered indexes.
         * (k+1)/2 index in the n-1 row for odd numbered indexes.
         * Also notice that the kth value of the index in nth row is:
         *
         * flipped (reversed) value of the parent i.e. k/2 index in the n-1 row for even numbered indexes.
         * same value as for the parent i.e. the (k+1)/2 index in the n-1 row for odd numbered indexes.
         */
    }

    // this approach also correct ,but it consumes more time, so not preferred
    public static int kthG(int n, int k) {
        return (int)kthG(n, k, new StringBuilder("0")) - '0';
    }
    public static char kthG(int n, int k, StringBuilder sb){
        if(n == 0) {
            return sb.charAt(k-1);
        }
        StringBuilder cur = new StringBuilder();
        for (int i = 0; i < sb.length(); i++) {
            cur.append(sb.charAt(i) == '0' ? "01" : "10");
        }
        return kthG(n-1, k, cur);
    }

}
