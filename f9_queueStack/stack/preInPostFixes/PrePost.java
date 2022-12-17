package com.f9_queueStack.stack.preInPostFixes;

import java.util.Stack;

public class PrePost {

    public static void main(String[] args) {

        String expressions = "*-A/BC-/AKL";
        String postfix = preToPost(expressions);
        System.out.println(postfix);
    }

    public static String preToPost(String preExp) {

        Stack<String> stack = new Stack<>();

        for (int i = preExp.length()-1; i >= 0; i--) {

            char ch = preExp.charAt(i);

            if (Character.isLetterOrDigit(ch)) {
                stack.push(ch+"");
            }
            else {
                String a = stack.pop();
                String b = stack.pop();
                String exp = a + b + ch;
                stack.push(exp);
            }
        }
        return stack.pop();
    }
}
