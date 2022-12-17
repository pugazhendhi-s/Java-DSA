package com.f5_oops.o4_theory;

public class thisKeyword {
    public static void main(String[] args) {

    }
    /**
     * Here is given the 6 usage of java this keyword.
     *
     * 1. this can be used to refer current class instance variable.
     *      if there is ambiguity btw instance variable and parameter
     * 2. this can be used to invoke current class method (implicitly)
     *
     * 3. this() can be used to invoke current class constructor.
     *      used for constructor chaining
     * 4. this can be passed as an argument in the method call.
     * 5. this can be passed as argument in the constructor call.
     * 6. this can be used to return the current class instance from the method.
     */


    //4.
    void m(thisKeyword obj){
        System.out.println("method is invoked");
    }
    void p(){
        m(this);
    }
}
