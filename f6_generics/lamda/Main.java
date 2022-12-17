package com.f6_generics.lamda;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Student greedy = new Student(6, 88.99f);
        Student ted = new Student(2, 78.23f);
        Student light = new Student(1, 71.98f);
        Student misa = new Student(3, 92.98f);
        Student kira = new Student(9, 99.98f);

        Student[] list  = {greedy, ted, light, misa, kira};
        System.out.println(Arrays.toString(list));

//        Arrays.sort(list, new Comparator<Student>() {
//            @Override
//            public int compare(Student o1, Student o2) {
//                return -(int)(o1.marks - o2.marks); // negative sign sort in descending order, this is same compareTo method
//            }
//        });
        // both sort are same , this is lambda expression
        Arrays.sort(list, (o1, o2) -> -(int)(o1.marks - o2.marks));

        System.out.println(Arrays.toString(list));

        if(greedy.compareTo(ted) > 0) {
            System.out.println("Greedy score more marks");
            System.out.println(greedy.compareTo(ted));
        }
    }
}
