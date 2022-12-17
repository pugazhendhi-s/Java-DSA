package com.f5_oops.o8_garbageCollector;

public class Student {

    final int ID = 5;
    public String name;
    public int age;
    public double salary;

    public Student(String name){
        this.name = name;
    }

    // if any object of Student free from the memory (garbage collector),
    // whenever object gonna destroyed by garbage collector, finalize method will execute
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Object is destroyed");
    }
}
