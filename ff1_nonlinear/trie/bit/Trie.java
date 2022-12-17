package com.ff1_nonlinear.trie.bit;

public class Trie {

    private final Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(int num) {
        Node node = root;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (!node.containsKey(bit)) {
                node.put(bit, new Node());
            }
            node = node.get(bit);
        }
    }

    public int getMax(int num) {
        Node node = root;
        int maxNum = 0;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (node.containsKey(bit ^ 1)) {    // (1-bit) or (1 ^ bit) it toggles the bit to opposite, that the one we needed to take
                maxNum = maxNum | (1 << i);
                node = node.get(bit ^ 1); // or 1-bit
            }
            else node = node.get(bit);
        }
        return maxNum;
    }
}
