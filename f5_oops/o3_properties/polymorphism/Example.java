package com.f5_oops.o3_properties.polymorphism;

/* Polymorphism
* 1.Compile time or Static achieved by method overloading
* 2.Run time or dynamic achieved by method overriding
* */
public class Example {
    public static void main(String[] args) {
        compileTime ct = new compileTime();
        ct.Car("245km/hr", 2011);
        compileTime.Car(2001, "200km/hr","Mercedes Benz");
        compileTime.Car(2010, "100km/h");

        runTime rt = new runTime();
        runTime.Car(2001, "200km/hr","Mercedes Benz");
        rt.Car("",2002);
        compileTime.Car(2001,"100km/hr");
    }
}
class compileTime{
    public void Car(String speed, int model){
        System.out.println("Speed : "+speed+", Model : "+model);
    }
    public static void Car(int model, String speed){
        System.out.println("Speed : "+speed+", Model : "+model);
    }
    public static void Car(int model, String speed, String name){
        System.out.println("Model : "+model+", Speed : "+speed+", Name : "+name);
    }
}
class runTime extends compileTime {
    @Override
    public void Car(String speed, int model){
        System.out.println("Speed is ver fast: "+speed+", Model : "+model);
    }
    public static void Car(int model, String speed, String name) {
        System.out.println("Model year : " + model + ", Speed : " + speed + ", Name : " + name);
    }
}

