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
}
