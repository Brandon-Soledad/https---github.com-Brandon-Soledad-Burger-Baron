package app;

import java.util.NoSuchElementException;

public class MyStack<T> {
    
    private Node head; // Top of the stack
    private int size; // Size of the stack

    //User defined Linked List class
    private class Node {
        private Node next;
        private T data;
    }
    /* Initializes an empty stack*/
    public MyStack() {
        head = null;
        size = 0;
    }
    /**
     * Returns true or false depending on whether the stack is empty or not
     */
    public boolean isEmpty() {
        return this.head == null;
    }
    /**
     * Pushes an item to the top of the stack.
     * @param item item to be pushed to the top of the stack
     */
    public void push(final T item) {
        final Node old = head; 
        head = new Node();
        head.data = item;
        head.next = old;
        size++;
    }
    /**
     * Deletes an item from the top of the stack
     */
    public T pop() {
        if (isEmpty()) throw new NoSuchElementException("The stack is empty");
        final T thisItem = head.data; // item to return
        head = head.next; // Deletes first node
        size--; // Decrease size of list
        return thisItem;
    }
    /**
     * Method to return item in the top of the stack
     * @return returns item at the top of the stack
     */
    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("The stack is empty"); 
        return head.data;
    }
    /* Returns the size of the stack */
    public int size() {
        return size;
    }
    /* Prints the contents of the stack */
    public String toString() {
        Node hasNext = this.head; // Starts at the head of stack
        final StringBuilder sb = new StringBuilder();
        String prefix = "";
        sb.append("[");
        while (hasNext != null) { // As long as the stack is not empty then print the contents
            sb.append(prefix);
            prefix = ", ";
            sb.append(hasNext.data); 
            hasNext = hasNext.next;
        }
        sb.append("]");
        return sb.toString();
    } 
    
}