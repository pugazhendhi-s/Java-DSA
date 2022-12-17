package com.f6_generics.lamda;

import java.util.ArrayList;
import java.util.function.Consumer;

public class LambdaFunctions {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            arr.add(i + 1);
        }
        arr.forEach((item) -> System.out.print(item+" "));
        // this also same
        Consumer<Integer> consumer = (item) -> System.out.print(item+" ");
        arr.forEach(consumer);

        // reference of lambda expression is only of type interface
        // e.g. Consumer interface, Operation(own)
        // both perform same
        Operation sum = (a, b) -> a + b;
        //Operation sum1 = Integer::sum;
        Operation sub = (a, b) -> a - b;
        Operation mul = (a, b) -> a * b;
        Operation div = (a, b) -> a / b;
        // (a, b) -> a / b; this is  body of interface method operation

        // inline
        System.out.println(sum.operation(4,5));

        // by method
        int val = operate(5, 3, sum);
        int prod = operate(5,3, mul);
        System.out.println(val + " "+ prod);

    }
    private static int operate(int a, int b, Operation op){
        return op.operation(a, b);
    }
}
interface Operation{
    int operation(int a, int b);
    // error -> int love(int c, int e);
    // if you want to perform lambda expression with this interface
    // you can only one method in interface
}