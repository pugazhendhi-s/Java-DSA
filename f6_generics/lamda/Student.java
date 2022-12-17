package com.f6_generics.lamda;

public class Student implements Comparable<Student>{
    public int rollNo;
    public float marks;

    public Student(int rollNo, float marks) {
        this.rollNo = rollNo;
        this.marks = marks;
    }

    @Override
    public int compareTo(Student o) {
        // if it returns (-ve) means value is lesser, 0 means equal, +ve greater
        return (int) (this.marks - o.marks);
        /*Arrays.sort(array, new Comparator<Student>() {
            @Override
            public int compareTo(Student o1, Student o2){
                return (int)o1.marks - o2.marks;
            }
        });*/
    }


    @Override
    public String toString() {
        return marks+"";
    }
}
