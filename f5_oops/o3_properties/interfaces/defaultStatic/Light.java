package com.f5_oops.o3_properties.interfaces.defaultStatic;

public interface Light {
    void love();
    default void fun(){
        System.out.println("It need not to be implement");
    }
    default void kira(){
        System.out.println("louyuyud");
    }
}
