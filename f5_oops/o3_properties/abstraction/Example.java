package com.f5_oops.o3_properties.abstraction;

/* Abstraction - Data Abstraction
*  Encapsulation - Data Hiding
* */

public class Example {
    public static void main(String[] args) {
        Benz a = new Benz(4,"Mercedes", 2,200);
        System.out.println(a);
    }
}
abstract class Car {
    public static int wheels;

    abstract double speed();
    public abstract String toString();
    public Car(int wheels){
        System.out.println("Car Constructor called...");
        Car.wheels = wheels;
    }
    public static int getWheels(){   // Concrete methods
        return wheels;
    }
}
class Benz extends Car{
    public String model;
    public int throttle;
    public int cc;

    public Benz(int wheels, String model, int throttle, int cc ){
        super(wheels);
        System.out.println("Benz Constructor called...");
        this.model = model;
        this.throttle = throttle;
        this.cc = cc;
    }
    @Override
    public double speed(){
        return throttle * cc;
    }
    @Override
    public String toString(){
        return model+" Benz is "+ getWheels()+" wheeled vehicle top speed upto "+ speed()+"km/hr";
    }
}


