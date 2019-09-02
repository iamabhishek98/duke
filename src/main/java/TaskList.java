import java.util.ArrayList;

public class TaskList {
    private final String SPACE = "\t    ";
    private ArrayList<Task> listOfTasks;
    private ErrorMessages errorMessages;
    private UI ui;

    public TaskList() {
        listOfTasks = new ArrayList<>();
        errorMessages = new ErrorMessages();
        ui = new UI();
    }

    // for user input "done ..."
    public void markDone(String input) throws DukeException {
        String[] newInput = input.split(" ");
        if(newInput.length == 1) throw new DukeException(errorMessages.taskDescriptionEmpty("done"));
        try {
            int i = Integer.parseInt(newInput[1]);
            if (i < 0 || i > listOfTasks.size()) throw new DukeException(errorMessages.invalidIndex());
            listOfTasks.get(i - 1).isDone = true;
            ui.printDuke("Nice! I've marked this task as done:\n" + SPACE + listOfTasks.get(i - 1).getItem());
        } catch (NumberFormatException e) {
            throw new DukeException(errorMessages.taskWrongFormat("done"));
        }
    }

    // for user input "delete ..."
    public void markDelete(String input) throws DukeException {
        String[] newInput = input.split(" ");
        if(newInput.length == 1) throw new DukeException(errorMessages.taskDescriptionEmpty("delete"));
        try {
            int i = Integer.parseInt(newInput[1]);
            if (i < 0 || i > listOfTasks.size()) throw new DukeException(errorMessages.invalidIndex());
            String numberOfTasks = ((listOfTasks.size() - 1) != 1) ? "tasks" : "task";
            int count = listOfTasks.size() - 1;
            ui.printDuke("Noted. I've removed this task:\n" + SPACE + listOfTasks.get(i - 1).getItem()
                    + "\n\t Now you have " + count + " " + numberOfTasks + " in the list.");
            listOfTasks.remove(i - 1);
        } catch (NumberFormatException e) {
            throw new DukeException(errorMessages.taskWrongFormat("delete"));
        }
    }

    // for user entry "todo ..."
    public void markToDo(String input) throws DukeException{
        String newInput = input.substring(5);
        if (newInput.trim().isEmpty()) throw new DukeException(errorMessages.taskDescriptionEmpty("todo"));
        Task x = new ToDo(newInput);
        listOfTasks.add(x);
        acknowledgment();
    }

    // reading from database
    public void markToDo(String description, boolean isDone) {
        Task x = new ToDo(description,isDone);
        listOfTasks.add(x);
    }

    // for user entry "deadline ..."
    public void markDeadline(String input) throws DukeException{
        String newInput = input.substring(9);
        if (newInput.trim().isEmpty()) throw new DukeException(errorMessages.taskDescriptionEmpty("deadline"));
        try {
            int lastOccurrence = newInput.lastIndexOf(" /by ");
            String description = newInput.substring(0,lastOccurrence);
            String dateAndTime = newInput.substring(lastOccurrence+5);
            if (description.trim().isEmpty()) throw new DukeException(errorMessages.taskDescriptionEmpty("deadline"));
            if (dateAndTime.trim().isEmpty()) throw new DukeException(errorMessages.taskDateAndTimeEmpty("deadline"));
            Task x = new Deadline(description, dateAndTime);
            if (!x.format) throw new DukeException(errorMessages.invalidDateAndTime());
            else {
                listOfTasks.add(x);
                acknowledgment();
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(errorMessages.taskWrongFormat("deadline"));
        }
    }

    // reading from database
    public void markDeadline(String description, boolean isDone, String date) {
        Task x = new Deadline(description,isDone,date);
        listOfTasks.add(x);
    }

    // for user entry "event ..."
    public void markEvent(String input) throws DukeException{
        String newInput = input.substring(6);
        if (newInput.trim().isEmpty()) throw new DukeException(errorMessages.taskDescriptionEmpty("event"));
        try {
            int lastOccurrence = newInput.lastIndexOf(" /at ");
            String description = newInput.substring(0,lastOccurrence);
            String dateAndTime = newInput.substring(lastOccurrence+5);
            if (description.trim().isEmpty()) throw new DukeException(errorMessages.taskDescriptionEmpty("event"));
            if (dateAndTime.trim().isEmpty()) throw new DukeException(errorMessages.taskDateAndTimeEmpty("event"));
            Task x = new Event(description, dateAndTime);
            if (!x.format) throw new DukeException(errorMessages.invalidDateAndTime());
            else {
                listOfTasks.add(x);
                acknowledgment();
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(errorMessages.taskWrongFormat("event"));
        }
    }

    // reading from database
    public void markEvent(String description, boolean isDone, String date) {
        Task x = new Event(description,isDone,date);
        listOfTasks.add(x);
    }

    // for user input "find ..."
    public void printMatchingTasks(String input) throws DukeException{
        String newInput = input.substring(5);
        if (newInput.trim().isEmpty()) throw new DukeException(errorMessages.taskDescriptionEmpty("find"));
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < listOfTasks.size(); i++) {
            String[] descriptions = listOfTasks.get(i).description.split(" ");
            for (int j = 0; j < descriptions.length; j++) {
                if (newInput.equals(descriptions[j])) {
                    matchingTasks.add(listOfTasks.get(i)); break;
                }
            }
        }
        if (matchingTasks.isEmpty()) {
            ui.printDuke("There are no matching tasks in the list."); return;
        }
        System.out.println(ui.HORIZONTAL_LINE);
        System.out.println("\t Here are the matching tasks in your list:");
        for (int i = 0 ; i < matchingTasks.size(); i++) {
            int count = i+1;
            System.out.println("\t "+count+". "+matchingTasks.get(i).getItem());
        }
        System.out.println(ui.HORIZONTAL_LINE);
    }

    // for user input "list"
    public void printList() {
        if (listOfTasks.isEmpty()) {
            ui.printDuke("There are no tasks in the list."); return;
        }
        System.out.println(ui.HORIZONTAL_LINE);
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0 ; i < listOfTasks.size(); i++) {
            int count = i+1;
            System.out.println("\t "+count+". "+listOfTasks.get(i).getItem());
        }
        System.out.println(ui.HORIZONTAL_LINE);
    }

    // to update database with new list
    public StringBuilder writeListToFile() {
        StringBuilder output = new StringBuilder();
        for (int i = 0 ; i < listOfTasks.size(); i++) {
            String task = listOfTasks.get(i).getItem().substring(1,2);
            output.append(task+" | "+((listOfTasks.get(i).isDone) ? "1":"0")+" | "+listOfTasks.get(i).description);
            if (task.equals("D") || task.equals("E")) {
                String[] temp = listOfTasks.get(i).getItem().split(": ");
                output.append(" | "+temp[1].substring(0,temp[1].length()-1));
            }
            output.append("\n");
        }
        return output;
    }

    // acknowledgement upon adding task to the list
    private void acknowledgment() {
        String numberOfTasks = (listOfTasks.size()!=1) ? "tasks":"task";
        ui.printDuke("Got it. I've added this task:\n" + SPACE + listOfTasks.get(listOfTasks.size()-1).getItem()
                + "\n\t Now you have " + listOfTasks.size() + " " + numberOfTasks + " in the list.");
    }
}