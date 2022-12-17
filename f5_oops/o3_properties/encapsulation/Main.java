package com.f5_oops.o3_properties.encapsulation;

import java.util.Scanner;
/**
 * Encapsulation
 * Provides data hiding
 * Wrapping up of data under a single unit
 * Prevents the data from being accessed by the code outside this shield.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Access obj = new Access();

        System.out.println("Enter Details");
        obj.name = sc.nextLine();
        obj.setAge(Integer.parseInt(sc.nextLine()));
        obj.setDob(sc.nextLine());
        obj.setSalary(sc.nextDouble());

        System.out.println("Output :");
        System.out.println("Name : "+obj.name);
        System.out.println("Age : "+obj.getAge());
        System.out.println("Date of Birth : "+obj.getDob());
        System.out.println("Salary : "+obj.getSalary());
    }
}
