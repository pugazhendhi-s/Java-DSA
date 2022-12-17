package com.ff1_nonlinear.trees.bt.traversals;

import java.util.ArrayList;
import java.util.List;

public class Morris {

    public static void main(String[] args) {

    }

    public static TreeNode createTree() {

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.left.right.left = new TreeNode(6);

        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(8);

        root.right.right.left = new TreeNode(9);
        root.right.right.right = new TreeNode(10);
        return root;
    }

    public static List<Integer> morrisInOrder(TreeNode root) {

        List<Integer> list = new ArrayList<>();
        TreeNode curr = root;

        while (curr != null) {

            if (curr.left == null) {
                list.add(curr.val);
                curr = curr.right;
            }
            else {
                TreeNode prev = curr.left;

                while (prev.right != null && prev.right != curr)
                    prev = prev.right;

                if (prev.right == null) {
                    prev.right = curr;
                    curr = curr.left;
                }
                else {
                    prev.right = null;
                    list.add(curr.val);
                    curr = curr.right;
                }
            }
        }
        return list;
    }

    public static List<Integer> morrisPreOrder(TreeNode root) {

        List<Integer> list = new ArrayList<>();
        TreeNode curr = root;

        while (curr != null) {

            if (curr.left == null) { // left, root , right
                list.add(curr.val);
                curr = curr.right;
            }
            else {
                TreeNode prev = curr.left;

                while (prev.right != null && prev.right != curr)
                    prev = prev.right;

                if (prev.right == null) {
                    prev.right = curr;
                    list.add(curr.val);
                    curr = curr.left;
                }
                else {
                    prev.right = null;
                    curr = curr.right;
                }
            }
        }
        return list;
    }
}
