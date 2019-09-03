public class DeadlineCommand extends Command {
    public DeadlineCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        String newInput = input.substring(9);
        if (newInput.trim().isEmpty()) throw new DukeException(ui.errorMessages.taskDescriptionEmpty("deadline"));
        try {
            int lastOccurrence = newInput.lastIndexOf(" /by ");
            String description = newInput.substring(0,lastOccurrence);
            String dateAndTime = newInput.substring(lastOccurrence+5);
            if (description.trim().isEmpty()) throw new DukeException(ui.errorMessages.taskDescriptionEmpty("deadline"));
            if (dateAndTime.trim().isEmpty()) throw new DukeException(ui.errorMessages.taskDateAndTimeEmpty("deadline"));
            Task x = new Deadline(description, dateAndTime);
            if (!x.format) throw new DukeException(ui.errorMessages.invalidDateAndTime());
            else {
                tasks.add(x);
                tasks.acknowledgment();
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(ui.errorMessages.taskWrongFormat("deadline"));
        }

        storage.update(tasks);
    }
}
