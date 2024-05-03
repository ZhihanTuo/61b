package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class MaxArrayDequeTest{
    /** Tests the appropriate max is returned with a deque of integers
     * Refer to the IntegerComparator class for the governing laws of integer comparison */
    @Test
    public void testIntegers() {
        MaxArrayDeque<Integer> temperature = new MaxArrayDeque<>(MaxArrayDeque.integerComparator());
        for (int i : new int[]{ 66, 76, 97, 42, 59}) {
            temperature.addFirst(i);
        }
        // Test max returns the biggest number in the deque (97)
        assertEquals(temperature.max(), Integer.valueOf(97));
        // Add a duplicate of the max item, max should return either item with the same value
        temperature.addLast(97);
        assertEquals(temperature.max(), Integer.valueOf(97));
    }

    @Test
    public void testStrings() {
        MaxArrayDeque<String> temperature1 = new MaxArrayDeque<>(MaxArrayDeque.stringComparator());
    }

    @Test
    public void testBooleans() {
        MaxArrayDeque<Boolean> temperature1 = new MaxArrayDeque<>(MaxArrayDeque.booleanComparator());
    }
}

