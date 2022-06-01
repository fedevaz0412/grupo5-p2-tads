package uy.edu.um.prog2.adt.heap;

import uy.edu.um.prog2.adt.BinarySearchTree.TreeNode;

public class MyHeapImpl<T extends Comparable<T>> implements MyHeap<T>{
    private T[] values;
    private int heapSize;

    public MyHeapImpl(int size) {
        this.values = (T[]) new Comparable[size];//Object
        this.heapSize = 0;
    }

    private int getFatherPosition(int position) {
        return (position - 1) / 2;
    }

    private int getLeftChildPosition(int position) {
        return 2 * position + 1;
    }

    private int getRightChildPosition(int position) {
        return 2 * position + 2;
    }

    private int getMaxChildPos(int position){
        T padre = values[position];
        T leftChild = values[getLeftChildPosition(position)];
        T rightChild = values[getRightChildPosition(position)];
        int maxChildPos = getLeftChildPosition(position);
        if(leftChild != null && rightChild != null) {
            if (leftChild.compareTo(rightChild) < 0) {//leftChild > rightChild
                maxChildPos = getRightChildPosition(position);
            }
        }
        if(leftChild == null && rightChild == null) {//caso en que no tenga ningun hijo
            maxChildPos = -1;
        }

        return maxChildPos;
    }


    @Override
    public void insert(T value) {//heap max
        int position = heapSize;
        heapSize++;
        values[position] = value;

        while (position != 0 && values[position].compareTo(values[getFatherPosition(position)]) > 0) {

            values[position] = values[getFatherPosition(position)];

            values[getFatherPosition(position)] = value;

            position = getFatherPosition(position);
        }
    }

    @Override
    public T delete() throws EmptyHeapException {//remove la raiz del heap
        T valueADevolver = null;
        if (heapSize == 0){
            throw new EmptyHeapException();
        }else if(heapSize == 1) {
            valueADevolver = values[0];
            values[0] = null;
        }else {

            int position = 0;

            valueADevolver = values[0];
            values[0] = values[heapSize-1];//muevo a la raiz el ultimo elemento
            int maxChildPos = getMaxChildPos(position);
            while (maxChildPos != -1 && values[maxChildPos].compareTo(values[position]) > 0) {//mientras que el max child tenga hijos y sea mas grande que su padre
                values[position] = values[maxChildPos];//paso el más grande para arriba
                values[maxChildPos] = values[heapSize-1];//paso el ultimo valor pq toavía no lo borré
                position = maxChildPos;//cambio para que vuelva al while con los valores nuevos
                maxChildPos = getMaxChildPos(position);
            }
            values[heapSize-1] = null;//borro el ultimo elem
        }
        heapSize--;//disminuyo tam del heap

        return valueADevolver;
    }


    @Override
    public int size() {
        return heapSize;
    }

    @Override
    public boolean isEmpty() {
        return heapSize == 0;
    }


    public String toString() {// { ( root ) ( nivel 1 ) ( nivel 2 ) ... }
        String str = "{ ";
        int start = 0;
        int levelSize = 1;
        while (start < heapSize) {
            //imprimir todos los elem del nivel en el que estoy
            str += "( ";
            for (int i = start; i < start + levelSize && i < heapSize; i++) {
                str += (values[i] + " ");
            }
            str += ") ";

            //siguiente nivel
            start += levelSize;
            levelSize *= 2;
        }
        str += "}";
        return str;
    }

}
