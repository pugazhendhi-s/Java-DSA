package com.f5_oops.o2_static1;

public class Human {
    int age;
    String name;
    int salary;
    boolean married;
    static long population; // common for all object

    public Human(int age, String name, int salary, boolean married) {
        this.age = age;
        this.name = name;
        this.salary = salary;
        this.married = married;
        population += 1; //Human.population += 1;
        message();      // Human.message()
    }
    static void message(){
        System.out.println("Hello world!");
        System.out.println(population);
        // this.age; age; => can't use this because not static
    }
}
