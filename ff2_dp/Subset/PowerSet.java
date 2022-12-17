package com.ff2_dp.Subset;
import java.util.*;

public class PowerSet {

    public static void main(String[] args) {

    }

    public List<String> AllPossibleStrings(String s) { // powerset

        int n = s.length();
        List<String> list = new ArrayList<>();

        for (int i=1; i<Math.pow(2,n); i++) {

            StringBuilder temp = new StringBuilder();
            for (int j=0; j<n; j++) {
                if ((i & (1 << j)) != 0)
                    temp.append(s.charAt(j));
            }
            list.add(temp.toString());
        }
        Collections.sort(list);
        return list;
    }
}
