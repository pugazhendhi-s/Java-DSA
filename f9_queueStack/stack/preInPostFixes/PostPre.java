package com.f9_queueStack.stack.preInPostFixes;

import java.util.Stack;

public class PostPre {

    public static void main(String[] args) {

        String postExp = "ABC/-AK/L-*";
        String prefix = postToPre(postExp);
        System.out.println(prefix);
    }

    public static String postToPre(String postExp) {

        Stack<String> stack = new Stack<>();

        for (int i = 0; i < postExp.length(); i++) {

            char ch = postExp.charAt(i);

            if (Character.isLetterOrDigit(ch)) {
                stack.push(ch+"");
            }
            else {
                String b = stack.pop();
                String a = stack.pop();
                String exp = ch + a + b;
                stack.push(exp);
            }
        }
        return stack.pop();
    }
}
