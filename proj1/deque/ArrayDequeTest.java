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

        ad.addFirst(4);
        ad.addLast(5);
        ad.addLast(6);
        ad.addFirst(3);
        ad.addLast(7);
        ad.addLast(2);
        ad.addFirst(1);

        assertArrayEquals()
    }

    @Test
    public void testAddToFullDeque() {

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
