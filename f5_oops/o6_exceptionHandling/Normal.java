package com.f5_oops.o6_exceptionHandling;

import java.util.*;

public class Normal{
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        level2();
    }
    static void level1(){
        int a = 5;
        int b = 0;

        try {
            int res = a / b;
            System.out.println("try fully executed");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            // e, e.fillStackTrace are print same
            System.out.println(e.fillInStackTrace());
            System.out.println(e);
            e.printStackTrace(); // error print lii=ke normal red color
            // e.printStacktrace always print at end of program
        }
        finally {
            System.out.println("Finally Always execute!..");
        }
        System.out.println("Love");
    }
    static void level2(){
        System.out.println("Enter Numbers");
        int a = sc.nextInt();
        int b = sc.nextInt();
        try {
            int res = a / b;
            System.out.println(res);
        }
        // multiple catch
        // more strict rules come above,
        catch (ArithmeticException | NumberFormatException | InputMismatchException e){
            System.out.println(e.getMessage());
        }
        // we can Must this catch(Exception e) as last, if above exception doesn't
        // catch , this will catch any exception, if u put before any
        // subClass exception, it will catch all exception ,
        // then no make sense catch(Exception), this will cause error
        catch (Exception e){
            // same as e.getMessage();
            System.out.println(e.getLocalizedMessage());
            // u can do like this System.out.println("normal Exception");
        }
        finally {
            System.out.println("Love");
        }
    }
}
