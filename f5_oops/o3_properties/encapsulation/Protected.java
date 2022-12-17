package com.f5_oops.o3_properties.encapsulation;

import com.f5_oops.o3_properties.inheritance.Box;

public class Protected extends Box {
    // if u need any constructor , create that
    Protected(int side){
        super(side);
    }

    public static void main(String[] args) {
        Protected obj = new Protected(79);
        //Box box = new Box(5); -> this is error , becoz that constructor is protected
        //u can access it diff package via extend the box class and access that
        obj.height = 8;
        double n = obj.height;
    }
}
class SubClass extends Protected{
    // w
    SubClass(int side){
        // this subclass doesn't extend directly box, but use those properties
        // by extending the class , which inherit the protected stuffs
        super(side);
    }
    // if parent class don't have any constructor
    // while child calls(super();) default constructor of parent class

    public static void main(String[] args) {
        SubClass obj = new SubClass(9);
        Protected po = new SubClass(8); //-> valid same package both class
        obj.height = 9;
        // u can access protected stuffs via extends that class, or extends the
        // which was inherited that protected stuffs
    }
}
