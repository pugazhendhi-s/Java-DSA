package com.ff1_nonlinear.trees.bt.traversals;


import java.util.*;

public class Level {
    public static void main(String[] args) {

    }

    public static void levelTree(){

        TreeNode root = new TreeNode(3);

        root.left = new TreeNode(9);
        root.right = new TreeNode(20);

        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println("Level Order");
        List<List<Integer>> lists = levelOrder(root);

        for (List<Integer> list : lists)
            System.out.print(list);

        System.out.println("\nZig Zag level order");
        List<List<Integer>> zzLevel = zigZagLevel(root);

        for (List<Integer> list : zzLevel)
            System.out.print(list);
    }
    public static List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> wrapList = new LinkedList<>();
        if(root == null) return wrapList;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            int levelNum = queue.size(); // it values as number of nodes in certain level
            List<Integer> subList = new LinkedList<>();

            for (int i = 0; i < levelNum; i++) {

                root = queue.poll();
                assert root != null;

                if(root.left != null) queue.offer(root.left);
                if(root.right != null) queue.offer(root.right);

                subList.add(root.val);
            }
            wrapList.add(subList);
        }
        return wrapList;
    }
    public static List<List<Integer>> zigZagLevel(TreeNode root){

        List<List<Integer>> zigZag = new ArrayList<>();
        if(root == null) return zigZag;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean reverse = false;

        while (queue.size() > 0) {

            int levelNum = queue.size(); // it values as number of nodes in certain level
            List<Integer> subList = new LinkedList<>();

            for (int i = 0; i < levelNum; i++) {

                root = queue.peek();
                assert root != null;

                if (root.left != null) queue.offer(root.left);
                if (root.right != null) queue.offer(root.right);

                assert queue.peek() != null;

                if(reverse) subList.add(0, queue.poll().val);
                else subList.add(queue.poll().val);
            }
            reverse = !reverse;
            zigZag.add(subList);
        }
        return zigZag;
    }
}
