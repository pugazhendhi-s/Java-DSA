package com.f5_oops.o1_constructor;

public class Main {
    // The Constructor is a block of code that is used to initialize
    // the instance variables within the class
    public static void main(String[] args) {
//        Copy copy1 = new Copy();
//        Copy copy2 = new Copy("T E D", 19);
//        Copy copy3 = new Copy(copy2);

        Chain chain1 = new Chain();
        Chain chain2 = new Chain("John", 18);
        Chain chain3 = new Chain("Salem");


//        MySingleton e = MySingleton.getInstance();
//        MySingleton f = MySingleton.getInstance();
//        e.x += 10;
//        System.out.println("e.x : " + e.x);
//        System.out.println("f.x : " + f.x);

        Box box1 = new Box();
        System.out.println(box1.length +" "+ box1.height+" "+box1.width);

        Box box2 = new Box(4);
        System.out.println(box2.length +" "+ box2.height+" "+box2.width);

        Box box3 = new Box(4,7,2);
        System.out.println(box3.length +" "+ box3.height+" "+box3.width);

        Box box4 = new Box(box2);
        System.out.println(box4.length +" "+ box4.height+" "+box4.width);

    }
}

