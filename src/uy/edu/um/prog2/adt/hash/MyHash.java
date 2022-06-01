package uy.edu.um.prog2.adt.hash;

import uy.edu.um.prog2.adt.hash.exceptions.KeyNotFound;
import uy.edu.um.prog2.adt.hash.exceptions.UnavailableIndex;

public interface MyHash <K, V> {

    void put(K key, V value) throws UnavailableIndex;

    V get(K key) throws KeyNotFound;

    void delete(K key) throws KeyNotFound;

    int size();

    boolean contains(K key);
}
