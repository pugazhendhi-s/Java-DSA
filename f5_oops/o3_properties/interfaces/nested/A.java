package com.f5_oops.o3_properties.interfaces.nested;

public class A {
    // nested interface can be created public, protected, default
    // but the normal interface must be public or default
    public interface NestedInterface{
        boolean isOdd(int num);
    }
}
class B implements A.NestedInterface{
    @Override
    public boolean isOdd(int num) {
        return (num & 1) == 1;
    }
}