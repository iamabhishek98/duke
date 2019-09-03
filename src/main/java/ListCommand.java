public class ListCommand extends Command {
    public ListCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.printDuke("There are no tasks in the list."); return;
        }
        System.out.println(ui.HORIZONTAL_LINE);
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0 ; i < tasks.size(); i++) {
            int count = i+1;
            System.out.println("\t "+count+". "+tasks.get(i).getItem());
        }
        System.out.println(ui.HORIZONTAL_LINE);

        storage.update(tasks);
    }
}
