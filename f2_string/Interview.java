package com.f2_string;

import java.util.*;

public class Interview {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

    }

    public static String firstNonRepeatingChar(String s) { // "afefas"
        LinkedHashMap<Character, Integer> hs = new LinkedHashMap<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            hs.put(ch, hs.getOrDefault(ch, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : hs.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey().toString();
            }
        }
        return null;
    }

    static void validString() {
        System.out.print("Enter testcases = ");
        int tc = Integer.parseInt(sc.nextLine());
        boolean flag = false;
        for (int i = 0; i < tc; i++) {
            System.out.print("Enter String length = ");
            int n = Integer.parseInt(sc.nextLine());
            System.out.print("Enter String = ");
            String s = sc.nextLine();
            int c0 = 0;
            int c1 = 0;
            for (int j = 0; j < n; j++) {
                c0 += (s.charAt(j) == '0') ? 1 : 0;
                c1 += (s.charAt(j) == '1') ? 1 : 0;
                flag = c0 < c1;
                if (flag)
                    break;
            }
            System.out.println((flag || c0 != c1) ? "No" : "Yes");
        }
    }

    static void duplicateWords() {
        String s = sc.nextLine();
        String[] a = s.split("\\s");
        StringBuilder res = new StringBuilder();
        HashSet<String> hs = new HashSet<>();
        for (String value : a) {
            if (!hs.contains(value.toLowerCase())) {
                res.append(value).append(" ");
                hs.add(value.toLowerCase());
            }
        }
        System.out.println(res);
    }

    public static boolean Pangram() {
        String s = "qwertyuioplkjhgfdsazxcvbnmmmmmmf686".toLowerCase();
        HashSet<Character> hs = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z')
                hs.add(s.charAt(i));
        }
        return hs.size() == 26;
    }

    public static boolean twoStringsKAnagrams(String s1, String s2) {
        /**
         * Given two strings of lowercase alphabets and a value K,
         * your task is to complete the given function which tells if
         * two strings are K-anagrams of each other or not.
         *
         * Two strings are called K-anagrams if both of the below conditions are true.
         * 1. Both have same numbed of characters.
         * 2. Two strings can become anagram by changing at most K characters in a string.
         *
         * Example:
         *
         * Input:
         * str1 = "fodr", str2="gork"
         * k = 2
         * Output:
         * 1
         * Explanation: Can change fd to gk
         */
        int k = 2;
        if (s1.length() != s2.length()) {
            return false;
        }
        HashMap<Character, Integer> hs = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            hs.put(s1.charAt(i), hs.getOrDefault(s1.charAt(i), 0) + 1);
        }
        for (char c : s2.toCharArray()) {
            if (hs.containsKey(c)) {
                hs.put(c, hs.get(c) - 1);
                if (hs.get(c) == 0)
                    hs.remove(c);
            }
            /*else{
                k--;
                if(k < 0)
                    return false;
            }*/ // this is an easy way without calculation remaining count
        }
        // Calculate remaining count
        int count = 0;
        for (Map.Entry<Character, Integer> entry : hs.entrySet()) {
            count += entry.getValue();
        }
        return count <= k;
    }

    static void validBrackets() {
        /**
         *Input: str = "(())))("
         * Output: 4
         * Explanation:
         * After index 4, string splits into (())
         * and ))(. Number of opening brackets in the
         * first part is equal to number of closing
         * brackets in the second part.
         */
        String s = "))()))";
        int len = s.length();
        int cnt_close = 0;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == ')')
                cnt_close++;
        }
        for (int i = 0; i < len; i++) {
            if (cnt_close == i) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(len);
    }

    static void encriptString() {
        /**
         * Input:
         * S = "aaaaaaaaaaa"
         * Output:
         * ba
         * Explanation:
         * aaaaaaaaaaa
         * Step1: a11 (a occurs 11 times)
         * Step2: a11 is ab [since 11 is b in
         * hexadecimal]
         * Step3: ba or 11a [After reversing]
         */
        String s = "aaabbbbcccccccccccccc";
        int n = s.length();
        StringBuilder res = new StringBuilder();
        char curr = s.charAt(0);
        int c = 1;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == curr)
                c++;
            else {
                res.insert(0, Integer.toHexString(c) + curr);
                c = 1;
                curr = s.charAt(i);
            }
        }
        // for last character the loops end so we add here
        res.insert(0, Integer.toHexString(c) + curr);
        System.out.println(res.reverse());
    }

    static void closestStrings() {
        ArrayList<String> s = new ArrayList<>
                (Arrays.asList("the", "quick", "brown", "fox",
                        "quick"));

        int n = s.size();
        String w2 = "the";
        String w1 = "fox";
        int start = 0;
        int end = 0;
        int minLen = n;
        boolean x = false, y = false;
        for (int i = 0; i < n; i++) {
            if (s.get(i).equals(w1)) {
                start = i;
                x = true;
            }
            if (s.get(i).equals(w2)) {
                end = i;
                y = true;
            }
            if (minLen > Math.abs(end - start) && x & y) {
                // count for check both end and start equals w1 and w2 if count
                minLen = Math.abs(end - start);
            }
        }
        System.out.println(minLen);
    }

    static String deciToRoman(int num) {
        int[] a = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] s = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder res = new StringBuilder();

        for (int i = 0; i < a.length; i++) {
            while (a[i] <= num) {
                num -= a[i];
                res.append(s[i]);
            }
        }
        return res.toString();
    }

    static int romanToDeci(String roman) {
        /*
         * Input: IX
         * Output: 9
         * IX is a Roman symbol which represents 9
         *
         * Input: XL
         * Output: 40
         * XL is a Roman symbol which represents 40
         *
         * Input: MCMIV
         * Output: 1904
         * M is a thousand,
         * CM is nine hundred and
         * IV is four
         */
        Map<Character, Integer> hs = new HashMap<>();
        hs.put('I', 1);
        hs.put('V', 5);
        hs.put('X', 10);
        hs.put('L', 50);
        hs.put('C', 100);
        hs.put('D', 500);
        hs.put('M', 1000);
        int n = roman.length();
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (i + 1 < n && hs.get(roman.charAt(i)) < hs.get(roman.charAt(i + 1))) {
                result += hs.get(roman.charAt(i + 1)) - hs.get(roman.charAt(i));
                i++;  // becuase we find value for i and i+1; eg IX = 9
            } else
                result += hs.get(roman.charAt(i));
        }
        return result;
    }

    static void reverseWords() {
        /**
         * Input:
         * S = i.like.this.program.very.much
         * Output: much.very.program.this.like.i
         * Explanation: After reversing the whole
         * string(not individual words), the input
         * string becomes
         * much.very.program.this.like.i
         */
        String S = "you.love.I";
        String[] phrase = S.split("\\.");

        StringBuilder res = new StringBuilder(phrase[0]);  // res = you
        for (int i = 1; i < phrase.length; i++) {
            res.insert(0, phrase[i] + "."); // it will insert value at 0 ,index
            // res = str[i].substring(4).concat(res.concat("."));
        }
        System.out.println(res);
    }

    static void largestCommonPrefix_Array() {
        /**
         * Input:
         * N = 4
         * arr[] = {geeksforgeeks, geeks, geek,
         *          geezer}
         * Output: gee
         * Explanation: "gee" is the longest common
         * prefix in all the given strings.
         */
        /*System.out.println("1.Method One by Sorting");
        String[] str = {"geeksforgeeks", "geeks", "geek" ,"geezer"};
        int n = str.length;
        Arrays.sort(str);
        System.out.println(Arrays.toString(str));
        String a = str[0];
        String b = str[n-1];
        StringBuilder result = new StringBuilder();
        int i=0;
        while (i < a.length() && i < b.length()){
            if(a.charAt(i) != b.charAt(i))
                break;
            result.append(a.charAt(i)); // or String res += a.charAt(i);
            i++;
        }
        System.out.println(result);*/
        /**
         * Method 2
         * Using Associative ABC = A(BC) =  AB    //(result(AB));
         * LCP(string1, string2, string3)
         *          = LCP (LCP (string1, string2), string3)
         *
         * Like here
         *
         * LCP (“geeksforgeeks”, “geeks”, “geek”)
         *      =  LCP (LCP (“geeksforgeeks”, “geeks”), “geek”)
         *      =  LCP (“geeks”, “geek”) = “geek”
         */
        String[] str = {"geeksforgeeks", "geeks", "geek", "geezer"};
        String prefix = str[0];
        for (int i = 1; i < str.length; i++) {
            prefix = findingPrefix(prefix, str[i]);
        }
        System.out.println(prefix);
    }

    static String findingPrefix(String a, String b) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < a.length() && i < b.length(); i++) {
            if (a.charAt(i) != b.charAt(i))
                break;
            res.append(a.charAt(i));
        }
        return res.toString();
    }

    static void parenthesisChecker() {
        /**
         * Given an expression string x.Examine whether the pairs
         * and the orders of “{“,”}”,”(“,”)”,”[“,”]”are correct in exp.
         * For example, the function should return 'true'
         * for exp = “[()]{}{[()()]()}” and 'false' for exp = “[(])”.
         * Example 1:
         *
         * Input:
         * {([])}
         * Output:
         * true
         * Explanation:
         * { ( [ ] ) }. Same colored brackets can form
         * balaced pairs, with 0 number of
         * unbalanced bracket.
         * Example 2:
         *
         * Input:
         * ()
         * Output:
         * true
         * Explanation:
         * (). Same bracket can form balanced pairs,
         * and here only 1 type of bracket is
         * present and in balanced way.
         */
        String x = sc.nextLine(); // {([])}
        boolean flag = isPar(x);
        System.out.println(flag);
    }

    static boolean isPar(String x) {
        Stack<Character> stack = new Stack<Character>();
        int n = x.length();
        if (n <= 1)
            return false;
        for (int i = 0; i < n; i++) {
            char ch = x.charAt(i);
            if (ch == '{' || ch == '(' || ch == '[')
                stack.push(ch);
            else {
                if (stack.empty())
                    return false; // becoz without opening brackets in stack , closing brackets are not valid
                else if (ch == ')' && stack.peek() == '(')
                    stack.pop();
                else if (ch == ']' && stack.peek() == '[')
                    stack.pop();
                else if (ch == '}' && stack.peek() == '{')
                    stack.pop();
                else
                    return false;
            }
        }
        return stack.empty();
    }

    static void rotateStringBy2() {
        String a = "amazon";
        String b = "azonam";
        int n = a.length();
        boolean flag = false;
        if (a.equals(b.substring(n - 2).concat(b.substring(0, n - 2))))
            flag = true;
        else if (a.equals(b.substring(2).concat(b.substring(0, 2))))
            flag = true;
        System.out.println(flag);
        /*
         else if(a.substring(0,n-2).equals(b.substring(2)))
            return true;
        else if(a.substring(2).equals(b.substring(0,n-2)))
            return true;
         */
    }
}
