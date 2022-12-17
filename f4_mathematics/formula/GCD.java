package com.f4_mathematics.formula;

public class GCD {
    public static void main(String[] args) {
        System.out.println(lcm(6, 12));
    }
    public static int gcd(int a, int b){
        if(a == 0)
            return b;
        return gcd(b % a, a); // Euclid's Theorem
    }
    public static int lcm(int a, int b){
        return a * b / gcd(a, b);
    }
}
