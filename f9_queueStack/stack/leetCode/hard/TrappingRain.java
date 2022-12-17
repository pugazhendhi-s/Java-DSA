package com.f9_queueStack.stack.leetCode.hard;
import java.util.*;

public class TrappingRain {

    public static void main(String[] args) {

        int []height = {0,1,0,2,1,0,1,3,2,1,2,1};
    }

    public static int trap(int[] ht) {

        Stack<Integer> stack = new Stack<>();
        int n = ht.length;
        int maxWater = 0;

        for (int i = 0; i < n; i++) {
            while (! stack.empty() && ht[i] > ht[stack.peek()]) {
                int block = stack.pop();
                if (! stack.empty()) {
                    int minHt = Math.min(ht[stack.peek()], ht[i]);
                    int width = i - 1 - stack.peek();
                    maxWater += (minHt - ht[block]) * (width);
                }
            }
            stack.push(i);
        }
        return maxWater;
    }
}
