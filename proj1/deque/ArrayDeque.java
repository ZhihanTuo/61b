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

    /** Returns item at a given position index in the deque
     * The first item is stored at the index position nextFirst + 1 (0 when nextFirst == items.length - 1)
     * The last item is stored at the index position nextLast - 1 (items.length - 1 when nextLast = 0) */
    public T get(int index) {
        int arrayIndex;
        int firstItem = nextFirst + 1;

        // First item in the deque
        if (index == 0) {
            // If nextLast is items.length - 1, incrementing nextFirst will yield an out of bound index
            // In a circular array, the next position to index items.length - 1 is at index 0
            arrayIndex =  (nextFirst == items.length - 1) ? 0 : nextFirst + 1;
        }
        // Last item in the deque
        else if (index == size - 1) {
            // If nextLast is 0, decrementing nextLast will yield an out of bound index
            // In a circular array, the previous position to index 0 is at index items.length - 1
            arrayIndex = (nextLast == 0) ? items.length - 1 : nextLast - 1;
        }
        else {
            // Finds position of item to get by adding index to firstItem
            // If result is out of bounds, subtract by the array length to yield the legal equivalent
            arrayIndex = (firstItem + index > items.length - 1) ? firstItem + index - items.length  : firstItem + index;
        }

        return items[arrayIndex];
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
