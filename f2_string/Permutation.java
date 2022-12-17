package com.f2_string;

import java.util.*;

public class Permutation {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

    }
    public static void permutation(){
        System.out.println("Enter string to find all combinations - Permutation");
        String s = sc.nextLine();
        List<String> li = find_permutation(s);
        System.out.println("Combinations : "+li.size()+"\n"+li);
    }
    public static List<String> find_permutation(String s) {
        ArrayList<String> list = new ArrayList<>();
        funcPermut(s, "", list);
        Collections.sort(list);
        return list;
    }
    public static void funcPermut(String s, String ans, ArrayList<String> list) {
        if (s.isEmpty()) {
            if (!list.contains(ans))
                list.add(ans);
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            String temp = s.substring(0, i) + s.substring(i + 1);

            funcPermut(temp, ans + ch, list);
        }
    }
}
