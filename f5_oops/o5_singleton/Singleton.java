package com.f5_oops.o5_singleton;

// U can create only one objects for the singleton class
public class Singleton {
    // for allow only one object and restrict users to call the constructors
    // becoz , whenever u call the constructor new object is created,
    // so restrict user to call constructor

    private Singleton (){

    }
    private static Singleton instance;

    public static Singleton getInstance(){
        // check whether one object is created or not
        if (instance == null)
            instance = new Singleton();
        return instance;
    }
}
