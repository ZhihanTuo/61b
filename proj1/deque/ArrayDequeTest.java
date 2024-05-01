package deque;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ArrayDequeTest {
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

    @Test
    public void testRemove() {

    }

    @Test
    public void testGet() {

    }

    @Test
    public void randomizedTest() {

    }

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
