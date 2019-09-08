package Commands;

import Duke.Storage;
import Tasks.Task;
import Tasks.TaskList;
import Duke.UI;
import ErrorHandling.DukeException;
import Tasks.Deadline;

public class DeadlineCommand extends Command {
    /**
     * Construction to initialize the variables of the parent class
     *
     * @param description task description
     */
    public DeadlineCommand(String description) {
        super(description);
    }

    /**
     * Checks whether the format of the deadline command is correct, and if it is, adds the deadline task
     * to the task list
     *
     * @param tasks object containing the task list
     * @param ui object which deals with interactions with the user
     * @param storage object which deals with loading tasks from the file and saving tasks in the file
     * @throws DukeException exception thrown when the task description entered by the user is invalid
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        String newInput = super.description.substring(9);
        if (newInput.trim().isEmpty()) throw new DukeException(ui.errorMessages.taskDescriptionEmpty("deadline"));
        try {
            int lastOccurrence = newInput.lastIndexOf(" /by ");
            String description = newInput.substring(0,lastOccurrence);
            String dateAndTime = newInput.substring(lastOccurrence+5);
            if (description.trim().isEmpty()) throw new DukeException(ui.errorMessages.taskDescriptionEmpty("deadline"));
            if (dateAndTime.trim().isEmpty()) throw new DukeException(ui.errorMessages.taskDateAndTimeEmpty("deadline"));
            Task x = new Deadline(description, dateAndTime);
            if (!x.format) throw new DukeException(ui.errorMessages.invalidDateAndTime());
            else {
                tasks.add(x);
                tasks.acknowledgment();
            }
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException(ui.errorMessages.taskWrongFormat("deadline"));
        }

        storage.update(tasks);
    }
}
