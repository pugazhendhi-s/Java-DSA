package com.f5_oops.o2_static1;

// outside class can't be static, while inner class must be static
public class InnerClass { // outer class

    // static stuffs are resolved during compile time, becoz it doesn't depend on object
    static class Test{ // internally everything will be static
        // if not static means, in main method it needs an object to
        // access this class. inner class don't have any object, so it must be static
        // inner class must be depended on outer class , so static gives inner depends on outer class
        String name;
        public Test(String name){
            this.name = name;
        }

        @Override // custom to String, while executed when sout -> println
        public String toString() {
            return name;
        }
        /* this is java default to String
        public String toString() {
            return getClass().getName() + "@" + Integer.toHexString(hashCode());
        }*/
    }

    public static void main(String[] args) {
        Test a = new Test("Love");
        Test b = new Test("Lust");
        // Test doesn't depend on objects of InnerClass,
        // it has its own instance , becoz main and Test are static,
        // so it has some instance

        System.out.println(a.name);
        System.out.println(b.name);

        System.out.println(a);
        // basically it will call the toString method of us, else if we don't create toString method , it will execute of
        // its own toString method
    }
}