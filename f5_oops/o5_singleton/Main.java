package com.f5_oops.o5_singleton;

public class Main {
    public static void main(String[] args) {

        // Error -> Singleton singleton = new Singleton();

        Singleton obj1 = Singleton.getInstance();
        Singleton obj2 = Singleton.getInstance();
        Singleton obj3 = Singleton.getInstance();

        // all three reference variables pointing to just one object(same object)
    }

}
