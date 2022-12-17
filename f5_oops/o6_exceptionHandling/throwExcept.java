package com.f5_oops.o6_exceptionHandling;

public class throwExcept {
    public static void main(String[] args) {
        int a = 10;
        int b = 0;
        try {
            divide(a, b);
        }
        catch (ArithmeticException e){
            // if u pass catch(Exception e), this also execute what we have thrown
            System.out.println(e.getMessage());
        }
        // if error occurred try block stop and catch block start execute
        // and rest all execute after catch block
        finally {
            System.out.println("Finally always executed...");
        }
        System.out.println("End of Program");
    }

    // throws -> to declare an exception
    // throw -> to throw an exception explicitly
    public static int divide(int a, int b) throws ArithmeticException{
        if(b == 0){
            throw new ArithmeticException("please do not divide by 0");
            //throw new ArithmeticException();
            //this will throw null, when u explicitly throw exception
            // u need to give String to exception
        }
        return a / b;
    }
}
