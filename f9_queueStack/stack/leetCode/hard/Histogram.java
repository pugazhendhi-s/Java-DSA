package com.f9_queueStack.stack.leetCode.hard;

import java.util.Stack;

public class Histogram {

    public static void main(String[] args) {

        int[] heights = {2,1,5,6,2,3};
    }

    public static int largestRectangleArea(int[] hist) {

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
