package com.f5_oops.o3_properties.polymorphism;

public class Numbers { // Compile time polymorphism
    public static void main(String[] args) {
        Numbers obj = new Numbers();
        System.out.println(obj.sum(2, 3));
        System.out.println(obj.sum(3,4,5));
        System.out.println(obj.sum(3.0, 2));
        System.out.println(obj.sum(2, 3.0));
    }
    int sum(int a, int b){
        return a + b;
    }
    int sum(int a, int b, int c){
        return a + b + c;
    }
    int sum(double a, int b){
        return (int) (a + b);
    }
    int sum(int a, double b){
        return (int) (a+b);
    }
    // below cause error, return type doesn't apply on polymorphism
//    String sum(int a, double b){ // this is ambiguity with previous method
//        return a+b+"";
//    }

}
