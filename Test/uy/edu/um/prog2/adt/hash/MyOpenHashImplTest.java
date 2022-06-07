package uy.edu.um.prog2.adt.hash;

import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.hash.exceptions.KeyNotFound;

import static org.junit.jupiter.api.Assertions.*;

class MyOpenHashImplTest {

    @Test
    void put() {
        HashTable<Integer, String> sut = new MyOpenHashImpl<>(12);
        sut.put(1,"primero");
        sut.put(2,"segundo");
        sut.put(3,"tercero");
        sut.put(4,"cuarto");
        sut.put(5,"quinto");
        try {
            assertEquals("primero",sut.get(1));
            assertEquals("segundo",sut.get(2));
            assertEquals("tercero",sut.get(3));
            assertEquals("cuarto",sut.get(4));
            assertEquals("quinto",sut.get(5));
            //sut.get(8);//salta a la exception
            sut.get(25);//salta a la exception
        } catch (KeyNotFound e) {
            System.out.println("key not found");
        }

        sut.put(16,"tercero2");//probado con debugger que se arma la linkedlist bien
        sut.put(17,"cuarto2");
        sut.put(18,"quinto2");

    }

    @Test
    void contains() {
        HashTable<Integer, String> sut = new MyOpenHashImpl<>(20);
        sut.put(1,"primero");
        sut.put(2,"segundo");
        sut.put(3,"tercero");
        sut.put(4,"cuarto");
        sut.put(5,"quinto");

        assertTrue(sut.contains(1));
        assertTrue(sut.contains(2));
        assertTrue(sut.contains(3));
        assertTrue(sut.contains(4));
        assertTrue(sut.contains(5));
        assertFalse(sut.contains(7));
        assertFalse(sut.contains(28));

        sut.put(16,"tercero2");
        sut.put(15,"cuarto2");
        sut.put(18,"quinto2");
        assertTrue(sut.contains(16));
        assertTrue(sut.contains(15));
        assertTrue(sut.contains(18));

    }

    @Test
    void get() {
        HashTable<Integer, String> sut = new MyOpenHashImpl<>(20);
        sut.put(1,"primero");
        sut.put(2,"segundo");
        sut.put(3,"tercero");
        sut.put(4,"cuarto");
        sut.put(5,"quinto");
        try {
            assertEquals("primero",sut.get(1));
            assertEquals("segundo",sut.get(2));
            assertEquals("tercero",sut.get(3));
            assertEquals("cuarto",sut.get(4));
            assertEquals("quinto",sut.get(5));
            //sut.get(8);//salta a la exception
            sut.get(25);//salta a la exception
        } catch (KeyNotFound e) {
            System.out.println("key not found");
        }

        sut.put(15,"tercero2");//probado con debugger que se arma la linkedlist
        sut.put(16,"cuarto2");
        sut.put(17,"quinto2");
        try {
            assertEquals("primero",sut.get(1));
            assertEquals("segundo",sut.get(2));
            assertEquals("tercero",sut.get(3));
            assertEquals("cuarto",sut.get(4));
            assertEquals("quinto",sut.get(5));
            assertEquals("tercero2",sut.get(15));
            assertEquals("cuarto2",sut.get(16));
            assertEquals("quinto2",sut.get(17));
        } catch (KeyNotFound e) {
            System.out.println("key not found");
        }
    }

    @Test
    void remove() {
        HashTable<Integer, String> sut = new MyOpenHashImpl<>(12);
        sut.put(1,"primero");
        sut.put(2,"segundo");
        sut.put(3,"tercero");
        sut.put(4,"cuarto");
        sut.put(5,"quinto");

        try {
            sut.remove(2);
        } catch (KeyNotFound e) {
            e.printStackTrace();
        }
        assertFalse(sut.contains(2));//queda bien porque al head==null es como si no hubiera nada

        sut.put(15,"tercero2");
        sut.put(16,"cuarto2");
        sut.put(17,"quinto2");
        sut.put(29,"cuarto3");

        try {
            sut.remove(4);//probado con debugger que borra bien
            sut.remove(17);
            //sut.remove(8);//salta a la exception
            sut.remove(25);//salta a la exception
        } catch (KeyNotFound e) {
            System.out.println("key not found");
        }


    }

    @Test
    void size() {
        HashTable<Integer, String> sut = new MyOpenHashImpl<>(20);
        assertEquals(0, sut.size());
        sut.put(1,"primero");
        sut.put(2,"segundo");
        sut.put(3,"tercero");
        sut.put(4,"cuarto");
        sut.put(5,"quinto");
        assertEquals(5, sut.size());
        try {
            sut.remove(4);
        } catch (KeyNotFound e) {
            //e.printStackTrace();
            System.out.println("key not found");
        }
        assertEquals(4, sut.size());

    }
}