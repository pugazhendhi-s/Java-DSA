package com.f4_mathematics.formula;



import java.util.*;

public class Maths {
    public static void main(String[] args) {

    }
    public static String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++){
            sb.append(i);
        }
        ArrayList<String> list =
                new ArrayList<>();
        permut(sb.toString(), "", k, list);
        Collections.sort(list);
        return list.get(k);
    }
    public static ArrayList<String> permut(String s, String current, int k, ArrayList<String> list){
        if(s.isEmpty()){
            list.add(current);
            return list;
        }
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            String temp = s.substring(0,i)+s.substring(i+1);
            permut(temp, current+ch, k, list);
        }
        return list;
    }

    public static int minCapacity(int[] a, int n){
        Arrays.sort(a);
        int s = a[0]; int e = a[n-1];
        while(s <= e){
            int m = s + (e - s)/2;
            int k = 0; boolean flag = false;
            for (int i = 0; i < n; i++, k++) {
                if(a[i] - m - k > 0) {
                    flag = true;
                    break;
                }
            }
            if(flag)
                s = m + 1;
            else
                e = m-1;
        }

        return s;
    }
    public static int findNth(int n){
        // Code your solution here.
        int mod = (int)(1e9 + 7);
        return fibo(n) % mod;
    }
    static int fibo(int n){
        if(n % 5 == 0 && n != 0)
            return 11;
        int[] f = new int[n+2];
        f[0] = 0;
        f[1] = 1;
        int i = 2;
        while(i <= n){
            if(i % 5 == 0)
                f[i] = 11;
            else
                f[i] = f[i-1] + f[i-2];
            i++;
        }
        return f[n];
    }
}
