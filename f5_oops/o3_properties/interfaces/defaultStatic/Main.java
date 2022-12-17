package com.f5_oops.o3_properties.interfaces.defaultStatic;


public class Main implements Light, Misa{
    public static void main(String[] args) {

    }

    @Override
    public void love() {

    }

    // if interface contain two default methods override like this
    @Override
    public void fun() {
        Light.super.fun();
    }

    @Override
    public void kira() {
        Light.super.kira();
    }

}
