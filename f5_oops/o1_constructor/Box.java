package com.f5_oops.o1_constructor;

public class Box {
    double length;
    double height;
    double width;
    Box(){
        this.height = -1;
        this.length = -1;
        this.width = -1;
    }
    // cube
    Box(double side){
        this.height = side;
        this.length = side;
        this.width = side;
    }

    Box(double length, double height, double width) {
        this.length = length;
        this.height = height;
        this.width = width;
    }
    Box(Box old){
        this.length = old.length;
        this.height = old.height;
        this.width = old.width;
    }
}
