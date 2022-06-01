package uy.edu.um.prog2.adt.queue;

import uy.edu.um.prog2.adt.queue.exceptions.EmptyQueueException;

public interface MyQueue<T> {
    void enqueue(T value);

    T dequeue() throws EmptyQueueException;

    boolean contains(T value);

    int size();

    boolean isEmpty();
}
