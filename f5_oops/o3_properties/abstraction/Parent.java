package com.f5_oops.o3_properties.abstraction;

abstract public class Parent {
    // abstract class can't be final, becoz abstract class must be inherited
    // final restrict inherit, so a class can't be abstract and final
    int age;
    final int VALUE;
    Parent(int age){
        this.age = age;
        VALUE = 2452454;
    }

    abstract void career();
    abstract void partner();

    static void hello(){
        System.out.println("Hey!..");
    }
    // normal method can't directly execute by parent class, but it can be overridden
    // or execute by child class
    void normal(){
        System.out.println("This is going to be a normal method");
    }
    // can't create constructor for abstract class;
    // error -> abstract Parent();
    // we can't instantiate object for abstract class,
    // so there is no use abstract constructors


    // a method can't be both abstract and static
    // becoz the main rule of abstract method is this must be overridden
    // but static can't be overridden.
    // error -> abstract static void career();
}
