package com.f9_queueStack.stack.leetCode.easy;

import java.util.Stack;

public class DeleteMid {

    public static void main(String[] args) {

    }

    public static void deleteMid(Stack<Integer> s, int sizeOfStack) {
        int mid = (sizeOfStack) / 2; // 1 based index;
        Stack<Integer> stack = new Stack<>();
        while (mid != 0) {
            stack.push(s.pop());
            mid--;
        }
        s.pop(); // popped middle element
        while (!stack.empty()) {
            s.push(stack.pop());
        }
    }

}
