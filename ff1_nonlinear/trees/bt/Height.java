package com.ff1_nonlinear.trees.bt;

import java.util.*;

public class Height {
    public static void main(String[] args) {
//        TreeNode root = makeTree();
//        int depth =  maximumDepth(root);
//        System.out.println(depth);

        //maxPathSum();
        //isIdentical();

        maxWidth();
    }

    // height of a binary tree
    private static TreeNode makeTree(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(8);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.right.right.left = new TreeNode(9);
        root.right.right.right = new TreeNode(10);

        return root;
    }
    public static int heightOfBT(TreeNode root){
        //find depth by level order
        int depth = 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            int levelNodes = queue.size();
            depth++;

            for (int i = 0; i < levelNodes; i++) {

                root = queue.poll();
                assert root != null;

                if(root.left != null) queue.offer(root.left);
                if(root.right != null) queue.offer(root.right);
            }
        }
        return depth;
    }
    public static int maximumDepth(TreeNode root){
        // find max depth by recursive post order => height or depth both is equal
        if(root == null) return 0;

        int leftDepth = maximumDepth(root.left);
        int rightDepth = maximumDepth(root.right);

        return 1 + Math.max(leftDepth, rightDepth);
    }
    public static int minDepth(TreeNode root) {  // find min depth
        if(root == null) return 0;

        int ld = minDepth(root.left);
        int rd = minDepth(root.right);

        // if any node zero means, if we take minimum means, that always take only zeros
        return (ld == 0 || rd == 0) ? 1 + Math.max(ld, rd) : 1 + Math.min(ld, rd);
        //return (ld * rd == 0) ? 1 + ld + rd : 1 + Math.min(ld, rd);
    }

    // diameter
    public static int maxDiameter(TreeNode root){
        int[] diameter = new int[1];
        maxDia(root, diameter);
        return diameter[0];
    }
    private static int maxDia(TreeNode root, int[] diameter){
        if(root == null)
            return 0;
        int lh = maxDia(root.left, diameter);
        int rh = maxDia(root.right, diameter);

        diameter[0] = Math.max(diameter[0], lh + rh);
        return 1 + Math.max(lh, rh);
    }

    // maxSum in a path, same as diameter
    public static void maxPathSum(){
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        int maxPathSum = maxPathSum(root);
        System.out.println(maxPathSum);
    }
    public static int maxPathSum(TreeNode root){
        int[] maxSum = new int[1];
        maxSum[0] = Integer.MIN_VALUE;
        mps(root, maxSum);
        return maxSum[0];
        // mps => maxPathSum, ls -> left sum, rs -> right sum
    }
    private static int mps(TreeNode root, int[] maxSum) {
        if(root == null) return 0;
        int ls = Math.max(0, mps(root.left, maxSum)); // Math.max(0 -> is to eliminate negative value
        int rs = Math.max(0, mps(root.right, maxSum));
        maxSum[0] = Math.max(maxSum[0], ls + rs + root.val);
        return Math.max(ls, rs) + root.val;
    }


    // Balanced tree or not, diff btw left and right <= 1
    public boolean isBalancedBT(TreeNode root){
        return maxDep(root) != -1;
    }
    public static int maxDep(TreeNode root){
        if(root == null) return 0;

        int ld = maxDep(root.left);
        if(ld == -1) return -1;
        int rd = maxDep(root.right);
        if(rd == -1) return -1;
        return (Math.abs(ld - rd) > 1) ? -1 : 1 + Math.max(ld, rd);
    }

    // check two trees are identical or not
    public static void isIdentical(){
        TreeNode r1 = new TreeNode(1);
        r1.left = new TreeNode(2);
        r1.right = new TreeNode(3);
        r1.right.left = new TreeNode(4);
        r1.right.right = new TreeNode(5);

        TreeNode r2 = new TreeNode(1);
        r2.left = new TreeNode(2);
        r2.right = new TreeNode(3);
        r2.right.left = new TreeNode(4);
        r2.right.right = new TreeNode(5);

        boolean identical = isIdentical(r1, r2);
        System.out.println(identical);
    }
    public static boolean isIdentical(TreeNode r1, TreeNode r2){
        if(r1 == null && r2 == null) return true; // both should be null
        if(r1 == null || r2 == null) return false; // if not both and other node null means not identical
        //return (r1.val == r2.val) && isIdentical(r1.left, r2.left) && isIdentical(r1.right, r2.right);
        if(r1.val != r2.val) return false;
        boolean left = isIdentical(r1.left, r2.left);
        boolean right = isIdentical(r1.right, r2.right);
        return left & right;
    }

    // max width
    public static void maxWidth(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(5);
        root.left.left.left = new TreeNode(7);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(6);
        int width = maxWidth(root);
        System.out.println(width);
    }
    public static int maxWidth(TreeNode root){

        if(root == null) return 0;

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));

        int maxWidth = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();
            int first = 0, last = 0;
            int minIndex = queue.peek().num;

            for (int i = 0; i < size; i++) {
                // queue.peek().num  -> represents their parent index
                // minIndex -> 1st  parent index
                assert queue.peek() != null;
                int curIndex = queue.peek().num - minIndex;
                TreeNode node = queue.poll().node;

                if(i == 0) first = curIndex;
                if(i == size-1) last = curIndex;

                if(node.left != null)
                    queue.offer(new Pair(node.left, 2 * curIndex + 1));
                if(node.right != null)
                    queue.offer(new Pair(node.right, 2 * curIndex + 2));
            }
            maxWidth = Math.max(maxWidth, last - first + 1);
        }
        return maxWidth;
    }
}
