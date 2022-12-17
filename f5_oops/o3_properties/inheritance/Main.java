package com.f5_oops.o3_properties.inheritance;

public class Main {
    public static void main(String[] args) {
        Box box1 = new Box();
        System.out.println(box1.height+" "+box1.width);

        Box box2 = new Box(4);
        System.out.println(box2.height+" "+box2.width);

        Box box3 = new Box(4,7,2);
        System.out.println(box3.height+" "+box3.width);

        Box box4 = new Box(box2);
        System.out.println(box4.height+" "+box4.width);

        BoxWeight box5 = new BoxWeight();
        System.out.println(box5.height+" "+box5.weight);

        BoxWeight box6 = new BoxWeight(2,3,4,5);
        System.out.println(box6.height+" "+box6.weight);

        /* Box box3 = new Box(4,7,2);
           Box box4 = new Box(box3);
           Error -> System.out.println(box4.weight);
           Child class can access all the properties parent class except private
           but if the object is instantiated using parent class , that object doesn't
           able to access child class properties
        */
        // reference type of box(parent), referencing to object of type BoxWeight
        // i.e. parent class referencing child class
        // i.e. the reference of subclass of object assigns to superclass objects
        // u will only have the access to only those parts of these objects
        // that are defined in that super class
        Box box7 = new BoxWeight(2,3,4,8);
        System.out.println(box7);
        // Error -> BoxWeight box8 = new Box(2, 3, 4);
        // there are many variables in both parent and child classes
        // you are given access to variables that are int the ref type i.e. BoxWeight
        // hence, you should have access to weight variable
        // this also means, that the ones you are trying to access should be initialised
        // but here, when the object itself is of type parent class, how will you
        // call the constructor, this is why the error was come.
        // parent class doesn't may or may not have the properties of child class
        // So, it doesn't make sense child class reference pointing to parent class

        // Multi Level inheritance
        BoxPrice box8 = new BoxPrice(4, 9, 200);
        BoxPrice box9 = new BoxPrice(box8);


        // static
        Box box = new Box(4.7, 7.9, 9.9 );
        Box.greeting();

        Box box10 = new BoxWeight();
        Box.greeting();
        // print as per parent class, even BoxWeight doesn't contains method

        // static methods can't be overriding, you inherit it uses it
        // via same class reference
        // BoxWeight box10 = new BoxWeight();
        // static methods depends only on classes, so u can't override that

        // i.e. Overriding depends on objects, static doesn't depend on objects
        // Hence, static methods can't be overriding.

        BoxWeight box11 = new BoxWeight();
        BoxWeight.greeting(); // it will execute greeting method in boxWeight
        // but if boxWeight doesn't contain that method, its get that method
        // parent class (extends)
        //i.e. You can inherit but u can't override

    }
}
