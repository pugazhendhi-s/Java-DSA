package com.ff1_nonlinear.trie;

public class TrieII {

    private final TrieNode root;
    public TrieII() {
        root = new TrieNode();
    }

    public void insert(String word) {

        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.containKey(ch)) {
                node.put(ch, new TrieNode());
            }
            node = node.get(ch);
            node.incPrefix();  // instead remove private from varible and directly increase the prefix i.e. node.prefix++;
        }
        node.incEnd();
    }

    public int countWordsEqualTo(String word) {

        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.containKey(ch)) {
                return 0;
            }
            node = node.get(ch);
        }
        return node.getEnd();
    }

    public int countWordsStartingWith(String word) {

        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.containKey(ch)) {
                return 0;
            }
            node = node.get(ch);
        }
        return node.getPrefix();
    }

    public void erase(String word) {

        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (node.containKey(ch)) {
                node = node.get(ch);
                node.reducePrefix();
            }
            else return;
        }
        node.deleteEnd();
    }
}
