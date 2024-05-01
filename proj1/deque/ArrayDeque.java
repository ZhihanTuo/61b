package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T>{
    private T[] items;
    private int size; /* Represents the num of items in the deque, initialized to 0 upon instantiation */
    private int nextFirst; /* Index location of the position before the first item in the deque */
    private int nextLast; /* Index location of the position after the last item in the deque */

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4; /* An arbitrary index position */
        nextLast = 5; /* An arbitrary index position */
    }

    /** Adds an item to the front of the deque using the nextFirst index
     * nextFirst will be decremented after each add operation
     * If an item already exists at index nextFirst, the array will be resized before a new item is added */
    @Override
    public void addFirst(T item) {
        if (nextFirst == nextLast) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        // Also could use a ternary operator
        if (nextFirst-- == 0) { nextFirst = items.length - 1; }
        size++;
    }

    /** Adds an item to the back of the deque using the nextLast index
     * nextLast will be incremented after each add operation
     * If an item already exists at index nextLast, the array will be resized before a new item is added */
    @Override
    public void addLast(T item) {
        if (nextLast == nextFirst) {
            resize(size * 2);
        }
        items[nextLast] = item;
        // Can also circle back to the front of the array using nextLast = (nextLast + 1) % items.length
        // Also could use a ternary operator
        if (nextLast++ == items.length - 1) { nextLast = 0; }
        size++;
    }

    /** Returns the size of the deque */
    public int size() {
        return size;
    }

    /** Prints out the items in the deque as a string, starting from the front of the deque at position nextFirst + 1 */
    @Override
    public void printDeque() {
        // Nothing to print if the deque is empty
        if (size == 0) { return; }
        // Sets position of first printed item to the position of the first item in the deque
        int position = nextFirst + 1;
        do {
            // Circle back to front of array if position goes past the "last" index in the array
            if (position == items.length) { position = 0; }
            // Prints out the item followed by ", " and iterates to the next item index
            System.out.print(items[position++] + ", ");
            // Breaks when the last item is reached (Does not print out, since we do not want ", " succeeding the last item
        } while (position != (nextLast - 1));
        // Prints out the last item
        System.out.print(items[position]);
    }

    /** Removes the first item in the deque and returns the removed value
     * Move the nextFirst index to its position prior to the last addFirst operation
     * If <25% of the array will be utilized after removing an item, resize the array by a factor of 1/4 */
    @Override
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
    @Override
    public T removeLast() {
        if (size == 0) { return null; }
        // Move nextLast to the position of the last item in the deque and store the last item in removed
        T removed = (--nextLast < 0) ? items[nextLast = items.length - 1] : items[nextLast];
        // No longer need to store the removed item
        items[nextLast] = null;
        // Resize if <25% of the array is being utilized after removing an item
        if (--size < items.length / 4) { resize(items.length / 4); }
        return removed;
    }

    /** Returns item at a given index position in the deque
     * The first item is stored at the index position nextFirst + 1 (0 when nextFirst == items.length - 1)
     * The last item is stored at the index position nextLast - 1 (items.length - 1 when nextLast = 0) */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) { return null; }

        int arrayIndex;
        // For easier to read code
        int firstItem = nextFirst + 1;
        int lastItem = nextLast - 1;

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
            arrayIndex = (nextLast == 0) ? items.length - 1 : lastItem;
        }
        else {
            // Finds position of item to get by adding index to firstItem
            // If result is out of bounds, subtract by the array length to yield the legal equivalent
            arrayIndex = (firstItem + index > items.length - 1) ? firstItem + index - items.length  : firstItem + index;
        }
        return items[arrayIndex];
    }

    /** Resizes the deque when full and when <25% of the deque is being utilized */
    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        // Starting from the first element in the deque (++nextFirst),
        // copy into newArray element by element with the first element being at position 0
        for (int i = 0; i < size; i++) {
            newArray[i] = items[(++nextFirst) % items.length];
        }
        // After completion of the above loop, the first item would be at index 0
        // and the last item would be at size - 1
        // (e.g. we copy an array containing integers 1-8 into newArray, the size of the old array would be 8
        // the first item (1) would be at index 0 while the last item (8) would be at index 7 or size - 1)

        /* Set nextFirst and nextLast to proper positions respective to the new array */
        nextFirst = capacity - 1;
        nextLast = size;
        // Assign to items the refactored array
        items = newArray;
    }

    /** Returns an ArrayDequeIterator object with the functions hasNext and next
     * Allowing us to iterate through the deque */
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    /** Helper class used to implement iterator, hidden from user */
    private class ArrayDequeIterator implements Iterator<T> {
        private int current;

        /** Constructs with current starting at the first item in the deque */
        ArrayDequeIterator() {
            current = nextFirst + 1;
        }

        /** Returns true if there are more items in the deque, false otherwise
         * Should be called to verify there are items to iterate through before calling next() */
        @Override
        public boolean hasNext() {
            return size > 0;
        }

        /** Returns the next item if hasNext() is true, returns null if there is no next item
         * Should only be called if hasNext() is true */
        @Override
        public T next() {
            current = (current + 1 == items.length) ? 0 : current + 1;
            return items[current];
        }
    }

    /** Returns true if o is a deque that contains the same contents in the same order,
     * Returns false otherwise */
    @Override
    public boolean equals(Object o) {
        // If o and this contain the same memory address, then they both point to the same object(are equal)
        if (this == o) { return true; }

        // If o is an instance of dynamic type Deque or any of its subclasses,
        // casts o as a static type Deque and assigns to oad
        if (o instanceof Deque oad) {
            // Check oad has same number of items as me
            if (oad.size() != this.size) {
                return false;
            }

            // Check oad has same items as me, in the same order
            for (int i = 0; i < this.size; i++) {
                if (oad.get(i) != this.get(i)) {
                    return false;
                }
            }
            // o is equal to me if previous checks passed
            return true;
        }
        return false;
    }
}
