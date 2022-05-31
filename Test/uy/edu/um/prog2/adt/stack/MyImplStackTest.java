package uy.edu.um.prog2.adt.stack;

import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.prog2.adt.linkedlist.MyList;

import static org.junit.jupiter.api.Assertions.*;

class MyImplStackTest {

    @Test
    void pop() {
        MyStack<Integer> stack = new MyImplStack<>();
        stack.push(5);
        stack.push(4);
        stack.push(7);
        stack.push(1);

        try {
            assertEquals(1,stack.pop());
            assertEquals(7,stack.pop());
            assertEquals(4,stack.pop());
        } catch (EmptyStackException e) {

        }

        assertEquals(1,stack.size());
    }

    @Test
    void push() {
        MyStack<Integer> stack = new MyImplStack<>();
        stack.push(5);
        stack.push(4);
        stack.push(7);
        stack.push(1);

        assertEquals(4,stack.size());
    }

    @Test
    void isEmpty() {
        MyStack<Integer> stack = new MyImplStack<>();
        stack.push(5);
        assertFalse(stack.isEmpty());
        try {
            assertEquals(5,stack.pop());
        } catch (EmptyStackException e) {

        }
        assertTrue(stack.isEmpty());

        MyStack<Integer> stack2 = new MyImplStack<>();
        assertTrue(stack2.isEmpty());
    }

    @Test
    void peek() {
        MyStack<Integer> stack = new MyImplStack<>();
        stack.push(5);
        stack.push(4);
        stack.push(7);
        stack.push(1);

        try {
            assertEquals(1,stack.peek());
        } catch (EmptyStackException e) {

        }
        try {
            assertEquals(1,stack.pop());
        } catch (EmptyStackException e) {

        }
        try {
            assertEquals(7,stack.peek());
        } catch (EmptyStackException e) {

        }

    }

    @Test
    void size() {
        MyStack<Integer> stack = new MyImplStack<>();
        stack.push(5);
        stack.push(4);
        stack.push(7);
        stack.push(1);

        assertEquals(4,stack.size());

        MyStack<Integer> stack2 = new MyImplStack<>();
        assertEquals(0,stack2.size());
    }
}