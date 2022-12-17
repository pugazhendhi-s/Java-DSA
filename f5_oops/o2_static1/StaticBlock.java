package com.f5_oops.o2_static1;

// this is demo to show initialization of static variables
public class StaticBlock {

    static int a = 4;
    static int b;
    // will only run once , when the first obj is created
    // i.e. when the class is loaded for the first time
    static {
        System.out.println("I am in static block");
        b = a * 5;
    }

    public static void main(String[] args) {
        StaticBlock obj = new StaticBlock(); // staic block execute for this
        System.out.println(a +" "+ b);

        b += 3; // StaticBlock.a or a both valid
        System.out.println(StaticBlock.a+" "+StaticBlock.b);
        StaticBlock obj1 = new StaticBlock(); // static block doesn't execute for this object
        System.out.println(a+" "+ b);
    }
}
