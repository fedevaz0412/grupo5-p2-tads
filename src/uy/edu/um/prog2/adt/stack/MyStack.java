package uy.edu.um.prog2.adt.stack;

public interface MyStack<T> {
    T pop () throws EmptyStackException;

    void push(T element);

    T  peek() throws EmptyStackException;

    boolean isEmpty ();

   int size();
}
