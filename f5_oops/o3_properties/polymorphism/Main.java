package com.f5_oops.o3_properties.polymorphism;

public class Main {
    public static void main(String[] args) {

        Shapes shape = new Shapes();
        Circle circle = new Circle(); // it doesn't depend on shapes
        Shapes square = new Square();
        Shapes cir = new Circle(); // its depends on shape reference

        square.area(); // call based on object created(new Square)

        // i.e. What It's able to access defined by the reference type(Shapes obj)
        // and which one is able to access is defined by the type of its object(new Square)

        // Shapes square -> it represents what needs to be access
        // it will access what are all in Shapes Classes,
        //
        // Shapes circle = new Circle(); // below explain for this
        // suppose if shape classes doesn't contain a method area,
        // but Circle contains a method area, now if you
        // try to access circle.area gives you error,
        // Shapes circle gives what need to be accessed
        // Shapes doesn't contain method area, u can't access child class method
        // i.e. Shapes contains area method, then it will execute
        // the method which was in object i.e. circle area
    }
}
