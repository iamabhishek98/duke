package main.Commands;

import main.DukeOperations.Storage;
import main.Tasks.TaskList;
import main.DukeOperations.UI;

public class ListCommand extends Command {
    public ListCommand(String input) {
        super(input);
    }

    /**
     * Displays the list of tasks
     *
     * @param tasks object containing the task list
     * @param ui object which deals with interactions with the user
     * @param storage object which deals with loading tasks from the file and saving tasks in the file
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.printDuke("There are no tasks in the list."); return;
        }
        System.out.println(ui.HORIZONTAL_LINE);
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0 ; i < tasks.size(); i++) {
            System.out.println("\t "+(i+1)+". "+tasks.get(i).getItem());
        }
        System.out.println(ui.HORIZONTAL_LINE);

        storage.update(tasks);
    }
}
