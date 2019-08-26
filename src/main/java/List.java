import java.util.ArrayList;

public class List {
    protected final String SPACE = "\t    ";
    protected ArrayList<Task> listOfTasks = new ArrayList<>();

    public void addList(String input) {
        Task x = new Task(input);
        listOfTasks.add(x);
        InOut.output("added: "+input);
    }

    public void markDone(String input) {
        String[] newInput = input.split(" ");
        try {
            int i = Integer.parseInt(newInput[1]);
            listOfTasks.get(i - 1).isDone = true;
            InOut.output("Nice! I've marked this task as done:\n" + SPACE + listOfTasks.get(i - 1).getItem());
        } catch(Exception e) {
            DukeException.invalidIndex();
        }
    }

    public void markDelete(String input) {
        String[] newInput = input.split(" ");
        try {
            int i = Integer.parseInt(newInput[1]);
            String numberOfTasks = ((listOfTasks.size()-1)!=1) ? "tasks":"task";
            int count = listOfTasks.size()-1;
            InOut.output("Noted. I've removed this task:\n" + SPACE + listOfTasks.get(i - 1).getItem()+"\n\t Now you have "+count+" "+numberOfTasks+" in the list.");
            listOfTasks.remove(i-1);
        } catch(Exception e) {
            DukeException.invalidIndex();
        }
    }

    public void markToDo(String input) {
        String newInput = input.substring(5);
        if (newInput.isEmpty()) DukeException.taskEmpty("todo");
        else {
            Task x = new ToDo(newInput);
            listOfTasks.add(x);
            acknowledgment();
        }
    }

    public void markToDo(String description, boolean isDone) {
        Task x = new ToDo(description,isDone);
        if (!x.description.isEmpty()) listOfTasks.add(x);
    }

    public void markDeadline(String input) {
        String newInput = input.substring(9);
        if (newInput.isEmpty()) DukeException.taskEmpty("deadline");
        else {
            try {
                String[] finalInput = newInput.split(" /by ");
                Task x = new Deadline(finalInput[0], finalInput[1]);
                if (x.format) {
                    listOfTasks.add(x);
                    acknowledgment();
                } else DukeException.invalidDateAndTime();
            } catch(Exception e) {
                DukeException.taskWrongFormat("deadline");
            }
        }
    }

    public void markDeadline(String description, boolean isDone, String date) {
        Task x = new Deadline(description,isDone,date);
        listOfTasks.add(x);
    }

    public void markEvent(String input) {
        String newInput = input.substring(6);
        if (newInput.isEmpty()) DukeException.taskEmpty("event");
        else {
            try {
                String[] finalInput = newInput.split(" /at ");
                Task x = new Event(finalInput[0], finalInput[1]);
                if (x.format) {
                    listOfTasks.add(x);
                    acknowledgment();
                } else DukeException.invalidDateAndTime();
            } catch(Exception e) {
                DukeException.taskWrongFormat("event");
            }
        }
    }

    public void markEvent(String description, boolean isDone, String date) {
        Task x = new Event(description,isDone,date);
        listOfTasks.add(x);
    }

    public void printMatchingTasks(String input) {
        String newInput = input.substring(5);
        if (newInput.isEmpty()) DukeException.taskEmpty("find");
        else {
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
            output += (task+";"+listOfTasks.get(i).isDone+";"+listOfTasks.get(i).description);
            if (task.equals("D") || task.equals("E")) {
                String[] temp = listOfTasks.get(i).getItem().split(": ");
                output += ";"+temp[1].substring(0,temp[1].length()-1);
            }
            output+="\n";
        }
        return output;
    }

    protected void acknowledgment() {
        String numberOfTasks = (listOfTasks.size()!=1) ? "tasks":"task";
        InOut.output("Got it. I've added this task:\n"+SPACE+listOfTasks.get(listOfTasks.size()-1).getItem()+"\n\t Now you have "+listOfTasks.size()+" "+numberOfTasks+" in the list.");
    }
}