package com.f6_generics.customArrayList;

public class Main implements GenericInterface<Integer>{

    public static void main(String[] args) {
        /*CustomArrayList list = new CustomArrayList(2);
        list.add(4);
        list.add(2);
        list.add(1);
        System.out.println(list);

        System.out.println(list.size());
        System.out.println(list.remove());
        list.set(0,5);
        System.out.println(list);
        System.out.println(list.get(0));*/
        CustomGenericArrayList<String> list = new CustomGenericArrayList<>();
        list.add("Love");
        list.add("Li=ust");
        System.out.println(list);

        System.out.println(list.size());
        System.out.println(list.remove());
        list.set(0,"Roby");
        System.out.println(list);
        System.out.println(list.get(0));

        Wildcard<Number> wc = new Wildcard<>();

        // error -> Wildcard<String> c = new Wildcard<>();
        // we are restricted with Number (Class which extends Number are allowed(Integer, Float,.)
    }
}
