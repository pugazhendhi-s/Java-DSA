package com.f9_queueStack.stack.leetCode.nextGreater;
import java.util.*;

public class NextGreaterII {

    public static void main(String[] args) {

        int[] nums = {1,2,3,4,3};
    }
    // monotonic stack -> strictly increasing or decreasing
    public static int[] nextGreaterElements(int[] nums) {

        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 2 * n; i++) {
            while ( ! stack.empty() && nums[stack.peek()] < nums[i % n]) {
                res[stack.pop()] = nums[i % n];
            }
            if (i < n) stack.push(i);
        }
        return res;
    }
}
