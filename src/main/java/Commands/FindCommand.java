package Commands;

import Duke.Storage;
import Tasks.Task;
import Tasks.TaskList;
import Duke.UI;
import ErrorHandling.DukeException;

import java.util.ArrayList;

public class FindCommand extends Command {
    public FindCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        String newInput = input.substring(5);
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
