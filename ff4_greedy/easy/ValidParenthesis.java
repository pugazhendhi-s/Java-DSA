package com.ff4_greedy.easy;

import java.util.Stack;

public class ValidParenthesis {

    public static void main(String[] args) {

        String paren = "(*))";
        boolean check = checkValidStringII(paren);
        System.out.println(check);
    }

    // two chars -> ['(', ')']
    public static boolean checkValidStringI(String s) {

        int openCount = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                openCount++;
            }
            else {
                openCount--;
            }
            if (openCount < 0) return false;
        }
        return openCount == 0;
    }

    public static boolean byStack(String s) {

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(ch);
            }
            else {
                if (stack.empty()) return false;
                else stack.pop();
            }
        }
        return stack.empty();
    }

    // Three chars -> ['(','*', ')']
    public static boolean checkValidStringII(String s) {

        int cmax = 0;
        int cmin = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                cmax++; cmin++;
            }
            else if (ch == ')') {
                cmax--; cmin--;
            }
            else {  // *
                cmax++; cmin--;
            }
            if (cmax < 0) return false;
            cmin = Math.max(cmin, 0);

        }
        return cmin == 0;
    }

    public static boolean usingTwoStacks(String s) {

        Stack<Integer> leftInd = new Stack<>();  // open braces
        Stack<Integer> starInd = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                leftInd.push(i);
            }
            else if (ch == '*') {
                starInd.push(i);
            }
            else {
                if (leftInd.empty() && starInd.empty()) 
                    return false;
                if (! leftInd.empty()) 
                    leftInd.pop();
                else 
                    starInd.pop();
            }
        }
        // check for excess open braces
        while ( !(leftInd.empty() && starInd.empty())) {
            if (leftInd.pop() > starInd.pop()) { // this refers '**((' this is not valid
                return false;                    //             '((**' this is valid
            }
        }
        return true;
    }

    public static boolean checkValidString(String s) {
        return check(s, 0, 0);
    }

    private static boolean check(String s, int start, int count) {
        if (count < 0) return false;

        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                count++;
            }
            else if (c == ')') {
                if (count <= 0) return false;
                count--;
            }
            else if (c == '*') {
                return check(s, i + 1, count + 1) || check(s, i + 1, count - 1) || check(s, i + 1, count);
            }
        }
        // we can use dp here
        return count == 0;
    }

}
