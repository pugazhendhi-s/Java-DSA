package com.ff1_nonlinear.trees.bt;
import java.util.*;
public class Main {

    public void morrisIn(TreeNode root) {

        TreeNode cur = root;

        List<Integer> list = new ArrayList<>();

        while (cur != null) {

            if (cur.left == null) {
                list.add(cur.val);
                cur = cur.right;
            }
            else {
                TreeNode prev = cur.left;

                while (prev.right != null && prev.right != cur) prev = prev.right;

                if (prev.right == null) {
                    prev.right = cur;
                    list.add(cur.val);
                    cur = cur.left;
                }
                if (prev.right == cur) {
                    prev.right = null;
                    cur = cur.right;
                }
            }
        }

    }
}
