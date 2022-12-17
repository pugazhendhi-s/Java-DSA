package com.ff1_nonlinear.trie;

public class Trie {  // pronounced as try

    private final Node root;

    public Trie() {
        root = new Node();
    }

    // Inserts a word into the trie

    public void insert(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.containsKey(ch)) {
                node.put(ch, new Node());
            }
            node = node.get(ch);
        }
        node.setEnd();
    }


    // Returns if the word is in the trie -> O(N)

    public boolean search(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.containsKey(ch)) {
                return false;
            }
            node = node.get(ch);
        }
        return node.isEnd();
    }


    // Returns if there is any word in the trie that starts with the given prefix
    // tc -> O(N)
    public boolean startsWith(String prefix) {
        Node node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (!node.containsKey(ch)) {
                return false;
            }
            node = node.get(ch);
        }
        return true;
    }

    public boolean checkIfPrefixExists(String word) {

        Node node = root;
        boolean flag = true; // root flag is true;

        for (int i = 0; i < word.length() && flag; i++) {
            char ch = word.charAt(i);
            if (node.containsKey(ch)) {
                node = node.get(ch);
                flag = node.isEnd();
            }
            else return false;
        }
        return flag;
    }
}
