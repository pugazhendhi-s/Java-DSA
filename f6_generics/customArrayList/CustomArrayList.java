package com.f6_generics.customArrayList;

import java.util.Arrays;

public class CustomArrayList {

    private int[] data;
    private static final int DEFAULT_SIZE = 10;
    private static int size = 0;

    public CustomArrayList(){
        this.data = new int[DEFAULT_SIZE];
    }
    public CustomArrayList(int size){
        this.data = new int[size];
    }

    public void add(int value){
        if(isFull()){
            resize();
        }
        data[size++] = value;
    }
    private boolean isFull() { return size == data.length; }
    private void resize() {
        int[] temp = new int[size * 2];
        System.arraycopy(data, 0, temp, 0, data.length);
        data = temp;
    }
    public int remove(){
        return data[--size];
    }
    public void set(int index, int value){
        data[index] = value;
    }
    public int get(int index) { return data[index]; }

    public int size(){ return size;}

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}
