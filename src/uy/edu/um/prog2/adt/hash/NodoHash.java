package uy.edu.um.prog2.adt.hash;

public class NodoHash<K,V>{

    private K key;
    private V data;
    private boolean borrado;

    public NodoHash(K key, V data) {
        this.key = key;
        this.data = data;
        this.borrado = false;
    }

    public K getKey() {
        return key;
    }

    public V getData() {
        return data;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setData(V data) {
        this.data = data;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public boolean equals(Object o) {
        boolean equalsToReturn = false;

        equalsToReturn = ((K) o).equals(this.key);

        return equalsToReturn;
    }
}
