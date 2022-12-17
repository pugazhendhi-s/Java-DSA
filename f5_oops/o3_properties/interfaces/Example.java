package com.f5_oops.o3_properties.interfaces;

/* Interface
 * declare constant fields, variables are final static by default
 * declare methods that abstract and public by default.
 * Methods in an interface are declared with an empty body
 * and are inbuilt public and abstract.
 * U can create static, default methods with implementations
 * A class that implements an interface must implement all the methods declared in the interface
 */

import java.util.*;
public class Example {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Love love = new LoveTypes();
        System.out.println("Valentine day : " +LoveTypes.valentinesDay);
        System.out.print("Enter Money : ");
        love.loveMoney(sc.nextInt());
        love.lovePerson();
        Love.loveLust();
    }
}
interface Love {
    String valentinesDay = "February 14";
    static void loveLust(){
        System.out.println("Love one for our beauty and lusty sight");
    }
    void loveMoney(int money);

    default void lovePerson(){
        System.out.println("Love one for our character and beauty...");
    }
}
class LoveTypes implements Love{

    @Override
    public void loveMoney(int money){
        System.out.println("If I need to love someone means they have income upto "+money+" INR/year");
    }

    @Override
    public void lovePerson(){

    }
}

