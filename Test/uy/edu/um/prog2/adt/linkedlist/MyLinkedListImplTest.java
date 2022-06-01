package uy.edu.um.prog2.adt.linkedlist;

import org.junit.jupiter.api.Test;

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
        list.add(null);

        assertEquals(4,list.get(0));
        assertEquals(5,list.get(1));
        assertEquals(7,list.get(2));
        assertEquals(2,list.get(3));
        assertEquals(4, list.size());
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
    void size() {
        MyList<Integer> list = new MyLinkedListImpl<>();
        list.add(4);
        list.add(5);
        list.add(7);
        list.add(2);

        assertEquals(4,list.size());
    }

    @Test
    void removePorValor() {
        MyList<Integer> list = new MyLinkedListImpl<>();
        list.add(4);
        list.add(5);
        list.add(7);
        list.add(2);
        list.removePorValor(12);//no esta en la lista
        assertEquals(4, list.size());
        list.removePorValor(7);
        assertEquals(3, list.size());
        list.removePorValor(4);
        assertEquals(2, list.size());
        list.removePorValor(2);
        assertEquals(1, list.size());
    }

    @Test
    void removePorPos() {
        MyList<Integer> list = new MyLinkedListImpl<>();
        list.add(4);
        list.add(5);
        list.add(7);
        list.add(2);
        list.removePorPos(1);
        assertEquals(3, list.size());
        assertEquals(4,list.get(0));
        assertEquals(7,list.get(1));
    }
}