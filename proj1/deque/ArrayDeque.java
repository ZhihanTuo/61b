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
        if (nextFirst-- == 0) { nextFirst = items.length - 1; }
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
        if (nextLast++ == items.length - 1) { nextLast = 0; }
        size++;
    }

    public boolean isEmpty() {
        return true;
    }

    /** Returns the size of the deque */
    public int size() {
        return size;
    }

    public void printDeque() {

    }

    /** Removes the first item in the deque and returns the removed value
     * Move the nextFirst index to its position prior to the last addFirst operation
     * If <25% of the array will be utilized after removing an item, resize the array by a factor of 1/4 */
    public T removeFirst() {
        if (size == 0) { return null; }

        T removed = (++nextFirst == items.length) ? items[nextFirst = 0] : items[nextFirst];
        items[nextFirst] = null;
        
        if (--size < items.length / 4) { resize(items.length / 4); }
        return removed;
    }

    /** Removes the last item in the deque and returns the removed value
     * Move the nextLast index to its position prior to the last addLast operation
     * If <25% of the array will be utilized after removing an item, resize the array by a factor of 1/4 */
    public T removeLast() {
        if (size == 0) { return null; }

        T removed = (--nextLast < 0) ? items[nextFirst = items.length - 1] : items[nextFirst];
        items[nextLast] = null;

        if (--size < items.length / 4) { resize(items.length / 4); }
        return removed;
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
