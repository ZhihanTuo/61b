package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{
    private Comparator<T> comparator;

    /** Creates a MaxArrayDeque with given Comparator */
    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    /** Returns the max element in the deque as governed by previous Comparator
     * Returns null if MaxArrayDeque is empty */
    public T max() {
        return null;
    }

    /** Returns the max element in the deque as governed by the Comparator c
     * Returns null if MaxDequeArray is empty */
    public T max(Comparator<T> c) {
        comparator = c;
        return null;
    }


}
