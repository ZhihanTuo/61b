package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>{
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    /** Adds an item to the front of the deque using the nextFirst index
     * nextFirst will be decremented after each add operation
     * If an item already exists at index nextFirst, the array will be resized before a new item is added */
    public void addFirst(T Item) {
        if (items[nextFirst] != null) {
            resize(size * 2);
        }
        items[nextFirst] = Item;
        nextFirst--;
        size++;
    }

    /** Adds an item to the back of the deque using the nextLast index
     * nextLast will be incremented after each add operation
     * If an item already exists at index nextLast, the array will be resized before a new item is added */
    public void addLast(T Item) {
        if (items[nextLast] != null) {
            resize(size * 2);
        }
        items[nextLast] = Item;
        nextLast++;
        size++;
    }

    public boolean isEmpty() {
        return true;
    }

    public int size() {
        return size;
    }

    public void printDeque() {

    }

    public T removeFirst() {
        return null;
    }

    public T removeLast() {
        return null;
    }

    public T get(int index) {
        return null;
    }

    private void resize(int capacity) {

    }

    public Iterator<T> iterator() {
        return null;
    }

    public boolean equals(Object o) {
        return true;
    }

}
