package com.f5_oops.o3_properties.inheritance;

public class Example{
    public static void main(String... args){
        Dobberman a = new Dobberman("White", 6,"1 metre");
        a.Breed();
        a.Height();

    }
}

class Dog{
    public String color;
    public int age;
    public Dog(){}

    public Dog(String color, int age){
        this.color = color;
        this.age = age;
        System.out.println("Base class");
    }
    public void Breed(){
        System.out.println("Normal Breed");
    }
    public String toString(){
        return "Normal Behavior";
    }
}
class Dobberman extends Dog{
    public String height;
    public Dobberman(){}
    public Dobberman(String color, int age, String height){
        super(color, age);
        this.height = height;
        super.Breed();
    }
    @Override public void Breed(){
        System.out.println(super.toString()+" Dobber man Breed");
    }
    public void Height(){
        System.out.println("Height : "+height);
    }
}
