package com.f9_queueStack.stack.leetCode.medium;

import java.util.Stack;

public class MinSubArraySum {

    public static void main(String[] args) {

        int[] arr = {11, 81, 94, 43, 3};
        int minSum = sumSubarrayMins(arr);
        System.out.println(minSum);
    }

    public static int sum(int[] arr) {

        int mod = (int)1e9+7;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        long sum = 0;
        int n = arr.length;

        for (int i = 0; i <= n; i++) {

            while (stack.size() > 1 && (i == n || arr[stack.peek()] > arr[i])) {
                int currInd = stack.pop();
                int leftInd = currInd - stack.peek();
                int rightInd = i - currInd;
                int contribution = leftInd * rightInd;

                sum += (((long) contribution * arr[currInd]) % mod);
                sum %= mod;
            }
            stack.push(i);
        }
        return (int)sum;
    }

    public static int sumSubarrayMins(int[] arr) {

        int mod = (int)1e9+7;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // left boundary
        long sum = 0;
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            while (stack.size() > 1 && arr[stack.peek()] > arr[i]) {
                int currInd = stack.pop();
                int leftInd = currInd - stack.peek(); // leftSmallestElementIndex
                int rightInd = i - currInd;  // rightSmallestIndex
                int contribution = leftInd * rightInd;

                sum += (((long) contribution * arr[currInd]) % mod);
                sum %= mod;
            }
            stack.push(i);
        }
        int i = n; // right boundary , this is duplicate code , i reused it above
        while (stack.size() > 1) {

            int currInd = stack.pop();
            int leftInd = currInd - stack.peek(); // leftSmallestElementIndex
            int rightInd = i - currInd;  // rightSmallestIndex
            int contribution = leftInd * rightInd;

            sum += (((long) contribution * arr[currInd]) % mod);
            sum %= mod;
        }
        return (int)sum;
    }

    // brute force
    public static int bf(int[] arr) {
        int mod = (int) 1e9 + 7;
        long sum = 0;
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int mini = arr[i];
            for (int j = i; j < n; j++) {
                mini = Math.min(mini, arr[j]);
                sum = (sum + (long) mini) % mod;
            }
        }
        return (int) sum;
    }
}
