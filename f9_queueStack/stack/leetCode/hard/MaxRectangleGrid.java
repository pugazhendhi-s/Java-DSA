package com.f9_queueStack.stack.leetCode.hard;

import java.util.Stack;

public class MaxRectangleGrid {

    public static void main(String[] args) {

    }

    public static int maximalRectangle(char[][] mat) {

        int n = mat.length;
        int m = mat[0].length;
        int[] prev = new int[m];
        int maxArea = 0;
        for (char[] hist : mat) {
            int i = 0; int[] curr = new int[m];
            for (char h : hist) {
                curr[i] = (h == '0') ? 0 : 1 + prev[i];
                i++;
            }
            maxArea = Math.max(maxArea, fn(curr));
            prev = curr;
        }
        return maxArea;
    }

    public static int fn(int[] hist) {

        int n = hist.length;
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i <= n; i++){
            while (!stack.empty() && (i == n || hist[stack.peek()] >= hist[i])) {
                int height = hist[stack.pop()];
                int width = (stack.empty()) ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, width * height);
            }
            stack.push(i);
        }
        return maxArea;
    }
}
