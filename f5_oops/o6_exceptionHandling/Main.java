package com.f5_oops.o6_exceptionHandling;

public class Main {
    public static void main(String[] args) {

        //throwExcept.divide(5, 0);
        // terminates here after exception

        /** Five keywords in exception
         * 1. try
         * 2. catch
         * 3. finally // only one finally block
         * 4. throw
         * 5. throws
         */

        try {
            String name = "TED";
            if(name.equals("TED")){
                throw new MyException("TED , MyException is called");
            }
        }
        catch (MyException e){
            System.out.println(e.getMessage());
        }

    }

}
