package uy.edu.um.prog2.adt.hash;

public class MyClosedHashImpl <K extends Comparable<K>, V> implements MyHash<K, V> {

    private static final int DEFAULT_INITIAL_TABLE_HASH_SIZE = 10;


    private NodoHash<K,V>[] tableHash;

    private int size;


    public MyClosedHashImpl() {
        tableHash = new NodoHash[DEFAULT_INITIAL_TABLE_HASH_SIZE];//array de nodos de tam definido
    }
    public MyClosedHashImpl(int tableHashSize) {
        tableHash = new NodoHash[tableHashSize];
    }



    private int colisionIndex(int index){ //Devuelve lugar a intentar insertar, de manera cuadratica
        int temp = 0;
        temp = index^2;
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

        while(tableHash[index] != null && //el espacio este ocupado
                !(intento > tableHash.length) && //esté dentro del hash
                !tableHash[index].getKey().equals(key)){//key del nodo sea diferente a la que quiero poner
            intento += 1;//se repite la busqueda
            index = tryCollision(key,intento);
        }

        //encontrado
        if(tableHash[index] != null &&  //el espacio este ocupado
                !(intento > tableHash.length) && //esté dentro del hash
                tableHash[index].getKey().equals(key) && //key del nodo sea igual a la que quiero poner
                !tableHash[index].isBorrado()){//no haya uno borrado en ese index
            returnData = tableHash[index].getData();
        }

        //no encontrado
        if(intento >= tableHash.length){
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
    public void delete(K key) throws KeyNotFound {
        int intento = 0;
        int index = tryCollision(key,intento);

        while(tableHash[index] != null  &&//el espacio este ocupado
                !(intento > tableHash.length) &&//esta dentro del hash
                !tableHash[index].getKey().equals(key)){//key del nodo sea diferente a la que quiero poner
            intento++;//se repite la busqueda
            index = tryCollision(key,intento);
        }

        //encontrado
        if(tableHash[index].getKey().equals(key) && //key es igual a la que quiero
                !tableHash[index].isBorrado() && //no esta borrado
                !(intento > tableHash.length)){//esta dentro del hash
            tableHash[index].setBorrado(true);//ponerlo como borrado
        }

        //no encontrado
        if(intento >= tableHash.length){
            throw new KeyNotFound();
        }

        size--;
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
