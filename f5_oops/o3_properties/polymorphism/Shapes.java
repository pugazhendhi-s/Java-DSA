package com.f5_oops.o3_properties.polymorphism;

public class Shapes {
    // late binding
    void area(){
        System.out.println("I am in Shapes");
    }

    // Final KeyWord
    /* Early binding : check notes
    final void area(){ // if you put this u can't override
        System.out.println("I am in Shapes");
    }*/
    // Overriding is happens at run time,
    // but here compiler will know some cases, this method can't be overridden
    // final it can't override determined in compile time
    //

    // whenever you put final in class declaration
    // prevents inheriting that class
    // and also implicitly all its properties are final.
}
