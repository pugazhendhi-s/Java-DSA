package com.f5_oops.o3_properties.inheritance;

public class Box {
    private double length;  // if its private means u can use this in file,
    // even child class also not able use this
    protected double height;
    protected double width;
    //double superExample;
    protected Box(){
        this.height = -1;
        this.length = -1;
        this.width = -1;
    }
    // assume cube
    protected Box(double side){
        super();  // this will call Object Class
        // every single class u create , every single is the child class of Object Class
        this.height = side;
        this.length = side;
        this.width = side;
    }

    static void greeting(){
        System.out.println("Hey!, I am in Box class");
    }

    Box(double length, double height, double width) {
        System.out.println("Box class constructor");
        this.length = length;
        this.height = height;
        this.width = width;
    }
    Box(Box old){
        this.length = old.length;
        this.height = old.height;
        this.width = old.width;
    }
    public void information(){
        System.out.println("Running the box");
    }
}
