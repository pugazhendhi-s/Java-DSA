package com.f4_mathematics.bits;
import java.util.*;
public class LCMedium {
    public static void main(String[] args) {
        System.out.println(repeatedDNASequence("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
    }
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for(int i=0; i< (1 << nums.length); i++){
            List<Integer> list = new ArrayList<>();
            for(int j=0; j<nums.length; j++){
                if(((i >> j) & 1) != 0)
                    list.add(nums[j]);
            }
            if(!result.contains(list))
                result.add(new ArrayList<>(list));
        }
        return result;
    }
    public static List<Integer> grayCode(int n){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < (1 << n); i++) {
            int val = i ^ i >> 1;
            list.add(val);
        }
        return list;
    }
    public static List<String> repeatedDNASequence(String s){
        /*HashSet<String> seen = new HashSet<>(), repeated = new HashSet<>();
        int n = s.length();
        for (int i = 0; i < n - 9; i++) {
            String dna = s.substring(i, i+10);
            if(!seen.add(dna))
                repeated.add(dna);
        }
        return new ArrayList<>(repeated);
        */
        List<String> repeatedSequence = new ArrayList<>();
        Map<String, Integer> seen = new HashMap<>();

        for(int i=0; i < s.length()-9; i++){
            String subSequence = s.substring(i, i+10);
            seen.put(subSequence, seen.getOrDefault(subSequence, 0)+1);
            if(seen.get(subSequence) == 2)
                repeatedSequence.add(subSequence);
        }
        return repeatedSequence;
    }
    public int getXORSum(int[] a, int[] b) {
        /**1835. Find XOR Sum of All Pairs Bitwise AND
         * Example 1:
         *
         * Input: arr1 = [1,2,3], arr2 = [6,5]
         * Output: 0
         * Explanation: The list = [1 AND 6, 1 AND 5, 2 AND 6, 2 AND 5, 3 AND 6, 3 AND 5] = [0,1,2,0,2,1].
         * The XOR sum = 0 XOR 1 XOR 2 XOR 0 XOR 2 XOR 1 = 0.
         *
         * Ans :
         * Intuition
         * We all know the distributive property that (a1+a2)*(b1+b2) = a1*b1 + a1*b2 + a2*b1 + a2*b2
         *
         * Now focus on each bit,
         * for example the last bit of A[i] and B[j],
         * and think how it works and affect the result.
         *
         *
         * Explanation
         * Distributive property is similar for AND and XOR here.
         * (a1^a2) & (b1^b2) = (a1&b1) ^ (a1&b2) ^ (a2&b1) ^ (a2&b2)
         * (I wasn't aware of this at first either)
         *
         *
         * Complexity
         * Time O(A + B)
         * Space O(1)
         */
        int xorA = 0;
        int xorB = 0;
        for(int n : a)
            xorA ^= n;
        for(int n : b)
            xorB ^= n;

        return xorA & xorB;
    }



    public static long calcBeauty(int[] a, int n){
        long sum = 0;
        StringBuilder sb = new StringBuilder();
        for (int val: a) {
            sb.append(val);
        }
        String ch = sb.toString();
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < ( 1 << n)-1; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if(((i >> j) & 1) != 0 && !list.contains(a[j]))
                    list.add(a[j]);
            }
            StringBuilder b = new StringBuilder();
            for (int val: list) {
                b.append(val);
            }
            String temp = b.toString();
            if(ch.contains(temp))
                res.add(list);
        }
        System.out.println(res);
        for(List<Integer> li : res){
            sum += li.size();
        }
        return sum;
    }
}
