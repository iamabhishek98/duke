import java.util.ArrayList;

public class TaskList {
    private final String SPACE = "\t    ";
    private ArrayList<Task> tasks;
    private UI ui;

    public TaskList() {
        tasks = new ArrayList<>();
        ui = new UI();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void remove(int i) {
        tasks.remove(i);
    }

    public void add(Task i) {
        tasks.add(i);
    }

    // reading from database
    public void addToDo(String description, boolean isDone) {
        Task x = new ToDo(description,isDone);
        tasks.add(x);
    }

    // reading from database
    public void addDeadline(String description, boolean isDone, String date) {
        Task x = new Deadline(description,isDone,date);
        tasks.add(x);
    }

    // reading from database
    public void addEvent(String description, boolean isDone, String date) {
        Task x = new Event(description,isDone,date);
        tasks.add(x);
    }

    // to update database with new list
    public StringBuilder writeListToFile() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            String task = tasks.get(i).getItem().substring(1,2);
            output.append(task+" | "+((tasks.get(i).isDone) ? "1":"0")+" | "+ tasks.get(i).description);
            if (task.equals("D") || task.equals("E")) {
                String[] temp = tasks.get(i).getItem().split(": ");
                output.append(" | "+temp[1].substring(0,temp[1].length()-1));
            }
            output.append("\n");
        }
        return output;
    }

    // acknowledgement upon adding task to the list
    public void acknowledgment() {
        String numberOfTasks = (tasks.size()!=1) ? "tasks":"task";
        ui.printDuke("Got it. I've added this task:\n" + SPACE + tasks.get(tasks.size()-1).getItem()
                + "\n\t Now you have " + tasks.size() + " " + numberOfTasks + " in the list.");
    }
}