package com.f7_linkedList.l3_circular;


public class Main {
    public static void main(String[] args) {

//        CircularSLL<Integer> cll = new CircularSLL<>();
//
//        cll.add(5);
//        cll.add(55);
//        System.out.println(cll);
//
//        cll.addFirst(555);
//        System.out.println(cll);
//
//        cll.add(0, 0);
//        System.out.println(cll);
//
//        cll.add(3, 33);
//        cll.add(5, 66);
//        System.out.println(cll);
//
//        System.out.print(cll.removeFirst()+" <- ");
//        System.out.println(cll);
//
//        System.out.print(cll.remove()+" <- ");
//        System.out.println(cll);
//
//        System.out.println(cll.remove(0));
//        System.out.println(cll);
//        cll.add(12);
//        cll.add(43);
//        System.out.println(cll.remove(2));
//        System.out.println(cll);
//
//        cll.add(92);
//        cll.add(13);
//        System.out.println(cll.remove(Integer.valueOf(13)));
//        System.out.println(cll.remove(Integer.valueOf(5)));
//        System.out.println(cll);
        CircularDLL<Integer> cll = new CircularDLL<>();

        cll.add(5);
        cll.add(55);
        System.out.println(cll);

        cll.addFirst(555);
        System.out.println(cll);

        cll.add(0, 0);
        System.out.println(cll);

        cll.add(3, 33);
        cll.add(5, 66);
        System.out.println(cll);

        cll.addAfter(33, 44);
        cll.addAfter(0, 1);
        cll.addAfter(66, 77);
        System.out.println(cll);

        System.out.print(cll.removeFirst());
        System.out.println(cll);

        System.out.print(cll.remove());
        System.out.println(cll);

        System.out.println(cll.remove(0));
        System.out.println(cll);
        cll.add(12);
        cll.add(43);
        System.out.println(cll.remove(2));
        System.out.println(cll);

        System.out.println(cll.remove(6));
        System.out.println(cll);

        cll.add(92);
        cll.add(13);
        System.out.println(cll);
        System.out.println(cll.remove(Integer.valueOf(13)));
        System.out.println(cll.remove(Integer.valueOf(55)));
        System.out.println(cll.remove(Integer.valueOf(66)));
        System.out.println(cll.remove(Integer.valueOf(555)));
        System.out.println(cll);

        cll.show();

        cll.reverse();
        System.out.println(cll);


//        LinkedList<Integer> list = new LinkedList<>();
//        System.out.println(list.add(23));
    }
}
