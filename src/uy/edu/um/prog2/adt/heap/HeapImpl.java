package uy.edu.um.prog2.adt.heap;

import uy.edu.um.prog2.adt.heap.exceptions.EmptyHeapException;
import uy.edu.um.prog2.adt.heap.exceptions.FullHeap;

public class HeapImpl<K extends Comparable<K>, T>{
    private HeapNode<K, T>[] Heap;
    private int maxSize;
    private int size;

    public HeapImpl(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
        Heap = new HeapNode[this.maxSize + 1];

    }

    private int padre(int i) { //posicion padre
        return (i - 1) / 2;
    }

    private int hijoIzq(int i) { // posicion hijo izq
        return (2 * i) + 1;
    }

    private int hijoDer(int i) {  // posicion hijo Der
        return (2 * i) + 2;
    }

    private boolean esHoja(int i) {
        return i <= size && i >= size / 2;
    }

    private void cambio(int posicion1, int posicion2) { // cambiar nodos

        HeapNode variable = Heap[posicion1];
        Heap[posicion1] = Heap[posicion2];
        Heap[posicion2] = variable;
    }

    public void insertMaxHeap(K key, T data) throws FullHeap {  //insertar en heap max

        HeapNode<K,T> nuevoNodo = new HeapNode<>(key,data);

        if(size >= maxSize){
            throw new FullHeap();
        }

        int position = size;
        size++;
        Heap[position] = nuevoNodo;

        while(position != 0 && Heap[position].getKey().compareTo(Heap[padre(position)].getKey()) > 0){
            Heap[position] = Heap[padre(position)];
            Heap[padre(position)] = nuevoNodo;

            position = padre(position);
        }
    }

    public int maxPosition(int position1, int position2) {
        int valueToReturn = position1;
        if (Heap[position1] != null && Heap[position2] != null) {
            if (Heap[position2].getKey().compareTo(Heap[position1].getKey()) > 0) {
                valueToReturn = position2;
            }
        }
        if (Heap[position1] == null && Heap[position2] == null) {
            valueToReturn = -1;
        }
        // Controlar posiciones fueras del arbol
        return valueToReturn;
    }

    public int minPosition(int position1, int position2) {
        int valueToReturn = position1;
        if (Heap[position1] != null && Heap[position2] != null) {
            if (Heap[position2].getKey().compareTo(Heap[position1].getKey()) < 0) {
                valueToReturn = position2;
            }
        }
        if (Heap[position1] == null && Heap[position2] == null) {
            valueToReturn = -1;
        }
        return valueToReturn;
    }

    public HeapNode<K,T> delete() throws EmptyHeapException {
        HeapNode<K,T> valueToReturn = null;

        if (size == 0) {
            throw new EmptyHeapException();
        }
        valueToReturn = Heap[0];

        if (size == 1) {
            Heap[0] = null;
        } else {
            int position = 0;
            Heap[position] = Heap[size - 1];

            int childMaxPosition = maxPosition(hijoIzq(position), hijoDer(position));
            while (childMaxPosition != -1 && Heap[childMaxPosition].getKey().compareTo(Heap[position].getKey()) > 0) {
                HeapNode<K,T> temp = Heap[position];
                Heap[position] = Heap[childMaxPosition];
                Heap[childMaxPosition] = temp;
                position = childMaxPosition;
                childMaxPosition = maxPosition(hijoIzq(position), hijoDer(position));
            }
            Heap[size - 1] = null;
        }

        size--;

        return valueToReturn;
    }

    public HeapNode<K,T> getMax(){  //extraer maximo del heap
        HeapNode max = null;

        if (size != 0){
            max = Heap[0];
        }

        return max;
    }

    public void vizualizar(){
        for(int i =1; i <= (size/2); i++){
            System.out.println("Padre :"+Heap[i] + "Hijo Izquierdo :" + Heap[(2*i)+1] + "Hijo Derecho :" + Heap[(2*i) + 2]);
            System.out.println();
        }
    }

    public int getSize(){
        return size;
    }
}
