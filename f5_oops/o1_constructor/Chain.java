package com.f5_oops.o1_constructor;

public class Chain extends Copy {
    static {
        System.out.println("static block execute only once for a class," +
                " don't care about how many objects u r creating and it always execute first");
    }
    Chain() {
        this("Sam",0);
        System.out.println("Default constructor is called");
    }
    Chain(String name, int age) {
        super(new Copy());
        //this("Salem");
        System.out.println("Name : "+name+", Age : "+age);
    }
    Chain(String place){
        super("John",19);
        System.out.println("Native : "+place);
    }
}