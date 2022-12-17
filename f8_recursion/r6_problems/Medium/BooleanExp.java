package com.f8_recursion.r6_problems.Medium;

import java.util.Stack;

public class BooleanExp {
    public static void main(String[] args) {
        System.out.println(parseBoolExpr("!(&(f,t))"));
    }
    // using switch
    public static boolean parseBoolExpr(String expression) {
        if (expression.length() == 0)
            return false;
        Stack<Character> operators = new Stack<>();
        Stack<Boolean> values = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            switch (ch){
                case ',':
                    break;
                case 'f':
                    values.push(false);
                    break;
                case 't':
                    values.push(true);
                    break;
                case '(':
                    values.push(null);
                    break;
                case ')':
                    char operator = operators.pop();
                    boolean res = operator != '|';
                    while (values.peek() != null) {
                        switch (operator) {
                            case '|':
                                res |= values.pop();
                                break;
                            case '&':
                                res &= values.pop();
                                break;
                            case '!':
                                res = !values.pop();
                        }
                    }
                    values.pop();
                    values.push(res);
                    break;
                default:
                    operators.push(ch);
            }
        }
        return values.peek();
    }
    public static boolean parseBooleanExpr(String exp){
        if(exp.length() == 0)
            return false;
        Stack<Character> operators = new Stack<>();
        Stack<Boolean> values = new Stack<>();
        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);
            if(ch == 'f')
                values.push(false);
            else if(ch == 't')
                values.push(true);
            else if(ch == '(')
                values.push(null);
            else if(ch == ')'){
                char op = operators.pop();
                boolean res = op != '|';
                while (values.peek() != null){
                    res = (op == '|') ? res | values.pop() : (op == '&') ? res & values.pop() : !values.pop();
                }
                values.pop();
                values.push(res);
            }
            else if(ch != ','){
                operators.push(ch);
            }
        }
        return values.peek();
    }
}
