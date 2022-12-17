package com.f5_oops.o4_theory;

public class instanceInitializerBlock {
    public static void main(String[] args) {
        Y ob = new Y();
    }
    /**
     * Rules for instance initializer block :
     * There are mainly three rules for the instance initializer block. They are as follows:
     * The instance initializer block is created when instance of the class is created.
     * The instance initializer block is invoked after the parent class constructor is invoked (i.e. after super() constructor call).
     * The instance initializer block comes in the order in which they appear.
     * but static initializer block always execute first, when we create a class constructor is invoked , child class call parent class,
     * i.e. static block is the first block to execute in all classes in hierarchy when constructor is invoked for child class ,
     * parent class static block, child class static block , and then parent class initial block,
     * parent class stuffs and child initial block and rest all stuffs
     */
}
class Z{
    Z() {
        System.out.println("Parent class constructor");
    }
    static {
        System.out.println("parent static block");
    }
}
class Y extends Z{
    Y(){
        super();
        // now only instance block copies here
        System.out.println("Child class constructor");
    }
    {
        System.out.println("instance block child class");
    }
    static {
        System.out.println("static block always execute first, when that class is invoked");
    }
}
