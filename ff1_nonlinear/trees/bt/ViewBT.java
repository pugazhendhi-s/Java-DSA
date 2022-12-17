package com.ff1_nonlinear.trees.bt;

import java.util.*;

public class ViewBT {
    public static void main(String[] args) {
        views();
    }

    public static void views(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        List<Integer> top = topView(root);
        System.out.println(top);

        List<Integer> bottom = bottomView(root);
        System.out.println(bottom);

        List<Integer> left = leftView(root);
        System.out.println(left);

        List<Integer> right = rightView(root);
        System.out.println(right);
    }
    public static List<Integer> topView(TreeNode root) {
        Map<Integer, Integer> treeMap = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        if(root == null)
            return new ArrayList<>();
        queue.offer(new Pair(root, 0));

        while (!queue.isEmpty()){
            Pair pair = queue.poll();
            TreeNode node = pair.node;
            int vertex = pair.num; // x-axis

            treeMap.putIfAbsent(vertex, node.val);

            if(node.left != null) queue.offer(new Pair(node.left, vertex-1));
            if(node.right != null) queue.offer(new Pair(node.right, vertex+1));
        }
        return new ArrayList<>(treeMap.values());
    }
    public static List<Integer> bottomView(TreeNode root) {
        Map<Integer, Integer> treeMap = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        if(root == null)
            return new ArrayList<>();
        queue.offer(new Pair(root, 0));

        while (!queue.isEmpty()){
            Pair pair = queue.poll();
            TreeNode node = pair.node;
            int vertex = pair.num; // x-axis

            treeMap.put(vertex, node.val); // just replace value, if there is any bottom vertex match with top vertex
            if(node.left != null) queue.offer(new Pair(node.left, vertex-1));
            if(node.right != null) queue.offer(new Pair(node.right, vertex+1));
        }
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()){
            list.add(entry.getValue());
        }
        return list;
    }

    // left view by using recursive preorder => root -> left -> right
    public static List<Integer> leftSideView(TreeNode root){
        List<Integer> list = new ArrayList<>();
        leftView(root, list, 0);
        return list;
    }
    private static void leftView(TreeNode root, List<Integer> list, int maxDepth) {
        if(root == null) return;
        if(maxDepth == list.size())
            list.add(root.val);
        leftView(root.left, list, 1+maxDepth);
        leftView(root.right, list, 1+maxDepth);
    }
    // right view by using recursive reverse preorder => root -> right -> left
    public static List<Integer> rightSideView(TreeNode root){
        List<Integer> list = new ArrayList<>();
        rightView(root, list, 0);
        return list;
    }
    private static void rightView(TreeNode root, List<Integer> list, int maxDepth) {
        if(root == null) return;
        if(maxDepth == list.size())
            list.add(root.val);
        rightView(root.right, list, 1+maxDepth);
        rightView(root.left, list, 1+maxDepth);
    }

    // by using level order
    public static List<Integer> leftView(TreeNode root){
        List<Integer> wrapList = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(root == null)
            return wrapList;
        queue.offer(root);
        while (!queue.isEmpty()){
            int levelNum = queue.size(); // it values as number of nodes in certain level
            int left = queue.peek().val;
            for (int i = 0; i < levelNum; i++) {
                root = queue.poll();
                assert root != null;
                if(root.left != null) queue.offer(root.left);
                if(root.right != null) queue.offer(root.right);
            }
            wrapList.add(left);
        }
        return wrapList;
    }
    public static List<Integer> rightView(TreeNode root){
        List<Integer> wrapList = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(root == null)
            return wrapList;
        queue.offer(root);
        while (!queue.isEmpty()){
            int levelNum = queue.size(); // it values as number of nodes in certain level
            int left = 0;
            for (int i = 0; i < levelNum; i++) {
                root = queue.poll();
                assert root != null;
                if(root.left != null) queue.offer(root.left);
                if(root.right != null) queue.offer(root.right);
                left = root.val; // keep assigning upto last value.
            }
            wrapList.add(left);
        }
        return wrapList;
    }


}
