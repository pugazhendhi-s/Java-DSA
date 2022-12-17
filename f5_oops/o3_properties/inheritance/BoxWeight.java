package com.f5_oops.o3_properties.inheritance;

public class BoxWeight extends Box{
    double weight;
    //double superExample;

    public BoxWeight(){
        this.weight = -1;
        // error -> this.length = -1; its private u can't access it
    }
    BoxWeight (BoxWeight other){
        super(other);
        weight = other.weight;
        // this constructor is exactly same as this
        // Box box7 = new BoxWeight(2,3,4,8);
        //System.out.println(box7);
        /* Box box3 = new Box(4,7,2);
           Box box4 = new Box(box3);
           Error -> System.out.println(box4.weight);
           Child class can access all the properties parent class except private
           but if the object is instantiated using parent class , that object doesn't
           able to access child class properties
        */
        // reference type of box(parent), referencing to object of type BoxWeight
        // i.e. parent class referencing child class
        // i.e. the reference of subclass of object assigns to superclass objects
        // u will only have the access to only those parts of these objects
        // that are defined in that super class
    }
    // super keyword
    // super must be the first statement in the constructor body
    // super is used to initialise values present in parent class
    // child class crae about whether the parent class values are initialised
    // or not, but parent class doesn't care about child class values
    // so super is first statement initialise the parent class values

    BoxWeight(double side, Double weight){
        super(side);
        this.weight = weight;
    }
    public BoxWeight(double l, double h, double w, double weight){
        super(l, h, w);
        this.weight = weight;
        // it will call class that are immediate parent of it.
        // BoxWeight parents => Box and Object class but Box is the immediate parent
        // this call is used to initialise values present in parent class
        // System.out.println(this.superExample); // child class superExample
        // System.out.println(super.superExample); // parent class super example
        // if super is not called, then child class uses the default values of the parent class
    }
    static void greeting() {
        System.out.println("Hey!, I am in BoxWeight class");
    }
    // static methods can't be overriding
    /*
    @Override  -> Error
    static void greeting(){
        System.out.println("Hey!, I am in BoxWeight class");
    }*/
}
