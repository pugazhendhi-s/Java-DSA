package com.f8_recursion.r4_string;
import java.util.*;
public class Combination {
    public static void main(String[] args) {
        System.out.println(dice("", 4,6)); // face we can take any generally dice has 6 faces
    }
    static void phonePad(String p, String up, List<String> list){
        if(up.isEmpty()){
            list.add(p);
            return;
        }
        int digit = up.charAt(0) - '0';
        for (int i = (digit-1)*3; i < digit * 3; i++) {
            if(i == 26)
                continue;
            char ch = (char) ('a' + i);
            phonePad( p+ch, up.substring(1), list);
        }
    }
    static ArrayList<String> phonePad(String p, String up){
        if(up.isEmpty()){
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }
        int digit = up.charAt(0) - '0';
        ArrayList<String> list = new ArrayList<>();
        for (int i = (digit -1)*3; i < digit * 3; i++) {
            char ch = (char) ('a' + i);
            list.addAll(phonePad( p+ch, up.substring(1)));
        }
        return list;
        /*List<String> list = phonePad("","12");
        System.out.println(list+" ");*/
    }
    static int padCount(String p, String up){
        if(up.isEmpty()){
            return 1;
        }
        int count = 0;
        int digit = up.charAt(0) - '0';
        for (int i = (digit -1)*3; i < digit * 3; i++) {
            char ch = (char) ('a' + i);
            count += (padCount( p+ch, up.substring(1)));
        }
        return count;
    }

    static ArrayList<String> dice(String p, int target, int face){
        if(target == 0){
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i <= face && i <= target; i++) {
            list.addAll(dice(i+p, target-i, face));
        }
        return list;
    }
}
