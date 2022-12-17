package com.f5_oops.o6_exceptionHandling;

public class Part1 {
    public static void main(String[] args) {
        //basicException();
        //throwException.validate(17);
        UserDefinedMethod();
    }
    static void basicException() {
        try{
            int a = 10/0;
        }
        catch (Exception e){
            System.out.println("Denominator can't be Zero");
        }
        finally {
            System.out.println("Finally");
        }
        System.out.println("Exception handled...");
    }
    static void UserDefinedMethod(){
        System.out.println("This method for Used Defined exception class");
        int age = 10;
        try{
            if(age < 18)
                throw new UserDefinedException();
            else
                System.out.println("Hey....");
        }catch (UserDefinedException e) {
            System.out.println(e);
        }
    }
    static class UserDefinedException extends Exception{
        public UserDefinedException(){
            System.out.println("What the error....");
        }
        public UserDefinedException(String str){
            super(str);
            System.out.println(str);
        }
    }
    static class throwException{
        public static void validate(int age){
            try{
                if(age < 18) {
                    throw new ArithmeticException("Not an Adult, Exception Thrown..!");
                }
                else
                    System.out.println("Adult..........");
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}