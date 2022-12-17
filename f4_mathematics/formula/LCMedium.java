package com.f4_mathematics.formula;

import java.util.*;

public class LCMedium {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        System.out.println(Arrays.toString(productExceptSelf(nums)));
    }
    public static int uniquePathsEff(int m, int n){
        int steps = m + n-2; // we are at (1,1) so reduce -2
        int down = m - 1;   // down steps m-1.
        long uniquePaths = 1;        //  (m+n)!/m!n!
        for (int curDn = 1; curDn <= down; curDn++) {
            uniquePaths = uniquePaths * (steps + curDn - down) / curDn;
        }
        return (int)uniquePaths;
    }
    public static int uniquePaths(int m, int n){
        if(m == 1 && n == 1)
            return 1;
        if(m > n)
            return uniquePaths(n, m);
        int steps = m + n-2;
        int j = 1; long uniquePaths = 1;
        for (int i = m; i <= steps; i++, j++) {  //  3*4*5*6*7*8/ 1*2*3*4*5*6
            uniquePaths *= i;
            uniquePaths /= j;
        }
        return (int)uniquePaths;
    }
    public int numSquares(int n) {
        // All natural num is represented in the form of sum of 4 square int => a^2+ b^2+ c^2+ d^2 = (1<= N <=infinity)
        int a = (int)Math.sqrt(n);
        if(a * a == n)
            return 1;  // when n is perfect square
        while(n % 4 == 0)
            n /= 4;
        if(n % 8 == 7)
            return 4;  // if number satisfies legendre's 3 square sum, 4^a(8b+7) when n in this form

        for(a=0; a*a <= n; a++){
            int b = (int)Math.sqrt(n - a*a);
            if(b* b == (n - a*a))
                return a == 0 ? 1 : 2; // a==0 means n is perfect square, u can straightly return 2 , becoz already check n is ps or not
        }
        return 3;
    }
    static void nextGreaterElementI() {
        int[] findGreater = {4, 1, 2}, nums = {1, 3, 4, 2};
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int num : nums) {
            while (!stack.empty() && stack.peek() < num) {
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }
        for (int i = 0; i < findGreater.length; i++) {
            findGreater[i] = map.getOrDefault(findGreater[i], -1);
        }
        System.out.println(Arrays.toString(findGreater));
    }
    static void nextGreaterElementII() {
        int[] nums = {1,2,3,4,3};
        int n = nums.length;
        int[] next = new int[n];
        Stack<Integer> stack = new Stack<>();
        Arrays.fill(next, -1);
        for (int i = 0; i < 2*n; i++) {
            int num = nums[i%n];
            while (!stack.isEmpty() && nums[stack.peek()]<num)
                next[stack.pop()] = num;
            if(i < n)
                stack.push(i);
        }
        System.out.println(Arrays.toString(next));
    }
    public static int nextGreaterElement(int n) {
        char[] nums = (n+"").toCharArray();
        int i = nums.length-2;
        //traverse from right to left, nums[i] < nums[i+1],this is one number to swap
        while (i >= 0 && nums[i] >= nums[i+1])
            i--;
        if(i < 0) // i = -1 refers array is sorted in descending, so we can't make greater than n.
            return -1;
        int j = nums.length-1;
        while (nums[j] <= nums[i])
            j--;
        swapXOR(nums, i, j);
        reverse(nums, i+1, nums.length-1);
        // instead reverse sort array from i+1; buts sorting takes more complexity

        try {
            return Integer.parseInt(String.valueOf(nums));
        }
        catch (NumberFormatException e){
            return -1;
        }
        //  long val = Long.parseLong(String.valueOf(new String(nums)));
        //  return val <= Integer.MAX_VALUE ? (int)val : -1;
    }
    static void reverse(char[] nums, int i, int j){
        int start = i;
        int end = j;
        while (start < end)
            swapXOR(nums, start++, end--);
    }
    static void swapXOR(char[] arr, int i, int j) {
        arr[i] ^= arr[j];
        arr[j] ^= arr[i];
        arr[i] ^= arr[j];
    }
    public static double angleClock(int hour, int minutes){
        /**1344. Angle Between Hands of a Clock
         * Given two numbers, hour and minutes, return the smaller angle
         * (in degrees) formed between the hour and the minute hand.
         *
         * Answers within 10-5 of the actual value will be accepted as correct.
         * Input: hour = 12, minutes = 30
         * Output: 165
         *
         * Input: hour = 3, minutes = 30
         * Output: 75
         *
         */
        double mins = (minutes * 6); // (1 minute = 6 degree)
        double hrs = ( (hour % 12) * 30) + ( minutes * 0.5);
        // 1 hour = 30 degree and 1 min , hour rotated = 1 * (30 / 60) => (min * 0.5)
        double angle = Math.abs( mins - hrs);
        return (angle > 180) ? ( 360 - angle) : angle;


    }
    public static int myAtoi(String s) {
        s = s.trim();
        if(s.length() == 0)
            return 0;
        int num = 0, sign = 1, index = 0;
        if(s.charAt(index) == '-' || s.charAt(index) == '+'){
            sign = s.charAt(index) == '-' ? -1 : 1;
            index++;
        }
        while (index < s.length()){
            int digit = s.charAt(index) - '0';
            if(digit < 0 || digit > 9)
                break;
            if(num > Integer.MAX_VALUE / 10 || num == Integer.MAX_VALUE/10 && digit > 7) // 7 becoz int_maxval % 10 = 7;
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            num = num * 10 + digit;
            index++;
        }
        return num * sign;
    }
    public static int kthFactor(int n, int k) {
        List<Integer> list = new ArrayList<>();
        int j = 1;
        for(int i = 1; i * i <= n; i++){
            if(n % i == 0){
                if(n / i != i)
                    list.add(n/i);
                if(j == k)
                    return i;
                j++;
            }
        }
        int size = list.size();
        return (k - j < size) ? list.get(size-1 - (k - j)) : -1;
        /** Time = O(sqrt(n)), Space = O(1)
            for(int i = 1; i < Math.sqrt(n); ++i)
                if(n % i== 0 && --k == 0)
                    return i;
            for(int i = (int) Math.sqrt(n); i >= 1; --i)
                if(n % (n/i) == 0 && --k == 0)
                    return n/i;
            return -1;
         */
    }
    public static int[] countPoints(int[][] P, int[][] Q) {
        int[] res = new int[Q.length];
        int i = 0;
        for(int[] q : Q){
            int x1 = q[0], y1 = q[1], r = q[2]; int points = 0;
            for (int[] p : P) {
                int x2 = p[0];
                int y2 = p[1];
                if ((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) <= r * r)
                    points++;
            }
            res[i] = points;
            i++;
        }
        return res;
    }
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] prefixProd = new int[n];
        prefixProd[0] = 1;
        for (int i = 1; i < n; i++) {
            prefixProd[i] = prefixProd[i-1] * nums[i-1];
        }
        int suffixProd = 1;
        for (int i = n-1; i >= 0; i--) {
            prefixProd[i] *= suffixProd; // before num[i] prefix prod and suffix prod after num[i]
            suffixProd *= nums[i];
        }
        return prefixProd;
    }
    public static String multiplyStrings(String a, String b){
        int m = a.length();
        int n = b.length();
        int[] position = new int[m+n];
        for (int i = m-1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {
                int mul = (a.charAt(i) - '0') * (b.charAt(j) - '0');
                int p1 = i + j; int p2 = i + j + 1;
                int sum = mul + position[p2];
                position[p1] += sum / 10;
                position[p2] = sum % 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int nums : position) {
            if(!(sb.length() == 0 && nums == 0))
                sb.append(nums);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
