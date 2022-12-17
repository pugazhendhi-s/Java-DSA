package com.f8_recursion.r4_string;

public class Basic {
    public static void main(String[] args) {
        String s = "SarahMine";
        System.out.println(skipSarNotSarah(s, "Sarah"));
    }
    public static String skipA(String s, String ans){
        if(s.isEmpty())
            return ans;
        if(s.charAt(0) != 'a')
            ans += s.charAt(0);
        return skipA(s.substring(1), ans);
    }
    public static String skipA2(String up){
        if(up.isEmpty())
            return "";
        char ch = up.charAt(0);
        if(ch == 'a')
            return skipA2(up.substring(1));
        return ch + skipA2(up.substring(1));
        /*String s = "abcacada";;
        System.out.println(skipA2(s));*/
    }

    public static String skipSarah(String s, String ignore){
        // it will skip Sar when it will be not Sarah
        if(s.isEmpty())
            return "";
        if(s.startsWith(ignore))
            return skipSarah(s.substring(ignore.length()), ignore);
        return s.charAt(0) + skipSarah(s.substring(1), ignore);
        /*
        String s = "SarahMine";
        System.out.println(skipSarah(s, "Sarah"));
         */
    }
    public static String skipSarNotSarah(String s, String ignore){
        if(s.isEmpty())
            return "";
        if(s.startsWith("Sar") && !s.startsWith(ignore))
            return skipSarah(s.substring("Sar".length()-1), ignore);
        return s.charAt(0) + skipSarah(s.substring(1), ignore);
    }
}
