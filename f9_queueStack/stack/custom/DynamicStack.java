package com.f9_queueStack.stack.custom;

import com.f9_queueStack.stack.custom.CustomStack;

import java.util.Arrays;

public class DynamicStack extends CustomStack {
    public DynamicStack(){
        super(); // call custom stack
    }
    public DynamicStack(int size){
        super();
    }
    @Override
    public boolean push(int item) {
        if(this.isFull()){
            int[] temp = new int[data.length * 2];
            System.arraycopy(data, 0, temp, 0, data.length);
            /*
            for (int i = 0; i < data.length; i++) {
                temp[i] = data[i];
            }
            data = temp;
             */
            data = temp;
        }
        return super.push(item);
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}
