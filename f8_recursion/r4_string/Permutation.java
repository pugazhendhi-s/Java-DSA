package com.f8_recursion.r4_string;

import java.util.ArrayList;
import java.util.List;

public class Permutation {
    public static void main(String[] args) {
        List<String> list = permutation("", "abc", new ArrayList<>());
    }
    public static List<String> permutation(String s, String ans, List<String> list){
        if(s.isEmpty()){
            if(!list.contains(ans)){
                list.add(ans);
            }
            return list;
        }
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            String next = s.substring(0,i)+s.substring(i+1);
            permutation(next, ans+ch, list);
        }
        return list;
        /*
        List<String> list = new ArrayList<>();
        permutation("abcc", "", list);
        System.out.println(list);
         */
    }
    public static void permutation(String p, String up){
        // p => processed string(ans), up = unprocessed string(given)
        if(up.isEmpty()){
            System.out.println(p);
            return;
        }
        char ch = up.charAt(0);
        for (int i = 0; i <= p.length(); i++) {
            String f = p.substring(0,i);
            String s = p.substring(i);
            permutation(s+ch+f, up.substring(1));
        }
    }
    public static int permutationCount(String s, String ans){
        /*
        List<String> list = permutation("abc", "", new ArrayList<>());
        System.out.println(list);
        int count = permutationCount("abcde", "");
        System.out.println(count);
         */
        if(s.isEmpty())
            return 1;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            String next = s.substring(0,i)+s.substring(i+1);
            count = count + permutationCount(next, ans+ch);
        }
        return count;
    }

}
