package com.f5_oops.o3_properties.abstraction;

public class Main {
    public static void main(String[] args) {
        Son son = new Son(26);
        son.career();
        Daughter daughter = new Daughter(22);
        daughter.career();

        //error -> Parent mom =  new Parent();
        // can't directly object of abstract class

        // static method in Parent class
        Parent.hello();

        Parent dat1 = new Daughter(23);
        dat1.normal();

    }
}
