package com.f1_arrays.sorting.problems;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CyclicSort {
    public static void main(String[] args) {

    }
    public static void swap(char[] a, int i, int j){
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    private static void swap(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    static void cyclicSort() {   // values range i1 to n
        int[] a = {3, 5, 2, 1, 4, 6};
        int i = 0;
        while (i < a.length) {
            int correct = a[i]-1;   // index
            if (a[i] != a[correct])
                swap(a, i, correct);
            else
                i++;
        }
        System.out.println(Arrays.toString(a));
    }
    static void missingNumber() {
            /* //By Sum of n
                int n = nums.length;
                int sum=0;
                for(int x : nums)
                    sum += x;
                int sumOfn = n * (n+1)/2;
                return sumOfn - sum;
             */
        int[] nums = {3,0,1};
        int i=0;
        while(i < nums.length){
            if(nums[i] == nums.length)
                i++;
            else if(nums[i] == i)
                // this will go infinite for duplicates, so check with correct val;
                i++;
            else
                swap(nums, i, nums[i]);
        }
        i=0;
        // search for which not found
        while(i < nums.length){
            if(i != nums[i])
                break;
            i++;
        }
        System.out.println(i);
    }
    static void findDisappearedNumbers(int[] nums) {
        // using list because in leetcode need to return list
        List<Integer> list = new ArrayList<>();
        for(int n : nums){
            list.add(n);
        }

        for(int i=1; i<=nums.length; i++){
            if(!list.contains(i))
                list.add(i);
            else{
                while(list.contains(i))
                    list.remove(i);
            }
        }
        System.out.println(list);
        // By cyclic sorting
            /*
            int[] nums = {4,3,2,7,8,2,3,1};
            List<Integer> list = new ArrayList<>();
            int i=0;
            while(i < nums.length){
                int correct = nums[i]-1;
                if(nums[i] != nums[correct])
                    AlgoSorting.Swap(nums, i, correct);
                else
                    i++;
            }
            System.out.println("end");
            i=1;
            while(i < nums.length){
                if(i != nums[i])
                    list.add(i);
                i++;
            }
            System.out.println(list);
             */
    }
    static void findDuplicate() {
        int[] a = {4,2,3,4,1};
        int i=0;
        while( i < a.length){
            if(a[i] != i+1){
                int correct = a[i]-1;
                if(a[i] != a[correct]){
                    swap(a, i, correct);
                }
                else
                    break;
            }
            i++;
        }
        System.out.println(Arrays.toString(a));
        System.out.println(a[i]);
            /*
            int[] a = {4,1,3,4,2};
            int i=0;
            while( i < a.length){
                int correct = a[i]-1;
                if(a[i] != a[correct]){
                    AlgoSorting.Swap(a, i, correct);
                }
                else
                    i++;
            }
            for(i=0; i<a.length; i++){
                if(a[i] != i+1) {
                    i = a[i];
                    break;
                }
            }
            System.out.println(i);*/
    }
    static void findAllDuplicates() {
        int[] nums = {4,3,2,7,8,2,3,1};
        List<Integer> list = new ArrayList<>();
        int i=0;
        while(i < nums.length){
            int correct = nums[i]-1;  // correct index
            if(nums[i] != nums[correct]){
                swap(nums, i, correct);
            }
            else
                i++;
        }
        i=1;
        for(int n : nums){
            if(i != n)
                list.add(n);
            i++;
        }
        System.out.println(list);
    }
    static void setMisMatch(){
        int[] nums = {1,2,2,4,3};
        // by changing neg val
            /*int size = nums.length;
            int correctSum = size * (size + 1)/2;
            int duplicateNum = 0;
            int actualSum = 0;
            for (int n : nums) {
                if(nums[n-1] < 0){
                    duplicateNum = n;
                }
                else
                    nums[n-1] *= -1;
                actualSum += n;
            }
            int diff = actualSum - correctSum;
            // -diff for changing -ve val to +ve
            int missingNum = (diff > 0) ? duplicateNum-diff : duplicateNum+(-diff);
            System.out.printf("MisMatch : [%d, %d]\n", duplicateNum, missingNum);*/
        // by cyclic sort
        int i=0;
        while(i < nums.length){
            int correct = nums[i] - 1;  // correct index
            if (nums[i] != nums[correct]) {
                swap(nums, i, correct);
            }
            else
                i++;
        }
        System.out.println(Arrays.toString(nums));
        i = 1;
        for (int n: nums) {
            if(n != i){
                System.out.printf("MisMatch : [%d, %d]\n", n, i);
                break;
            }
            i++;
        }
    }
    static void firstMissingPositive(){
        int[] a = {7,9};
        int i = 0;
        while (i < a.length) {
            int correct = a[i]-1;   // index
            if (a[i] > 0 && a[i] <= a.length && a[i] != a[correct])
                swap(a, i, correct);
            else
                i++;
        }
        System.out.println(Arrays.toString(a));
        for (int j = 0; j < a.length; j++) {
            if(a[j] != j+1) {
                System.out.println(j + 1);
                break;
            }
        }
        // if array already sorted, like this, 1,2,3,4,5 etc..
        System.out.println(a.length+1);
    }
    static void restoreString(){
        /** 1528. Shuffle String
         * Input: s = "codeleet", indices = [4,5,6,7,0,2,1,3]
         * Output: "leetcode"
         */
        String s = "codeleet";
        int[] indices = {4,5,6,7,0,2,1,3};
        char[] ch = new char[s.length()];
        for (int i = 0; i < indices.length; i++) {
            while (i != indices[i]){
                swap(ch, i, indices[i]);
                swap(indices, i, indices[i]);
            }
        }
        System.out.println(new String(ch));
            /*StringBuilder builder = new StringBuilder();
                char[] ch = new char[s.length()];
                for (int i = 0; i < indices.length; i++) {
                    ch[indices[i]] = s.charAt(i);
                }
                builder.append(ch);
                System.out.println(builder);*/
    }
}
