package uy.edu.um.prog2.adt.BinarySearchTree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyBSTImplTest {

    @Test
    void find() {
        MyBinarySearchTree<Integer, String> sut = new MyBSTImpl<Integer, String>();
        sut.insert(100, "data100");
        sut.insert(80, "data80");
        sut.insert(50, "data50");
        sut.insert(75, "data75");

        assertEquals("data100", sut.find(100));
        assertEquals("data80", sut.find(80));
        assertEquals("data50", sut.find(50));
        assertEquals("data75", sut.find(75));

        sut.delete(80);
        assertEquals("data100", sut.find(100));
        assertEquals("data50", sut.find(50));
        assertEquals("data75", sut.find(75));
        sut.delete(100);
        assertEquals("data50", sut.find(50));
        assertEquals("data75", sut.find(75));
        sut.delete(75);
        assertEquals("data50", sut.find(50));
    }
    //comparo siempre las keys ( key: cedula, data: Presona) K key, T data
    @Test
    void insert() {
        MyBinarySearchTree<Integer, String> sut = new MyBSTImpl<Integer, String>();
        assertEquals(0, sut.size());
        sut.insert(100, "data100");
        System.out.println(sut.toString());
        sut.insert(80, "data80");
        System.out.println(sut.toString());
        sut.insert(50, "data50");
        System.out.println(sut.toString());
        sut.insert(75, "data75");
        System.out.println(sut.toString());
        assertEquals(4, sut.size());
        assertEquals(" , 50,  , 75,  , 80,  , 100,  ", sut.toString());
    }

    @Test
    void delete() {
        MyBinarySearchTree<Integer, String> sut = new MyBSTImpl<Integer, String>();
        sut.insert(100, "data100");
        sut.insert(80, "data80");
        sut.insert(50, "data50");
        sut.insert(75, "data75");

        sut.delete(80);

        assertEquals(3, sut.size());
        assertEquals(" , 50,  , 75,  , 100,  ", sut.toString());
    }

    @Test
    void exists() {
        MyBinarySearchTree<Integer, String> sut = new MyBSTImpl<Integer, String>();
        sut.insert(100, "data100");
        sut.insert(80, "data80");
        sut.insert(50, "data50");
        sut.insert(75, "data75");

        assertTrue(sut.exists(80));
        assertFalse(sut.exists(12));
    }

    @Test
    void size() {//probado en insert y delete
    }

    @Test
    void testToString() {//probado en insert y delete
    }
}