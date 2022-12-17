package com.f6_generics.customArrayList;

import java.util.ArrayList;
import java.util.Scanner;

public class MultiArrayList {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

    }
    static void multiArrayList(){
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                list.get(i).add(sc.nextInt());
            }
        }
        System.out.println(list);
    }
}
