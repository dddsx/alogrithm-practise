package activeobject;

public class SleepCommand implements Command {

    private Command wakeupCommand = null;

    private ActiveObjectEngine engine = null;

    private long sleepTime = 0;

    private long startTime = 0;

    private boolean started = false;

    private int executeTimes = 0;

    public SleepCommand(long millisecondds, ActiveObjectEngine e, Command wakeupCommand) {
        sleepTime = millisecondds;
        engine = e;
        this.wakeupCommand = wakeupCommand;
    }

    public void execute() {
        long currentTime = System.currentTimeMillis();
        //System.out.println("executeTimes:" + ++executeTimes);
        if (!started) {
            started = true;
            startTime = currentTime;
            engine.addCommands(this);
        } else if ((currentTime - startTime) < sleepTime) {
            engine.addCommands(this);
        } else {
            engine.addCommands(wakeupCommand);
        }
    }
}
