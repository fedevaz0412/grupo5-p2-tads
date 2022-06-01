package uy.edu.um.prog2.adt.hash;

import org.junit.jupiter.api.Test;

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
        } catch (UnavailableIndex e) {
        }
        assertEquals(5,sut.size());

        try {
            sut.put(6,"sexto");
            sut.put(2,"septimo");

        } catch (UnavailableIndex e) {
        }
        assertEquals(7,sut.size());

    }

    @Test
    void get() {
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
        try {
            assertEquals("segundo", sut.get(2));
            assertEquals("cuarto", sut.get(4));
            assertEquals("primero", sut.get(1));
        } catch (KeyNotFound e) {
        }
        try {
            sut.put(6, "sexto");
            sut.put(2, "septimo");//se sobreescribe en 2 a septimo
        }catch (UnavailableIndex e){

        }
        try {
            assertEquals("septimo", sut.get(2));
            assertEquals("sexto", sut.get(6));
            assertEquals("primero", sut.get(1));
            assertEquals(null, sut.get(9));
            assertEquals(null, sut.get(15));//esto da bien pero debería saltar error pq no hay index 15 en este hash
            sut.get(15);//tendría que saltar el error de key not found ?
        } catch (KeyNotFound e) {
            e.printStackTrace();
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
            assertEquals(null, sut.get(4));
        } catch (KeyNotFound e) {
        }

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