package Commands;

import Duke.Storage;
import Tasks.Task;
import Tasks.TaskList;
import Duke.UI;
import ErrorHandling.DukeException;
import Tasks.Event;

public class EventCommand extends Command {
    public EventCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        String newInput = input.substring(6);
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
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(ui.errorMessages.taskWrongFormat("event"));
        }

        storage.update(tasks);
    }
}
