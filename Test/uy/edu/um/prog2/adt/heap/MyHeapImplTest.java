package uy.edu.um.prog2.adt.heap;

import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.heap.exceptions.EmptyHeapException;

import static org.junit.jupiter.api.Assertions.*;

class MyHeapImplTest {

    @Test
    void insert() {
        MyHeap<Integer> sut = new MyHeapImpl<>(10);
        sut.insert(8);
        sut.insert(5);
        sut.insert(9);
        sut.insert(7);
        sut.insert(2);
        sut.insert(15);
        assertEquals(6,sut.size());
    }

    @Test
    void delete() {
        MyHeap<Integer> sut = new MyHeapImpl<>(10);
        sut.insert(8);
        sut.insert(5);
        sut.insert(9);
        sut.insert(7);
        sut.insert(2);
        sut.insert(15);
        assertEquals(6,sut.size());

        try {
            assertEquals(15,sut.delete());
        } catch (EmptyHeapException e) {
        }
        assertEquals(5,sut.size());
        assertEquals("{ ( 9 ) ( 7 8 ) ( 5 2 ) }", sut.toString());
        try {
            assertEquals(9,sut.delete());
        } catch (EmptyHeapException e) {
        }
        assertEquals(4,sut.size());
        assertEquals("{ ( 8 ) ( 7 2 ) ( 5 ) }", sut.toString());
        try {
            assertEquals(8,sut.delete());
        } catch (EmptyHeapException e) {
        }
        assertEquals(3,sut.size());
        assertEquals("{ ( 7 ) ( 5 2 ) }", sut.toString());
        try {
            assertEquals(7,sut.delete());
        } catch (EmptyHeapException e) {
        }
        assertEquals(2,sut.size());
        assertEquals("{ ( 5 ) ( 2 ) }", sut.toString());
        try {
            assertEquals(5,sut.delete());
        } catch (EmptyHeapException e) {
        }
        assertEquals(1,sut.size());
        assertEquals("{ ( 2 ) }", sut.toString());
        try {
            assertEquals(2,sut.delete());
        } catch (EmptyHeapException e) {
        }
        assertEquals(0,sut.size());
        assertEquals("{ }", sut.toString());
    }

    @Test
    void size() {
        MyHeap<Integer> sut = new MyHeapImpl<>(10);
        assertEquals(0, sut.size());
        sut.insert(8);
        sut.insert(5);
        sut.insert(9);
        sut.insert(7);
        sut.insert(2);
        sut.insert(15);
        assertEquals(6,sut.size());
    }

    @Test
    void isEmpty() {
        MyHeap<Integer> sut = new MyHeapImpl<>(10);
        assertTrue(sut.isEmpty());
        sut.insert(8);
        assertFalse(sut.isEmpty());
    }

    @Test
    void testToString() {
        MyHeap<Integer> sut = new MyHeapImpl<>(10);
        assertEquals("{ }", sut.toString());
        sut.insert(8);
        sut.insert(5);
        sut.insert(9);
        sut.insert(7);
        sut.insert(2);
        sut.insert(15);
        assertEquals("{ ( 15 ) ( 7 9 ) ( 5 2 8 ) }", sut.toString());
    }
}