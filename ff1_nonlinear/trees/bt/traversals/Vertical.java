package com.ff1_nonlinear.trees.bt.traversals;

import java.util.*;

public class Vertical {

    public static void main(String[] args) {

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

    }
    static class Tuple{

        TreeNode node;
        int x;  // x - axis
        int y;  // y - axis

        public Tuple(TreeNode node, int x, int y) {
            this.node = node;
            this.x = x;
            this.y = y;
        }
    }

    public static List<List<Integer>> verticalTraversal(TreeNode root) {

        // 1st integer for vertical, 2nd integer for level, priorityQue for ascending value in same vert & level

        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> treeMap = new TreeMap<>();

        Queue<Tuple> queue = new LinkedList<>();
        queue.offer(new Tuple(root, 0, 0));

        while (!queue.isEmpty()){

            Tuple tuple = queue.poll();
            TreeNode node = tuple.node;

            int x = tuple.x;
            int y = tuple.y;

            treeMap.putIfAbsent(x, new TreeMap<>());
            treeMap.get(x).putIfAbsent(y, new PriorityQueue<>());

            treeMap.get(x).get(y).offer(node.val);

            if(node.left != null)
                queue.offer(new Tuple(node.left, x-1, y+1));

            if(node.right != null)
                queue.offer(new Tuple(node.right, x+1, y+1));
        }
        List<List<Integer>> list = new ArrayList<>();

        for(TreeMap<Integer, PriorityQueue<Integer>> xs : treeMap.values()){
            list.add(new ArrayList<>());

            for (PriorityQueue<Integer> ys : xs.values()){

                while (!ys.isEmpty()){
                    list.get(list.size()-1).add(ys.poll());
                }
            }
        }
        return list;
    }
}
