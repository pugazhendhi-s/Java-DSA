package com.f4_mathematics.bits;
import com.f4_mathematics.formula.PrimeNumber;

import java.util.*;

public class LCEasy {
    public static void main(String[] args) {
        System.out.println(countConsistentStrings("ab", new String[]{"ad","bd","aaab","baa","badab"}));
    }
    static public String addBinary(String a, String b) {
        /**67. Add Binary
         * Example 1:
         *
         * Input: a = "11", b = "1"
         * Output: "100"
         * Example 2:
         *
         * Input: a = "1010", b = "1011"
         * Output: "10101"
         */
        StringBuilder sb = new StringBuilder();

        int i=a.length()-1; int j=b.length()-1;
        int sum;
        int carry = 0;
        while(i >= 0 || j >= 0){
            sum = carry;
            if(j >= 0) sum += b.charAt(j--) - '0';
            if(i >= 0) sum += a.charAt(i--) - '0';
            sb.append(sum%2);
            carry = sum/2;
        }
        if(carry == 1)
            sb.append(carry);
        return sb.reverse().toString();
    }
    public static int singleNumber(int[] nums) {
        /**136. Single Number
         * Example 1:
         *
         * Input: nums = [2,2,1]
         * Output: 1
         * Example 2:
         *
         * Input: nums = [4,1,2,1,2]
         * Output: 4
         */
        int unique = 0;
        for(int n : nums){
            unique ^= n;
        }
        return unique;
    }
    public static int singleNumberII(int[] nums){
        /*int unique = 0;
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            int mask = 1 << i;
            for (int n : nums) {
                if((n & mask) != 0)
                    sum++;
            }
            unique |= (sum % 3 == 1) ? mask : 0;
            //if(sum % 3 == 1)
              //  unique |= mask;
        }
        return unique;*/
        int unique = 0;
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            for (int n : nums) {
                if( ((n >> i) & 1) == 1)
                    sum++;
            }
            unique |= (sum % 3 == 1) ? sum << i : 0;
        }
        return unique;
    }
    public static int[] singleNumberIII(int[] nums) {
        int xor = 0;
        int[] unique = new int[2];
        for(int n : nums){
            xor ^= n;
        }
        xor &= -xor; // this is xor of unique bits
        // finding right most set bit, at right most set bit both unique 
        // number are not same because we already xor it
        for (int n : nums) {
            if((n & xor) == 0)  // one xor true for this condition
                unique[0] ^= n;
            else                // another true for this condition
                unique[1] ^= n;
        }
        return unique;
    }
    public static int reverseBits(int num){
        /*
            int n = num;
            int result = 0;
            for(int i=0; i<32; i++){
                result <<= 1;
                result += n&1;
                n >>= 1;
            }
            return result;
         */
        // Divide and Conquer approach
        num = ( (num & (0xffff0000)) >>> 16)| ( (num & (0x0000ffff)) << 16);
        num = ( (num & (0xff00ff00)) >>> 8) | ( (num & (0x00ff00ff)) << 8);
        num = ( (num & (0xf0f0f0f0)) >>> 4) | ( (num & (0x0f0f0f0f)) << 4);
        num = ( (num & (0xcccccccc)) >>> 2) | ( (num & (0x33333333)) << 2);
        num = ( (num & (0xaaaaaaaa)) >>> 1) | ( (num & (0x55555555)) << 1);
        return num;
    }
    public static int bitCount(int n) {
        int ones = 0;
        while (n != 0){
            ones++;
            n = n & (n-1);
        }
        return ones;
        /*
            int ones = 0;
            while (n != 0){
                ones += (n & 1);
                n >>>= 1;
            }
            return ones;*/
        /*
            int count = 0;
            for(int i=0; i<32; i++){
                int mask = 1 << i;
                if( ((n >> i) & 1) == 1)
                    count++;
            return count;
        }*/

    } // hamming weight
    public static List<String> readBinaryWatch(int turnedOn) {
        ArrayList<String> list = new ArrayList<>();
        for(int h=0; h<12; h++){
            for(int m=0; m<60; m++){
                if(Integer.bitCount(h) + Integer.bitCount(m) == turnedOn)  // bitCount refers no of ones
                    list.add(h+":"+((m < 10) ? ("0"+m) : m));
                    //list.add(String.format("%d:%02d", h, m)); %02d means an integer, left padded with zeros up to 2 digits.
            }
        }
        return list;
    }
    public static int hammingDistance(int x, int y) {
        int xor = x ^ y; //return Integer.bitCount(x ^ y);
        int bitCount = 0;
        while(xor > 0){
            bitCount++;
            xor &= xor-1;
        }
        return bitCount;
    }
    public static int findComplement(int num) {
        int n = 0;
        while (n < num) {
            n = (n << 1) | 1;
        }
        return n - num;
    }
    public static int[] findErrorNums(int[] nums) {
        /**645.Set Mismatch
         * Example 1:
         *
         * Input: nums = [1,2,2,4]
         * Output: [2,3]
         */
        // Xor approach multipass
        /*int xor = 0; int xor0 = 0; int xor1 = 0;
        int len = nums.length;
        for(int n : nums)
            xor ^= n;
        for(int i=1; i<=len; i++)
            xor ^= i;
        // now xor is xor missing amd duplicate term
        xor &= (-xor);
        for(int i=1; i<=len; i++){
            if( (xor & i) == 0)
                xor0 ^= i;
            else
                xor1 ^= i;
        }
        for(int n : nums){
            if( (xor & n) == 0)
                xor0 ^= n;
            else
                xor1 ^= n;
        }
        for(int n : nums){
            if(n == xor0)
                return new int[]{xor0, xor1};
        }
        return new int[]{xor1, xor0};*/
        // Single Pass negative marking with xor
        /*
        The idea is based on:
            (1 ^ 2 ^ 3 ^ .. ^ n) ^ (1 ^ 2 ^ 3 ^ .. ^ n) = 0
            Suppose we change 'a' to 'b', then all but 'a' and 'b' are XORed exactly 2 times.
            The result is then 0 ^ a ^ b ^ b ^ b = a ^ b
            Let c = a ^ b, if we can find 'b' which appears 2 times in the original array,
             'a' can be easily calculated by a = c ^ b.
         */
        int[] ans = new int[2];
        for (int i = 0; i < nums.length; i++) {
            ans[1] ^= (i+1) ^ nums[i];   // ans[1] gives the xor of missing and duplicate element
            if(nums[i]-1 < 0) ans[0] = nums[i];
            else nums[nums[i]-1] = -nums[i];
        }
        ans[1] ^= ans[0];
        return ans;
        //ans[0] = store value of duplicate index
        // we want missing so xor (a[0]^a[1]) gives missing becoz a[1] already xor with
        // duplicate term , duplicate becomes 0 and  missing   terms is stored

    }
    public static boolean hasAlternatingBits(int n) {
        int prev = n & 1;
        n >>= 1;
        while(n > 0){
            if(prev == (n & 1) )
                return false;
            prev = n & 1;
            n >>= 1;
        }
        return true;
        // Efficient approach
        /*
        n = n ^ n >> 1;
        return(n & (n + 1)) == 0;
        Explanation :
        n =         1 0 1 0 1 0 1 0
        n >> 1      0 1 0 1 0 1 0 1
        n ^ n>>1    1 1 1 1 1 1 1 1
        n           1 1 1 1 1 1 1 1
        n + 1     1 0 0 0 0 0 0 0 0
        n & (n+1)   0 0 0 0 0 0 0 0
        */

        /**Stack<Character> stack = new Stack<>();
        String bin = Integer.toBinaryString(n);
        stack.push(bin.charAt(0));
        for (int i = 1; i < bin.length(); i++) {
            if(stack.pop() != bin.charAt(i)){
                stack.push(bin.charAt(i));
            }
            else
                return false;
        }
        return true;*/
    }
    public static int countPrimeSetBits(int left, int right) {
        int pCount = 0;
        while(left <= right){
            int setBits = bitCount(left); // counts 1 in bits
            pCount += PrimeNumber.isPrime(setBits) ? 1 : 0;
            left++;
        }
        return pCount;
    }
    public static int binaryGap(int n) {
        //        int maxCount=0;
//        int count=0;
//        boolean flag = false;
//        while(n > 0){
//            if( (n & 1) == 1){
//                flag = true;
//                maxCount = Math.max(maxCount, count);
//                count=1;
//            }
//            else if(flag)
//                count++;
//            n >>= 1;
//        }
//        return maxCount;
        int maxGap = 0;
        int last = -1;
        for (int i = 0; i < 32; i++) {
            if( ((n >> i) & 1) > 0) {
                if (last >= 0)
                    maxGap = Math.max(maxGap, i - last);
                last = i;
            }
        }
        return maxGap;
    }
    public static int numberOfSteps(int num) {
        /**1342. Number of Steps to Reduce a Number to Zero
         * Given an integer num, return the number of steps to reduce it to zero.
         *
         * In one step, if the current number is even, you have to divide it by 2,
         * otherwise, you have to subtract 1 from it.
         * Input: num = 14
         * Output: 6
         * Explanation:
         * Step 1) 14 is even; divide by 2 and obtain 7.
         * Step 2) 7 is odd; subtract 1 and obtain 6.
         * Step 3) 6 is even; divide by 2 and obtain 3.
         * Step 4) 3 is odd; subtract 1 and obtain 2.
         * Step 5) 2 is even; divide by 2 and obtain 1.
         * Step 6) 1 is odd; subtract 1 and obtain 0.
         */
        if(num == 0)
            return 0;
        int steps = 0;
        while(num != 0){
            steps += ((num & 1) == 0) ? 1 : 2; // when its Odd means need to subtract and divide, so 2 steps
            num >>= 1;
        }
        return steps - 1; // whenever we reach 1 , it is calculated as 2 steps , but only subtract is enough to reach 0.
    }
    public static int[] sortByBits(int[] a) {

        int n = a.length;
        for(int i=0; i<n; i++){
            a[i] += bitCount(a[i]) * 10001;
        }
        Arrays.sort(a);
        for(int i=0; i<n; i++){
            a[i] %= 10001;
        }
        return a;

    }
    public static int[] sortByBitsNew(int[] arr) {
        return Arrays.stream(arr)
                .boxed()
                .sorted(Comparator.comparing(Integer::bitCount).thenComparing(Integer::intValue))
                .mapToInt(i -> i)
                .toArray();
//        ArrayList<Integer> list = new ArrayList<>();
//        for (int n: arr) {
//            list.add(n);
//        }
//        list.sort((a, b) -> bitCount(a) == bitCount(b) ? a - b : bitCount(a) - bitCount(b));
//        int i=0;
//        for (int n : list) {
//            arr[i++] = n;
//        }
//        return arr;
    }
    public static int countConsistentStrings(String allowed, String[] words) {
        int alphabet = 0; int cnt = 0;
        for (int i = 0; i < allowed.length(); i++) {
            int shift = allowed.charAt(i) - 'a'; // it gives the val of char
            alphabet |= 1 << shift;
        }
        outer:
        for (String w : words) {
            for (int i = 0; i < w.length(); i++) {
                int shift = w.charAt(i) - 'a';
                if((alphabet & (1 << shift)) == 0){
                    continue outer;
                }
            }
            cnt++;
        }
        return cnt;
        /**Contains Method
         * int count=0;
         *         outer:
         *         for(String w : words){
         *             for(int i=0; i < w.length(); i++){
         *                 if(!allowed.contains(w.charAt(i) +""))
         *                     continue outer;
         *             }
         *             count++;
         *         }
         *         return count;
         */
    }

}
