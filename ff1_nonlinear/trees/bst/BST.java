package com.ff1_nonlinear.trees.bst;

import java.util.Stack;

public class BST {

    private TreeNode first, prev, last;

    public static void main(String[] args) {

        TreeNode root = buildTree();
        TreeNode succ = inorderSuccessor(root, root);
        if (succ != null)
            System.out.println(succ.val);
        else System.out.println(-1);
//        TreeNode node = searchBST(root, 10);
//        System.out.println(node.val);
//
//        int ceil = ceil(root, 11);
//        System.out.println(ceil);
//
//        root = insertIntoBST(root, 1);
//        int cel = ceil(root, 0);
//        System.out.println(cel);
    }

    public static TreeNode buildTree() {

        TreeNode root = new TreeNode(8);

        root.left = new TreeNode(5);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(7);
        root.left.right.left = new TreeNode(6);

        root.right = new TreeNode(12);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(14);
        root.right.right.left = new TreeNode(12);

        return root;
    }

    private static TreeNode searchBST(TreeNode root, int val) {

        while (root != null && root.val != val)
            root = (root.val > val) ? root.left : root.right;

        return root;
    }

    private static int ceil(TreeNode root, int key) {

        int ceil = -1;

        while (root != null) {
            if (root.val == key) {
                ceil = key; // root.val
                return ceil;
            }
            else if (key > root.val) root = root.right;
            else {
                ceil = root.val;
                root = root.left;
            }
        }
        return ceil;
    }

    private static int floor(TreeNode root, int key) {

        int floor = -1;

        while (root != null) {
            if (root.val == key) {
                return key; // root.val
            } else if (key < root.val) root = root.left;
            else {
                floor = root.val;
                root = root.right;
            }
        }
        return floor;
    }

    public static TreeNode insertIntoBST(TreeNode root, int val) {

        TreeNode node = root;

        if (node == null) return new TreeNode(val);

        while (true) {

            if (node.val > val) {
                if (node.left == null) {
                    node.left = new TreeNode(val);
                    break;
                }
                node = node.left;
            } else {
                if (node.right == null) {
                    node.right = new TreeNode(val);
                    break;
                }
                node = node.right;
            }
        }
        return root;
    }

    public static TreeNode deleteNode(TreeNode root, int key) {

        if (root == null) return null;
        if (root.val == key) return helper(root);

        TreeNode node = root;

        while (node != null) {

            if (node.val > key) {
                if (node.left != null && node.left.val == key) {
                    node.left = helper(root.left);
                    break;
                }
                node = node.left;
            } else {
                if (node.right != null && node.right.val == key) {
                    node.right = helper(root.right);
                    break;
                }
                node = node.right;
            }
        }
        return root;
    }

    private static TreeNode helper(TreeNode delNode) {

        if (delNode.left == null) return delNode.right;
        if (delNode.right == null) return delNode.left;

        TreeNode rightChild = delNode.right;

        TreeNode lastRight = findLastRight(delNode.left); // find last right in left child
        lastRight.right = rightChild;

        return delNode.left; // dropping delete node
    }

    private static TreeNode findLastRight(TreeNode leftChild) {

        while (leftChild.right != null)
            leftChild = leftChild.right;

        return leftChild;
    }

    // Inorder traversal of BST is always result in sorted values
    public static int kthSmallest(TreeNode root, int k) {

        Stack<TreeNode> stack = new Stack<>();

        while (true) {

            while (root != null) {

                stack.push(root);
                root = root.left;
                // first push to left most to stack and then --k and go right
            }
            root = stack.pop();

            if (--k == 0) return root.val;

            root = root.right;
        }
    }

    // Reverse inorder traversal in BST always result in descending sorted values
    public static int kthLargest(TreeNode root, int k) {

        Stack<TreeNode> stack = new Stack<>();

        while (true) {

            while (root != null) {

                stack.push(root);
                root = root.right;
            }
            root = stack.pop();

            if (--k == 0) return root.val;

            root = root.left;
        }
    }

    public static boolean isValidBST(TreeNode root) {

        Stack<TreeNode> stack = new Stack<>();

        TreeNode node = root;
        TreeNode prev = null;

        while (node != null || !stack.empty()) {

            if (node != null) {

                stack.push(node);
                node = node.left;
            }
            else {
                node = stack.pop();

                if (prev != null && node.val <= prev.val) return false;

                prev = node;
                node = node.right;
            }
        }
        return true;
    }

    public static TreeNode bstFromPreorder(int[] preorder) {

        TreeNode root = new TreeNode(preorder[0]);

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        for (int i = 1; i < preorder.length; i++) {

            TreeNode node = new TreeNode(preorder[i]);

            if (preorder[i] < stack.peek().val) stack.peek().left = node;
            else {
                TreeNode parent = stack.pop();

                while (!stack.empty() && preorder[i] > stack.peek().val) {
                    parent = stack.pop();
                }
                parent.right = node;
            }
            stack.push(node);
        }
        return root;
    }

    public static TreeNode bstPreR(int[] pre) {

        return bstPreR(pre, Integer.MAX_VALUE, new int[1]);
    }

    private static TreeNode bstPreR(int[] pre, int bound, int[] index) {

        if (index[0] == pre.length || pre[index[0]] > bound) return null;

        TreeNode root = new TreeNode(pre[index[0]++]);

        root.left = bstPreR(pre, root.val, index);
        root.right = bstPreR(pre, bound, index);

        return root;
    }

    public static TreeNode inorderSuccessor(TreeNode root, TreeNode x) {

        TreeNode successor = null;

        while (root != null) {

            if (x.val >= root.val) root = root.right;

            else {
                successor = root;
                root = root.left;
            }
        }

        return successor; // O(H) time
    }

    public static TreeNode predecessor(TreeNode root, int key) {

        TreeNode predecessor = null;

        while (root != null) {

            if (key > root.val) {

                predecessor = root;
                root = root.right;
            } else {
                root = root.left;
            }
        }

        return predecessor;
    }

    // brute force - O(N^2)
    public int largestBSTSubTree(TreeNode root) {

        if (root == null) return 0;

        int count = 0;
        int largestBST = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.empty()) {

            TreeNode node = stack.pop();

            count = (isValidBST(node)) ? countOfNodes(node) : 0;
            largestBST = Math.max(largestBST, count);

            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return largestBST;
    }

    private int countOfNodes(TreeNode root) {
        int count = 1;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.empty()) {
            TreeNode node = stack.pop();
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
            count++;
        }
        return count;
    }

    public int largestBSTSubTreeEff(TreeNode root) {

        return largBSTFunc(root).maxSize;
    }

    private NodeValue largBSTFunc(TreeNode root) {
        if (root == null)
            return new NodeValue(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);

        NodeValue left = largBSTFunc(root.left);
        NodeValue right = largBSTFunc(root.right);

        if (left.maxNode < root.val && root.val < right.minNode)
            // it is bst
            return new NodeValue(Math.min(left.minNode, root.val), Math.max(right.maxNode, root.val),
                    1 + left.maxSize + right.maxSize);

        // otherwise,  [-int_max, int_max] so, that the parent can't be valid BST
        return new NodeValue(Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left.maxSize, right.maxSize));
    }

    public void recoverTree(TreeNode root) {

        first = last = null;
        prev = new TreeNode(Integer.MIN_VALUE);  // at left most will be minimum

        inorder(root);

        if (first != null && last != null) {

            int temp = first.val;
            first.val = last.val;
            last.val = temp;
        }
    }

    private void inorder(TreeNode root) {

        if (root == null) return;

        inorder(root.left);

        if (first == null && root.val < prev.val) first = prev;

        if (first != null && root.val < prev.val) last = root;
        prev = root;

        inorder(root.right);
    }

    static class NodeValue {

        int maxNode, minNode, maxSize;

        public NodeValue(int minNode, int maxNode, int maxSize) {
            this.minNode = minNode;
            this.maxNode = maxNode;
            this.maxSize = maxSize;
        }
    }
}
