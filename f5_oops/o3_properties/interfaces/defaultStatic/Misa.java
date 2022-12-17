package com.f5_oops.o3_properties.interfaces.defaultStatic;

public interface Misa{
    void love();
    default void fun(){
        System.out.println("It need not to be implement");
    }
    // static interface methods should always have a body
    // call via the interface name
    static void greeting(){
        System.out.println("Hey! I am static method");
    }
}
