package com.f8_recursion.r6_problems.Medium;

import java.util.ArrayList;
import java.util.LinkedList;

public class KthPermutationString {
    public static void main(String[] args) {
        System.out.println(getPermutationEff(3, 5));
    }
    // all are same
    public static String getPermutationEff(int n, int k){
        LinkedList<Integer> notUsed = new LinkedList<>();
        int fact = 1;
        for (int i = 1; i < n; i++) {
            fact *= i;
            notUsed.add(i);
        }
        k -= 1;
        notUsed.add(n);
        StringBuilder res = new StringBuilder();
        while (true){
            int index = k / fact;
            k = k % fact;
            res.append(notUsed.remove(index));
            if (notUsed.isEmpty())
                break;
            fact /= notUsed.size();
        }
        return res.toString();
    }
    public static String getPermutation(int n, int k) {
        ArrayList<Integer> num = new ArrayList<>();
        int[] f = new int[n+1];
        int fact = 1;
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
            f[i] = fact;
            num.add(i);
        }
        k = k-1;
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++){
            int index = k / f[n-i];
            sb.append(num.get(index));
            num.remove(index);
            k -= index * f[n-i];
        }
        return sb.toString();
    }
    public static String getPermutationI(int n, int k) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> numbers = new ArrayList<>();
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
            numbers.add(i);
        }
        k -= 1; // index starts at 0, if k = 6, means get string at index 5
        for (int i = 0; i < n; i++) {
            fact /= (n - i);
            int index = k / fact;
            sb.append(numbers.remove(index));
            k -= (index * fact);
        }
        return sb.toString();
    }
}
