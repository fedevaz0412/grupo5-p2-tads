package uy.edu.um.prog2.adt.heap;

public class HeapNode<K extends Comparable<K>, T>{

    public K key;
    public T data;

    public K getKey() {
        return key;
    }
    public void setKey(K key) {
        this.key = key;
    }

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

    public HeapNode(K key, T data){
        this.key = key;
        this.data = data;
    }

}
