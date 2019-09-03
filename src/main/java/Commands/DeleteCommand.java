package Commands;

import Duke.Storage;
import Tasks.TaskList;
import Duke.UI;
import ErrorHandling.DukeException;

public class DeleteCommand extends Command {
    public DeleteCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        String[] newInput = input.split(" ");
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
