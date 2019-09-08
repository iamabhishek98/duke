package Commands;

import Duke.Storage;
import Tasks.TaskList;
import Duke.UI;
import ErrorHandling.DukeException;

public class DoneCommand extends Command {
    /**
     * Construction to initialize the variables of the parent class
     *
     * @param description task description
     */
    public DoneCommand(String description) {
        super(description);
    }

    /**
     * Checks whether the format of the done command is correct, and if it is, marks the task as done
     *
     * @param tasks object containing the task list
     * @param ui object which deals with interactions with the user
     * @param storage object which deals with loading tasks from the file and saving tasks in the file
     * @throws DukeException exception thrown when the task description entered by the user is invalid
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        String[] newInput = super.description.split(" ");
        if(newInput.length == 1) throw new DukeException(ui.errorMessages.taskDescriptionEmpty("done"));
        try {
            int i = Integer.parseInt(newInput[1]);
            if (i < 0 || i > tasks.size()) throw new DukeException(ui.errorMessages.invalidIndex());
            tasks.get(i - 1).isDone = true;
            ui.printDuke("Nice! I've marked this task as done:\n" + super.SPACE + tasks.get(i - 1).getItem());
        } catch (NumberFormatException e) {
            throw new DukeException(ui.errorMessages.taskWrongFormat("done"));
        }

        storage.update(tasks);
    }
}
