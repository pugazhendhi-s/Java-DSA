package com.f8_recursion.r6_problems.Medium;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class Hackerrank {
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println(superDigit("9875", 4));
    }
    public static int powerSum(int X, int N) {
        // Write your code here
        return powerSum(X, N, 1);
    }
    public static int powerSum(int X, int N, int start) {
        if(X == 0)
            return 1;
        // Write your code here
        if(X < 0 || Math.pow(start, N) > X && X - Math.pow(start, N) != 0)
            return 0;

        int count = 0;
        for (int i = start; Math.pow(i, N) <= X; i++) {
            count += powerSum((int) (X - Math.pow(i, N)), N, i+1);
        }
        return count;
    }

    // 2 super digit

    public static int superDigit(String n, int k) {
        int number = 0;
        for (int i = 0; i < n.length(); i++) {
            number += Character.getNumericValue(n.charAt(i));
        }
        int sum = superDigit(number);
        int kSum = k * sum;
        return k == 1 ? sum : superDigit(kSum+"",1);
    }
    private static int superDigit(int n){
        if(n % 10 == n) // n / 10 == 0
            return n;
        int sum = 0;
        while (n > 0){
            sum += (n % 10);
            n /= 10;
        }
        return superDigit(sum);
    }

    // PASSWORD CRACKER
    public static void passwordCracker() {
        /*
        String[] passwords = {"because", "can", "do", "must", "we", "what"};
        String loginAttempt = "wedowhatwemustbecausewecan";
        boolean isCracked = passwordCracker(passwords, loginAttempt);
        if (isCracked){
            while (!curPass.empty())
                System.out.print(curPass.pop()+" ");
        }
        else
            System.out.println("Wrong password");
         //Answer : we do what we must because we can
         */
        System.out.println("Enter number of Testcases");
        int testCase = Integer.parseInt(sc.nextLine());
        while (testCase-- != 0) {
            System.out.println("Enter number of Users");
            int noOfUsers = Integer.parseInt(sc.nextLine());
            System.out.println("Enter Users Passwords");
            String[] passwords = new String[noOfUsers];
            for (int i = 0; i < noOfUsers; i++) {
                passwords[i] = sc.nextLine();
            }
            System.out.println("Login attempt");
            String loginAttempt = sc.nextLine();
            System.out.println("Is Password Cracked");
            boolean isPassWordCracked = passwordCracker(passwords, loginAttempt);
            if(isPassWordCracked){
                while (!curPass.isEmpty()){
                    System.out.print(curPass.pop()+" ");
                }
                System.out.println();
            }
            else
                System.out.println("Wrong Password");
        }
    }
    static StringBuilder sb = new StringBuilder();
    private static final Stack<String> curPass = new Stack<>();
    // or declare object and initialize after =>
    // private static Stack<String> curPass;
    // curPass = new Stack<>();
    private static final Set<String> badLoginAttempt = new HashSet<>(); // is string[] doesn't match with login attempt. then false
    private static boolean passwordCracker(String[] passwords, String loginAttempt) {
        if(badLoginAttempt.contains(loginAttempt))
            return false;
        if(loginAttempt.isEmpty())
            return true;
        for (String pwd : passwords) {
            if( loginAttempt.startsWith(pwd) && passwordCracker(passwords, loginAttempt.substring(pwd.length()))){
                curPass.push(pwd);
                return true;
            }
        }
        badLoginAttempt.add(loginAttempt);
        return false;
    }

}
