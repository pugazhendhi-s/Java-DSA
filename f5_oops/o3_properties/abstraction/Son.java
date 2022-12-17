package com.f5_oops.o3_properties.abstraction;

public class Son extends Parent{
    public Son(int age) {
        super(age);
    }
    @Override
    void career(){
        System.out.println("I am going to be a wild life");
    }
    @Override
    void partner(){
        System.out.println("I love Samantha");
    }

    @Override
    void normal() {
        super.normal();
    }
}
