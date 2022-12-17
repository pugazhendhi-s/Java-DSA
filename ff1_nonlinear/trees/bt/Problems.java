package com.ff1_nonlinear.trees.bt;

import java.util.*;

public class Problems {

    public static void main(String[] args) {

    }

    public static void pathNode() {

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        root.right.right.right = new TreeNode(8);
        root.right.right.right.right = new TreeNode(9);

        List<Integer> list = pathNode(root, 8);
        System.out.println(list);

    }
    public static ArrayList<Integer> pathNode(TreeNode root, int key) {

        ArrayList<Integer> list = new ArrayList<>();
        pathNode(root, key, list);

        return list;
    }
    private static boolean pathNode(TreeNode root, int key, ArrayList<Integer> list){

        if(root == null) return false;
        list.add(root.val);

        if(root.val == key) return true;

        if(pathNode(root.left, key, list) || pathNode(root.right, key, list))
            return true;

        list.remove(list.size()-1);
        return false;
    }

    // 236. Lowest Common Ancestor of a Binary Tree


    public static void LCA() {

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        root.right.right.right = new TreeNode(8);
        root.right.right.right.right = new TreeNode(9);

        root = lCA(root, root.left.left, root.left.right);
        System.out.println(root.val);
    }
    public static TreeNode lCA(TreeNode root, TreeNode p, TreeNode q) {

        if(root == null || root == p || root == q) return root;

        TreeNode left = lCA(root.left, p, q);
        TreeNode right = lCA(root.right, p, q);

        // return left == null ? right : right == null ? left : root;
        if(left != null && right != null) return root; // both != null , so we found our ancestor

        return (left == null) ? right : left;
    }



    // 297. Serialize and Deserialize Binary Tree
    public static String serialize(TreeNode root) {

        if (root == null) return "null";

        StringBuilder builder = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){

            TreeNode node = queue.poll();

            if(node == null) {
                builder.append("n ");
                continue;
            }
            builder.append(node.val).append(" ");

            queue.add(node.left);
            queue.add(node.right);
        }
        return builder.toString();
    }

    public static TreeNode deserialize(String data) {

        if(data.equals("null")) return null;

        String[] values = data.split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        for(int i = 1; i < values.length; i++) {

            TreeNode parent = queue.poll();

            if (!values[i].equals("n")){

                TreeNode leftChild = new TreeNode(Integer.parseInt(values[i]));
                assert parent != null;

                parent.left = leftChild;
                queue.add(leftChild);
            }
            if (!values[++i].equals("n")) {

                TreeNode rightChild = new TreeNode(Integer.parseInt(values[i]));
                assert parent != null;

                parent.right = rightChild;
                queue.add(rightChild);
            }
        }
        return root;
    }


    public static boolean isSymmetric(TreeNode root) {

        if (root == null) return true;

        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root.left);
        queue.add(root.right);

        while (!queue.isEmpty()) {

            TreeNode left = queue.poll(), right = queue.poll();

            if (left == null && right == null) continue;

            if (left == null || right == null || left.val != right.val) return false;

            queue.add (left.left);
            queue.add (right.right);

            queue.add (left.right);
            queue.add (right.left);
        }
        return true;
    }

    public static boolean isSym(TreeNode root) {

        return isSym(root.left, root.right);
    }

    private static boolean isSym(TreeNode left, TreeNode right) {

        if (left == null || right == null) return left == right;  // both l, r null means true, else false;

        return (left.val == right.val) && isSym(left.left, right.right) && isSym(left.right, right.left);
    }
}
