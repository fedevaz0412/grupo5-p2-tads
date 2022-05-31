package uy.edu.um.prog2.adt.hash;

public class MyClosedHashImpl <K extends Comparable<K>, V> implements MyHash<K, V> {

    private static final int DEFAULT_INITIAL_TABLE_HASH_SIZE = 10;


    private NodoHash<K,V>[] tableHash;

    private int size;


    //CONSTRUCTORS
    public MyClosedHashImpl() {
        tableHash = new NodoHash[DEFAULT_INITIAL_TABLE_HASH_SIZE];
    }
    public MyClosedHashImpl(int tableHashSize) {
        tableHash = new NodoHash[tableHashSize];
    }


    //IMPL

    private int colisionIndex(int index){ //Devuelve lugar a intentar insertar, de manera cuadratica
        int temp = 0;


        temp = index^2;


        return temp;
    }

    private int tryCollision(K key, int intento){ // intenta insertar
        int index = (key.hashCode() + colisionIndex(intento)) % tableHash.length;
        if(index < 0){
            index = index*(-1);
        }
        return index;
    }


    @Override
    public void put(K key, V value) throws UnavailableIndex {
        int intento = 0;
        int index = tryCollision(key,intento);

        NodoHash<K,V> nuevoNodo = new NodoHash<>(key,value);

        //Mintras el espacio este ocupado, se mantenga dentro del rango del hash, no este el nodo borrado o que la key del nodo sea diferente intentamos de vuelta
        while (tableHash[index] != null && !(intento > tableHash.length) && !tableHash[index].isBorrado() && !tableHash[index].getKey().equals(key)){
            intento++;
            index = tryCollision(key,intento);
        }

        if(intento > tableHash.length){ // No hay lugar disponible
            throw new UnavailableIndex();
        }

        if(tableHash[index] == null || tableHash[index].isBorrado()){ //Si borraron un nodo o hay lugar insertamos
            tableHash[index] = nuevoNodo;
        }
        else{ //Ya existe uno y se actualiza el valor
            tableHash[index].setData(value);
        }

        size++;

    }

    @Override
    public V get(K key) throws KeyNotFound {

        int intento = 0;

        V returnData = null;

        int index = tryCollision(key,intento);

        //Minetras no encontremos, repetimos la busqueda cuadraticamente
        while(tableHash[index] != null && !tableHash[index].getKey().equals(key) && !(intento > tableHash.length)){
            intento += 1;
            index = tryCollision(key,intento);
        }

        //Encontramos
        if(tableHash[index] != null &&  !(intento > tableHash.length) && tableHash[index].getKey().equals(key) && !tableHash[index].isBorrado()){
            returnData = tableHash[index].getData();
        }

        //No encontramos
        if(intento >= tableHash.length){
            throw new KeyNotFound();
        }

        return returnData;

    }

    public V getPosition(int index){
        V dataReturn = null;
        if(tableHash[index] != null && !tableHash[index].isBorrado()){
            dataReturn = tableHash[index].getData();
        }
        return dataReturn;
    }

    @Override
    public void delete(K key) throws KeyNotFound {
        int intento = 0;
        int index = tryCollision(key,intento);

        //Minetras no encontremos, repetimos la busqueda cuadraticamente
        while(!(intento > tableHash.length) && !tableHash[index].getKey().equals(key) && tableHash[index] != null){
            intento++;
            index = tryCollision(key,intento);
        }

        //Encontramos
        if(tableHash[index].getKey().equals(key) && !tableHash[index].isBorrado() && !(intento > tableHash.length)){
            tableHash[index].setBorrado(true);
        }

        //No encontramos
        if(intento >= tableHash.length){
            throw new KeyNotFound();
        }
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

        //Minetras no encontremos, repetimos la busqueda cuadraticamente
        while(!(intento > tableHash.length) && !tableHash[index].getKey().equals(key) && tableHash[index] != null){
            intento++;
            index = tryCollision(key,intento);
        }

        //Encontramos
        if(tableHash[index].getKey().equals(key) && !tableHash[index].isBorrado() && !(intento > tableHash.length)){
            encontre = true;
        }

        return encontre;

    }

    public  NodoHash<K,V> getNodo(int i){
        return tableHash[i];
    }
}
