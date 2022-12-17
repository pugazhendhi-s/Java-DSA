package com.f5_oops.o3_properties.interfaces;

public class Main{
    public static void main(String[] args) {
//        Car car = new Car();
//        System.out.println(Car.price);
//        car.acc(); car.start(); car.stop(); car.brake();
//
//        Brake bc = new Car(); // overrriding
//        bc.brake();
        // as like classes, reference variables what needs to access
        // here reference is of Brake, object type defines which one to
        // access.
        // error -> bc.start();

        Media carMedia = new Car();
        carMedia.stop();

        NiceCar niceCar = new NiceCar();
        niceCar.start();
        niceCar.startMusic();
        niceCar.upgradeEngine(new ElectricEngine());
        niceCar.start();
    }
}
