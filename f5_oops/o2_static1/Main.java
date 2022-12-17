package com.f5_oops.o2_static1;

public class Main {
    public static void main(String[] args) {

        Human pugazh = new Human(19, "Pugazh", 10, false);
        System.out.println(Human.population);
        Human ted = new Human(17, "Ted", 100, true);

        System.out.println(pugazh.name);
        //System.out.println(pugazh.population); this also works, but convention access it by class name
        //System.out.println(ted.population);
        /*this is wrong access
          static is not depend on object, its same for all objects
          created by that class, so accessing static variables,etc..
          using is object instance make no sense,
          so that we directly access static values with class Name.
          */

        System.out.println(Human.population);


    }
    static void greeting(){
        // static  method - not depend on objects
        //greeting();
        // not static, belongs to object
        Main obj = new Main();
        obj.welcome();
        // if ur try to access not static method directly
        // it doesn't for which instance , ur accessing this method
    }
    void welcome(){
        greeting(); // access static method in not static methods
    }
    // In static method , only another static method can be
    // directly access by name of name.
    // not static methods accessed in static methods
    // by creating object reference and access through it

    void fun(){}
    void fun2(){
        // in the end everything in class called by static method(main method)
        // so object is surely created in main method
        fun(); // fun2 has object, so this method also belongs to that
        welcome();
        // this methods used for specific objects, u can directly call those methods by
        // this methods, becoz both are instances
    }

}
