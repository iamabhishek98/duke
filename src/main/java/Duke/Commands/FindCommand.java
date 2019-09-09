package Duke.Commands;

import Duke.DukeOperations.Storage;
import Duke.Tasks.Task;
import Duke.Tasks.TaskList;
import Duke.DukeOperations.UI;
import Duke.ErrorHandling.DukeException;

import java.util.ArrayList;

public class FindCommand extends Command {
    /**
     * Construction to initialize the variables of the parent class
     *
     * @param description task description
     */
    public FindCommand(String description) {
        super(description);
    }

    /**
     * Checks whether the format of the find command is correct, and if it is, returns the matching tasks
     * to the keyword(s) entered by the user
     *
     * @param tasks object containing the task list
     * @param ui object which deals with interactions with the user
     * @param storage object which deals with loading tasks from the file and saving tasks in the file
     * @throws DukeException exception thrown when the task description entered by the user is invalid
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        String newInput = super.description.substring(5);
        if (newInput.trim().isEmpty()) throw new DukeException(ui.errorMessages.taskDescriptionEmpty("find"));
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            String description = tasks.get(i).description;
            if (newInput.length() > description.length()) continue;
            if (description.contains(newInput)) matchingTasks.add(tasks.get(i));
//            String[] descriptions = tasks.get(i).description.split(" ");
//            for (int j = 0; j < descriptions.length; j++) {
//                if (newInput.equals(descriptions[j])) {
//                    matchingTasks.add(tasks.get(i)); break;
//                }
//            }
        }
        if (matchingTasks.isEmpty()) {
            ui.printDuke("There are no matching tasks in the list."); return;
        }
        System.out.println(ui.HORIZONTAL_LINE);
        System.out.println("\t Here are the matching tasks in your list:");
        for (int i = 0 ; i < matchingTasks.size(); i++) {
            System.out.println("\t "+(i+1)+". "+matchingTasks.get(i).getItem());
        }
        System.out.println(ui.HORIZONTAL_LINE);

        storage.update(tasks);
    }
}
