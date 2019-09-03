package Commands;

import Duke.Storage;
import Tasks.TaskList;
import Duke.UI;

public class ExitCommand extends Command {
    public ExitCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.byeDuke();
        super.isExit = true;

        storage.update(tasks);
    }
}
