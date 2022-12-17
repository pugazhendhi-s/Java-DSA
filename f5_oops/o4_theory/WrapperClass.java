package com.f5_oops.o4_theory;

public class WrapperClass {
    public static void main(String[] args) {
        int a = 5;
        Integer integer = new Integer(a); // boxing primitive to wrapper class object
        Integer integer1 = 1; // auto-boxing, java done this passing to method
        Integer i = Integer.valueOf(a); // boxing

        int b = integer.intValue(); // unboxing wrapper class object to primitive type
        b = integer; // auto-unboxing

        char c = 'a';
        Character ch = c; // auto-boxing

        // Convert the binary number to the integer value
        Integer five = Integer.valueOf("101", 2);

        /* why we need wrapper classes
            1.Objects are needed if we want to pass object as argument in method to change its contents(primitives pass by values)
            2.java.util.package contains ArrayList, Hash, store only object
            3.Wrapper has some in built methods, valueOf(), parseInt, toString
            4.Wrapper class primitive objects are final and immutable
         */

    }
}
