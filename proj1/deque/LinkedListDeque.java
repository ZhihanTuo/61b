package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    /** Node class */
    private static class Node<T> {
        public T item;
        public Node<T> prev;
        public Node<T> next;

        /** Construct node with item i, prev node p, and next node n */
        public Node(T i, Node<T> p, Node<T> n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private Node<T> sentinel;
    private int size;

    /** Constructs sentinel node pointing to nothing and sets size to 0 */
    public LinkedListDeque() {
        sentinel = new Node<>(null, null, null);
        size = 0;
    }

    /** Private helper function for adding a node to an empty deque */
    // If the deque is empty, point sentinel.next and sentinel.prev to new node
    // Set head's item to item and point head.next and head.prev to sentinel
    private void addToEmptyDeque(T item) {
        sentinel.next = new Node<>(item, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size++;
    }

    @Override
    /** Adds an item to the front of the deque and increments size */
    public void addFirst(T item) {
        // If the deque is empty, add a node that is pointed at by sentinel's prev and next next
        // and also points back to sentinel with MY own prev and next
        if (sentinel.next == null) {
            addToEmptyDeque(item);
        } else {
            // If the deque is not empty (sentinel.next points to something)
            // Point sentinel.next to the new node (sentinel.prev still points to the back)
            // Insert new head node with prev pointing to sentinel and next pointing to head(old)
            Node<T> head = sentinel.next;
            sentinel.next = new Node<>(item, sentinel, head);
            size++;
        }
    }

    @Override
    /** Adds an item to the back of the deque and increments size */
    public void addLast(T item) {
        // If the deque is empty, add a node that is pointed at by sentinel's prev and next next
        // and also points back to sentinel with MY own prev and next
        if (sentinel.next == null) {
            addToEmptyDeque(item);
        } else {
            // If the deque is not empty (sentinel.next points to something)
            // Point tail.next to new node
            // Insert new node with prev pointing to tail(old) and next pointing to sentinel
            Node<T> tail = sentinel.prev;
            tail.next = new Node<>(item, tail, sentinel);
            sentinel.prev = tail.next;
            size++;
        }
    }

    @Override
    /** Returns num of items in deque */
    public int size() {
        return size;
    }

    @Override
    /** Prints the items contained in the deque first to last, each separated by a space
     * Prints out new line when done */
    public void printDeque() {
        if (sentinel.next == null) {
            System.out.println("Deque is empty T_T");
            return;
        }

        Node<T> p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println("\n");
    }

    @Override
    /** Removes the first item in the deque and returns item removed */
    public T removeFirst() {
        // Return the removed head node's item
        if (size > 0) {
            Node<T> head = sentinel.next;
            T removed = head.item;

            // Connects sentinel with 2nd node in the deque since we are removing the first
            // Point sentinel.next to 2nd node in the deque (head.next)
            // Point prev of that node(head.next) to sentinel
            sentinel.next = head.next;
            head.next.prev = sentinel;

            // No longer need
            head = null;
            if (--size == 0) {
                sentinel.prev = null;
                sentinel.next = null;
            }
            // Returns removed item
            return removed;
        }
        // Returns null if deque is empty
        return null;
    }

    @Override
    /** Removes the last item in the deque and returns item removed */
    public T removeLast() {
        if (size > 0) {
            Node<T> tail = sentinel.prev;
            T removed = tail.item;

            // Connects sentinel with 2nd to last node in the deque since we are removing the last
            // Point sentinel.prev to the 2nd to last node in the deque (tail.prev)
            // Point next of that node(tail.prev) to sentinel
            sentinel.prev = tail.prev;
            tail.prev.next = sentinel;

            tail = null;
            if (--size == 0) {
                sentinel.prev = null;
                sentinel.next = null;
            }
            // Returns removed item
            return removed;
        }
        // Returns null if deque is empty
        return null;
    }

    @Override
    /** Returns item at a given index, where 0 is the first item
     * Returns null if no such item */
    public T get(int index) {
        int i = 0;
        Node<T> p = sentinel.next;
        while (i < index && p != sentinel) {
            p = p.next;
            i++;
        }
        return (i == index) ? p.item : null;
    }

    /** Returns item at a given index, where 0 is the first item
     * Returns null if no such item */
    public T getRecursive(int index) {
        return getRecursive(index, sentinel.next);
    }

    /** Helper method for getRecursive, uses node n to traverse the deque */
    private T getRecursive(int index, Node<T> n) {
        if (index == 0) {
            return n.item;
        }
        return getRecursive(index - 1, n.next);
    }

    public boolean equals(Object o) {
        return true;
    }

    /** Returns a LLDequeIterator object with next() and hasNext(),
     * allowing us to iterate through the LLDeque */
    public Iterator<T> iterator() {
        return new LLDequeIterator();
    }

    /** Helper class used to implement iterator, should not be accessible by the user */
    private class LLDequeIterator implements Iterator<T> {
        private Node<T> p;

        public LLDequeIterator() {
            p = sentinel.next;
        }

        @Override
        /** Returns true if there are items to iterate through, false otherwise */
        public boolean hasNext() {
            return p != null;
        }

        @Override
        /** Returns the next item, and iterates to the item after */ 
        public T next() {
            T returnedItem = p.item;
            p = p.next;
            return returnedItem;
        }
    }
}
