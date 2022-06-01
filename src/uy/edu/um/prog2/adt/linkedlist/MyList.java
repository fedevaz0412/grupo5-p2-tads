package uy.edu.um.prog2.adt.linkedlist;

public interface MyList<T> {
    void add(T value);

    T get(int position);

    boolean contains(T value);

    void removePorValor(T value);

    void removePorPos(int position);

    int size();
}
