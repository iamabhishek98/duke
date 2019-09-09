package Duke.Tasks;

public class ToDo extends Task {
    /**
     * Constructor to instantiate variables of parent class called when
     * reading user input
     *
     * @param description string containing the task description
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructor to instantiate variables of parent class called when
     * reading from persistent file storage
     *
     * @param description string containing the task description
     * @param isDone boolean variable indicating whether the task has been marked as done or not
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the string containing the task description in the Duke.Duke.DukeOperations format
     *
     * @return string in the specific format
     */
    @Override
    public String getItem() {
        return "[T]"+super.getItem();
    }
}