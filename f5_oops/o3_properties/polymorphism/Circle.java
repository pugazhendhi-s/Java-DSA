package com.f5_oops.o3_properties.polymorphism;

public class Circle extends Shapes{
    // this will run when object of circle is created
    // hence it is overriding the parent method

    @Override // this is called Annotation
    void area(){
        System.out.println("Area of circle is [pie * r * r]");
    }

    // if you want to check whether a method is overridden or not
    // put annotation before it, it will give error
    // when the method is not overridden
    //below is example for not overridden
    /*
    @Override  -> shows error
    void areaa(){
        System.out.println("Area of circle is [pie * r * r]");
    }*/
}
