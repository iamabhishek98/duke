import java.util.ArrayList;

public class List {
    protected final String SPACE = "\t    ";
    protected ArrayList<Task> listOfTasks = new ArrayList<>();

    public void markDone(String input) throws DukeException {
        String[] newInput = input.split(" ");
        if(newInput.length == 1) throw new DukeException(ErrorMessages.taskDescriptionEmpty("delete"));
        try {
            int i = Integer.parseInt(newInput[1]);
            if (i < 0 || i > listOfTasks.size()) throw new DukeException(ErrorMessages.invalidIndex());
            listOfTasks.get(i - 1).isDone = true;
            InOut.output("Nice! I've marked this task as done:\n" + SPACE + listOfTasks.get(i - 1).getItem());
        } catch (ArrayIndexOutOfBoundsException e) {
            InOut.output(ErrorMessages.taskWrongFormat("delete"));
        }
    }

    public void markDelete(String input) throws DukeException {
        String[] newInput = input.split(" ");
        if(newInput.length == 1) throw new DukeException(ErrorMessages.taskDescriptionEmpty("delete"));
        try {
            int i = Integer.parseInt(newInput[1]);
            if (i < 0 || i > listOfTasks.size()) throw new DukeException(ErrorMessages.invalidIndex());
            String numberOfTasks = ((listOfTasks.size() - 1) != 1) ? "tasks" : "task";
            int count = listOfTasks.size() - 1;
            InOut.output("Noted. I've removed this task:\n" + SPACE + listOfTasks.get(i - 1).getItem()
                    + "\n\t Now you have " + count + " " + numberOfTasks + " in the list.");
            listOfTasks.remove(i - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            InOut.output(ErrorMessages.taskWrongFormat("delete"));
        }
    }

    public void markToDo(String input) throws DukeException{
        String newInput = input.substring(5);
        if (newInput.trim().isEmpty()) throw new DukeException(ErrorMessages.taskDescriptionEmpty("todo"));
        Task x = new ToDo(newInput);
        listOfTasks.add(x);
        acknowledgment();
    }

    public void markToDo(String description, boolean isDone) {
        Task x = new ToDo(description,isDone);
        listOfTasks.add(x);
    }

    public void markDeadline(String input) throws DukeException{
        String newInput = input.substring(9);
        if (newInput.trim().isEmpty()) throw new DukeException(ErrorMessages.taskDescriptionEmpty("deadline"));
        try {
            int lastOccurrence = newInput.lastIndexOf(" /by ");
            if (lastOccurrence == -1 || newInput.substring(0,lastOccurrence).trim().isEmpty()) throw new DukeException(ErrorMessages.taskDescriptionEmpty("deadline"));
            String description = newInput.substring(0,lastOccurrence);
            String dateAndTime = newInput.substring(lastOccurrence+5);
            if (dateAndTime.trim().isEmpty()) throw new DukeException(ErrorMessages.taskDateAndTimeEmpty("deadline"));
            Task x = new Deadline(description, dateAndTime);
            if (!x.format) throw new DukeException(ErrorMessages.invalidDateAndTime());
            else {
                listOfTasks.add(x);
                acknowledgment();
            }
        } catch(ArrayIndexOutOfBoundsException e) {
            InOut.output(ErrorMessages.taskWrongFormat("deadline"));
        }
    }

    public void markDeadline(String description, boolean isDone, String date) {
        Task x = new Deadline(description,isDone,date);
        listOfTasks.add(x);
    }

    public void markEvent(String input) throws DukeException{
        String newInput = input.substring(6);
        if (newInput.trim().isEmpty()) throw new DukeException(ErrorMessages.taskDescriptionEmpty("event"));
        try {
            int lastOccurrence = newInput.lastIndexOf(" /at ");
            if (lastOccurrence == -1 || newInput.substring(0,lastOccurrence).trim().isEmpty()) throw new DukeException(ErrorMessages.taskDescriptionEmpty("event"));
            String description = newInput.substring(0,lastOccurrence);
            String dateAndTime = newInput.substring(lastOccurrence+5);
            if (dateAndTime.trim().isEmpty()) throw new DukeException(ErrorMessages.taskDateAndTimeEmpty("event"));
            if (description.trim().isEmpty() || dateAndTime.trim().isEmpty()) throw new DukeException(ErrorMessages.taskDescriptionEmpty("event"));
            Task x = new Event(description, dateAndTime);
            if (!x.format) throw new DukeException(ErrorMessages.invalidDateAndTime());
            else {
                listOfTasks.add(x);
                acknowledgment();
            }
        } catch(ArrayIndexOutOfBoundsException e) {
            InOut.output(ErrorMessages.taskWrongFormat("event"));
        }
    }

    public void markEvent(String description, boolean isDone, String date) {
        Task x = new Event(description,isDone,date);
        listOfTasks.add(x);
    }

    public void printMatchingTasks(String input) throws DukeException{
        String newInput = input.substring(5);
        if (newInput.trim().isEmpty()) throw new DukeException(ErrorMessages.taskDescriptionEmpty("find"));
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
            InOut.output("There are no matching tasks in the list."); return;
        }
        System.out.println(InOut.HORIZONTAL_LINE);
        System.out.println("\t Here are the matching tasks in your list:");
        for (int i = 0 ; i < matchingTasks.size(); i++) {
            int count = i+1;
            System.out.println("\t "+count+". "+matchingTasks.get(i).getItem());
        }
        System.out.println(InOut.HORIZONTAL_LINE);
    }

    public void printList() {
        if (listOfTasks.isEmpty()) {
            InOut.output("There are no tasks in the list."); return;
        }
        System.out.println(InOut.HORIZONTAL_LINE);
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0 ; i < listOfTasks.size(); i++) {
            int count = i+1;
            System.out.println("\t "+count+". "+listOfTasks.get(i).getItem());
        }
        System.out.println(InOut.HORIZONTAL_LINE);
    }

    public String writeListToFile() {
        String output = "";
        for (int i = 0 ; i < listOfTasks.size(); i++) {
            String task = listOfTasks.get(i).getItem().substring(1,2);
            output += (task+" | "+((listOfTasks.get(i).isDone) ? "1":"0")+" | "+listOfTasks.get(i).description);
            if (task.equals("D") || task.equals("E")) {
                String[] temp = listOfTasks.get(i).getItem().split(": ");
                output += " | "+temp[1].substring(0,temp[1].length()-1);
            }
            output+="\n";
        }
        return output;
    }

    protected void acknowledgment() {
        String numberOfTasks = (listOfTasks.size()!=1) ? "tasks":"task";
        InOut.output("Got it. I've added this task:\n" + SPACE + listOfTasks.get(listOfTasks.size()-1).getItem()
                + "\n\t Now you have " + listOfTasks.size() + " " + numberOfTasks + " in the list.");
    }
}