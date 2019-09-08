package main.Commands;

import main.DukeOperations.Storage;
import main.Tasks.TaskList;
import main.DukeOperations.UI;
import main.ErrorHandling.DukeException;

abstract public class Command {
    protected String description;
    protected final String SPACE = "\t    ";
    protected boolean isExit;

    /**
     * Constructor to set the entered string to the input variable and to set
     * the boolean variable isExit to false
     *
     * @param description task description
     */
    public Command(String description) {
        this.description = description;
        this.isExit = false;
    }

    /**
     * Getter function for the boolean variable isExit which acts as a flag for
     * whether the Exit command is entered by the user
     *
     * @return isExit
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Function executed upon receiving a command from the user
     *
     * @param tasks object containing the task list
     * @param ui object which deals with interactions with the user
     * @param storage object which deals with loading tasks from the file and saving tasks in the file
     * @throws DukeException exception thrown when the task description entered by the user is invalid
     */
    abstract public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException;
}
