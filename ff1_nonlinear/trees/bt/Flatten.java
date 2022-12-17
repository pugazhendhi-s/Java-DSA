package com.ff1_nonlinear.trees.bt;

import java.util.Stack;

public class Flatten {

    public static void main(String[] args) {

    }

    // 114. Flatten Binary Tree to Linked List
    public static void flatten(){

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        root.left.left.left = new TreeNode(8);
        root.left.left.right = new TreeNode(9);

        TreeNode r1 = flatten(root); // comes like linked list
        flattenRec(root);
    }

    public static TreeNode flatten(TreeNode root) {

        TreeNode curr = root;

        while (curr != null) {

            if (curr.left != null) {
                TreeNode prev = curr.left;

                while (prev.right != null)
                    prev = prev.right;

                prev.right = curr.right;
                curr.right = curr.left;
                curr.left = null;
            }
            else
                curr = curr.right;
        }
        return root;
    }  // T -> O(N), S -> O(1)

    private static TreeNode prev = null;


    // initialize this prev for every object, using constructor call, else remove static in both method and prev
    // becoz if static means, for new object same prev will be executed


    public static void flattenRec(TreeNode root){

        if (root == null) return;

        flatten(root.right);
        flatten(root.left);

        root.right = prev;
        root.left = null;
        prev = root;
    }

    public static void flattenByStack(TreeNode root){

        if (root == null) return;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.empty()) {

            TreeNode curr = stack.pop();

            if (curr.right != null) stack.push(curr.right);
            if (curr.left != null) stack.push(curr.left);

            if (!stack.empty()) curr.right = stack.peek();
            curr.left = null;
        }
    }
}
