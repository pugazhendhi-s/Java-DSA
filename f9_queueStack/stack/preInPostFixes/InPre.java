package com.f9_queueStack.stack.preInPostFixes;

import java.util.Stack;

public class InPre {

    public static void main(String[] args) {

        String expressions = "(A-B/C)*(A/K-L)";
        String prefix = infixToPrefix(expressions);
        System.out.println(prefix);
    }

    public static String infixToPrefix(String exp) {

        Stack<Character> stack = new Stack<>();
        StringBuilder builder = new StringBuilder(exp);
        exp = builder.reverse().toString(); // reversing original string

        builder = new StringBuilder();

        for (char ch : exp.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                builder.append(ch);
            }
            else if (ch == ')') {
                stack.push(')');
            }
            else if (ch == '(') {
                while (! stack.empty() && stack.peek() != ')') {
                    builder.append(stack.pop());
                }
                if (! stack.empty()) stack.pop(); // popped ')';
            }
            else {
                int cop = precedence(ch);  // cp -> current operator precedence
                if (! stack.empty() && cop > precedence(stack.peek())) {
                    stack.push(ch);
                    continue;
                }
                while (! stack.empty() && cop < precedence(stack.peek())) {
                    builder.append(stack.pop());
                }
                stack.push(ch);  // pushed current char
            }
        }

        while (! stack.empty()) {
            builder.append(stack.pop());
        }
        return builder.reverse().toString();
    }

    private static int precedence(char ch) {
        switch (ch) {
            case '+' :
            case '-' : return 1;

            case '*':
            case '/': return 2;

            case '^': return 3;
        }
        return -1;
    }
}
