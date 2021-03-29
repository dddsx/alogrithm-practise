package activeobject;

public class DelayedTyper implements Command {

    private long itsDelay;

    private char itsChar;

    private static ActiveObjectEngine engine = new ActiveObjectEngine();

    private static boolean stop = false;

    public DelayedTyper(long delay, char c) {
        itsDelay = delay;
        itsChar = c;
    }

    public void execute() {
        System.out.print(itsChar);
        if (!stop) {
            delayAndRepeat();
        }
    }

    private void delayAndRepeat() {
        engine.addCommands(new SleepCommand(itsDelay, engine, this));
    }

    public static void main(String[] args) {
        engine.addCommands(new DelayedTyper(100, '1'));
        engine.addCommands(new DelayedTyper(300, '3'));
        engine.addCommands(new DelayedTyper(500, '5'));
        engine.addCommands(new DelayedTyper(700, '7'));
        Command stopCommand = () -> stop = true;
        engine.addCommands(new SleepCommand(10000, engine, stopCommand));
        engine.run();
    }
 }
