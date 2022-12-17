package com.ff1_nonlinear.trees.bt;

import java.util.LinkedList;
import java.util.Queue;

public class ChildSum {

    public static void main(String[] args) {

    }

    public static void isChildSum(){

        TreeNode root = new TreeNode(10);

        root.left = new TreeNode(8);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(2);

        boolean childSum = iCSIter(root); // isChildSum
        System.out.println(childSum);
    }
    private static boolean iCS(TreeNode root) {

        if(root == null || (root.left == null && root.right == null))
            return true;

        else{
            int left = (root.left != null) ? root.left.val : 0;
            int right = (root.right != null) ? root.right.val : 0;

            int childSum = left + right;

            return (childSum == root.val) && iCS(root.left) && iCS(root.right);
        }
    }
    private static boolean iCSIter(TreeNode root) {  // u can use stack also

        if(root == null)
            return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){

            root = queue.poll();
            int child = 0; boolean isLeaf = true;

            if (root.left != null){

                queue.offer(root.left);
                child += root.left.val;
                isLeaf = false;
            }
            if (root.right != null){

                queue.offer(root.right);
                child += root.right.val;
                isLeaf = false;
            }
            if(!isLeaf && child != root.val) return false;
        }
        return true;
    }
    // make binary tree as child sum binary tree
    public static void changeTree(TreeNode root){

        if (root == null) return;

        int child = (root.left != null) ? root.left.val : 0;
        child += (root.right != null) ? root.right.val : 0;

        if(child >= root.val) root.val = child;

        else {
            if(root.left != null) root.left.val = root.val;
            if(root.right != null) root.right.val = root.val;
        }
        changeTree(root.left);
        changeTree(root.right);

        child = 0;

        if(root.left != null) child += root.left.val;
        if(root.right != null) child += root.right.val;

        if(root.left != null || root.right != null) root.val = child;
    }
}
