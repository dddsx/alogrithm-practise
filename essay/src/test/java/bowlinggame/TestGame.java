package bowlinggame;

import junit.framework.TestCase;
import org.junit.Test;

public class TestGame extends TestCase {

    private Game g;

    @Override
    protected void setUp() {
        g = new Game();
    }

    @Test
    public void test() {
        g.add(10);
        g.add(3);
        g.add(6);
        assertEquals(19, g.scoreForFrame(1));
        assertEquals(28, g.score());
    }

    @Test
    public void testOneThrows() {

    }

    @Test
    public void testFourThrowsNoMark() {
        g.add(5);
        g.add(4);
        g.add(7);
        g.add(2);
        assertEquals(18, g.score());
        assertEquals(9, g.scoreForFrame(1));
        assertEquals(18, g.scoreForFrame(2));
    }

    @Test
    public void testSimpleSpare() {
        g.add(3);
        g.add(7);
        g.add(3);
        g.add(2);
        assertEquals(13, g.scoreForFrame(1));
        assertEquals(18, g.scoreForFrame(2));
        assertEquals(18, g.score());
    }

    @Test
    public void testPerfectGame() {
        for (int i = 0; i < 12; i++) {
            g.add(10);
        }
        assertEquals(300, g.score());
    }

    @Test
    public void testEndOfArray() {
        for (int i = 0; i < 9; i++) {
            g.add(0);
            g.add(0);
        }
        g.add(2);
        g.add(8);
        g.add(10);
        assertEquals(20, g.score());
    }

    @Test
    public void testSampleGame() {
        g.add(new int[]{1,4,4,5,6,4,5,5,10,0,1,7,3,6,4,10,2,8,6});
        assertEquals(133, g.score());
    }

    @Test
    public void testHeartBreak() {
        for (int i = 0; i < 11; i++) {
            g.add(10);
        }
        g.add(9);
        assertEquals(299, g.score());
    }

    @Test
    public void testTenthFrameSpare() {
        for (int i = 0; i < 9; i++) {
            g.add(10);
        }
        g.add(9);
        g.add(1);
        g.add(1);
        assertEquals(270, g.score());
    }
}
