package com.f9_queueStack.stack.preInPostFixes;

import java.util.Stack;

public class PostIn {

    public static void main(String[] args) {

        String expressions = "abcd^e-fgh*+^*+i-";
        String infix = postToInfix(expressions);
        System.out.println(infix);
    }

    public static String postToInfix(String postExp) {

        Stack<String> stack = new Stack<>();

        for (int i = 0; i < postExp.length(); i++) {
            char ch = postExp.charAt(i);

            if (Character.isLetterOrDigit(ch) || ch == ' ') {
                stack.push(ch+"");
            }

            else {
                String b = stack.pop();
                String a = stack.pop();
                String merge = "(" + a + ch + b + ")";
                stack.push(merge);
            }
        }
        return stack.pop();
    }
}
