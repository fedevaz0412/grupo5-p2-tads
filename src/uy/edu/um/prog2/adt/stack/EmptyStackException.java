package uy.edu.um.prog2.adt.stack;

public class EmptyStackException extends Exception{
    public EmptyStackException(String errorMsg) {
        super(errorMsg);
    }
}
