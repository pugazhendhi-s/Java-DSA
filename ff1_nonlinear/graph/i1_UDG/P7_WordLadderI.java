package com.ff1_nonlinear.graph.i1_UDG;

import java.util.*;

public class P7_WordLadderI {

    public static void main(String[] args) {

    }

    public static void wordLadderI() {

        String beginWord = "hit";
        String endWord = "cog";  // wordList[i].length will be eqauls for all words int list
        String[] wordList = {"hot", "dot", "dog", "lot", "log", "cog"};
    }

    public static int ladderLength(String bw, String ew, List<String> wl) {

        Set<String> set = new HashSet<>(wl);
        if (!set.contains(ew)) return 0;

        Queue<String> queue = new LinkedList<>();
        queue.offer(bw);

        Set<String> vis = new HashSet<>();
        vis.add(bw);

        int changes = 0;

        // tc ->outer -> O( N)
        while (!queue.isEmpty()) {

            int size = queue.size();
            changes++;

            for (int i = 0; i < size; i++) {

                String word = queue.poll();
                if (word.equals(ew)) return changes;

                // Tc -> inner loop -> O(word[i].length * 26 * log N) log N for set
                for (int j = 0; j < word.length(); j++) {

                    for (char k = 'a'; k <= 'z'; k++) {

                        char[] ch = word.toCharArray();
                        ch[j] = k;

                        String temp = new String(ch);

                        if (set.contains(temp) && vis.add(temp)) {
                            queue.add(temp);
                        }
                    }
                }
            }
        }
        return 0;

        // tc -> O(N * word.length * 26 * logN)
    }
}

