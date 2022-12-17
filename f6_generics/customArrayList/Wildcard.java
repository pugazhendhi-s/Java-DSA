package com.f6_generics.customArrayList;

import java.util.Arrays;
import java.util.List;

public class Wildcard<T extends Number> {
    private Object[] data;
    private static final int DEFAULT_SIZE = 10;
    private static int size = 0;

    public Wildcard(){
        this.data = new Object[DEFAULT_SIZE];
    }
    public Wildcard(int size){
        this.data = new Object[size];
    }
    // example for wildcard
    private void getList(List<? extends Number> list){ // class which extend numbers will be allowed
        // if you declared like List<Number> , then u will only be allowed to pass Number
        // List<? extends Number> list , here you can pass Subclass of Number (Integer, Float, Double..)
    }
    public void add(T value){
        if(isFull()){
            resize();
        }
        data[size++] = value;
    }
    private boolean isFull() { return size == data.length; }
    private void resize() {
        Object[] temp = new Object[size * 2];
        System.arraycopy(data, 0, temp, 0, data.length);
        data = temp;
    }
    public T remove(){
        return (T)data[--size];
    }
    public void set(int index, T value){
        data[index] = value;
    }
    public T get(int index) { return (T)data[index]; }

    public int size(){ return size;}

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}
