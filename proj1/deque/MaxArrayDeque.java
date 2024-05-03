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
        // If MaxArrayDeque is empty, return null;
        if (isEmpty()) { return null; }
        // Assigns the first item in the deque to maxElem, which will hold the maxElem we return
        T maxElem = get(0);
        // Starting from the 2nd item in the deque, iterate through the deque, comparing the current item to the one held in maxElem
        // Assigns current item to maxElem if current item is greater, otherwise go to comparing the next item if current item is equal or less than maxElem
        for (int i = 1; i < size(); i++) {
            // How the objects are compared is governed by the comparator's compare(),
            // but it should return a positive integer if the 1st argument is greater, 0 if equal, and a negative integer if lesser than the 2nd argument
            if (comparator.compare(get(i), maxElem) > 0) {
                maxElem = get(i);
            }
        }
        return maxElem;
    }

    /** Returns the max element in the deque as governed by the Comparator c
     * Returns null if MaxDequeArray is empty */
    public T max(Comparator<T> c) {
        // If MaxArrayDeque is empty, return null;
        if (isEmpty()) { return null; }
        // Uses the parameter c as the comparator instead of the default/previous comparator
        comparator = c;
        // Assigns the first item in the deque to maxElem, which will hold the maxElem we return
        T maxElem = get(0);
        // Starting from the 2nd item in the deque, iterate through the deque, comparing the current item to the one held in maxElem
        // Assigns current item to maxElem if current item is greater, otherwise go to comparing the next item if current item is equal or less than maxElem
        for (int i = 1; i < size(); i++) {
            if (comparator.compare(get(i), maxElem) > 0) {
                maxElem = get(i);
            }
        }
        return maxElem;
    }

    /** Comparator class for comparing integers, use as the default comparator when instantiating a MaxArrayDeque */
    private static class DefaultIntComparator implements Comparator<Integer> {
        /** How comparison in the IntComparator class is governed:
         * @param o1 the integer compared to
         * @param o2 the integer comparing with
         * o1 is greater than o2 if o1 is numerically bigger than o2
         * o1 is less than o2 if o1 is numerically smaller than o2
         * o1 and o2 are equal if they are the same number
         */
        @Override
        public int compare(Integer o1, Integer o2) {
            if (o1 > o2) { return 1; }
            else if (o1 < o2 ) { return -1; }
            return 0;
        }
    }

    /** Comparator class for comparing strings */
    private static class DefaultStringComparator implements Comparator<String> {
        /** How comparison in the StringComparator class is governed:
         *
         * @param o1 the string compared to
         * @param o2 the string comparing with
         * o1 is greater than o2 if o1 is a longer string than o2 (has more characters)
         * o1 is less than o2 if o1 is a shorter string than o2 (has fewer characters)
         * o1 is equal to o2 if they are both of the same length (have the same amount of characters)
         */
        @Override
        public int compare(String o1, String o2) {
            if (o1.length() > o2.length()) { return 1; }
            else if (o1.length() < o2.length()) { return -1; }
            return 0;
        }
    }

    /** Comparator class for comparing booleans */
    private static class DefaultBooleanComparator implements Comparator<Boolean> {
        /** How comparison in the BooleanComparator class is governed:
         *
         * @param o1 the boolean compared to
         * @param o2 the boolean comparing with
         * o1 is greater than o2 if o1 is true and o2 is false
         * o1 is less than o2 if o1 is false and o2 is true
         * o1 is equal to o2 if they both contain the same boolean value (are both true or both false)
         */
        @Override
        public int compare(Boolean o1, Boolean o2) {
            if (o1 == true && o2 == false) { return 1;}
            else if (o1 == false && o2 == true) { return -1;}
            return 0;
        }
    }

    /** Comparator method accessible to the user for comparing integers */
    public static Comparator<Integer> integerComparator() {
        return new DefaultIntComparator();
    }

    /** Comparator method accessible to the user for comparing strings */
    public static Comparator<String> stringComparator() {
        return new DefaultStringComparator();
    }
    /** Comparator method accessible to the user for comparing booleans */
    public static Comparator<Boolean> booleanComparator() {
        return new DefaultBooleanComparator();
    }
}
