package com.f5_oops.o3_properties.polymorphism;

public class Triangle extends Shapes{
    @Override
    void area(){
        System.out.println("Area of Triangle is [0.5 * b * h]");
    }
}
