package uy.edu.um.prog2.adt.linkedlist;

public class MyLinkedListImpl<T> implements MyList<T> {
    private Node<T> head = null;

    @Override
    public void add(T value) {
        Node<T> valueToAdd = new Node<T>(value);
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
    public void remove(T value) {
        Node<T> prev = null;
        Node<T> current = this.head;

        // Busco el elemento a eliminar teniendo en cuenta mantener una referencia al elemento anterior
        while (current != null && !current.getValue().equals(value)) {
            prev = current;
            current = current.getNext();
        }
        if (current != null) { // si encontre el elemento a eliminar

            if (current == this.head && current.getNext() != null) {// si es el primer valor y no el ultimo

                Node<T> temp = this.head;
                this.head = this.head.getNext(); // pongo head al segundo/siguiente

                temp.setNext(null); // quito referencia del elemento eliminado al siguiente

            } else if (current.getNext() == null && current != this.head) {// si es el ultimo valor y no el primero

                prev.setNext(null);

            } else if (current.getNext() == null && current == this.head) {// si es el primer valor y el ultimo (lista de un solo valor)

                this.head = null;
                this.head.setNext(null);

            } else { // resto de los casos

                prev.setNext(current.getNext());
                current.setNext(null);

            }

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
