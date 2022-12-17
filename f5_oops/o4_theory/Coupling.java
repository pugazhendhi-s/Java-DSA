package com.f5_oops.o4_theory;

public class Coupling {
    // Coupling -> dependency btw two classes, whether they are tightly
    // coupled or loosely coupled
    //  is best to practice(because u can grow your code, it
    // doesn't affect other classes
    public static void main(String[] args) {
        Volume vol = new Volume();
        vol.volume();
        /* this is tight coupling - dependency
        there is a strong interdependency between both the classes.
        If there is any change in Box class then they
        reflect in the result of Class Volume.
         */
        /*
           loose coupling is done by implementing interfaces to both classes
           change in one class doesn't affect other
         */
    }
}
class Volume
{
    public void volume(){
        Box b = new Box(5,5,5);
        System.out.println(b.volume);
    }
}
class Box
{
    public int volume;
    Box(int length, int width, int height)
    {
        this.volume = length * width * height;
    }
}
