package com.ff1_nonlinear.trie;

public class Main {

    public static void main(String[] args) {

        String s = "abab";
        int distinct = countDistinctSubstrings(s);
        System.out.println(distinct);

    }
    public static void trie() {
        int n = 5;
        int[] type = {1, 1, 2, 3, 2};
        String[] value = {"hello", "help", "help", "hel", "hel"};

        Trie trie = new Trie();

        for (int i = 0; i < n; i++) {
            if (type[i] == 1) {
                trie.insert(value[i]);
            }
            else if (type[i] == 2) {
                if (trie.search(value[i])) {
                    System.out.println( "true" );
                }
                else {
                    System.out.println("false");
                }
            }
            else {
                if (trie.startsWith(value[i])) {
                    System.out.println("true" );
                }
                else {
                    System.out.println("false");
                }
            }
        }
    }

    public static String completeString(int n, String[] words) {

        Trie trie = new Trie();

        for (String wd : words) {
            trie.insert(wd);
        }
        String longest = "";
        for (String wd : words) {
            if (trie.checkIfPrefixExists(wd)) {
                if (wd.length() > longest.length()) {
                    longest = wd;
                }
                else if (wd.length() == longest.length() && wd.compareTo(longest) < 0){
                    // wd.compareTo(longest) < 0 , means wd is lexicographically smaller than longest
                    // lexicographically smaller refers alphabet order first
                    longest = wd;
                }
            }
        }
        return longest.equals("") ? "None" : longest;

        // Tc -> O(N * len) + O(N * len)
        //        insert       check
    }

    public static int countDistinctSubstrings(String s) {

        int n = s.length();
        int distinct = 0;
        Node root = new Node();
        for (int i=0; i<n; i++) {
            Node node = root;
            for (int j=i; j<n; j++) {
                char ch = s.charAt(j);
                if (!node.containsKey(ch)) {
                    distinct++;
                    node.put(ch, new Node());
                }
                node = node.get(ch);
            }
        }
        return distinct + 1;
    }
}
