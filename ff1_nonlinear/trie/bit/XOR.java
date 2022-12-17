package com.ff1_nonlinear.trie.bit;

import java.util.*;

public class XOR {

    public static void main(String[] args) {

    }

    public static int maxXOR(int n, int m, ArrayList<Integer> arr1, ArrayList<Integer> arr2) {

        Trie trie = new Trie();
        for (int num : arr1) {
            trie.insert(num);
        }
        int maxXOR = 0;
        for (int num : arr2) {
            maxXOR = Math.max(maxXOR, trie.getMax(num));
        }
        return maxXOR;
    }

    public int findMaximumXOR(int[] nums) {

        Trie trie = new Trie();
        for (int num : nums) {
            trie.insert(num);
        }
        int maxXOR = 0;
        for (int num : nums) {
            maxXOR = Math.max(maxXOR, trie.getMax(num));
        }
        return maxXOR;
    }

    public int findMaximumXORBySet(int[] nums) {

        int maxXor = 0;
        int mask = 0;

        for (int i=31; i >=0; i--) {
            mask = mask | (1 << i);
            Set<Integer> set = new HashSet<>();

            for (int n : nums) {
                set.add(n & mask);     //   [3,10,5,25,2,8]    // 25 -> 11001
            }

            int greedy = maxXor | (1 << i);
            for (int prefix : set) {
                if (set.contains(greedy ^ prefix)) {
                    maxXor = greedy;
                    break;
                }
            }
        }
        return maxXor;
    }

    public int[] maximizeXor(int[] nums, int[][] qu) {  // TLE

        int n = qu.length;
        int m = qu[0].length;

        Trie trie = new Trie();
        int[][] queries = new int[n][m+1];
        for (int i=0; i<n; i++) {
            queries[i][0] = qu[i][0];
            queries[i][1] = qu[i][1];
            queries[i][2] = i;
        }
        Arrays.sort(queries, Comparator.comparingInt(a -> a[1]));
        Arrays.sort(nums);

        int ind = 0;
        int len = nums.length;
        int[] xor = new int[n];

        for (int[] q : queries) {
            while(ind < len && nums[ind] <= q[1]) {
                trie.insert(nums[ind]);
                ind++;
            }
            xor[q[2]] = (ind != 0) ? trie.getMax(q[0]) : -1;
        }
        return xor;
    }

    public static ArrayList<Integer> maxXorQueries(ArrayList<Integer> nums, ArrayList<ArrayList<Integer>> qu) {

        int n = qu.size();
        int m = qu.get(0).size();
        int[] xor = new int[n];
        Trie trie = new Trie();
        int[][] queries = new int[n][m+1];
        for (int i=0; i<n; i++) {
            queries[i][0] = qu.get(i).get(0);
            queries[i][1] = qu.get(i).get(1);
            queries[i][2] = i;
        }
        Arrays.sort(queries, Comparator.comparingInt(a -> a[1]));
        Collections.sort(nums);

        int i = 0;
        int len = nums.size();
        for (int[] q : queries) {
            while (i < len && nums.get(i) <= q[1]) {
                trie.insert(nums.get(i));
                i++;
            }
            xor[q[2]] = (i == 0) ? -1 : trie.getMax(q[0]);
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int x : xor) {
            list.add(x);
        }
        return list;
    }
}
