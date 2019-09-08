package Commands;

import Duke.Storage;
import Tasks.TaskList;
import Duke.UI;

public class ExitCommand extends Command {
    /**
     * Construction to initialize the variables of the parent class
     *
     * @param description task description
     */
    public ExitCommand(String description) {
        super(description);
    }

    /**
     * Updates the boolean variable isExit in the parent class to true indicating that the user has
     * entered the Exit command
     *
     * @param tasks object containing the task list
     * @param ui object which deals with interactions with the user
     * @param storage object which deals with loading tasks from the file and saving tasks in the file
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.byeDuke();
        super.isExit = true;

        storage.update(tasks);
    }
}
