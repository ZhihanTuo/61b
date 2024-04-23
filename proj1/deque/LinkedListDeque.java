package deque;

public class LinkedListDeque<T> {
    // Node class
    private static class Node<T> {
        public T item;
        public Node<T> prev;
        public Node<T> next;

        // Construct node with item, prev, and next
        public Node(T i, Node<T> p, Node<T> n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private Node<T> sentinel;
    private int size;

    // Constructs sentinel node pointing to nothing and sets size to 0
    public LinkedListDeque() {
        sentinel = new Node<>(null, null, null);
        size = 0;
    }

    // Adds an item to the front of the deque
    public void addFirst(T item) {
        // If the deque is empty, point sentinel.next and sentinel.prev to new node
        // Set head's item to item and point head.next and head.prev to sentinel
        if (sentinel.next == null) {
            sentinel.next = new Node<>(item, sentinel, sentinel);
            sentinel.prev = sentinel.next;
            size++;
        } else {
            // If the deque is not empty (sentinel.next points to something)
            // Point sentinel.next to the new node
            // Insert new head node with prev pointing to sentinel and next pointing to head(old)
            Node<T> head = sentinel.next;
            sentinel.next = new Node<>(item, sentinel, head);
            size++;
        }
    }

    // Adds an item to the back of the deque
    public void addLast(T item) {
        // If the deque is empty, point sentinel.next and sentinel.prev to new node
        // New node will contain item with prev and next both pointing to sentinel
        if (sentinel.next == null) {
            sentinel.next = new Node<>(item, sentinel, sentinel);
            sentinel.prev = sentinel.next;
            size++;
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

    // Returns true if deque is empty, false otherwise
    public boolean isEmpty() {
        return sentinel.next == null && sentinel.prev == null;
    }

    // Returns num of items in deque
    public int size() {
        return size;
    }

    //
    public void printDeque() {

    }

    // Removes the first item in the deque and returns item removed
    public T removeFirst() {
        // Returns null if deque is empty
        if (sentinel.next == null) {
            return null;
        }

        // Point sentinel.next to 2nd node in the deque (head.next)
        // Point prev of that node(head.next) to sentinel
        // Return the removed head node's item
        if (size > 0) {
            Node<T> head = sentinel.next;
            T removed = head.item;

            sentinel.next = head.next;
            head.next.prev = sentinel;

            head = null;
            if (size-- == 1) {
                sentinel.prev = null;
                sentinel.next = null;
            }
            return removed;
        }
        return null;
    }

    // Removes the last item in the deque and returns item removed
    public T removeLast() {
        // Returns null if deque is empty
        if (sentinel.next == null) {
            return null;
        }

        // Point sentinel.prev to the 2nd to last node in the deque (tail.prev)
        // Point next of that node(tail.prev) to sentinel
        if (size > 0) {
            Node<T> tail = sentinel.prev;
            T removed = tail.item;

            sentinel.prev = tail.prev;
            tail.prev.next = sentinel;

            tail = null;
            if (size-- == 1) {
                sentinel.prev = null;
                sentinel.next = null;
            }
            return removed;
        }
        return null;
    }

    // Returns item at a given index, where 0 is the first item
    // Returns null if no such item
    public T get(int index) {
        int i = 0;
        Node<T> p = sentinel.next;
        while (i < index && p != sentinel) {
            p = p.next;
            i++;
        }
        return (i == index) ? p.item : null;
    }

    public T getRecursive() {
        return null;
    }

    public boolean equals(Object o) {
        return true;
    }
}
