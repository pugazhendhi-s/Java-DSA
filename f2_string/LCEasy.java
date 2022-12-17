package com.f2_string;

public class LCEasy {
    public static void main(String[] args) {

    }
    static void freqAlphabets() {
        /**1309. Decrypt String from Alphabet to Integer Mapping
         *
         * Characters ('a' to 'i') are represented by ('1' to '9') respectively.
         * Characters ('j' to 'z') are represented by ('10#' to '26#') respectively.
         * Input: s = "10#11#12"
         * Output: "jkab"
         * Explanation: "j" -> "10#" , "k" -> "11#" , "a" -> "1" , "b" -> "2".
         */
        String s = "10#11#12";
        int n = s.length();
        StringBuilder builder = new StringBuilder();
        int i = n-1;
        while (i >= 0){
            if(s.charAt(i) != '#'){
                int val = 96 + Integer.parseInt(s.substring(i,i+1));
                char ch = (char)val;
                builder.append(ch);
                i--;
            }
            else{
                int val = Integer.parseInt(s.substring(i-2,i));
                char ch = (char)('a'+val-1);
                // -1 for start from before 'a'=97, so start at 96 and add 1
                builder.append(ch);
                i -= 3;
            }
        }
        builder.reverse();
        System.out.println(builder);
    }
    static void numOfStrings() {
        /**
         * Input: patterns = ["a","abc","bc","d"], word = "abc"
         * Output: 3
         * Explanation:
         * - "a" appears as a substring in "abc".
         * - "abc" appears as a substring in "abc".
         * - "bc" appears as a substring in "abc".
         * - "d" does not appear as a substring in "abc".
         * 3 of the strings in patterns appear as a substring in word
         */
        String[] patterns = {"a","abc","bc","d"};
        String word = "abc";
        int cnt=0;
        for(String pat : patterns){
            if(word.contains(pat))
                cnt++;
            // word.length() always >= pat.length, else strictly it returns false
        }
        System.out.println(cnt);
    }
    static void reverseWords() {
        /** 557. Reverse Words in a String III
         * Input: s = "Let's take LeetCode contest"
         * Output: "s'teL ekat edoCteeL tsetnoc"
         */
        String s = "Let's take LeetCode contest";
        String[] words = s.split(" ");
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<words.length-1; i++){
            StringBuilder temp = new StringBuilder(words[i]);
            builder.append(temp.reverse()).append(" ");
        }
        StringBuilder temp = new StringBuilder(words[words.length-1]);
        builder.append(temp.reverse());
        System.out.println(builder);
        /** using two pointer
         * int lspace = -1;
         char[] ch = s.toCharArray();
         int len = ch.length;
         for(int i=0; i<=len; i++){
         if(i == len || ch[i] == ' '){
         int start = lspace+1;
         int end = i-1;
         while(start <= end){
         char temp = ch[start];
         ch[start] = ch[end];
         ch[end] = temp;
         start++; end--;
         }
         lspace = i;
         }
         }
         System.out.println(new String(ch)); // String.valueOf(ch);
         */
    }
    static void convertToTitle() {
        int n = 2147483647;
        StringBuilder builder = new StringBuilder();
        while(n != 0){
            n--;
                /* if n = 26,ans = Z, if we add 25 + 'A' = 'Z' , decrement one by n every time to get value,
                it's 0 based index like that..
                start from 0 to 25 , A+0 = A, A+25 = Z, like wise for 26-1 = Z, so decrement 1 everytime
                 */
            char ch = (char)((n % 26)+'A');
            builder.append(ch);
            n /= 26;
        }
        builder.reverse();
        System.out.println(builder);
    }
    static void isLongPressedName() {
        /** 925. Long Pressed Name
         * Example 1:
         *
         * Input: name = "alex", typed = "aaleex"
         * Output: true
         * Explanation: 'a' and 'e' in 'alex' were long pressed.
         * Example 2:
         *
         * Input: name = "saeed", typed = "ssaaedd"
         * Output: false
         * Explanation: 'e' must have been pressed twice, but it was not in the typed output.
         */
        String name = "saeed";
        String typed = "ssaaedd";
        int m = name.length();
        int n = typed.length();
        int i=0; int j=0;
        for(j=0; j<n; j++){
            if(i < m && name.charAt(i) == typed.charAt(j))
                i++;
            else if(j == 0 || typed.charAt(j) != typed.charAt(j-1)){
                    /*
                     i, j
                     j < tl, i < nl
                     condition 1 -> i < nl and n[i]==t[j] i++;
                     condition 2 -> when j =0 both char should be equal, or condition
                     1 will be execute whenever both char will be equal, if 1 false, then t[j]==t[j-1] other wise false;
                    */
                System.out.println(false);
                return;
            }
        }
        System.out.println(i == m);
    }

    //end
    static void longestCommonPrefix() {
        String[] strs = {"flower","flow","flight"};
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0){
                prefix = prefix.substring(0, prefix.length()-1);
                if(prefix.isEmpty()){
                    System.out.println("No common prefix");
                    return;
                }
            }
        }
        System.out.println(prefix);
    }
    static void maxRepeating() {
        String sq = "aaabaaaabaaabaaaabaaaabaaaabaaaaba";
        String wd = "aaaba";
        int lw = wd.length();
        String find =wd;
        while(sq.contains(find)){
            find += wd;
        }
        int countOfRepeatSubStr = (find.length() - lw)/lw;
        System.out.println(countOfRepeatSubStr);
    }
    static void mergeAlternately() {
        String wd1 = "abcd";
        String wd2 = "zyxwuv";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < wd1.length() || i < wd2.length(); i++) {
            if(i < wd1.length())
                builder.append(wd1.charAt(i));
            if(i < wd2.length())
                builder.append(wd2.charAt(i));
        }
        System.out.println(builder);
        /** using concept of merge sort
         int i=0; int j=0;
         StringBuilder builder = new StringBuilder();
         while(i < wd1.length() && j < wd2.length()){
         builder.append(wd1.charAt(i));
         builder.append(wd2.charAt(j));
         i++; j++;
         }
         while(i < wd1.length()){
         builder.append(wd1.charAt(i));
         i++;
         }
         while(j < wd2.length()){
         builder.append(wd2.charAt(j));
         j++;
         }
         System.out.println(builder);*/
    }
    public void reversePrefix(String word, char ch) {
        StringBuilder builder = new StringBuilder();
        boolean flag = true;
        for(int i=0; i<word.length(); i++){
            if(word.charAt(i) == ch && flag){
                builder.append(ch).reverse();
                flag = false;
            }
            else
                builder.append(word.charAt(i));
        }
        System.out.println(builder);
    }
}
