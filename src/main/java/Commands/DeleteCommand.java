package Commands;

import Duke.Storage;
import Tasks.TaskList;
import Duke.UI;
import ErrorHandling.DukeException;

public class DeleteCommand extends Command {
    /**
     * Construction to initialize the variables of the parent class
     *
     * @param description task description
     */
    public DeleteCommand(String description) {
        super(description);
    }

    /**
     * Checks whether the format of the delete command is correct, and if it is, deletes the task using its index
     * from the task list
     *
     * @param tasks object containing the task list
     * @param ui object which deals with interactions with the user
     * @param storage object which deals with loading tasks from the file and saving tasks in the file
     * @throws DukeException exception thrown when the task description entered by the user is invalid
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        String[] newInput = super.description.split(" ");
        if(newInput.length == 1) throw new DukeException(ui.errorMessages.taskDescriptionEmpty("delete"));
        try {
            int i = Integer.parseInt(newInput[1]);
            if (i < 0 || i > tasks.size()) throw new DukeException(ui.errorMessages.invalidIndex());
            String numberOfTasks = ((tasks.size() - 1) != 1) ? "tasks" : "task";
            int count = tasks.size() - 1;
            ui.printDuke("Noted. I've removed this task:\n" + SPACE + tasks.get(i - 1).getItem()
                    + "\n\t Now you have " + count + " " + numberOfTasks + " in the list.");
            tasks.remove(i - 1);
        } catch (NumberFormatException e) {
            throw new DukeException(ui.errorMessages.taskWrongFormat("delete"));
        }

        storage.update(tasks);
    }
}
