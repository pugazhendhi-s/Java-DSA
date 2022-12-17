package com.f5_oops.o7_objectCloning;

public class Human implements Cloneable{

    public String name;
    public int age;

    int[] arr;

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
        this.arr = new int[]{3,4,5,6,9,1};
    }
/*
    @Override // override Object class
    public Object clone() throws CloneNotSupportedException {
        // this is shallow copy
        return super.clone();
    }
*/
@Override // override Object class
    public Object clone() throws CloneNotSupportedException {
        // this is deep copy
        Human twin = (Human) super.clone(); // this is actually shallow

        // make a deep copy
        twin.arr = new int[twin.arr.length];
        System.arraycopy(this.arr, 0, twin.arr, 0, twin.arr.length);
        /*
        for (int i = 0; i < twin.arr.length; i++) {
            twin.arr[i] = this.arr[i];
        }
         */
        return twin;
    }

    // CloneNotSupportedException
    //  - becoz if we didn't implement interface, this exception wil throw
    /*
    @Override
    public Human clone() {
        try {
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return (Human) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }*/
}
