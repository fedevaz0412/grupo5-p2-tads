package uy.edu.um.prog2.adt.hash;

import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.hash.exceptions.KeyNotFound;
import uy.edu.um.prog2.adt.hash.exceptions.UnavailableIndex;

import static org.junit.jupiter.api.Assertions.*;

class MyClosedHashImplTest {

    @Test
    void put() {
        MyHash<Integer,String> sut = new MyClosedHashImpl<>();
        assertEquals(0,sut.size());
        try {
            sut.put(1,"primero");
            sut.put(2,"segundo");
            sut.put(3,"tercero");
            sut.put(4,"cuarto");
            sut.put(5,"quinto");

            assertEquals("segundo",sut.get(2));
        } catch (UnavailableIndex e) {
        } catch (KeyNotFound e) {
            e.printStackTrace();
        }
        assertEquals(5,sut.size());

        try {
            sut.put(6,"sexto");
            sut.put(2,"septimo");
            sut.put(17,"octavo");
            sut.put(9,"noveno");
            sut.put(10,"decimo");
            sut.put(15,"hola");//deberia saltar error
            assertEquals("septimo",sut.get(2));
        } catch (UnavailableIndex e) {
            System.out.println("unavailable index");
        } catch (KeyNotFound e) {
            System.out.println("key not found");
        }
        assertEquals(10,sut.size());

    }

    @Test
    void get() {
        MyHash<Integer,String> sut = new MyClosedHashImpl<>();
        assertEquals(0,sut.size());
        try {
            sut.get(5);//cuando no hay nada salta a key not found
        } catch (KeyNotFound e) {
            System.out.println("key not found");
        }
        try {
            sut.put(1,"primero");
            sut.put(2,"segundo");
            sut.put(3,"tercero");
            sut.put(4,"cuarto");
            sut.put(5,"quinto");
        } catch (UnavailableIndex e) {
        }
        try {
            assertEquals("primero", sut.get(1));
            assertEquals("segundo", sut.get(2));
            assertEquals("tercero", sut.get(3));
            assertEquals("cuarto", sut.get(4));
            assertEquals("quinto", sut.get(5));

            sut.get(17);//salta error
        } catch (KeyNotFound e) {
            System.out.println("key not found");
        }
        try {
            sut.put(6,"sexto");
            sut.put(5,"septimo");//se sobreescribe
        } catch (UnavailableIndex e) {
        }
        try {
            assertEquals("sexto", sut.get(6));
            assertEquals("septimo", sut.get(5));
        } catch (KeyNotFound e) {
            System.out.println("key not found");
        }

    }

    @Test
    void getPosition() {

    }

    @Test
    void delete() {
        MyHash<Integer,String> sut = new MyClosedHashImpl<>();
        assertEquals(0,sut.size());
        try {
            sut.put(1,"primero");
            sut.put(2,"segundo");
            sut.put(3,"tercero");
            sut.put(4,"cuarto");
            sut.put(5,"quinto");
        } catch (UnavailableIndex e) {
        }
        assertEquals(5,sut.size());
        try {
            sut.delete(4);
        } catch (KeyNotFound e) {
        }
        assertEquals(4,sut.size());
        try {
            assertEquals(null, sut.get(4));//salta exception
        } catch (KeyNotFound e) {
        }
        try {
            sut.delete(1);
        } catch (KeyNotFound e) {
        }
        assertEquals(3,sut.size());
        try {
            sut.delete(18);//salta a exception pq no existe
        } catch (KeyNotFound e) {
            System.out.println("key not found");
        }
        assertEquals(3,sut.size());

    }

    @Test
    void size() {
        MyHash<Integer,String> sut = new MyClosedHashImpl<>();
        assertEquals(0,sut.size());
        try {
            sut.put(1,"primero");
            sut.put(2,"segundo");
            sut.put(3,"tercero");
            sut.put(4,"cuarto");
            sut.put(5,"quinto");
        } catch (UnavailableIndex e) {
        }
        assertEquals(5,sut.size());
    }

    @Test
    void contains() {
        MyHash<Integer,String> sut = new MyClosedHashImpl<>();
        assertEquals(0,sut.size());
        try {
            sut.put(1,"primero");
            sut.put(2,"segundo");
            sut.put(3,"tercero");
            sut.put(4,"cuarto");
            sut.put(5,"quinto");
        } catch (UnavailableIndex e) {
        }
        assertEquals(5,sut.size());
        assertTrue(sut.contains(1));
        assertFalse(sut.contains(15));
        assertFalse(sut.contains(9));
    }

    @Test
    void getNodo() {
    }
}