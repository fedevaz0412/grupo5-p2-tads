package uy.edu.um.prog2.adt.hash;

import uy.edu.um.prog2.adt.arraylist.ArrayList;
import uy.edu.um.prog2.adt.arraylist.ListaArray;
import uy.edu.um.prog2.adt.hash.exceptions.KeyNotFound;
import uy.edu.um.prog2.adt.hash.exceptions.UnavailableIndex;

public class MyClosedHashImpl <K extends Comparable<K>, V> implements MyHash<K, V> {

    private static final int DEFAULT_INITIAL_TABLE_HASH_SIZE = 10;


    private NodoHash<K,V>[] tableHash;

    private int size;

    private ListaArray<K> arraylistKeys = new ArrayList<>();

    public ListaArray<K> getArraylistKeys() { return arraylistKeys; }

    public MyClosedHashImpl() {
        this.tableHash = new NodoHash[DEFAULT_INITIAL_TABLE_HASH_SIZE];//array de nodos de tam definido
    }
    public MyClosedHashImpl(int tableHashSize) {
        this.tableHash = new NodoHash[tableHashSize];
        this.arraylistKeys = new ArrayList<>(tableHashSize);
    }



    private int colisionIndex(int index){ //Devuelve lugar a intentar insertar, de manera cuadratica
        int temp = 0;
        temp = index*index;
        return temp;
    }

    private int tryCollision(K key, int intento){ // intenta insertar
        int index = (key.hashCode() + colisionIndex(intento)) % tableHash.length;
        if(index < 0){//si da negativo, hacerlo positivo
            index = index*(-1);
        }
        return index;
    }


    @Override
    public void put(K key, V value) throws UnavailableIndex {
        int intento = 0;
        int index = tryCollision(key,intento);

        NodoHash<K,V> nuevoNodo = new NodoHash<>(key,value);

        while (tableHash[index] != null && //el espacio este ocupado
                !(intento > tableHash.length) && //esté dentro del hash
                !tableHash[index].isBorrado() && // no este el nodo borrado ?
                !tableHash[index].getKey().equals(key)){//key del nodo sea diferente a la que quiero poner
            intento++;
            index = tryCollision(key,intento);
        }

        if(intento > tableHash.length){ // No hay lugar disponible
            throw new UnavailableIndex();
        }
        if(tableHash[index] == null || tableHash[index].isBorrado()){ //Si hay un nodo borrado o hay lugar se inserta
            tableHash[index] = nuevoNodo;
            size++;
            arraylistKeys.addLast(key);
        } else{ //Ya existe uno y se actualiza el valor
            tableHash[index].setData(value);
        }

    }

    @Override
    public V get(K key) throws KeyNotFound {
        int intento = 0;
        V returnData = null;

        int index = tryCollision(key,intento);

        while(tableHash[index] != null &&
                !tableHash[index].getKey().equals(key) &&
                !(intento > tableHash.length)){
            intento += 1;
            index = tryCollision(key,intento);
        }
        //encontrado
        if(tableHash[index] != null &&
                !(intento > tableHash.length) &&
                tableHash[index].getKey().equals(key) &&
                !tableHash[index].isBorrado()){
            returnData = tableHash[index].getData();
        }
        //no encontrado
        if (intento >= tableHash.length) {
            throw new KeyNotFound();
        }

        return returnData;

    }

    public V getPosition(int index){//getData ? es necesario?
        V dataReturn = null;
        if(tableHash[index] != null && !tableHash[index].isBorrado()){//si no esta vacio ni borrado
            dataReturn = tableHash[index].getData();
        }
        return dataReturn;
    }

    @Override
    public void delete(K key) throws KeyNotFound {//falta ver el caso que pasen una key que no existe
        int intento = 0;
        int index = tryCollision(key,intento);

        while(tableHash[index] != null &&
                !(intento > tableHash.length) &&
                !tableHash[index].getKey().equals(key)){
            intento++;
            index = tryCollision(key,intento);
        }


        //encontrado
        if(tableHash[index] != null &&
                tableHash[index].getKey().equals(key) &&
                !tableHash[index].isBorrado() &&
                !(intento > tableHash.length)){
            tableHash[index].setBorrado(true);
        }

        //no encontrado
        if (intento >= tableHash.length || tableHash[index] == null) {
            throw new KeyNotFound();
        }

        size--;
        arraylistKeys.removeElement(key);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(K key) {

        boolean encontre = false;
        int intento = 0;
        int index = tryCollision(key,intento);

        while(tableHash[index] != null && //el espacio este ocupado
                !(intento > tableHash.length) && //esta dentro del hash
                !tableHash[index].getKey().equals(key)){//key del nodo sea diferente a la que quiero poner
            intento++;
            index = tryCollision(key,intento);
        }

        //encontrado
        if(tableHash[index] != null &&//si tiene contenido
                tableHash[index].getKey().equals(key) && //key es igual a la que quiero
                !tableHash[index].isBorrado() && //no esta borrado
                !(intento > tableHash.length)){ //esta dentro del hash
            encontre = true;
        }else if (tableHash[index] == null ||//si no tiene contenido
                tableHash[index].isBorrado() ||//o si esta borrado
                intento > tableHash.length){//o si no esta en el rango de hash
            encontre = false;
        }

        return encontre;

    }

    public  NodoHash<K,V> getNodo(int i){
        return tableHash[i];
    } //es necesario?
}
