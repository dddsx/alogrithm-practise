package activeobject;

import java.util.LinkedList;

public class ActiveObjectEngine {

    LinkedList<Command> itsCommands = new LinkedList<>();

    public void addCommands(Command c) {
        itsCommands.add(c);
    }

    public void run() {
        while (!itsCommands.isEmpty()) {
            Command c = itsCommands.getFirst();
            itsCommands.removeFirst();
            c.execute();
        }
    }
}
