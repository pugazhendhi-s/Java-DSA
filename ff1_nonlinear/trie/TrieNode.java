package com.ff1_nonlinear.trie;

public class TrieNode {

    private final TrieNode[] links = new TrieNode[26];
    private int end = 0;  // count ends with
    private int prefix = 0; // count prefix

    public boolean containKey(char ch) {
        return links[ch -'a'] != null;
    }

    public void put(char ch,TrieNode node) {
        links[ch -'a'] = node;
    }

    public TrieNode get(char ch) {
        return links[ch - 'a'];
    }

    public void incPrefix() {
        prefix++;
    }

    public void incEnd() {
        end++;
    }

    public void reducePrefix() {
        prefix--;
    }

    public void deleteEnd() {
        end--;
    }

    public int getPrefix() {
        return prefix;
    }

    public int getEnd() {
        return end;
    }
}
