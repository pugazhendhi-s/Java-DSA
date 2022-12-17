package com.f2_string;

import java.util.Stack;

public class LCMedium {
    public static void main(String[] args) {

    }
    static class CharacterIterator{
        String[] words;
        public CharacterIterator(String[] words){
            this.words = words;
        }
        int wordIndex = 0;
        int charIndex = 0;
        public boolean hasNext(){
            return this.wordIndex != this.words.length;
        }
        public char next(){
            char currChar = words[wordIndex].charAt(charIndex);
            charIndex++;
            if(charIndex == words[wordIndex].length()){
                charIndex = 0;
                wordIndex++;
            }
            return currChar;
        }
    }
    void firstOccurrenceOfWord() {
        String haystack = "leetcode";
        String needle = "etcoe";
        int len = haystack.length();
        int sublen = needle.length();
        int i=0;
        while(i <= len-sublen){
            String substr = haystack.substring(i, i+sublen);
            if(needle.equals(substr))
                break;
            i++;
        }
        if(i <= len-sublen)
            System.out.println("First index of word = "+i);
        else
            System.out.println("Not found = "+-1);
    }
    static void areSentencesSimilar(){
        /**1813. Sentence Similarity III
         * Example 1:
         *
         * Input: sentence1 = "My name is Haley", sentence2 = "My Haley"
         * Output: true
         * Explanation: sentence2 can be turned to sentence1 by inserting
         * "name is" between "My" and "Haley".
         * Example 2:
         *
         * Input: sentence1 = "of", sentence2 = "A lot of words"
         * Output: false
         * Explanation: No single sentence can be inserted inside
         * one of the sentences to make it equal to the other.
         * Example 3:
         *
         * Input: sentence1 = "Eating right now", sentence2 = "Eating"
         * Output: true
         * Explanation: sentence2 can be turned to sentence1 by inserting
         * "right now" at the end of the sentence.
         */
        String s1 = "My name is Haley";
        String s2 = "I My";
        System.out.println(areSentencesSimilar(s1,s2));
    }
    static boolean areSentencesSimilar(String s1, String s2) {
        String[] w1 = s1.split(" ");
        String[] w2 = s2.split(" ");
        int n1 = w1.length;
        int n2 = w2.length;
        if(n1 > n2)
            return areSentencesSimilar(s2, s1);
        int i=0;
        while (i < n1 && w1[i].equals(w2[i]))
            i++;
        while (i < n1 && w1[i].equals(w2[n2-n1+i]))
            i++;
        return i == n1;
    }
    static void repeatedStringMatch(){
        /**686. Repeated String Match
         * Given two strings a and b, return the minimum number of
         * times you should repeat string a so that string b is a
         * substring of it. If it is impossible for b to be a substring
         * of a after repeating it, return -1.
         *
         * Notice: string "abc" repeated 0 times is "", repeated 1 time is
         * "abc" and repeated 2 times is "abcabc".
         *
         * Example 1:
         *
         * Input: a = "abcd", b = "cdabcdab"
         * Output: 3
         * Explanation: We return 3 because by repeating a three times
         * "abcdabcdabcd", b is a substring of it.
         *
         * Example 2:
         *
         * Input: a = "a", b = "aa"
         * Output: 2
         */
        String a = "abcd";
        String b = "cdabcdab";
        System.out.println(repeatedStringMatch(a, b));
    }
    static int repeatedStringMatch(String a, String b) {
        StringBuilder sb = new StringBuilder();
        sb.append(a);
        int count=1;
        while(sb.indexOf(b) == -1){
            if(sb.length() >= a.length()+b.length())
                // append length > a&b means , b can't possibly be substring of a
                return -1;
            sb.append(a);
            count++;
        }
        return count;
    }
    static void maximumRemovals(){
        /**1898. Maximum Number of Removable Characters
         * Example 1:
         *
         * Input: s = "abcacb", p = "ab", removable = [3,1,0]
         * Output: 2
         * Explanation: After removing the characters at indices 3 and 1, "abcacb" becomes "accb".
         * "ab" is a subsequence of "accb".
         * If we remove the characters at indices 3, 1, and 0, "abcacb"
         * becomes "ccb", and "ab" is no longer a subsequence.
         * Hence, the maximum k is 2.
         * Example 2:
         *
         * Input: s = "abcbddddd", p = "abcd", removable = [3,2,1,4,5,6]
         * Output: 1
         * Explanation: After removing the character at index 3, "abcbddddd" becomes "abcddddd".
         * "abcd" is a subsequence of "abcddddd".
         */
        String s = "qobftgcueho";
        String p = "obue";
        int[] rem = {5,3,0,6,4,9,10,7,2,8};
        System.out.println(maximumRemovals(s, p, rem));
            /*static int maximumRemovals(String s, String p, int[] rem) {
            int lo = 0;
            int
            StringBuilder sb = new StringBuilder(s);
            int i=0;
            for(i=0; i<rem.length; i++){
                sb.replace(rem[i],rem[i]+1," "); // add in hashset, ignore index contains in set
                if(!checkSeq(sb.toString(), p))
                    return i;
            }
            return i;
        }
        static boolean checkSeq(String s, String p){
            int i=0; int j=0;
            while(i < s.length() && j < p.length()){
                if(s.charAt(i) == p.charAt(j)){
                    i++; j++;
                }
                else
                    i++;
            }
            return j >= p.length();
        }*/
    }
    static int maximumRemovals(String s, String p, int[] rem) {

        char[] a = s.toCharArray();
        char[] b = p.toCharArray();
        int lo = 0;
        int hi = rem.length-1;
        while(lo <= hi){

            int mid = lo + (hi-lo)/2;
            for(int i=lo; i<=mid; i++){
                a[rem[i]] = '$';
            }
            if(checkSeq(a, b))
                lo = mid+1;
            else{
                hi = mid-1;
                for(int i=lo; i<=mid; i++)
                    a[rem[i]] = s.charAt(rem[i]);
            }
        }
        return lo;
    }
    static boolean checkSeq(char[] a, char[] b){
        int i=0; int j=0;
        while(i < a.length && j < b.length){
            if(a[i] == b[j]){
                i++; j++;
            }
            else
                i++;
        }
        return j == b.length;
    }
    static void canTransform(){
        String start = "XLLR", end = "LXLX";
        System.out.println(canTransform(start, end));
    }
    static boolean canTransform(String s, String e) {
        int n = s.length();
        int i=0; int j=0;
        while (i < n || j < n){
            // 1.move i pointer to char != X
            while (i < n && s.charAt(i) == 'X')
                i++;
            while (j < n && e.charAt(j) == 'X')
                j++;
            if(i >= n || j >= n)
                break;  //then return i and j at same index., eg s = 'X', e ='X', i and i == 1
            // 2.after X both String char must be same
            if(s.charAt(i) != e.charAt(j))
                return false;
            // 3. Check R relative order s is less than e (i < j), becoz R move from left -->> right
            if(s.charAt(i) == 'R' && i > j)
                return false;
            // Check L relative order s is greater than e (i > j), becoz L move from left <<-- right
            if(s.charAt(i) == 'L' && i < j)
                return false;
            i++; j++;
        }
        return i == j;
    }
    static void swap(char[] a, int i, int j){
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    static void multiplyStrings(){
        String a = "95954298548964363959543798596064396993299834624";
        String b = "92596238453500000000000000000000000000000000";
        System.out.println(multiplyStrings(a, b));
    }
    static String multiplyStrings(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] pos = new int[m + n];

        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j, p2 = i + j + 1;
                int sum = mul + pos[p2];  // this p2 point the previous cycle p1(carry)

                pos[p1] += sum / 10;   // store carry and with next cycle
                pos[p2] = (sum) % 10;  // store value after carry index.
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int p : pos)
            if(!(sb.length() == 0 && p == 0))   // this prevents to start with '0' eg.(045)
                sb.append(p);
        return sb.length() == 0 ? "0" : sb.toString();
    }
    static void basicCalci(){
        String exp = "3-5*5+50/2";
        System.out.println(calciUsingStack(exp));
    }
    static int calciUsingStack(String exp){
        int num = 0;
        char lastSign = '+';
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);
            if(Character.isDigit(ch))
                num = num * 10 + ch - '0';
            if(!Character.isDigit(ch) && ch != ' ' || i == exp.length()-1){
                if(lastSign == '+')
                    stack.push(num);
                else if(lastSign == '-')
                    stack.push(-num);
                else if(lastSign == '*')
                    stack.push(stack.pop() * num); // for * and / high preference,should take immediate action
                else if(lastSign == '/')
                    stack.push(stack.pop() / num);
                num = 0;
                lastSign = ch;
            }
        }
        int val=0;
        while (!stack.isEmpty())
            val += stack.pop();
        return val;
    }
    static int calci(String exp){
        int val = 0;
        int currVal = 0;
        int num = 0;
        char lastSign = '+';   // done operation with last sign, reason for take '+',
        // both val and curVal are 0 , it doesn't make any change in result...
        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);
            if(Character.isDigit(ch)){
                num = num * 10 + ch - '0';  // eg 57 means first add 5 and 5*10+7 = 57
            }
            if(i == exp.length()-1 || !Character.isDigit(ch) && ch != ' '){
                switch (lastSign){
                    case '+':
                        val += currVal;
                        currVal = num;
                        break;
                    case '-':
                        val += currVal;
                        currVal = -num;
                        break;
                    case '*':
                        currVal *= num;
                        break;
                    case '/':
                        currVal /= num;
                        break;
                }
                lastSign = ch;
                num = 0;
            }
        }
        val += currVal;
        return val;
    }
    static void minimumLength(){
        /**1750. Minimum Length of String After Deleting Similar Ends
         * Given a string s consisting only of characters 'a', 'b', and 'c'.
         * You are asked to apply the following algorithm on the string any number of times:
         *
         * Pick a non-empty prefix from the string s where all the characters in the prefix are equal.
         * Pick a non-empty suffix from the string s where all the characters in this suffix are equal.
         * The prefix and the suffix should not intersect at any index.
         * The characters from the prefix and suffix must be the same.
         * Delete both the prefix and the suffix.
         * Return the minimum length of s after performing the above operation any number of times (possibly zero times).
         * Example 3:
         *
         * Input: s = "aabccabba"
         * Output: 3
         * Explanation: An optimal sequence of operations is:
         * - Take prefix = "aa" and suffix = "a" and remove them, s = "bccabb".
         * - Take prefix = "b" and suffix = "bb" and remove them, s = "cca".
         */
        System.out.println(minimumLength("aabccabba"));
    }
    static int minimumLength(String s) {
        int i=0; int j = s.length()-1;
            /*
            while(i < j && s.charAt(i) == s.charAt(j)){
            char ch = s.charAt(j);
            while(i <= j && s.charAt(i) == ch)
                i++;
            while(i <= j && s.charAt(j) == ch)
                j--;
            }
            return j-i+1;
             */
        while(i < j){
            if(s.charAt(i) == s.charAt(j)){
                if(i+1 < j-1 && s.charAt(i+1) == s.charAt(j-1)){
                    i++; j--;
                }
                else if(i+1 < j && s.charAt(i+1) == s.charAt(j)){
                    i++;
                }
                else if(i < j-1 && s.charAt(i) == s.charAt(j-1)){
                    j--;
                }
                else{
                    i++; j--;
                }
            }
            else
                break;
        }
        return j-i+1;
    }
    public void numSub() {
        String s = "011100111111";
        /**1513. Number of Substrings With Only 1s
         * Given a binary string s, return the number of substrings with all characters 1's.
         * Since the answer may be too large, return it modulo 109 + 7.
         *
         *
         *
         * Example 1:
         *
         * Input: s = "0110111"
         * Output: 9
         * Explanation: There are 9 substring in total with only 1's characters.
         * "1" -> 5 times.
         * "11" -> 3 times.
         * "111" -> 1 time.
         */
        int res=0; int count=0; int mod = (int)(1e9 + 7);
        for(int i=0; i<s.length(); i++){
            count = s.charAt(i) == '1' ? count+1 : 0;
            res = (res+count)%mod;
        }
        System.out.println(res);
    }
    public void countHomogenous() {
        /**1759. Count Number of Homogenous Substrings
         * Input: s = "abbcccaa"
         * Output: 13
         * Explanation: The homogenous substrings are listed as below:
         * "a"   appears 3 times.
         * "aa"  appears 1 time.
         * "b"   appears 2 times.
         * "bb"  appears 1 time.
         * "c"   appears 3 times.
         * "cc"  appears 2 times.
         * "ccc" appears 1 time.
         * 3 + 1 + 2 + 1 + 3 + 2 + 1 = 13.
         */
        String s = "abbcccaa";
        int res=1;
        int count=1;
        int mod = 1_000_000_007;
        for(int i=1; i<s.length(); i++){
            count = (s.charAt(i-1)==s.charAt(i)) ? count+1 : 1;
            res = (res + count) % mod;
        }
            /*
                int res = 0, cur = 0, count = 0, mod = 1_000_000_007;
                for (int i = 0; i < s.length(); ++i) {
                    count = s.charAt(i) == cur ? count + 1 : 1;
                    cur = s.charAt(i);
                    res = (res + count) % mod;
                }
             */
        System.out.println(res);
    }
}
