package deque;

import java.util.Iterator;

public interface Deque<T> {
    public void addFirst(T Item);
    public void addLast(T Item);
    public default boolean isEmpty() {
        return size() == 0;
    }
    public int size();
    public void printDeque();
    public T removeFirst();
    public T removeLast();
    public T get(int index);
}
