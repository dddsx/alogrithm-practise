package activeobject;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class TestSleepCommand extends TestCase {

    private boolean coomandExecuted = false;

    @Test
    public void test() {
        Command wakeup = () -> coomandExecuted = true;

        ActiveObjectEngine e = new ActiveObjectEngine();
        SleepCommand c = new SleepCommand(1000, e, wakeup);
        e.addCommands(c);
        long start = System.currentTimeMillis();
        e.run();
        long stop = System.currentTimeMillis();
        long sleepTime = (stop - start);
        Assert.assertTrue(sleepTime > 999);
        Assert.assertTrue(sleepTime < 1001);
        Assert.assertTrue(coomandExecuted);
    }
}
