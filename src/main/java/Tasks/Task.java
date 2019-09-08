package Tasks;

public class Task {
    public String description;
    public boolean isDone;
    public boolean format;

    /**
     * Constructor instantiate the description, isDone and format variables
     * called when reading user input
     *
     * @param description string containing the task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.format = true;
    }

    /**
     * Constructor instantiate the description, isDone and format variables
     * called when reading from persistent file storage
     *
     * @param description string containing the task description
     * @param isDone boolean variable indicating whether the task has been marked as done or not
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.format = true;
    }

    /**
     * Returns tick or X symbols
     *
     * @return returns the valid symbol
     */
    private String getStatusIcon() {
        return "[" + (this.isDone ? "\u2713" : "\u2718") + "] ";
    }

    /**
     * Returns the task description in the Duke format
     *
     * @return the string in the specific format
     */
    public String getItem() {
        return this.getStatusIcon() + this.description;
    }
}