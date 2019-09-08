package main.Tasks;

import main.DukeOperations.UI;
import java.util.ArrayList;

public class TaskList {
    private final String SPACE = "\t    ";
    private ArrayList<Task> tasks;
    private UI ui;

    /**
     * Constructor to instantiate the ui and tasks objects
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.ui = new UI();
    }

    /**
     * Returns the isEmpty attribute of the object
     *
     * @return attribute of ArrayList
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Returns the size attribute of the object
     *
     * @return attribute of ArrayList
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Retrieves the object at the index
     *
     * @param i integer containing the index of the object
     * @return object retrieved at the index
     */
    public Task get(int i) {
        return this.tasks.get(i);
    }

    /**
     * Removes the object at the index
     *
     * @param i integer containing the index of the object
     */
    public void remove(int i) {
        this.tasks.remove(i);
    }

    /**
     * Adds the object to the ArrayList
     *
     * @param i integer containing the index of the object
     */
    public void add(Task i) {
        this.tasks.add(i);
    }

    /**
     * Adds the task read from the persistent file storage to the ArrayList
     * @param description the string containing the task description
     * @param isDone the boolean variable indicating whether the task has been marked as done or not
     */
    public void addToDo(String description, boolean isDone) {
        Task x = new ToDo(description,isDone);
        this.tasks.add(x);
    }

    /**
     * Adds the task read from the persistent file storage to the ArrayList
     * @param description the string containing the task description
     * @param isDone the boolean variable indicating whether the task has been marked as done or not
     */
    public void addDeadline(String description, boolean isDone, String date) {
        Task x = new Deadline(description,isDone,date);
        this.tasks.add(x);
    }

    /**
     * Adds the task read from the persistent file storage to the ArrayList
     * @param description the string containing the task description
     * @param isDone the boolean variable indicating whether the task has been marked as done or not
     */
    public void addEvent(String description, boolean isDone, String date) {
        Task x = new Event(description,isDone,date);
        this.tasks.add(x);
    }

    /**
     * Appends all the tasks in the ArrayList to a string and returns it
     *
     * @return appended string containing task list in main.main.DukeOperations Format
     */
    public StringBuilder writeListToFile() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            String task = this.tasks.get(i).getItem().substring(1,2);
            output.append(task+" | "+((this.tasks.get(i).isDone) ? "1":"0")+" | "+ this.tasks.get(i).description);
            if (task.equals("D") || task.equals("E")) {
                String[] temp = this.tasks.get(i).getItem().split(": ");
                output.append(" | "+temp[1].substring(0,temp[1].length()-1));
            }
            output.append("\n");
        }
        return output;
    }

    /**
     * Prints the acknowledgement upon adding a task to the list
     */
    public void acknowledgment() {
        String numberOfTasks = (this.tasks.size()!=1) ? "tasks":"task";
        this.ui.printDuke("Got it. I've added this task:\n" + SPACE + this.tasks.get(this.tasks.size()-1).getItem()
                + "\n\t Now you have " + this.tasks.size() + " " + numberOfTasks + " in the list.");
    }
}