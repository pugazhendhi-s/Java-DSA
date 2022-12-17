package com.f4_mathematics.bits;

import java.util.Scanner;

public class BitManipulation {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println(rangeXOR(3, 9));
    }
    public static boolean isOdd(int n){
        return (n & 1) == 1;
    }
    static void findUnique(){
        int[] a = {2, 6, 1, 6, 1, 3, 2};
        int unique = 0;
        for (int x: a) {
            unique ^= x;
        }
        System.out.println(unique);
        /*it is same like addition, order doesn't matter
        example
        int[] b = {2,3,-2,5,-5,-3,6};
        unique = 0;
        for (int x: b) {
            unique += x;
        }
        System.out.println(unique);*/
    }
    static void nthMagicNumber(){
        int n = 6;
        int magicNum = 0;
        int base = 5;
        while (n > 0){
            int lastDigit = n & 1; // for getting last bit LSB
            n = n >> 1; // right shift becoz, need to shift all numbers to LSB
            magicNum += (lastDigit * base);
            base = base * 5;
        }
        System.out.println(magicNum);

    }
    static void noOfDigits(){
        int n = 15;
        int b = 16;  // this is which base where n is represented
        int digits = (int)(Math.log(n) / Math.log(b)) + 1;
        System.out.println(digits);

        // approach 2 :  by right shift till n > 0
        // this gives answer in binary
        digits = 0;
        while (n > 0){
            digits++;
            n = n >> 1;
        }
        System.out.println(digits);
    }
    static void nthRowSumPascalTriangle(){
        /*
                  1        - 2^0 = 1
                 1 1       - 2^1 = 2
                1 2 1      - 2^2 = 4
               1 3 3 1     - 2^3 = 8
              1 4 6 4 1

              sum of nth row = 2 power n-1
         */
        int n = 5;
        System.out.println(Math.pow(2, n-1));
        // by left shift
        int ans = 1 << n-1;
        System.out.println(ans);
    }
    static void isPowerOf2(){
        int n = sc.nextInt(); // if n == 0, false
        boolean isPower = (n & n-1) == 0 && n != 0;
        System.out.println(isPower);
        /*int i = 0;
        int sum = 0;
        while (sum < n){
            sum = 1 << i;
            i++;
        }
        System.out.println(sum==n);*/
    }
    static int pow(int base, int power){         // find 3^5 = ?
        int ans = 1;
        while (power > 0){
            ans *= (power & 1) == 1 ? base : 1;
            base *= base; // for base 3 -> .... 3^4 , 3^2, 3^1, 3^0,  (0, 2, 4) base increment
            power = power >> 1;               // 81, 9, 3, 1
        }
        return ans; // Complexity = O(log power)
    }
    static int noOfSetBits(int n){  // O(logN)
        /**13 contains 3 setBits (count of 1)
                apply right most bit concept (n & (-n)) gives rightmost set bit
               (13) = 1101 -> -n = 0010 -> n& (-n) -> 0001, right most set bit
               to traverse to next set bit -> n = n - (n & (-n);
               it reset right most bit to 0 and find next set bit

         */
        System.out.println(Integer.toBinaryString(n));
        int count = 0;
        while (n > 0){
            count++;
            n -= (n & -n);
        }
        // method 2
        n = 13;
        count = 0;
        while (n > 0){
            count++;
            n = n & (n-1);
        }
        // perform right shift count
        n = 13;
        count = 0;
        while (n > 0){
            count += (n & 1) == 1 ? 1 : 0;
            n = n >> 1;
        }
        return count;
    }
    static int xor(int a){
        if(a % 4 == 0)
            return a;
        if(a % 4 == 1)
            return 1;
        if(a % 4 == 2)
            return a+1;
        return 0;  // a % 4 == 3
    }
    static int rangeXOR(int a, int b){
        // range for a,b gives ans = xor(b) ^ xor(a-1);
        return xor(b) ^ xor(a-1);

        // loop method, only for check, will give Time Limit Exceed(TLE) for large numbers
        /*int ans = 0;
        for (int i = a; i <= b; i++) {
            ans ^= i;
        }
        return ans;*/
    }
    static int[][] flipAndInvertImage(int[][] image){
        /**832. Flipping an Image
         * Input: image = [[1,1,0],[1,0,1],[0,0,0]]
         * Output: [[1,0,0],[0,1,0],[1,1,1]]
         * Explanation: First reverse each row: [[0,1,1],[1,0,1],[0,0,0]].
         * Then, invert the image: [[1,0,0],[0,1,0],[1,1,1]]
         */
        int n = image.length;
        /*for (int[] row : image) {
            for (int i = 0; i < n/2; i++) {
                int temp = row[i] ^ 1;
                row[i] = row[n-1-i] ^ 1;
                row[n-1-i] = temp;
            }
        }
        return image;*/
        // efficient approach
        /*
         * Compare the i th and n - i - 1 th in a row.
         * The "trick" is that if the values are not the same,
         * but you swap and flip, nothing will change.
         * So if they are same, we toggle both, otherwise we do nothing.
         */
        for (int[] row : image) {
            for (int i = 0; i * 2 < n; i++) {  // i * 2 < n or i < (n+1)/2
                if(row[i] == row[n-1-i])
                    row[i] = row[n-1-i] ^= 1;
            }
        }
        return image;
    }
}
