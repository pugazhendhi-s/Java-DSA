package com.f9_queueStack.stack.preInPostFixes;

import java.util.Stack;

public class EvalPrefix {

    public static void main(String[] args) {
        
    }
    // for single digit
    public static double prefixEvaluation(String s) {

        Stack<Double> stack = new Stack<>();
        
        for (int i = s.length()-1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                stack.push((double) (ch -'0'));
            }
            else {
                double a = stack.pop();
                double b = stack.pop();
                double evaluation = evaluate(a, b, ch);
                stack.push(evaluation);
            }
        }
        return stack.pop();
    }

    // multiple digit
    public static double prefixEval(String s) {

        Stack<Double> stack = new Stack<>();

        for (int i = s.length()-1; i >= 0; i--) {
            char ch = s.charAt(i);

            if (ch == ' ') continue;

            if (Character.isDigit(ch)) {
                double num = 0; int digit = 0;
                while (Character.isLetterOrDigit(ch)) {  // 587
                    num += (Math.pow(10, digit) * (ch - '0'));
                    digit++;
                    ch = s.charAt(--i);
                }
                stack.push(num);
            }
            else {
                double a = stack.pop();
                double b = stack.pop();
                double operation = evaluate(a, b, ch);
                stack.push(operation);
            }
        }
        return stack.pop();
    }

    // for multiple digit
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
