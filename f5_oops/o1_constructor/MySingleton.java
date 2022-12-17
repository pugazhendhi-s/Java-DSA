package com.f5_oops.o1_constructor;

public class MySingleton {
    static MySingleton instance = null;
    public int x = 10;

    private MySingleton(){} // Default Constructor

    public static MySingleton getInstance(){
        if(instance == null)
            instance = new MySingleton();
        return instance;
    }
    /*static int value = 0;
        public static int getValue(){
        if(value == 0)
            value = ;
        return value;
    }*/
}