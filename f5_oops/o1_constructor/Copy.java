package com.f5_oops.o1_constructor;

public class Copy {
    public String name;
    public int age;
    public Copy(){
        System.out.println("Default Constructor called");
    }
    public Copy(String name, int age){
        this.name = name;
        this.age = age;
        System.out.println(name+" "+age+" Base class constructor called");
    }
    public Copy(Copy copy){//
        name = copy.name;
        age = copy.age;
        System.out.println("Copy Constructor called");
    }
}