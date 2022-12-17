package com.ff1_nonlinear.trees.bst;

import java.util.Stack;

public class BSTIterator {

    public boolean reverse = true;
    private final Stack<TreeNode> stack = new Stack<>();

    public BSTIterator(TreeNode root, boolean reverse) {
        this.reverse = reverse;
        pushAll(root);
    }


    public boolean hasNext() {
        return !stack.empty();
    }

    public int next() {

        TreeNode node = stack.pop();

        if (reverse) pushAll(node.left);
        else pushAll(node.right);

        return node.val;
    }

    private void pushAll(TreeNode node) {

        while (node != null) {

            stack.push(node);
            node = (reverse) ? node.right : node.left;
        }
    }

    public boolean find2Sum(TreeNode root, int key) {

        if (root == null) return false;

        BSTIterator left = new BSTIterator(root, false); // ascending
        BSTIterator right = new BSTIterator(root, true);

        int i = left.next();
        int j = right.next();

        while (i < j) {

            if (i + j == key) return true;
            else if (i + j < key) i = left.next();
            else j = right.next();
        }
        return false;
        // TIme complexity -> O(N), space -> O(2 * H);
    }
}
