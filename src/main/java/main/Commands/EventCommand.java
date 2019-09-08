package main.Commands;

import main.DukeOperations.Storage;
import main.Tasks.Task;
import main.Tasks.TaskList;
import main.DukeOperations.UI;
import main.ErrorHandling.DukeException;
import main.Tasks.Event;

public class EventCommand extends Command {
    public EventCommand(String input) {
        super(input);
    }

    /**
     * Checks whether the format of the event command is correct, and if it is, adds the event task to
     * the task list
     *
     * @param tasks object containing the task list
     * @param ui object which deals with interactions with the user
     * @param storage object which deals with loading tasks from the file and saving tasks in the file
     * @throws DukeException exception thrown when the task description entered by the user is invalid
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        String newInput = super.description.substring(6);
        if (newInput.trim().isEmpty()) throw new DukeException(ui.errorMessages.taskDescriptionEmpty("event"));
        try {
            int lastOccurrence = newInput.lastIndexOf(" /at ");
            String description = newInput.substring(0,lastOccurrence);
            String dateAndTime = newInput.substring(lastOccurrence+5);
            if (description.trim().isEmpty()) throw new DukeException(ui.errorMessages.taskDescriptionEmpty("event"));
            if (dateAndTime.trim().isEmpty()) throw new DukeException(ui.errorMessages.taskDateAndTimeEmpty("event"));
            Task x = new Event(description, dateAndTime);
            if (!x.format) throw new DukeException(ui.errorMessages.invalidDateAndTime());
            else {
                tasks.add(x);
                tasks.acknowledgment();
            }
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException(ui.errorMessages.taskWrongFormat("event"));
        }

        storage.update(tasks);
    }
}
