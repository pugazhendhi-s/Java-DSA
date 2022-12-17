package com.ff2_dp.shapes;

import java.util.*;

public class MaximumRectangle {

    public static void main(String[] args) {

    }

    public static int maximalAreaOfSubMatrixOfAll1(int[][] mat, int n, int m) {

        int maxArea = 0;
        int[] heights = new int[m];

        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if(mat[i][j] == 1) heights[j]++;
                else heights[j] = 0;
            }
            maxArea = Math.max(maxArea, fn(heights));
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
