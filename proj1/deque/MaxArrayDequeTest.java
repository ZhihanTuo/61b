package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class MaxArrayDequeTest{
    /** Tests the appropriate max is returned with a deque of integers using the default and wrong comparators
     * Refer to the IntegerComparator classes for the governing laws of integer comparison */
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
        // Test with a compare method that returns the smaller integer, max should then return the min item in the deque (42)
        assertEquals(temperature.max(MaxArrayDeque.integerComparatorWrong()), Integer.valueOf(42));
    }

    /** Tests the appropriate max is returned with a deque of strings using the default and wrong comparators
     * Refer to the StringComparator classes for the governing laws of string comparison */
    @Test
    public void testStrings() {
        MaxArrayDeque<String> names = new MaxArrayDeque<>(MaxArrayDeque.stringComparator());
        for (String s : new String[] {"Emma", "Ethan", "Jay", "Isabella" }) {
            names.addLast(s);
        }
        // Test max returns the longest name in the deque (Isabella)
        assertEquals(names.max(), "Isabella");
        // Adding a name of the same length as the max item should return the newest addition since compare replaces the old max
        names.addLast("Benedict");
        assertEquals(names.max(), "Benedict");
        // Test with a compare method that returns the shortest string, max should then return the shortest name in the deque (Jay)
        assertEquals(names.max(MaxArrayDeque.stringComparatorWrong()), "Jay");
    }

    /** Tests the appropriate max is returned with a deque of booleans using the default and wrong comparators
     * Refer to the BooleanComparator classes for the governing laws of boolean comparison */
    @Test
    public void testBooleans() {
        MaxArrayDeque<Boolean> answers = new MaxArrayDeque<>(MaxArrayDeque.booleanComparator());
        for (boolean b : new boolean[] { false, false, false, false}) {
            answers.addFirst(b);
        }
        // Test max will return false with a deque that only contains false values
        assertEquals(answers.max(), false);
        // Adding a true value into the deque will cause max() to return true since true is defined as being greater than false in compare()
        answers.addLast(true);
        assertEquals(answers.max(), true);
        // Test with a compare method that defines false as being greater than true, max should now return false
        assertEquals(answers.max(MaxArrayDeque.booleanComparatorWrong()), false);
    }
}

