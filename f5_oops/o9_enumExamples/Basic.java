package com.f5_oops.o9_enumExamples;

public class Basic {

    enum Week implements A{
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;


        Week() {
            System.out.println("Constructor called for "+ this);
        }

        @Override
        public void hello() {
            System.out.println("Hey !...");
        }

        void love(){
            System.out.println("Mine");
        }
        /* what is enum ?
         * These are enum constants public, static and final
         * since its final you can create child enums
         * type is week
         **/
        /*  why we use enums ?
         *
         * if we know that if we have fixed number of objects
         * Week has only 7 days, so 7 objects only so
         * creating new objects is not needed, so we use enum constants
         */
         /*
         u can't extend (inherit) any class to enums, becoz enums already extends the enum class,
         but we can implement interface to it.
          */
         /* Constructors
             this is not public or protected
             only private or default
             if we allow public or protected, it will lead
             many no of objects, that's its private or default
             we don't want ot create new objects,
             and we can't call constructors explicitly that's
             all constants contructor are called when we create
         */

        // internally : public static final Week MONDAY = new Week();
    }

    public static void main(String[] args) {
        Week week;
        week = Week.WEDNESDAY;
        // iterate
        for (Week day : Week.values()){
            System.out.println(day);
        }
        System.out.println(week);
        // index
        System.out.println(week.ordinal());
        week.hello();

        // value of

        System.out.println(Week.valueOf("MONDAY"));
    }
}
