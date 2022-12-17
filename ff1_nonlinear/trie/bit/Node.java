package com.ff1_nonlinear.trie.bit;

public class Node {

    Node[] links = new Node[2];

    public boolean containsKey(int bit) {
        return links[bit] != null;
    }

    public void put(int bit, Node node) {
        links[bit] = node;
    }

    public Node get(int bit) {
        return links[bit];
    }
}
