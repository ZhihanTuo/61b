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
        BuggyAList<Integer> L2 = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                L2.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                assertEquals(L.size(), L2.size());
            } else if (operationNumber == 2) {
                // getLast
                if (L.size() > 0) {
                    assertEquals(L.getLast(), L2.getLast());
                }
            } else if (operationNumber == 3) {
                // removeLast
                if (L.size() > 0) {
                    assertEquals(L.removeLast(), L2.removeLast());
                }
            }

        }
    }
}
