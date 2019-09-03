public class ToDoCommand extends Command {
    public ToDoCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        String newInput = input.substring(5);
        if (newInput.trim().isEmpty()) throw new DukeException(ui.errorMessages.taskDescriptionEmpty("todo"));
        Task x = new ToDo(newInput);
        tasks.add(x);
        tasks.acknowledgment();

        storage.update(tasks);
    }
}
