package Commands;

import Duke.Storage;
import Tasks.TaskList;
import Duke.UI;
import ErrorHandling.DukeException;

abstract public class Command {
    protected String input;
    protected final String SPACE = "\t    ";
    protected boolean isExit;

    public Command(String input) {
        this.input = input;
        isExit = false;
    }

    public boolean isExit() {
        return this.isExit;
    }

    abstract public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException;
}
