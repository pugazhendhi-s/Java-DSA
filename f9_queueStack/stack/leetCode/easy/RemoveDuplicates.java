package com.f9_queueStack.stack.leetCode.easy;

import java.util.Stack;

public class RemoveDuplicates {


    public static void main(String[] args) {

    }


    public static String removeDuplicates(String s) { // abbaca
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (stack.empty() || stack.peek() != ch)
                stack.push(ch);
            else
                stack.pop();
        }
        StringBuilder sb = new StringBuilder();
        for (char ch : stack)
            sb.append(ch);
        //return sb.toString();

        int i = 0, n = s.length();
        char[] res = s.toCharArray();
        for (int j = 0; j < n; j++, i++) {
            res[i] = res[j]; // override characters
            if (i > 0 && res[i - 1] == res[i])
                i -= 2; // once again we override last two values
        }
        return new String(res, 0, i);
    }
}
