package com.ff2_dp.shapes;

import java.util.*;

public class RectArea {

    public static void main(String[] args) {
        int[] hist =  {1,2,3,1,2,3}; //{6, 2, 5, 4, 5, 1, 6};
        int area = largestRectangleArea(hist);
        System.out.println("Maximum area is " + area);
        int maxArea = getMaxArea(hist, hist.length);
        System.out.println("Maximum area is " + maxArea);
    }

    private static int getMaxArea(int[] hist, int n) {

        Stack<Integer> stack = new Stack<>();

        int maxArea = 0;
        int i = 0;

        while (i < n) {

            if (stack.empty() || hist[stack.peek()] <= hist[i]) {
                stack.push(i); i++;
            }
            else{
                int top = stack.pop();
                int topArea = hist[top] * (stack.empty() ? i : i - (stack.peek() + 1));
                maxArea = Math.max(maxArea, topArea);
            }
        }

        while (!stack.empty()) {
            int top = stack.pop();
            int topArea = hist[top] * (stack.empty() ? i : i - (stack.peek() + 1));
            maxArea = Math.max(maxArea, topArea);
        }
        return maxArea;
    }
        // easy code for before , it skips rewrite of code for while(stack.empty()) in previous code
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