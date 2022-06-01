package uy.edu.um.prog2.adt.hash;

import uy.edu.um.prog2.adt.hash.exceptions.KeyNotFound;

public interface HashTable <K, V> {
    void put(K key, V value);
    boolean contains(K key);
    void remove(K key) throws KeyNotFound;
}
