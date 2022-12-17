package com.f5_oops.o8_garbageCollector;

public class Main {
    public static void main(String[] args) {
        Student student = new Student("Love");
        System.out.println(student);

    }
    /**Garbage Collector
     * u can't directly destroy the object, but we can tell in finalize ,method what
     * to do when object is gonna destroyed by garbage collector.
     *
     * Student student;
     * for (int i = 0; i < 1000000; i++) {
     * student = new Student("Random name");
     * }
     *
     * whenever certain memory reached garbage collector destroys the object are not used
     * for less object garbage doesn't do anything.
     */

    /**Final KeyWord
     * Student student = new Student("Gazump");
     * student.name = "new name";
     * student = new Student("new object"); // we
     *
     * final Student ted = new Student("GREEDY");
     * ted.name = "new Name";
     * Error => ted = new Student("new Object"); // we can't assign new object, it writes only
     *
     * //
     */
}
