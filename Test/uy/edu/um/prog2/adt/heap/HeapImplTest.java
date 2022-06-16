package uy.edu.um.prog2.adt.heap;

import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.heap.exceptions.EmptyHeapException;
import uy.edu.um.prog2.adt.heap.exceptions.FullHeap;

import static org.junit.jupiter.api.Assertions.*;

class HeapImplTest {

    @Test
    void insertMaxHeap() {
        HeapImpl<Integer, String> sut = new HeapImpl<>(10);
        try {
            sut.insertMaxHeap(1,"primero");
            sut.insertMaxHeap(2,"segundo");
            sut.insertMaxHeap(3,"tercero");
            sut.insertMaxHeap(4,"cuarto");
        } catch (FullHeap e) {
            e.printStackTrace();
        }
        assertEquals(4,sut.getSize());
    }

    @Test
    void delete() {
        HeapImpl<Integer, String> sut = new HeapImpl<>(10);
        try {
            sut.insertMaxHeap(1,"primero");
            sut.insertMaxHeap(2,"segundo");
            sut.insertMaxHeap(3,"tercero");
            sut.insertMaxHeap(4,"cuarto");
        } catch (FullHeap e) {
            e.printStackTrace();
        }
        try {
            HeapNode<Integer,String> h = sut.delete();
            assertEquals("cuarto", h.getData());
            assertEquals(4, h.getKey());
        } catch (EmptyHeapException e) {
            e.printStackTrace();
        }
        assertEquals(3,sut.getSize());
    }

    @Test
    void getMax() {
        HeapImpl<Integer, String> sut = new HeapImpl<>(10);
        try {
            sut.insertMaxHeap(1,"primero");
            sut.insertMaxHeap(2,"segundo");
            sut.insertMaxHeap(3,"tercero");
            sut.insertMaxHeap(4,"cuarto");
        } catch (FullHeap e) {
            e.printStackTrace();
        }
        HeapNode<Integer,String> h = sut.getMax();
        assertEquals("cuarto", h.getData());
        assertEquals(4, h.getKey());

    }

    @Test
    void getSize() {
        HeapImpl<Integer, String> sut = new HeapImpl<>(10);
        try {
            sut.insertMaxHeap(1,"primero");
            sut.insertMaxHeap(2,"segundo");
            sut.insertMaxHeap(3,"tercero");
            sut.insertMaxHeap(4,"cuarto");
        } catch (FullHeap e) {
            e.printStackTrace();
        }
        assertEquals(4,sut.getSize());
        try {
            HeapNode<Integer,String> h = sut.delete();
            assertEquals("cuarto", h.getData());
            assertEquals(4, h.getKey());
            assertEquals(3,sut.getSize());
            sut.delete();
            sut.delete();
            assertEquals(1,sut.getSize());
            sut.delete();
            assertEquals(0,sut.getSize());
        } catch (EmptyHeapException e) {
            e.printStackTrace();
        }


    }
}