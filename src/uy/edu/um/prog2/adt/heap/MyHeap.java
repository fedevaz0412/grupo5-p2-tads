package uy.edu.um.prog2.adt.heap;

import uy.edu.um.prog2.adt.heap.exceptions.EmptyHeapException;

public interface MyHeap<T extends Comparable<T>> {
    void insert(T value);
    T delete() throws EmptyHeapException;
    int size();
    boolean isEmpty();
    String toString();
}
