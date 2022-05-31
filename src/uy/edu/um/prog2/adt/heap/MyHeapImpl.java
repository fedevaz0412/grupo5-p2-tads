package uy.edu.um.prog2.adt.heap;

import uy.edu.um.prog2.adt.BinarySearchTree.TreeNode;

public class MyHeapImpl<T extends Comparable<T>> implements MyHeap<T>{
    @Override
    public void insert(T value) {

    }

    @Override
    public T delete() throws EmptyHeapException {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public String toString() {
        return null;
    }

    private String toStringRecursivo(int node, int currentLevel){//inOrder
        return null;
    }
}
