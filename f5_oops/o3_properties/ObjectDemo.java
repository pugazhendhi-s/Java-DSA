package com.f5_oops.o3_properties;

public class ObjectDemo {
    int num;
    float gpa;
    @Override
    public String toString() {
        return super.toString();
    }
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    // we will go in details how to create it in hashmap
    @Override
    public int hashCode() {
        // hashcode -> unique representation of an object via number
        // this is not an address, it is random integer value
        // it gives whether the object is same or not
        return super.hashCode();
        // return num; while printing it will print the given number
    }
    public ObjectDemo(int num, float gpa){
        this.num = num;
        this.gpa = gpa;
    }
    @Override
    public boolean equals(Object obj) {
        // this obj of type object class, we need to cast it to object demo
        return this.num == ((ObjectDemo) obj).num;
        //return super.equals(obj);
    }
    public ObjectDemo(){
         super();
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    public static void main(String[] args) {

        ObjectDemo obj1 = new ObjectDemo(32, 56.4f);
        ObjectDemo obj2 = new ObjectDemo(32, 23f);
        System.out.println(obj1.hashCode());
        System.out.println(obj2.hashCode());
        // above both show different hashcode
        obj2 = obj1; // gives same hashcode as obj1.

        // Equals
        ObjectDemo obj3 = new ObjectDemo(12, 43.23f);
        ObjectDemo obj4 = new ObjectDemo(12, 78.45f);
        // == -> check these to objects are pointing to the same object are not
        // equals -> checks value or content
        if(obj3 == obj4)
            System.out.println("obj3 is == to obj4");
        if (obj3.equals(obj4))
            System.out.println("obj3 is equals to obj4");

        // check instance of class

        System.out.println(obj3 instanceof Object);

        // get class

        System.out.println(obj4.getClass());
        // to get data about classes lots of properties
        System.out.println(obj4.getClass().getName());
        System.out.println(obj4.getClass().getClassLoader());
    }

}
