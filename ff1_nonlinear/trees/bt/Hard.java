package com.ff1_nonlinear.trees.bt;

import java.util.*;

public class Hard {

    public static void main(String[] args) {

    }

    public static void distanceK(){
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.left.left = new TreeNode(9);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        List<Integer> list = distanceK(root, root.left, 2);
        System.out.println(list);
    }
    private static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {

        Map<TreeNode, TreeNode> parent_track = new HashMap<>();
        markParents(root, parent_track);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(target);

        HashSet<TreeNode> visited = new HashSet<>();
        visited.add(target);

        while (!queue.isEmpty() && k-- != 0){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                assert node != null;
                if (node.left != null && visited.add(node.left)){
                    queue.offer(node.left);
                }
                if (node.right != null && visited.add(node.right)){
                    queue.offer(node.right);
                }
                if (parent_track.get(node) != null && visited.add(parent_track.get(node))){
                    queue.offer(parent_track.get(node));
                }
            }
        }
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()){
            list.add(queue.poll().val);
        }
        return list;
    }
    private static void markParents(TreeNode root, Map<TreeNode, TreeNode> parent_track) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node.left != null){
                parent_track.put(node.left, node);
                queue.offer(node.left);
            }
            if(node.right != null){
                parent_track.put(node.right, node);
                queue.offer(node.right);
            }
        }
    }


    public static void burnTree(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(8);
        root.right.right.right = new TreeNode(9);
        root.right.right.right.right = new TreeNode(10);

        int minTime = minTimeBurn(root, 8);
        System.out.println(minTime);

    }
    private static int minTimeBurn(TreeNode root, int target) {

        Map<TreeNode, TreeNode> parent_track = new HashMap<>();
        markParents(root, parent_track);

        TreeNode tnode = findTarget(root, target);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(tnode);

        HashSet<TreeNode> visited = new HashSet<>();
        visited.add(tnode);

        int time = 0;
        while (!queue.isEmpty()) {

            int size = queue.size();
            boolean isBurn = false;

            for (int i = 0; i < size; i++) {

                TreeNode node = queue.poll();
                assert node != null;

                if (node.left != null && visited.add(node.left)){
                    queue.offer(node.left);
                    isBurn = true;
                }
                if (node.right != null && visited.add(node.right)){
                    queue.offer(node.right);
                    isBurn = true;
                }
                if (parent_track.get(node) != null && visited.add(parent_track.get(node))){
                    queue.offer(parent_track.get(node));
                    isBurn = true;
                }
            }
            time += isBurn ? 1 : 0;
            // suppose the nodes, parent, left, right -> all already burnt or null,
            // then no need to increase time, and this will happen at last cycle of while loop,
            // that cycle ends when all node is to be null or visited in for loop,
            // so you can just reduce time-1 at return time-1 is enough
        }
        return time;
    }
    private static TreeNode findTarget(TreeNode root, int target){

        if (root == null) return null;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.empty()){

            TreeNode node = stack.pop();

            if(node.val == target) return node;
            if(node.right != null) stack.push(node.right);
            if(node.left != null) stack.push(node.left);
        }
        return root;
    }
}
