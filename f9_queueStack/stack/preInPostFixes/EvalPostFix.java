package com.f9_queueStack.stack.preInPostFixes;

import java.util.Stack;

public class EvalPostFix {

    public static void main(String[] args) {

        String expressions = "(111 - 1 * 111)";
        String postfix = InPost.infixToPostfix(expressions);
        System.out.println(postfix);

        double ans = evalPost(postfix);
        System.out.println(ans);
    }

    public static double evalPost(String postExp) {

        Stack<Double> stack = new Stack<>();

        for (int i = 0; i < postExp.length(); i++) {
            char ch = postExp.charAt(i);

            if (ch == ' ') continue;

            if (Character.isLetterOrDigit(ch)) {
                double num = 0; int digit = 0;
                while (Character.isDigit(ch)) {
                    num += (Math.pow(10, digit) + (ch - '0'));
                    digit++;
                    ch = postExp.charAt(++i);
                }
                stack.push(num);
            }

            else {
                double b = stack.pop();
                double a = stack.pop();
                double operation = evaluate(a, b, ch);
                stack.push(operation);
            }
        }
        return stack.pop();
    }

    public static double evaluate(double a, double b, char operator) {

        switch (operator) {
            case '+' :
                return a + b;
            case '-' :
                return a - b;
            case '*' :
                return a * b;
            case '/' :
                return a / b;
            case '^' :
                return (int)Math.pow(a, b);
        }
        return -1;
    }

}
