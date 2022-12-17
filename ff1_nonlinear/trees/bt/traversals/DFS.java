package com.ff1_nonlinear.trees.bt.traversals;

import java.util.*;

public class DFS {

    public static void main(String[] args) {

    }

    public static void examples() {
        TreeNode root = createTree();

        preorder(root);
        System.out.println();

        inorder(root);
        System.out.println();

        postorder(root);
        System.out.println();

        preInPost(root);
        preorder(root);
        System.out.println();

        List<Integer> preorder = preorderIter(root);
        System.out.println(preorder);

        inorder(root);
        System.out.println();

        List<Integer> inorder = inorderIter(root);
        System.out.println(inorder);

        postorder(root);
        System.out.println();

        List<Integer> post = postorderIter(root);
        System.out.println(post);

        List<Integer> postorder = postorderIt(root);
        System.out.println(postorder);
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

    // recursive
    public static void preorder(TreeNode node) {

        if (node == null) return;

        System.out.print(node.val + " -> ");

        preorder(node.left);
        preorder(node.right);

        // 1 -> 2 -> 4 -> 5 -> 6 -> 3 -> 7 -> 8 -> 9 -> 10
        // time complexity -> O(n) - number of nodes
        // space complexity -> O(n) - Best case O(logN) Balanced BT , Worst case O(N) - degenerate (like linked list)
    }

    public static void inorder(TreeNode node) {

        if (node == null) return;

        inorder(node.left);
        System.out.print(node.val + " -> ");
        inorder(node.right);
    }

    public static void postorder(TreeNode node) { // root or node

        if (node == null) return;

        postorder(node.left);
        postorder(node.right);
        System.out.print(node.val + " -> ");

    }

    // iterative
    public static List<Integer> preorderIter(TreeNode node) {

        List<Integer> preorder = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();

        if (node == null) return preorder;

        stack.push(node);

        while (!stack.empty()) {

            node = stack.pop();
            preorder.add(node.val);

            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return preorder;
    }

    public static List<Integer> inorderIter(TreeNode root) {

        List<Integer> inorder = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode node = root;

        while (node != null || !stack.empty()) {

            if (node != null) {
                stack.push(node);
                node = node.left;
            }
            else {
                node = stack.pop();
                inorder.add(node.val);
                node = node.right;
            }
        }
        return inorder;
    }

    public static List<Integer> postorderIter(TreeNode root) {

        List<Integer> post = new LinkedList<>();
        if (root == null)
            return post;

        Stack<TreeNode> st1 = new Stack<>();
        Stack<TreeNode> st2 = new Stack<>();
        st1.push(root);

        while (!st1.empty()) {

            root = st1.pop();
            st2.push(root);

            if (root.left != null) st1.push(root.left);
            if (root.right != null) st1.push(root.right);
        }
        while (!st2.empty()) {
            post.add(st2.pop().val);
        }
        return post;
    } // 2 stack

    public static List<Integer> postorderIt(TreeNode root) {  // 1 stack

        List<Integer> post = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode node = root;

        while (node != null || !stack.empty()) {

            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                TreeNode temp = stack.peek().right;

                if (temp == null) {

                    temp = stack.pop();
                    post.add(temp.val);

                    while (!stack.empty() && temp == stack.peek().right) {
                        temp = stack.pop();
                        post.add(temp.val);
                    }
                } else node = temp;
            }
        }
        return post;
    } // 1 stack

    public static void preInPost(TreeNode root) {

        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(root, 1));

        List<Integer> preorder = new LinkedList<>();
        List<Integer> inorder = new LinkedList<>();
        List<Integer> postorder = new LinkedList<>();

        if (root == null) return;

        while (!stack.empty()) {

            Pair it = stack.pop();

            if (it.num == 1) {

                preorder.add(it.node.val);
                it.num++;
                stack.push(it);

                if (it.node.left != null) {
                    stack.push(new Pair(it.node.left, 1));
                }
            } else if (it.num == 2) {

                inorder.add(it.node.val);
                it.num++;
                stack.push(it);

                if (it.node.right != null) {
                    stack.push(new Pair(it.node.right, 1));
                }
            } else {
                postorder.add(it.node.val);
            }
        }

        System.out.println(preorder);
        System.out.println(inorder);
        System.out.println(postorder);
    }
}
