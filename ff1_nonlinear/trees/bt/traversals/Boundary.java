package com.ff1_nonlinear.trees.bt.traversals;


import java.util.ArrayList;

public class Boundary {
    
    public static void main(String[] args) {
        
    }

    // boundary traversal

    public static void boundaryTraversal(){

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(4);
        root.left.left.right.left = new TreeNode(5);
        root.left.left.right.right = new TreeNode(6);

        root.right = new TreeNode(7);
        root.right.right = new TreeNode(8);
        root.right.right.left = new TreeNode(9);
        root.right.right.left.left = new TreeNode(10);
        root.right.right.left.right = new TreeNode(11);

        ArrayList< Integer > boundary = storeBoundary(root);
        System.out.println(boundary);
    }
    public static ArrayList<Integer> storeBoundary(TreeNode node){

        ArrayList<Integer> boundary = new ArrayList<>();

        if (!isLeaf(node)) boundary.add(node.val);

        addLeftBound(node, boundary);
        addLeaves(node, boundary);
        addRightBound(node, boundary);

        return boundary;
    }
    private static boolean isLeaf(TreeNode node){

        return node.left == null && node.right == null;
    }
    private static void addLeftBound(TreeNode root, ArrayList<Integer> boundary) {
        // pre order traversal
        TreeNode curr = root.left;

        while (curr != null){

            if(!isLeaf(curr)) boundary.add(curr.val);
            if(curr.left != null) curr = curr.left;
            else curr = curr.right;
        }
    }
    private static void addLeaves(TreeNode root, ArrayList<Integer> boundary) {

        // inorder traversal, to get first root , and then left right in leaves, it traverses entire trees
        if (isLeaf(root)) boundary.add(root.val);

        else{
            if (root.left != null) addLeaves(root.left, boundary);
            if (root.right != null) addLeaves(root.right, boundary);
        }
    }
    private static void addRightBound(TreeNode root, ArrayList<Integer> boundary) {

        TreeNode curr = root.right;
        int i = boundary.size();

        while (curr != null){

            if(!isLeaf(curr)) boundary.add(i, curr.val); // adding at first index, after boundary.size() for reverse
            if(curr.right != null) curr = curr.right;
            else curr = curr.left;
        }
        /* Actually we are reversing here, instead,
        //we directly add every value at boundary.size(),
        //so last val comes at boundary.size(), so no need to reverse it

        curr = root.right;
        ArrayList<Integer> temp = new ArrayList<>();
        while (curr != null){
            if(!isLeaf(curr)) temp.add(curr.val);
            if(curr.right != null) curr = curr.right;
            else curr = curr.left;
        }
        for (int j = temp.size()-1; j >= 0; j--) {
            boundary.add(temp.get(j));
        }*/
    }
}
