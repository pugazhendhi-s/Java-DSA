package com.f5_oops.o3_properties.interfaces.defaultStatic;

public class DeathNote implements Misa, Light {

    // why we use default because default methods u can may or may not implement.
    // suppose If you want to upgrade interface(i.e.add new methods)
    // if default implementation not allowed means, we need to implement
    // the new method in all class which implemented that interface.
    // that's why default methods solve that, whenever u upgrade interface
    // by default methods, rest classes which implements won't be affected
    @Override
    public void love() {

    }

    @Override
    public void kira(){
        System.out.println("Light");
    }

    // if implemented interface contains more than one default or two class implement same default methods then override like this
    @Override
    public void fun() {
        Misa.super.fun();
    }
}
