package com.f5_oops.o3_properties.polymorphism;

public class ObjectPrint {

    int num;
    public ObjectPrint(int num){
        this.num = num;
    }

    @Override // if I didn't declare this Object.toString method executes
    public String toString() {
        // this is overriding Object.toString method in Object Class
        // ObjectPrint{num=54}
        return "ObjectPrint{" +
                "num=" + num +
                '}';
    }
    /* InBuilt Java toString for println
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }
     */

    public static void main(String[] args) {
        ObjectPrint obj = new ObjectPrint(54);
        System.out.println(obj);
        // here I didn't tell java , what to do print
        // so java automatically calls Object.toString
        // and print the path of object
    }

}
