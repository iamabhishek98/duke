package main.Commands;

import main.DukeOperations.Storage;
import main.Tasks.Task;
import main.Tasks.TaskList;
import main.DukeOperations.UI;
import main.ErrorHandling.DukeException;
import main.Tasks.ToDo;

public class ToDoCommand extends Command {
    /**
     * Construction to initialize the variables of the parent class
     *
     * @param description task description
     */
    public ToDoCommand(String description) {
        super(description);
    }

    /**
     * Checks whether the format of the todo command is correct, and if it is, adds the todo task
     * to the task list
     *
     * @param tasks object containing the task list
     * @param ui object which deals with interactions with the user
     * @param storage object which deals with loading tasks from the file and saving tasks in the file
     * @throws DukeException exception thrown when the task description entered by the user is invalid
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        String newInput = super.description.substring(5);
        if (newInput.trim().isEmpty()) throw new DukeException(ui.errorMessages.taskDescriptionEmpty("todo"));
        Task x = new ToDo(newInput);
        tasks.add(x);
        tasks.acknowledgment();

        storage.update(tasks);
    }
}
