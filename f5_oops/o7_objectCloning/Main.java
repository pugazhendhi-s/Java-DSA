package com.f5_oops.o7_objectCloning;

import java.util.*;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {

        Human pugazh = new Human("Pugazhendhi", 21);
        // normally we clone like pass other object to current object constructor
        //Human twin = new Human(pugazh);

        Human twin = (Human) pugazh.clone();
        // clone will copy
        System.out.println(twin.name +" "+ twin.age);

        /** Shallow copy
         primitives will be copied as it is. But
         if pugazh contains any Object datatypes, it doesn't copy that
         but both reference variable is pointing to same object
        */
        System.out.println(Arrays.toString(twin.arr));
        // for primitives change in one object doesn't affect other object
        twin.age = 11;

        // Object data types change in object reflects the other also
        System.out.println(pugazh.age);
        // both pugazh and twin objects pointing to same reference arr

        twin.arr[0] = 100;
        // pugazh object also change ,

        System.out.println(Arrays.toString(pugazh.arr));

        // Deep Copy
        // twin Everything will be created new as pugazh,  object also
        // create new for twin

        twin.arr[1] = 111;
        // this twin doesn't affect pugazh
        System.out.println(Arrays.toString(pugazh.arr));



    }
}
