package com.f5_oops.o4_theory;

public class Cohesion {
    public static void main(String[] args) {
        // this is less cohesive , here all class are doing single classes
        Name n = new Name();
        System.out.println(n.getName("Geeksforgeeks"));
        Age a = new Age();
        System.out.println(a.getAge(10));
        Number no = new Number();
        System.out.println(no.getNumber(1234567891));

        // Details class performs lots of jobs. so it is less cohesive
        Details d = new Details();
        System.out.println(d.getName("Love")+
                           d.getAge(7)+ d.getNumber(343536356));
    }
    /*
    Cohesion -> it makes sure that the class is designed with single, well-focused
    Difference between high cohesion and low cohesion:
        1. High cohesion is when you have a class that does a well-defined job.
            Low cohesion is when a class does a lot of jobs that don’t have much in common.
        2. High cohesion gives us better-maintaining facility and
            Low cohesion results in monolithic classes that are difficult to maintain, understand and reduce re-usability
     */
}
// tightly coupled example
class Details{
    String name;
    int age;
    long number;

    public String getName(String name) {
        this.name = name;
        return name;
    }

    public int getAge(int age) {
        this.age = age;
        return age;
    }

    public long getNumber(long number) {
        this.number = number;
        return number;
    }
}
class Name {
    String name;
    public String getName(String name)
    {
        this.name = name;
        return name;
    }
}

class Age {
    int age;
    public int getAge(int age)
    {
        this.age = age;
        return age;
    }
}

class Number {
    int mobileNo;
    public int getNumber(int mobileNo)
    {
        this.mobileNo = mobileNo;
        return mobileNo;
    }
}

// for loosely coupled
// just take interface and implement in two classes