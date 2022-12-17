package com.f9_queueStack.stack.preInPostFixes;

import java.util.Stack;

public class EvalInfix {

    public static void main(String[] args) {

        String expressions = "100 * ( 2 + 12 ) / 7";

        double ans = evalInfix(expressions);
        System.out.println(ans);
    }

    public static double evalInfix(String exp) {

        Stack<Character> ops = new Stack<>();
        Stack<Double> values = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            if (ch == ' ')continue;

            if (Character.isDigit(ch)) {
                StringBuilder builder = new StringBuilder(); 
                while (i < exp.length() && Character.isDigit(exp.charAt(i))) {
                    builder.append(exp.charAt(i++));
                }
                i--;
                values.push(Double.parseDouble(builder.toString()));
            }
            else if (ch == '(') {
                ops.push('(');
            }
            else if (ch == ')') {
                while (! ops.empty() && ops.peek() != '(') {
                    double b = values.pop();
                    double a = values.pop();
                    double operation = evaluate(a, b, ops.pop());
                    values.push(operation);
                }
                ops.pop(); // popped '(';
            }
            else {
                while (!ops.empty() && hasPrecedence(ch, ops.peek())) {
                    double b = values.pop();
                    double a = values.pop();
                    double operation = evaluate(a, b, ops.pop());
                    values.push(operation);
                }
                ops.push(ch);  // pushed current char
            }
        }
        
        while ( !ops.empty()) {
            double b = values.pop();
            double a = values.pop();
            double operation = evaluate(a, b, ops.pop());
            values.push(operation);
        }

        return values.pop();
    }

    private static boolean hasPrecedence(char op1, char op2) {
        // hasPrecedence -> refers op1 same or low precedences op2 // i.e. -> op1 = curr, op2 = peek
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        return (op1 != '*' && op1 != '/') || (op2 != '+' && op2 != '-');
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
                try {
                    return a / b;
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
        }
        return 0;
    }
}
