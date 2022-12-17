package com.f5_oops.o3_properties.encapsulation;

public class Access {

    public String name;
    protected int age;
    String dob;
    private double salary;

    // only private variables need getter and setters
    public int getAge(){ return age; }
    public String getDob() { return dob; }
    public double getSalary() { return salary; }

    public void setAge(int age) { this.age = age; }
    public void setDob(String dob) { this.dob = dob; }
    public void setSalary(Double salary){
        this.salary = salary;
    }

    /*
    public Encapsulation(String name, int age, String dob, double salary){
        System.out.println("Constructor called");
        this.name = name;
        this.age = age;
        this.dob = dob
        this.salary = salary;
    }*/
}
