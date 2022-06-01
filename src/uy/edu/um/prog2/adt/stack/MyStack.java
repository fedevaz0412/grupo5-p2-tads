package uy.edu.um.prog2.adt.stack;

import uy.edu.um.prog2.adt.stack.exceptions.EmptyStackException;

public interface MyStack<T> {
    T pop () throws EmptyStackException;

    void push(T element);

    T  peek() throws EmptyStackException;

    boolean isEmpty ();

   int size();
}
