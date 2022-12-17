package com.f1_arrays.sorting.problems;

import java.util.*;

public class LCEasy {
    public static void main(String[] args) {
        minimumAbsDifference();
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    static void majorityElement() {
        // Moore voting algo
        // element freq which is greater than array size/2;
        int[] a = {9, 2, 4, 4, 4, 2, 4};
        int count = 0;
        int curVal = 0;
        for (int x : a) {
            curVal = count == 0 ? x : curVal;
            count += (curVal != x) ? -1 : 1;
        }
        System.out.println(curVal);
    }

    static void duplicate() {
        int[] nums = {1, 0, 1, 1};
        int k = 1;
        System.out.println(slidingWindowUsingSet(nums, k));
    }

    static boolean slidingWindowUsingSet(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                set.remove(nums[i - k - 1]);
                //remove element if its distance to nums[i]
                // is not lesser than k
            }
            if (!set.add(nums[i])) {
                return true;
                     /*because all still existed elements is
                     closer than k distance to the num[i],
                     therefore if the add() return false,
                     it means there's a same value element already
                     existed within the distance k, therefore return true.*/
            }
        }
        return false;
    }

    static boolean containsNearbyDuplicateMap(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer lastIndex = map.put(nums[i], i);
            // map.put(nums[i], i) return previous value of key
            if (lastIndex != null && i - lastIndex <= k)
                return true;
        }
        return false;
    }

    static void maxProd3num() {
        int[] a = {-2, 7, 1, 2, 20, 1, -100};

            /* // O(nlogn), s - O(nlogn)
            Arrays.sort(a);
            int max1 = a[0]*a[1]*a[2];
            int max2 = a[n-1]*a[n-2]*a[n-3];
            System.out.println(Math.max(max1, max2));*/
            /* efficient approach O(n), O(1)-s
            max prod is formed from three numbers either three positive numbers or 2 neg and 1 positive
             */
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;  // 2 min value
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;  // 3 max val

        for (int x : a) {
            // find min
            if (x < min1) {
                min2 = min1;
                min1 = x;
            } else if (x < min2)
                min2 = x;
            // find max
            if (x >= max1) {
                max3 = max2;
                max2 = max1;
                max1 = x;
            } else if (x >= max2) {
                max3 = max2;
                max2 = x;
            } else if (x >= max3)
                max3 = x;
        }
        int max = Math.max(min1 * min2 * max1, max1 * max2 * max3);
        System.out.println(max);
    }

    static void sortArrayByParity() {
        int[] A = {1, 2, 3, 4};
        Integer[] B = new Integer[A.length];
        for (int t = 0; t < A.length; ++t)
            B[t] = A[t];

        Arrays.sort(B, Comparator.comparingInt(a -> a % 2));

//        for (int t = 0; t < A.length; ++t)
//            A[t] = B[t];
//        System.out.println(Arrays.toString(A));

        //Alternative:
        A = Arrays.stream(A)
                .boxed()
                .sorted(Comparator.comparingInt(a -> a % 2))
                .mapToInt(i -> i)
                .toArray();
        System.out.println(Arrays.toString(A));

        int[] nums = {1, 2, 3, 4};
        for (int i = 0, j = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j++] = temp;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    static void alternativeEvenOdd() {
        // or use temp array to store values
        int[] a = {1, 2, 3, 4, 5, 6};
        int odd = 1;
        int even = 0;
        while (odd < a.length && even < a.length) {
            if (a[even] % 2 != 0) {
                swap(a, even, odd);
                odd += 2;
            } else
                even += 2;
        }
        System.out.println(Arrays.toString(a));
    }

    static void sortedSquares() {
        int[] a = {-7, -3, 2, 3, 11};
        int n = a.length;
        int[] res = new int[n];
        int i = 0, j = n - 1;
        int k = n - 1;
        while (i <= j) {
            if (Math.abs(a[i]) > Math.abs(a[j])) {
                res[k--] = a[i] * a[i];
                i++;
            } else {
                res[k--] = a[j] * a[j];
                j--;
            }
        }
        a = res;
        System.out.println(Arrays.toString(a));
    }

    static void relativeSortArray() {
            /*
            Example 1:
            Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
            Output: [2,2,2,1,4,3,3,9,6,7,19]

            Example 2:
            Input: arr1 = [28,6,22,8,44,17], arr2 = [22,28,8,6]
            Output: [22,28,8,6,17,44]
             */
        // B is subset of A, A contains all values of B
        int[] a = {2, 21, 43, 38, 0, 42, 33, 7, 24, 13, 12, 27, 12, 24, 5, 23, 29, 48, 30, 31};
        int[] b = {2, 42, 38, 0, 43, 21};
        // counting sort  , this problem array range 0 to 1000;
        int[] count = new int[1001];
        for (int n : a) {
            count[n]++;
        }
        int i = 0;
        for (int n : b) {
            while (count[n]-- > 0)
                a[i++] = n;
        }
        for (int n = 0; n < count.length; n++) {
            while (count[n]-- > 0)
                a[i++] = n;
        }
        System.out.println(Arrays.toString(a));


            /*HashMap<Integer, Integer> map = new HashMap<>();

            for (int x : a) {
                map.put(x, map.getOrDefault(x, 0)+1);
            }
            int i=0;
            for(int x : b){
                int freq = map.get(x);
                while (freq-- > 0){
                    a[i++] = x;
                }
                map.remove(x);
            }
            ArrayList<Integer> list = new ArrayList<>();
            for (int n : map.keySet()) {
                for (int j = 0; j < map.get(n); j++) {
                    list.add(n);
                }
            }
            Collections.sort(list);
            for (int val: list) {
                a[i++] = val;
            }
            System.out.println(Arrays.toString(a));*/
            /*HashSet<Integer> hs = new HashSet<>();
            for (int value : b) {
                hs.add(value);
            }
            int flag = 0;
            for (int value: a) {
                if(!hs.contains(value))
                    flag++;
            }
            int k=0;
            for (int value : b) {
                int j = 0;
                while (j < a.length) {
                    if(value == a[j]){
                        swap(a, j, k);
                        k++;
                    }
                    j++;
                }
            }
            Arrays.sort(a, a.length-flag,a.length);
            System.out.println(Arrays.toString(a));*/
    }

    static void minimumAbsDifference() {
        int[] a = {3, 8, -10, 23, 19, -4, -14, 27};
        // ans : [[-14, -10], [19, 23], [23, 27]] , this pair contains min diff of 4.
        Arrays.sort(a);
        List<List<Integer>> list = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < a.length - 1; i++) {
            int diff = a[i + 1] - a[i];
            if (diff < min) {
                min = diff;
                list.clear();
                list.add(Arrays.asList(a[i], a[i + 1]));
            } else if (diff == min) {
                list.add(Arrays.asList(a[i], a[i + 1]));
            }
        }
        System.out.println(list);
            /*int n = a.length;
            Arrays.sort(a);
            int min = a[n-1];
            for(int i=0; i<n-1; i++){
                if(a[i]+1 == a[i+1]){
                    min = 1;
                    break;
                }
                min = Math.min(min,a[i+1]-a[i]);
            }
            int i=1;
            List<List<Integer>> list = new ArrayList<>();
            for (int k : a) {
                List<Integer> li = new ArrayList<>();
                boolean flag = true;
                while (i < n) {
                    if(a[i]-k != min)
                        break;
                    flag = false;
                    li.add(k);
                    li.add(a[i]);
                    list.add(li);
                    i++;
                }
                if(flag)
                    i++;
            }
            System.out.println(list);*/
    }

    static void rankTransform() {
        int[] a = {200, 100, 100};
        int[] temp = a.clone();
        Arrays.sort(temp);
        int i = 1;
        HashMap<Integer, Integer> rank = new HashMap<>();
        for (int x : temp) {
            if (!rank.containsKey(x))
                // use instead of if rank.putIfAbsent(x , rank.size()+1);
                rank.put(x, i++);
        }
        i = 0;
        for (int x : a) {
            a[i++] = rank.get(x);
        }
        System.out.println(Arrays.toString(a));
    }

    static void avgSalaryNotMinMax() {
        int[] salary = {3000, 4000, 1000, 2000};
        int min = salary[0];
        int max = salary[0];
        for (int x : salary) {
            if (x < min)
                min = x;
            if (x > max)
                max = x;
        }
        int sum = -(max + min);
        for (int x : salary) {
            sum += x;
        }
        System.out.println((double) (sum / (salary.length - 2)));
    }

    static void canBeEqual() {
        int[] source = {1, 2, 2, 3};
        int[] target = {1, 1, 2, 3};
        int n = target.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(target[i], map.getOrDefault(target[i], 0) + 1);
            map.put(source[i], map.getOrDefault(source[i], 0) - 1);
        }
        for (int key : map.keySet()) {
            if (map.get(key) != 0) {
                System.out.println(false);
                return;
            }
        }
        System.out.println(true);
            /*
            public boolean canBeEqual(int[] target, int[] arr) {
                int n = target.length;
                HashMap<Integer, Integer> map = new HashMap<>();
                for (int j : target)
                    map.put(j, map.getOrDefault(j, 0) + 1);
                for(int x : arr){
                    if(map.containsKey(x)){
                        map.put(x, map.get(x)-1);
                        if(map.get(x)==0)
                            map.remove(x);
                    }
                    else
                        return false;
                }
                return true;
            }
             */

            /*// counting
                  int[] cnt = new int[1001];
                ` for (int t : target)
                    ++cnt[t];
                  for (int a : arr) {
                    if (--cnt[a] < 0) {
                        return false;
                    }
                  }
                  return true;
             */
    }

    static void canMakeArithmeticProgression(int[] arr) {
        // an = a1 + (n-1)d;  Arithmetic progression
        // (max-min)/(n-1) = d;  // d==0, true;
        HashSet<Integer> seen = new HashSet<>();  // remove the duplicates
        int n = arr.length;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int a : arr) {
            max = Math.max(max, a);
            min = Math.min(min, a);
            seen.add(a);
        }
        int diff = max - min;    // (max-min)%(n-1) always divisible
        if (diff % (n - 1) != 0) {
            System.out.println(false);
            return;
        }
        int step = diff / (n - 1);
        while (--n > 0) {
            if (!seen.contains(min)) {
                System.out.println(false);
                return;
            }
            min += step;
        }
        System.out.println(true);
    }

    static void frequencySort() {
        int[] nums = {1, 2, 3, 1, 2, 1};
            /*for(int n : nums){
                //map.put(n, map.getOrDefault(n,0)+1);
                map.merge(n, 1, Integer::sum); // both are same;
            }*/
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.stream(nums).
                forEach(n -> map.put(n, map.getOrDefault(n, 0) + 1));
        nums = Arrays.stream(nums).boxed()
                .sorted((a, b) -> map.get(a) != map.get(b)
                        ? map.get(a) - map.get(b) : b - a)
                .mapToInt(n -> n)
                .toArray();
        System.out.println(Arrays.toString(nums));
    }

    static void specialArray(int[] nums) {
        /*
         * You are given an array nums of non-negative integers.
         * nums is considered special if there exists a number x
         * such that there are exactly x numbers in nums that are
         * greater than or equal to x.
         *
         * Notice that x does not have to be an element in nums.
         *
         * Return x if the array is special, otherwise, return -1.
         * It can be proven that if nums is special, the value for x is unique.
         * Input: nums = [3,5]
         * Output: 2
         * Explanation: There are 2 values (3 and 5) that are greater than or equal to 2
         *
         */
        int x = 0;
        while (x <= 1000) {
            int count = 0;
            for (int num : nums) {
                if (num >= x)
                    count++;
            }
            if (count == x) {
                System.out.println(x);
                return;
            }
            x++;
        }
        System.out.println(-1);
    }

    static void findErrorNums() {
        int[] nums = {1, 2, 2, 4};
        int len = nums.length;
        int correctSum = len * (len + 1) / 2;
        int actualSum = 0;
        int badNum = 0;
        for (int n : nums) {
            n = Math.abs(n);
            if (nums[n - 1] < 0)
                badNum = n;
            else
                nums[n - 1] *= -1;
            actualSum += n;
        }
        int losNum = correctSum - (actualSum - badNum);
        System.out.println(badNum + ", " + losNum);
    }

    static void twoSum() {
        int[] nums = {12, 7, 11, 1};
        int target = 8;
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                res[0] = map.get(target - nums[i]);
                res[1] = i;
                break;
            }
            map.put(nums[i], i);
        }
        System.out.println("Index : " + Arrays.toString(res));
    }
}
