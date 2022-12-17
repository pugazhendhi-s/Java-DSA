package com.f9_queueStack.stack.leetCode.medium;

import java.util.*;
public class Medium {
    public static void main(String[] args) {
        numIslands();
    }

    public static String decodeString(String s) {
        //System.out.println(decodeString("3[a1[bc]]"));
        Stack<Integer> nums = new Stack<>();
        Stack<String> res = new Stack<>();
        int n = 0;
        res.push("");
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                n = n * 10 + ch - '0';  // increment number for 10.., 100, for digits
            }
            else if(ch == '['){
                nums.push(n); // push n, so that string can be repeated that times
                n = 0; // after make n as 0
                res.push("");
            }
            else if(ch == ']'){
                String str = res.pop();
                StringBuilder sb = new StringBuilder();
                int repeat = nums.pop();
                while (repeat-- > 0)
                    sb.append(str);
                res.push(res.pop() + sb);
            }
            else{
                res.push(res.pop() + ch);
            }
        }
        return res.pop();
    }
    private static String decodeStringRecursion(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            deque.offer(s.charAt(i));
        }
        return helper(deque);
    }
    private static String helper(Deque<Character> deque) {
        StringBuilder sb = new StringBuilder();
        int num = 0;
        while (!deque.isEmpty()){
            char ch = deque.poll();
            if(Character.isDigit(ch)) num = num * 10 + ch - '0';

            else if(ch == '['){
                String sub = helper(deque);
                while (num-- > 0)
                    sb.append(sub);
                num = 0;
            }
            else if(ch == ']') break;

            else sb.append(ch);
        }
        return sb.toString();
    }

    public static void asteroidCollision(){
        int[] asteroids = {5, -5, 10, 10, -7, -17, 19};
        asteroids = asteroidCollision(asteroids);
        System.out.println(Arrays.toString(asteroids));
    }
    private static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int aster : asteroids) {
            collision :
            {
                while (!stack.empty() && (aster < 0 && 0 < stack.peek())) {
                    if (stack.peek() < -aster) {
                        stack.pop();
                        continue;
                    }
                    else if (stack.peek() == -aster) {
                        stack.pop();
                    }
                    break collision;
                    // stack.peek() > aster, cut out from collision, no need to push aster
                }
                stack.push(aster);
            }
        }
        return stack.stream().mapToInt(a -> a).toArray();
    }

    public static void find132pattern(){
        int[] nums = {-2, 1, 2, -2, 2, 1, 2};
        boolean is132 = find132pattern(nums);
        System.out.println(is132);
    }
    private static boolean find132pattern(int[] nums) {

        // 132 patter -> a < c && b > c
        Stack<Integer> stack = new Stack<>();
        int min = Integer.MAX_VALUE;
        for (int n : nums){
            if(n <= min){
                min = n;
            }
            else{
                while (!stack.empty()){
                    if(stack.peek() >= n) break; // n -> third value, stack.peek() -> first value
                    stack.pop(); // popping first value, it satisfies condition
                    if(stack.pop() > n) return true; // second value > n , then true.
                }
                stack.push(n); // this refers second value
                stack.push(min); // this refers first value, when n > first and n < second
            }
        }
        return false;
    }

    public static void mostCompetitive(){
        int[] nums = {2,4,5,6,3,3,6,7};
        int k = 4;
        int[] res = mostCompetitive(nums, k);
        System.out.println(Arrays.toString(res));
    }
    private static int[] mostCompetitive(int[] nums, int k) {

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.empty() && stack.peek() > nums[i] && stack.size()+(nums.length - i) > k){
                stack.pop();
            }
            if(stack.size() < k)
                stack.push(nums[i]);
        }
        //return stack.stream().mapToInt(a -> a).toArray();

        // monotonic stack -> using index of array
        Stack<Integer> monoStack = new Stack<>();
        int[] res = new int[k];
        for (int i = 0; i < nums.length; i++) {
            while (!monoStack.empty() && nums[monoStack.peek()] > nums[i] && (nums.length - i) + monoStack.size() > k)
                monoStack.pop();
            if(monoStack.size() < k)
                monoStack.push(i);
        }
        for (int i = k-1; i >= 0; i--) {
            res[i] = nums[monoStack.pop()];
        }
        //return res;

        // using array
        int[] arrayStack = new int[k];
        int n = nums.length;
        int j = 0;
        for (int i = 0; i < n; i++) {
            while (j > 0 && arrayStack[j - 1] > nums[i] && (n-i+j) > k)
                j--;
            if(j < k)
                arrayStack[j++] = nums[i];
        }
        return arrayStack;
    }

    public static void circularTour(){
        //https://practice.geeksforgeeks.org/problems/circular-tour/1
        int n = 4;
        int[] petrol = {4, 3, 11, 4};
        int[] distance = {6, 5, 3, 5};
        System.out.println(tour(petrol, distance));
    }
    private static int tour(int[] petrol, int[] distance) {
        int start = 0;
        int balance = 0;
        int shortage = 0;
        for(int i=0; i<petrol.length; i++){
            balance += petrol[i] - distance[i];
            if(balance < 0){
                start = i+1;
                shortage += balance;
                balance = 0;
            }
        }
        return balance + shortage >= 0 ? start : -1;
    }

    public void leastInterval(){
        char[] tasks = {'A','A','A','A','A','A','B','C','D','E','F','G'};
        int n = 2;
        System.out.println(leastInterval(tasks, n));
    }
    private int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        int maxTask = 0;
        int totalMaxTask = 0;
        for (char task : tasks){
            count[task -'A']++;
            if(count[task -'A'] > maxTask){
                maxTask = count[task -'A'];
                totalMaxTask = 1;
            }
            else if(count[task - 'A'] == maxTask){
                maxTask = count[task - 'A'];
                totalMaxTask++;
            }
        }
        int gapLength = n+1;
        int noOfGaps = maxTask - 1;
        return Math.max(tasks.length, noOfGaps * gapLength + totalMaxTask);
    }

    public static void calculateSpan(){
        int[] price = {100, 80, 60, 70, 60, 75, 85};
        int n = price.length;
        int[] res = calculateSpan(price, n);
        System.out.println(Arrays.toString(res));
    }
    private static int[] calculateSpan(int[] price, int n) {
        Stack<Integer> stack = new Stack<>();
        int[] span = new int[n];
        for (int i = 0; i < n; i++) {
            while(!stack.empty() && price[stack.peek()] <= price[i]){
                stack.pop();
            }
            span[i] = stack.empty() ? i+1 : i - stack.peek();
            stack.push(i);
        }
        return span;
    }

    public static void celebrity(){

        int[][] party = {
                {0, 1, 1, 0},
                {1, 0, 1, 0},
                {0, 0, 0, 0},// 2 is celebrity, he doesn't know anyone, but he is known by everyone
                {1, 1, 1, 0}
        } ;
        int n = party.length;
        int celebrity = celebrityStack(party, n);
        System.out.println(celebrity);
    }
    private static int celebrity(int[][] party, int n) { // O(N^2)
        for (int i = 0; i < n; i++) {
            boolean isCelebrity = true;
            for (int j = 0; j < n; j++) {
                if(party[i][j] == 1){
                    isCelebrity = false;
                    break;
                }
            }
            if(isCelebrity){
                for (int j = 0; j < n; j++) {
                    if(i != j && party[j][i] == 0){
                        isCelebrity = false;
                        break;
                    }
                }
            }
            if(isCelebrity)
                return i;
        }
        //return -1;
        // by two pointer approach
        int start = 0;
        int end = n-1;
        while (start < end){
            if(party[start][end] == 1) start++;
            else end--;
        }
        // start is potential celebrity
        for (int i = 0; i < n; i++) {
            if(i != start && (party[start][i] == 1 || party[i][start] == 0))
                return -1;
        }
        return start;
    }
    private static int celebrityStack(int[][] party, int n) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            stack.push(i);
        }
        while (stack.size() >= 2){
            int i = stack.pop();
            int j = stack.pop();
            if(party[i][j] == 1) { // i know j , so i not a celebrity, j may be possible of celebrity
                stack.push(j);
            }
            else stack.push(i);  // i don't know j, so i may be celebrity
        }

        // single element in stack is potential
        // so verify it, celebrity row must be zero, and celebrity column must be 1, because celebrity is known by everyone
        int celebrity = stack.pop();
        for (int k = 0; k < n; k++) {
            if (k != celebrity && (party[celebrity][k] == 1 || party[k][celebrity] == 0))
                return -1;
        }
        return celebrity;
    }

    public static void longestValidParentheses(){
        String s = "))(()()())";
        String maxLong = longestValidParentheses(s);
        System.out.println(maxLong.length());
        System.out.println(maxLong);
    }
    private static String longestValidParentheses(String s){
        int maxLong = 0; int start = 0; int end = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(')
                stack.push(i);
            else{
                if(stack.empty())
                    stack.push(i);
                else {
                    stack.pop();
                    if(stack.empty())
                        stack.push(i);
                    else {
                        if(maxLong < i-stack.peek()) {
                            start = stack.peek() + 1;
                            end = i + 1;
                            maxLong = i-stack.peek();
                        }
                    }
                }
            }
        }
        return s.substring(start, end);
    }
    private static int longestValidParenthesesConstantSpace(String s) {
        int left = 0; int right = 0;
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') left++;
            else right++;
            if(right > left) left = right = 0; // invalid case
            maxLen = (left == right) && (2 * left > maxLen) ? 2 * left : maxLen;


        }
        left = right = 0;
        for (int i = s.length()-1; i > -1; i--) {
            if (s.charAt(i) == '(') left++;
            else right++;
            if (left > right) left = right = 0; // invalid case
            maxLen = (left == right) && (2 * left > maxLen) ? 2 * left : maxLen;
        }
        return maxLen;
    }

    public static void maxSlidingWindow(){
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        int[] maxWindow = maxSlidingWindowDP(nums, k);
        System.out.println(Arrays.toString(maxWindow));
    }
    // dynamic programming
    private static int[] maxSlidingWindowDP(int[] nums, int k){
        int n = nums.length;
        int[] maxWind = new int[n -(k-1)];
        int[] left = new int[n];
        left[0] = nums[0];
        int[] right = new int[n];
        right[n-1] = nums[n-1];
        for (int i = 1; i < n; i++) {
            // left
            if(i % k == 0)
                left[i] = nums[i];
            else
                left[i] = Math.max(left[i-1], nums[i]);

            // right
            int j = n-1-i;
            if(j % k == (k-1)) // (k-1) last val of window
                right[j] = nums[j];
            else
                right[j] = Math.max(right[j+1], nums[j]);
            /*
            left[i] = (i % k == 0) ? nums[i] : Math.max(left[i-1], nums[i]);

            int j = n-1-i;
            right[j] = (j%k == k-1) ? nums[j] : Math.max(right[j+1], nums[j]);
             */
        }
        for (int i=0, j=i + k-1; j < n; i++, j++) {
            maxWind[i] = Math.max(left[j], right[i]);  // left val max at window last, right val max at window end
        }
        return maxWind;
    }
    // deque
    private static int[] maxSlidingWindow(int[] nums, int k){
        int n = nums.length;
        int[] maxWindow = new int[n - (k-1)];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            // remove left part if window size reached
            if (!deque.isEmpty() && deque.peek() < i - (k-1))
                deque.pollFirst();
            // remove element which are less than current
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i])
                deque.pollLast();
            deque.offer(i);
            if(i >= k-1)  // after reach at window size, we are getting maxVal
                maxWindow[i - (k-1)] = nums[deque.peek()];
        }
        return maxWindow;
    }
    // time limit exceeded brute force
    private static int[] maxSlidingWind(int[] nums, int k) {
        int[] maxWind = new int[nums.length+1-k];
        for (int i = 0; i < nums.length-k+1; i++) {
            int[] temp = new int[k];
            System.arraycopy(nums, i, temp, 0, k);
            maxWind[i] = getMax(temp);
        }
        return maxWind;
    }
    private static int getMax(int[] nums){
        int max = 0;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] > nums[max])
                max = i;
        }
        return nums[max];
    }


    public static void numIslands(){
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        int noOfIslands = numIslands(grid);
        System.out.println(noOfIslands);
    }
    private static int numIslands(char[][] grid) {
        int islands = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if(grid[r][c] == '1'){
                    dfs(grid, r, c);
                    islands++;
                }
            }
        }
        return islands;
    }

    private static void dfs(char[][] grid, int r, int c) {
        // check edge cases
        if(r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] != '1')
            return;
        grid[r][c] = '0';
        dfs(grid, r-1, c);
        dfs(grid, r+1, c);
        dfs(grid, r, c-1);
        dfs(grid, r, c+1);
    }


}
