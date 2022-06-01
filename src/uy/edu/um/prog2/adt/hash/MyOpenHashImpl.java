package uy.edu.um.prog2.adt.hash;

import uy.edu.um.prog2.adt.hash.exceptions.KeyNotFound;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;

public class MyOpenHashImpl <K extends Comparable<K>,V> implements HashTable<K,V> {

    private MyLinkedListImpl<NodoHash<K,V>>[] array;  //Hash Table

    private int capacity;  //cantidad maxima

    public int getCapacity() { return capacity;}

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


    public MyOpenHashImpl(int capacity) {
        this.capacity = capacity;
        this.array = new MyLinkedListImpl[capacity];
    }


    private int getHashIndex(K key){  //genera un index a partir del hash code para insertar

        int codigoHash = key.hashCode();

        int index = codigoHash % array.length;

        if(index < 0){
            return index * (-1);
        }
        else{
            return index;
        }
    }

    @Override
    public void put(K key, V value) {
        int index = getHashIndex(key);

        MyLinkedListImpl<NodoHash<K,V>> posicionLista = array[index];
        //si no existe una lista en posicion
        if(posicionLista == null){
            MyLinkedListImpl<NodoHash<K,V>> nuevaLista = new MyLinkedListImpl<>();
            nuevaLista.add(new NodoHash<>(key,value));
            array[index] = nuevaLista;
        }
        else {
            posicionLista.add(new NodoHash<>(key, value));  //no tiene en cuenta objetos identicos
        }
    }

    @Override
    public boolean contains(K key) {
        int position = getHashIndex(key);
        boolean encontre = false;

        MyLinkedListImpl<NodoHash<K,V>> listaActual = array[position];

        if(listaActual != null){  //buscamos si existe la key
            for(int i = 0; i < listaActual.size(); i++){
                if(listaActual.get(i).equals(key)){
                    encontre = true;
                    break;
                }
            }
        }
        return encontre;
    }

    @Override
    public V get(K key){
        int position = getHashIndex(key);
        V returnData = null;

        MyLinkedListImpl<NodoHash<K,V>> listaActual = array[position];

        if(listaActual != null){
            for(int i = 0; i < listaActual.size(); i++){
                if(listaActual.get(i).equals(key)){
                    returnData = listaActual.get(i).getData();
                    break;
                }
            }
        }
        return returnData;
    }

    public MyLinkedListImpl<NodoHash<K,V>> getList(K key){//necesario?
        int postion = getHashIndex(key);
        return array[postion];
    }

    public MyLinkedListImpl<NodoHash<K,V>> getList(int i){//necesario?
        return array[i];
    }

    @Override
    public void remove(K key) throws KeyNotFound {
        int index = getHashIndex(key);

        MyLinkedListImpl<NodoHash<K,V>> listaActual = array[index];

        int lugar = 0;
        boolean encontre = false;

        if(listaActual != null){
            for(int i = 0; i < listaActual.size(); i++){
                lugar++;
                if(listaActual.get(i).equals(key)){
                    encontre = true;
                    break;
                }
            }
        }

        if (!encontre){
            throw new KeyNotFound();
        }
        else {
            listaActual.removePorPos(lugar);
        }
    }
}
