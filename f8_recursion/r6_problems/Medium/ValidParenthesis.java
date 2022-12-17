package com.f8_recursion.r6_problems.Medium;

import java.util.*;

public class ValidParenthesis {
    public static void main(String[] args) {
    }
    // 1. remove Consecutive Characters from the sentence
    static void removeConsecutiveSequence(){
        String up = "geeksforgeeks";
        System.out.println(rmConseqDupli(up));
    }
    public static String rmConseqDupli(String p, String up){
        if(up.isEmpty()){
            return p;
        }
        char ch = up.charAt(0);
        if(p.isEmpty() || p.charAt(p.length()-1) != ch)
            return rmConseqDupli(p+ch, up.substring(1));
        return rmConseqDupli(p, up.substring(1));
    }
    public static String rmConseqDupli(String input){
        if(input.length() <= 1)
            return input;
        if(input.charAt(0) == input.charAt(1))
            return rmConseqDupli(input.substring(1));
        else
            return input.charAt(0) + rmConseqDupli(input.substring(1));
    }

    // 2. Check valid opening and closing parenthesis
    static void isValidParenthesis(){
        String paren = "{[()]}()";
        boolean balance = isBalanced(paren);
        System.out.println(balance);
    }
    public static boolean isBalanced(String p){
        if(p.isEmpty())
            return true;
        HashMap<Character, Character> closing = new HashMap<>();
        closing.put('{', '}');
        closing.put('(', ')');
        closing.put('[', ']');
        char op = p.charAt(0);
        if(!closing.containsKey(op))
            return false;
        char cl = closing.get(op);
        int cnt = 1;
        int i;
        for (i = 1; i < p.length(); i++) {
            char cur = p.charAt(i);
            if(op == cur)
                cnt++;
            if(cl == cur){
                cnt--;
                if(cnt == 0)
                    break;
            }
        }
        if(i == p.length()) // we can't find closing brackets
            return false;
        if(i == 1)
            return isBalanced(p.substring(2));
        return isBalanced(p.substring(1, i)) && isBalanced(p.substring(i+1));
    }


}
