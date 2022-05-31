package uy.edu.um.prog2.adt.linkedlist;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListImplTest {

    @org.junit.jupiter.api.Test
    void add() {
        MyList<Integer> list = new MyLinkedListImpl<>();
        list.add(4);
        list.add(5);
        list.add(7);
        list.add(2);

        assertEquals(4,list.get(0));
    }

    @org.junit.jupiter.api.Test
    void get() {
        MyList<Integer> list = new MyLinkedListImpl<>();
        list.add(4);
        list.add(5);
        list.add(7);
        list.add(2);

        assertEquals(4,list.get(0));
        assertEquals(5,list.get(1));
        assertEquals(7,list.get(2));
        assertEquals(2,list.get(3));
    }

    @org.junit.jupiter.api.Test
    void contains() {
        MyList<Integer> list = new MyLinkedListImpl<>();
        list.add(4);
        list.add(5);
        list.add(7);
        list.add(2);

        assertTrue(list.contains(5));
        assertFalse(list.contains(1));
    }

    @org.junit.jupiter.api.Test
    void remove() {
        MyList<Integer> list = new MyLinkedListImpl<>();
        list.add(4);
        list.add(5);
        list.add(7);
        list.add(2);
        list.remove(12);
        assertEquals(4, list.size());
        list.remove(7);
        assertEquals(3, list.size());
        list.remove(4);
        assertEquals(2, list.size());
        list.remove(2);
        assertEquals(1, list.size());
    }

    @org.junit.jupiter.api.Test
    void size() {
        MyList<Integer> list = new MyLinkedListImpl<>();
        list.add(4);
        list.add(5);
        list.add(7);
        list.add(2);

        assertEquals(4,list.size());
    }
}