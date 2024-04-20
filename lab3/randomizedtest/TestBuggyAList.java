package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> NoResize = new AListNoResizing<>();
        BuggyAList<Integer> Buggy = new BuggyAList<>();
        for (int i : new int[]{7, 8, 9}) {
            NoResize.addLast(i);
            Buggy.addLast(i);
        }

        assertEquals(NoResize.removeLast(), Buggy.removeLast());
        assertEquals(NoResize.removeLast(), Buggy.removeLast());
        assertEquals(NoResize.removeLast(), Buggy.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();

        int N = 500;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 2);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                System.out.println("size: " + size);
            }
        }
    }
}
