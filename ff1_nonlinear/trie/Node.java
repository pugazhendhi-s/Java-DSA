package com.ff1_nonlinear.trie;

public class Node {

    private final Node[] links = new Node[26];
    private boolean flag = false;

    public boolean containsKey(char ch) {
        return links[ch -'a'] != null;
    }

    public void put(char ch, Node node) {
        links[ch -'a'] = node;
    }

    public Node get(char ch) {
        return links[ch -'a'];
    }

    public void setEnd() {
        flag = true;
    }

    public boolean isEnd() {
        return flag;
    }
}
