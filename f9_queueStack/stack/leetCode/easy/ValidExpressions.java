package com.f9_queueStack.stack.leetCode.easy;

import java.util.Stack;

public class ValidExpressions {

    public static void main(String[] args) {

    }

    public static boolean isValid(String s) {

        if (s.length() <= 1)
            return false;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{')
                stack.push(ch);
            else {
                if (stack.empty())
                    return false;
                else {
                    if (ch == ')' && stack.peek() == '(')
                        stack.pop();
                    else if (ch == ']' && stack.peek() == '[')
                        stack.pop();
                    else if (ch == '}' && stack.peek() == '{')
                        stack.pop();
                    else
                        return false;
                }
            }
        }
        return stack.empty();
    }

    public static String removeOuterParentheses(String s) {

        StringBuilder sb = new StringBuilder();
        int opened = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(' && opened++ > 0) sb.append(ch);
            if (ch == ')' && --opened > 0) sb.append(ch);  // opened-- > 1
        }
        return sb.toString();

        // by stack
        /*Stack<Character> stack = new Stack<>();

        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == '(') {
                count++;
                stack.push(ch);
            }
            else{
                while (!stack.empty()) {
                    char popped = stack.pop();
                    if(!stack.empty()) sb.append(popped);
                }
                count -= 1;
                if(count != 0) {
                    stack.push('(');
                    sb.append(ch);
                }
            }
        }
        return sb.toString();*/
    }


    public static int minAddToMakeValid(String s) {
        // by counting
        int open = 0, close = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                close++;
            else if (close > 0)
                close--;
            else
                open++;
        }
        //return open + close;

        // by stack
        Stack<Character> stack = new Stack<>();
        int count = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(')
                stack.push('(');
            else {
                if (stack.empty())
                    count++;
                else
                    stack.pop();
            }
        }
        return count + stack.size();
    }
}
