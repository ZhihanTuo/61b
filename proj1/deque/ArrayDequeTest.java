package deque;

import org.junit.Assert;
import org.junit.Test;
import edu.princeton.cs.algs4.StdRandom;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ArrayDequeTest {
    /** Tests that isEmpty(), size(), addFirst() and addLast() all function as intended when adding to an empty deque
     * NO RESIZING */
    @Test
    public void testAddToEmptyDeque() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        // New ArrayDeque created, should be empty and of size 0
        assertTrue(ad.isEmpty());
        assertEquals(0, ad.size());

        ad.addFirst(4);
        ad.addLast(5);
        ad.addLast(6);
        ad.addFirst(3);
        ad.addLast(7);
        ad.addFirst(2);
        ad.addFirst(1);
        ad.addLast(8);

        // Should be of size 8 and no longer empty
        assertFalse(ad.isEmpty());
        assertEquals(8, ad.size());

        // Checks that the values stored in ad.size are '1, 2, 3, 4, 5, 6, 7, 8' as expected
        for (int i : new int[] {1, 2, 3, 4, 5, 6, 7, 8}) {
            assertEquals(Integer.valueOf(i + 1), Integer.valueOf(ad.get(i)));
        }
    }

    /** Tests that isEmpty(), size(), addFirst(), addLast() all function as intended when adding to a full deque
     * TESTS RESIZING TO BIGGER DEQUE*/
    @Test
    public void testAddToFullDeque() {
        ArrayDeque<Boolean> ad = new ArrayDeque<>();
        // A new ArrayDeque with no elements will be of size 0
        assertEquals(0, ad.size());

        for (int i = 0; i < 8; i++) {
            ad.addFirst(true);
        }
        // Should be of size 8, which is the full storage of the initially constructed array
        assertEquals(8, ad.size());

        ad.addFirst(false);
        ad.addLast(false);
        // Should be able to add additional items by resizing the deque, so check that
        // 1. Size is increasing as expected
        // 2. Added values are in their proper positions
        assertEquals(10, ad.size());
        assertEquals(false, ad.get(0));
        assertEquals(false, ad.get(9));
    }

    /** Tests that removeFirst(), removeLast(), isEmpty(), size() all function as intended when removing items from deque
     * TESTS RESIZING TO SMALLER DEQUE (Viewable through java visualizer) */
    @Test
    public void testRemove() {
        ArrayDeque<Double> units = new ArrayDeque<>();
        units.addFirst(1.0);
        units.addLast(2.5);
        units.addLast(3.2);
        units.addLast(4.7);
        units.addFirst(5.4);
        units.addLast(6.8);
        units.addLast(7.9);
        units.addLast(8.1);
        units.addLast(9.3);

        // Order of items in the deque: 5.4, 1.0, 2.5, 3.2, 4.7, 6.8, 7.9, 8.1, 9.3
        assertEquals(0, Double.compare(units.removeLast(), 9.3));
        // 5.4, 1.0, 2.5, 3.2, 4.7, 6.8, 7.9, 8.1
        assertEquals(0, Double.compare(units.removeFirst(), 5.4));
        // 1.0, 2.5, 3.2, 4.7, 6.8, 7.9, 8.1
        assertEquals(0, Double.compare(units.removeFirst(), 1.0));
        // 2.5, 3.2, 4.7, 6.8, 7.9, 8.1
        assertEquals(0, Double.compare(units.removeFirst(), 2.5));
        // 3.2, 4.7, 6.8, 7.9, 8.1
        assertEquals(0, Double.compare(units.removeFirst(), 3.2));
        // 4.7, 6.8, 7.9, 8.1
        assertEquals(0, Double.compare(units.removeFirst(), 4.7));
        // 6.8, 7.9, 8.1
        assertEquals(0, Double.compare(units.removeFirst(), 6.8));
        // 7.9, 8.1
        assertEquals(0, Double.compare(units.removeFirst(), 7.9));
        // 8.1
        assertEquals(0, Double.compare(units.removeFirst(), 8.1));
        // No items left
        assertNull(units.removeFirst());
        assertEquals(0, units.size());
        assertTrue(units.isEmpty());
    }

    /** Test that get() functions as intended for retrieving items at position i in the deque
     * Note: The actual position of an item inside the circular array used to implement the deque is not equivalent to
     its ordered position in the deque */
    @Test
    public void testGet() {
        ArrayDeque<String> ad = new ArrayDeque<>();
        int i = 0;
        for (String s : new String[] {"Annie", "Bernard", "Cassie", "Dave", "Ethan"}) {
            ad.addLast(s);
            assertEquals("Should be the same string", s, ad.get(i));
            i++;
        }
    }

    /** Test that equals() functions as intended when comparing two ArrayDeque objects */
    @Test
    public void testEquals() {
        ArrayDeque<Long> lad = new ArrayDeque<>();
        LinkedListDeque<Long> ladCopy = new LinkedListDeque<>();
        ArrayDeque<String> sad = new ArrayDeque<>();

        // Both are deques that hold no values, should return true
        assertTrue(lad.equals(ladCopy));

        long hrsInDayEarth = 24;
        long hrsInDayJupiter = 10;
        lad.addLast(hrsInDayEarth);
        ladCopy.addLast(hrsInDayEarth);
        lad.addFirst(hrsInDayJupiter);
        ladCopy.addFirst(hrsInDayJupiter);

        sad.addLast("Earth");
        sad.addFirst("Jupiter");

        // Different subclasses of deque interface, but have the same contents in the same order so are equal
        assertTrue(lad.equals(ladCopy));
        // Same subclass, with different content are not equal
        assertFalse(lad.equals(sad));

        ladCopy = null;
        // Deque with items is not equal to null object
        assertFalse(lad.equals(ladCopy));

        ArrayDeque<Long> newAD = lad;
        // Same memory address, are equal
        assertTrue(lad.equals(newAD));
    }

    /** Randomly calls ArrayDeque's methods and asserts values for methods with return values */
    @Test
    public void randomizedTest() {
        ArrayDeque<Character> ad = new ArrayDeque<>();
        ArrayDeque<Character> adCopy = new ArrayDeque<>();

        int size = 0;
        int N = 10000;
        for (int i = 0; i < N; i++) {
            int operation = StdRandom.uniform(0, 6);

            char[] alphabet = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o', 'p','q','r','s','t','u','v','w','x','y','z'};
            int randomIndex = StdRandom.uniform(0, alphabet.length);

            switch (operation) {
                // addFirst
                case 0:
                    ad.addFirst(alphabet[randomIndex]);
                    adCopy.addFirst(alphabet[randomIndex]);
                    System.out.println("Added " + alphabet[randomIndex] + " to front deque");
                    size += 1;
                    break;
                // addLast
                case 1:
                    ad.addLast(alphabet[randomIndex]);
                    adCopy.addLast(alphabet[randomIndex]);
                    System.out.println("Added " + alphabet[randomIndex] + " to back deque");
                    size += 1;
                    break;
                // removeFirst
                case 2:
                    assertEquals(ad.get(0), ad.removeFirst());
                    adCopy.removeFirst();
                    if (size > 0) {
                        size -= 1;
                    }
                    break;
                // removeLast
                case 3:
                    assertEquals(ad.get(size - 1), ad.removeLast());
                    adCopy.removeLast();
                    if (size > 0) {
                        size -= 1;
                    }
                    break;
                // size
                case 4:
                    assertEquals(size, ad.size());
                    break;
                // equals
                case 5:
                    assertTrue(ad.equals(adCopy));
                    break;
            }
        }
    }

    /** Tests that printDeque() correctly prints out the contents in the deque, separated by a comma and space ", " between each item */
    @Test
    public void printDeque() {
        PrintStream standardOut = System.out;
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStreamCaptor));

        ArrayDeque<Boolean> testPass = new ArrayDeque<>();
        for (Boolean b : new Boolean[] {true, false, false, true ,false}) {
            testPass.addFirst(b);
        }

        testPass.printDeque();
        Assert.assertEquals("false, true, false, false, true", outputStreamCaptor.toString().trim());

        System.setOut(standardOut);
    }

}
