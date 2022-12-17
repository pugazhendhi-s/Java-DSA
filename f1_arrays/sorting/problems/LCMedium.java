package com.f1_arrays.sorting.problems;

import java.util.*;

public class LCMedium {
    public static void main(String[] args) {
        noOf3SumInArray();
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    static void noOf3SumInArray() {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        Arrays.sort(nums);
        List<List<Integer>> list = threeSumUsingSet(nums);
        System.out.println(list);
    }

    static List<List<Integer>> threeSumUsingSet(int[] a) {
        List<List<Integer>> list = new LinkedList<>();
        for (int i = 0; i < a.length - 2; i++) {
            int lo = i + 1;
            int hi = a.length - 1;
            if (i == 0 || a[i] != a[i - 1])
                twoSumBySet(list, a, lo, hi, -a[i]);
        }
        return list;
    }

    private static void twoSumBySet(List<List<Integer>> list, int[] a, int lo, int hi, int target) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = lo; i <= hi; i++) {
            if (set.contains(target - a[i])) {
                list.add(Arrays.asList(-target, a[i], target - a[i]));
                while (i < hi && a[i] == a[i + 1]) i++;
            } else
                set.add(a[i]);
        }
    }

    static List<List<Integer>> threeSum2Pointer(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> list = new LinkedList<>();
        for (int i = 0; i < n - 2; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                int start = i + 1;
                int end = n - 1;
                while (start < end) {   // a+b+c = 0, a+b = -c;
                    if (nums[start] + nums[end] == -nums[i]) {
                        list.add(Arrays.asList(nums[i], nums[start], nums[end]));
                        while (start < end && nums[start] == nums[start + 1])  // remove duplicates
                            start++;
                        while (start < end && nums[end] == nums[end - 1])
                            end--;
                        start++;
                        end--;
                    } else if (nums[start] + nums[end] > -nums[i])
                        end--;
                    else
                        start++;
                }
            }
        }
        return list;
    }

    static void threeSumClosestTarget() {
        int[] a = {-1, 2, 1, -4};
        int target = 1;
        int n = a.length;
        Arrays.sort(a);
        int res = Integer.MAX_VALUE;  // target-res, which was min;
        for (int i = 0; i < n - 2; i++) {
            if (i == 0 || a[i] != a[i - 1]) {
                int start = i + 1;
                int end = n - 1;
                while (start < end) {
                    int sum = a[i] + a[start] + a[end];
                    if (sum > target)
                        end--;
                    else
                        start++;
                    if (Math.abs(target - res) > Math.abs(sum - target))
                        res = sum;
                }
            }
        }
        System.out.println(res);
    }

    static void fourSum() {
        int[] a = {1000000000, 1000000000, 1000000000, 1000000000};
        // if we use int means every value add with another comes 0, Integer overflow
        int target = -294967296;
        System.out.println(fourSumTarget(a, target));
    }

    static List<List<Integer>> fourSumTarget(int[] a, int target) {
        int n = a.length;
        Arrays.sort(a);
        List<List<Integer>> list = new ArrayList<>();
        if (n < 4)
            return list;
        for (int i = 0; i < n; i++) {
            if (i != 0 && a[i] == a[i - 1]) continue;

            for (int j = i + 1; j < n; j++) {
                if (j != i + 1 && a[j] == a[j - 1]) continue;
                int start = j + 1, end = n - 1;

                while (start < end) {
                    long sum = (long) a[i] + (long) a[j] + (long) a[start] + (long) a[end];
                    if (sum == (long) target) {
                        list.add(Arrays.asList(a[i], a[j], a[start], a[end]));
                        start++;
                        while (start < end && a[start] == a[start - 1]) start++;
                    } else if (sum > target) end--;
                    else start++;
                }
            }
        }
        return list;
    }

    static void groupAnagram() {
        String[] str = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(funcGroupAnagrams(str));
    }

    static List<List<String>> funcGroupAnagrams(String[] str) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : str) {
            char[] ch = new char[26];
            for (char c : s.toCharArray()) {
                ch[c - 'a']++;
            }
            String keyStr = String.valueOf(ch);
            if (map.containsKey(keyStr))
                map.get(keyStr).add(s);
            else
                map.put(keyStr, new ArrayList<>());
        }
        return new ArrayList<>(map.values());  // return new ArrayList<>(map.values());;
    }

    static void mergeIntervals() {
        /*
         * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
         * Output: [[1,6],[8,10],[15,18]]
         * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
         */
        int[][] intervals = {{1, 3}, {2, 6}, {15, 18}, {8, 10}};
        // i[0] sort by intervals[i][0] start
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
        List<int[]> res = new ArrayList<>();
        int[] newInterval = intervals[0];
        res.add(newInterval);
        for (int[] interval : intervals) {
            // interval[0] = 1, newin[1] = 3, we can change end if needed
            if (interval[0] <= newInterval[1])
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            else {
                newInterval = interval;  // adding new interval, previous interval ended
                res.add(newInterval);
            }
        }
        int[][] nonCollisionIntervals = res.toArray(new int[res.size()][]);
        for (int[] interval : nonCollisionIntervals) {
            System.out.print(interval[0] + " -> ");
            System.out.print(interval[1] + "\n");
        }
            /*int[][] res = new int[n][2];
            int k=0;
            for(int i=0; i<n; i++){
                int start = a[i][0];
                int end = a[i][1];
                for(int j=i+1; j<n; j++){
                    if(end >= a[j][0]){
                        end = a[j][1];
                        i++;
                    }
                    else
                        break;
                }
                res[k][0] = start;
                res[k][1] = end;
                k++;
            }
            for (int[] x: res) {
                for (int val: x) {
                    System.out.print(val+" ");
                }
                System.out.println();
            }*/
    }

    static void sortColors() {
        int[] nums = {2, 1, 2, 0, 2, 0};
        // two pointer efficient
        int zero = 0;
        int two = nums.length - 1;
        for (int i = 0; i <= two; i++) {
            if (nums[i] == 0) {
                swap(nums, i, zero++);
            } else if (nums[i] == 2) {
                swap(nums, i--, two--);
                // swap and maintain same i(i--) for next loop to check swapped value also 2
            }
        }
        System.out.println(Arrays.toString(nums));
            /*HashMap<Integer, Integer> map = new HashMap<>();
            for(int val : nums){
                map.put(val, map.getOrDefault(val, 0)+1);
            }
            int k=0;
            for (int i = 0; i < 3; i++) {
                if(map.containsKey(i)) {
                    int cnt = map.get(i);
                    while (cnt-- > 0)
                        nums[k++] = i;
                }
            }
            System.out.println(Arrays.toString(nums));*/
    }

    static void largestNumber() {
        int[] nums = {3, 30, 34, 5, 9};
        String[] strNum = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strNum[i] = String.valueOf(nums[i]);
        }
        // compare number by append. nums[0].append(nums[1]) check greater or less than nums[1].append(nums[0]) ,
        Arrays.sort(strNum, (n1, n2) -> (n2 + n1).compareTo(n1 + n2));
        // type 2: instead we use compartor
            /*Comparator<String> comparator = new Comparator<String>() {
                @Override
                public int compare(String n1, String n2) {
                    return (n2+n1).compareTo(n1+n2);
                }
            };
            // Simply implement this by lamda exp, Comparator<String> comparator = (s1,s2) -> (s2+s1).compareTo.(s1+s2);
            Arrays.sort(strNum, comparator);*/

        StringBuilder largestNumber = new StringBuilder();
        for (String s : strNum) {
            largestNumber.append(s);
        }
        System.out.println(largestNumber);
    }

    public static int findKthLargest(int[] nums, int k) {

        k = nums.length - k;  // largest
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {

            int pivot = partition(nums, lo, hi);

            if (pivot == k) break;
            if (pivot < k) lo = pivot + 1;
            else hi = pivot - 1;
        }
        return nums[k];
    }

    private static int partition(int[] nums, int lo, int hi) {

        int pivot = nums[hi];
        int i = lo;
        for (int j = lo; j < hi; j++) {

            if (nums[j] < pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, hi);

        return i;
    }
}
