package com.f2_string;

import java.util.Scanner;

public class Palindrome {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

    }
    public static void main() {
        checkPalindromeFormation();
    }
    //1.
    static void sentencePalindrome() {
        // efficient approach
        String s = "A man, a plan, a 8c8anal: Panama";
        s = s.toLowerCase();
        int i = 0, j = s.length()-1;
        while(i < j){
            if(!Character.isLetterOrDigit(s.charAt(i)))
                i++;
            else if(!Character.isLetterOrDigit(s.charAt(j)))
                j--;
            else if(s.charAt(i) != s.charAt(j)){
                System.out.println("Not Palindrome");
                return;
            }
            else{
                i++; j--;
            }
        }
        System.out.println("Palindrome");
        // method 2
        String given = "A man, a plan, a 8c8anal: Panama";
        String modified = given.replaceAll("[^A-Za-z0-9]","").toLowerCase();
        int start = 0; int end = modified.length()-1;
        // just use builder and reverse a string like that
        while (start < end){
            if(modified.charAt(start) != modified.charAt(end)){
                System.out.println("Not a Palindrome");
                return;
            }
            start++; end--;
        }
        System.out.println("Palindrome");
    }
    //2.
    static boolean scanPalindrome(String s) {
        int l=0; int r=s.length()-1;
        while(l < r){
            if(s.charAt(l)!=s.charAt(r)){
                return isPalindrome(s, l+1, r) || isPalindrome(s, l, r-1);
            }
            l++; r--;
        }
        return true;
    }
    static boolean isPalindrome(String s, int l, int r){
        while(l < r){
            if(s.charAt(l) != s.charAt(r))
                return false;
            l++; r--;
        }
        return true;
    }
    // 3 ,both isPalindrome and isPalin returns same..
    static boolean isPalin(String s, int i, int j){
        while(i<j && s.charAt(i) == s.charAt(j)){
            i++; j--;
        }
        return i>=j;
    }
    static void checkPalindromeFormation() {
        /**1616. Split Two Strings to Make Palindrome
         * Example 3:
         *
         * Input: a = "ulacfd", b = "jizalu"
         * Output: true
         * Explaination: Split them at index 3:
         * aprefix = "ula", asuffix = "cfd"
         * bprefix = "jiz", bsuffix = "alu"
         * Then, aprefix + bsuffix = "ula" + "alu" = "ulaalu", which is a palindrome.
         */
        String a = "aluxbf";
        String b = "rfeula";
        if (check(a, b) || check(b, a)) {
            System.out.println("Valid Palindrome");
        }
        else
            System.out.println("Not Valid");
    }
    static boolean check(String a, String b){

        int i = 0; int j = b.length()-1;
        while(i<j && a.charAt(i) == b.charAt(j)){
            i++; j--;
        }
        return isPalin(a, i, j) || isPalin(b, i, j);
    }
    // 4 , my approach
    static void longestPalindrome(){
        System.out.println("Enter String to find longest palindrome");
        String s = sc.nextLine();
        int n = s.length();

        String longest = "";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(n-j > i) {
                    String temp = s.substring(i, n - j);
                    if (checkPalindrome(temp)) {
                        if (longest.length() < temp.length()) {
                            longest = temp;
                        }
                    }
                }
            }
        }
        System.out.println(longest);
    }
    static boolean checkPalindrome(String s){
        int n = s.length();
        for (int i=0; i<n/2;i++){
            if(s.charAt(i) != s.charAt(n-1-i))
                return false;
        }
        return true;
    }
    static void anyPalindrome(){
        String s = "aacdaa";
        if (checkPalindrome(s)){
            System.out.println("Palindrome");
            return;
        }
        for (int i = 0; i < s.length()-1; i++) {
            String res = s.substring(0,i)+s.substring(i+1);
            if(checkPalindrome(res)) {
                System.out.println("Palindrome");
                System.out.println(res);
                return;
            }
        }
        System.out.println("Not");
    }
}

