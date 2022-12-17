package com.f5_oops.o9_hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Hashmap {

    public static void main(String[] args) {

        String str = "Love is Lust";
        String pat = "ov";
        rollingHash(str, pat);
    }

    public static void rollingHash(String str, String pat) {

        // Rabin-Karp Algorith
        int ps = pat.length();
        int ss = str.length();

        int patHash = 0;
        int strHash = 0;

        for (int i = 0; i < ps; i++) {
            patHash += pat.charAt(i);
            strHash += str.charAt(i);
        }

        for (int i = 0; i <= ss - ps; i++) {
            int j = 0;
            if (patHash == strHash) {
                for (j = 0; j < ps; j++) {
                    if (str.charAt(i + j) != pat.charAt(j)) break;
                }
            }
            if (j == ps) System.out.print(i + " ");
            if (i == ss - ps) break;

            strHash = (strHash - str.charAt(i)) + str.charAt(i + ps);
        }

    }

    public static int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int val : nums) {
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        Map<Integer, Integer> sortedMap = new LinkedHashMap<>();
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue((a, b) -> (b - a))) // instead of lambda use Comparator.reverseorder
                .forEach(entry -> sortedMap.put(entry.getKey(), entry.getValue()));

        int[] res = new int[k];
        int i = 0;
        for (Integer key : sortedMap.keySet()) {
            if (i == k) break;
            res[i++] = key;
        }
        return res;
    }

    public static void frequencySort() {
        int[] nums = {1, 2, 3, 1, 2, 1};
            /*for(int n : nums){
                //map.put(n, map.getOrDefault(n,0)+1);
                map.merge(n, 1, Integer::sum); // both are same;
            }*/
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.stream(nums).
                forEach(n -> map.put(n, map.getOrDefault(n, 0) + 1));
        nums = Arrays.stream(nums).boxed()
                .sorted((a, b) -> map.get(b) - map.get(a))
                .mapToInt(n -> n)
                .toArray();
        System.out.println(Arrays.toString(nums));
    }
}
