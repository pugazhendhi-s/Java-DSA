package com.ff1_nonlinear.trees.bt.traversals;

import java.util.*;

public class BuildTree {

    public static void main(String[] args) {
        
    }

    public static void buildTree(){   // Inorder and Post Order

        int[] preorder = {3,6,8,9,20,15,7};
        int[] inorder = {9,8,6,3,15,20,7};

        TreeNode root = inPre1(preorder, inorder);

        System.out.println(Morris.morrisInOrder(root));
        System.out.println(Morris.morrisPreOrder(root));
        System.out.println(DFS.postorderIt(root));
    }
    // using map
    public static TreeNode inPre(int[] preorder, int[] inorder) {

        Map<Integer, Integer> inMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) inMap.put(inorder[i], i);

        return inPre(preorder, 0, preorder.length-1, 0, inorder.length-1, inMap);
    }

    private static TreeNode inPre(int[] preorder, int preStart, int preEnd, int inStart, int inEnd, Map<Integer, Integer> inMap) {

        if(preStart > preEnd || inStart > inEnd) return null;

        TreeNode root = new TreeNode(preorder[preStart]);

        int inRoot = inMap.get(root.val);
        int numsLeft = inRoot - inStart;

        root.left = inPre(preorder, preStart + 1, preStart + numsLeft, inStart, inRoot - 1, inMap);
        root.right = inPre(preorder, preStart + 1 + numsLeft, preEnd, inRoot + 1, inEnd, inMap);

        return root;
    }
    // without map
    private static int pre = 0; private static int in = 0;

    public static TreeNode inPre1(int[] preorder, int[] inorder) {

        return inPre1(preorder, inorder, Integer.MIN_VALUE);
    }
    private static TreeNode inPre1(int[] preorder, int[] inorder, int stop) {

        if (pre >= preorder.length) return null;

        if (inorder[in] == stop) {  // this represents leaf node
            in++;
            return null;
        }
        TreeNode root = new TreeNode(preorder[pre++]);

        root.left = inPre1(preorder, inorder, root.val);
        root.right = inPre1(preorder, inorder, stop);

        return root;
    }

    // construct unique binary tree from inorder and postorder traversals
    public static void inPost(){

        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};

        TreeNode root = inPost(postorder, inorder);

        System.out.println(Morris.morrisInOrder(root));
        System.out.println(Morris.morrisPreOrder(root));
        System.out.println(DFS.postorderIt(root));
    }
    // using map
    public static TreeNode inPost(int[] inorder, int[] postorder) {

        Map<Integer, Integer> inMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) inMap.put(inorder[i], i);

        return inPost(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1, inMap);
    }
    private static TreeNode inPost(int[] inorder, int is, int ie, int[] postorder, int ps, int pe, Map<Integer, Integer> inMap) {

        if (ps > pe || is > ie) return null;

        TreeNode root = new TreeNode(postorder[pe]);

        int inRoot = inMap.get(root.val);
        int numsLeft = inRoot - is;

        root.left = inPost(inorder, is, inRoot-1, postorder, ps, ps + numsLeft-1, inMap);
        root.right = inPost(inorder, inRoot+1, ie, postorder, ps+numsLeft, pe-1, inMap);

        return root;
    }

}
