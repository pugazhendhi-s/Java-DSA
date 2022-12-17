package com.f9_queueStack.stack.preInPostFixes;

import java.util.Stack;

public class PreIn {

    public static void main(String[] args) {

        String expressions = "*-A/BC-/AKL";
        String infix = preToInfix(expressions);
        System.out.println(infix);
    }
    public static String preToInfix(String pre_exp) {

        Stack<String> stack = new Stack<>();

        for (int i = pre_exp.length()-1; i >= 0; i--) {
            char ch = pre_exp.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                stack.push(ch+"");
            }
            else {
                String a = stack.pop();
                String b = stack.pop();
                String exp = "(" + a + ch + b + ")";
                stack.push(exp);
            }
        }
        return stack.pop();
    }
}
