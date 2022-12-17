package com.ff1_nonlinear.trees.bt;

import java.util.ArrayDeque;
import java.util.Deque;

public class CompleteBT {

    public static void main(String[] args) {

    }
    // count no of nodes in complete binary tree
    public static void completeBT(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8);
        root.left.left.right = new TreeNode(9);

        int nodes = countNodesEf(root);
        System.out.println(nodes);
    }


    /* O((logN)^2) logN for traverse, logN for find height of tree (logN * logN)
     it is complete binary so, u need to check only nodes at the height of tree.
     Complete tree (height of tree must be logged(N))..
     No extra space, only auxiliary space for recursion -> O(LogN) */

    private static int countNodesEf(TreeNode root){
        if(root == null) return 0;

        int left = leftHeight(root.left);
        int right = rightHeight(root.right);

        if(left == right) return ((2 << left)-1) ;

        else return 1 + countNodesEf(root.left) + countNodesEf(root.right);
    }
    private static int leftHeight(TreeNode node) {

        int lh = 0;

        while (node != null) {
            node = node.left;
            lh++;
        }
        return lh;
    }
    private static int rightHeight(TreeNode node) {

        int rh = 0
                ;
        while (node != null) {
            node = node.right;
            rh++;
        }
        return rh;
    }
    // Time  Complexity -> O(N) - brute force
    // space ExtraSpace -> O(N), if recursion takes O(N) - auxiliary space
    private static int countNodes(TreeNode root) {

        if(root == null) return 0;

        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);

        int count = 1;

        while (!deque.isEmpty()){

            TreeNode node = deque.poll();

            if(node.left != null){
                deque.offer(node.left);
                count++;
            }
            if(node.right != null){
                deque.offer(node.right);
                count++;
            }
        }
        return count;
    }
}
