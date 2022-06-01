package uy.edu.um.prog2.adt.linkedlist;

public class MyLinkedListImpl<T> implements MyList<T> {
    private Node<T> head = null;

    @Override
    public void add(T value) {
        Node<T> valueToAdd = new Node<T>(value);
        if (value == null){
            return;
        }
        if(this.head == null){
            this.head = valueToAdd;
        }else{
            Node<T> current = head;//first?
            while(current.getNext() != null){
                current = current.getNext();
            }
            current.setNext(valueToAdd);
        }
    }

    @Override
    public T get(int position) {
        T valueToReturn = null;
        int tempPosition = 0;
        Node<T> current = this.head;

        // Se busca el nodo que corresponde con la posicion
        while(current!=null) {  //empieza a recorrer la lista si no es vacía
            if (tempPosition == position) { //si la posición que quiero es i
                if (tempPosition == 0) {  //si i es la inicial
                    valueToReturn = this.head.getValue();
                } else {
                    valueToReturn = current.getValue();
                }
            }
            current = current.getNext();
            tempPosition++;
        }

        return valueToReturn;
    }

    @Override
    public boolean contains(T value) {//no esta hecho con equals
        Node<T> current = this.head;
        boolean estaEnLista = false;
        while(current!=null) {  //empieza a recorrer la lista si no es vacía
            if (value == current.getValue()) {
                estaEnLista = true;
                break;//una vez que lo encuentra tiene que salir sino da siempre false (queda bien solo para el ultimo elemento
            } else {
                estaEnLista = false;
            }
            current = current.getNext();
        }
        return estaEnLista;
    }

    @Override
    public void removePorValor(T value) {
        Node<T> prev = null;
        Node<T> current = this.head;

        //busco el elemento a eliminar
        while (current != null && !current.getValue().equals(value)) {
            prev = current;
            current = current.getNext();
        }
        if (current != null) { //si encontre el elemento a eliminar

            if (current == this.head && current.getNext() != null) {// si es el primer valor y no el ultimo

                Node<T> temp = this.head;
                this.head = this.head.getNext(); // pongo head al segundo/siguiente

                temp.setNext(null); //saco referencia del elemento eliminado al siguiente

            } else if (current.getNext() == null && current != this.head) {// si es el ultimo valor y no el primero

                prev.setNext(null);

            } else if (current.getNext() == null && current == this.head) {// si es el primer valor y el ultimo (lista con un valor)

                this.head = null;
                this.head.setNext(null);

            } else { //otros casos

                prev.setNext(current.getNext());
                current.setNext(null);

            }

        }

    }

    @Override
    public void removePorPos(int position) {
        Node<T> prev = null;
        Node<T> current = this.head;
        int posEstoy = 0;


        while (current!= null && current.getNext() != null){//empieza a recorrer la lista si no es vacia o si el siguiente no es vacío
            if(posEstoy == position){//si la posicion que quiero es la que estoy
                if(posEstoy == 0){
                    head = current.getNext();//como voy a borrar el que quiero hago el siguiente como head
                    current = current.getNext();//y el que voy a ver va a ser el siguiente
                }
                else if (current.getNext().getNext() == null){//current.next se refiere al ultimo nodo
                    current.setNext(null);//elimina el ultimo nodo de la lista
                }else{
                    prev.setNext(current.getNext());//como voy a borrar uno me tengo que saltear ese hago el prev.next igual al next del que estoy viendo
                    current = current.getNext();//y el que voy a ver va ser el siguiente
                }
            }
            //i no es la que estoy buscando hace:
            prev = current;//hago el previo el que ya vi
            current = current.getNext();//hago el que quiero ver el proximo
            posEstoy++;//aumento el contador
        }
    }

    @Override
    public int size() {
        int count = 0;
        Node<T> current = this.head;
        while (current != null) {
            current = current.getNext();
            count++;
        }
        return count;
    }
}
