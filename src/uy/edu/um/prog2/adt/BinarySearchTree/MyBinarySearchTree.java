package uy.edu.um.prog2.adt.BinarySearchTree;

public interface MyBinarySearchTree<K extends Comparable<K>, T> {
    T find(K key);
    void insert (K key, T data);
    void delete (K key);
    boolean exists(K key);
    int size();
    String toString();
}
