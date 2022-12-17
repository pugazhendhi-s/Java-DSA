package com.f9_queueStack.stack.leetCode.nextGreater;
import java.util.*;

public class NextGreater {

    public static void main(String[] args) {

    }

    public static int[] nextGreaterElement(int[] findNums, int[] nums) {

        int n = findNums.length;
        int m = nums.length;

        HashMap<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        for (int val : nums) {
            while (! stack.empty() && stack.peek() < val) {
                map.put(stack.pop(), val);
            }
            stack.push(val);
        }

        for (int i = 0; i < n; i++) {
            findNums[i] = map.getOrDefault(findNums[i], -1);
        }
        return findNums;
    }

    // using index

    public int[] nextGreater(int[] findNums, int[] nums) {

        int n = findNums.length;
        int m = nums.length;

        int[] res = new int[n];
        Arrays.fill(res, -1);

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(findNums[i], i);
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < m; i++) {
            while (! stack.empty() && nums[stack.peek()] < nums[i]) {
                int num = nums[stack.pop()];
                if (map.containsKey(num)) {
                    int idx = map.get(num);
                    res[idx] = nums[i];
                }
            }
            stack.push(i);
        }

        return res;
    }
}
