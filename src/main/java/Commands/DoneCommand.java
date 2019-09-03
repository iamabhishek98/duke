package Commands;

import Duke.Storage;
import Tasks.TaskList;
import Duke.UI;
import ErrorHandling.DukeException;

public class DoneCommand extends Command {
    public DoneCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        String[] newInput = input.split(" ");
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
