import java.util.ArrayList;

public class List {
    protected final String SPACE = "\t    ";
    public ArrayList<Task> list = new ArrayList<>();

    public void addList(String input) {
        Task x = new Task(input);
        list.add(x);
        InOut.output("added: "+input);
    }

    public void markDone(String input) {
        String[] newInput = input.split(" ");
        try {
            int i = Integer.parseInt(newInput[1]);
            list.get(i - 1).isDone = true;
            InOut.output("Nice! I've marked this task as done:\n" + SPACE + list.get(i - 1).getItem());
        } catch(Exception e) {
            DukeException.invalidIndex();
        }
    }

    public void markDelete(String input) {
        String[] newInput = input.split(" ");
        try {
            int i = Integer.parseInt(newInput[1]);
            String numberOfTasks = ((list.size()-1)!=1) ? "tasks":"task";
            int count = list.size()-1;
            InOut.output("Noted. I've removed this task:\n" + SPACE + list.get(i - 1).getItem()+"\n\t Now you have "+count+" "+numberOfTasks+" in the list.");
            list.remove(i-1);
        } catch(Exception e) {
            DukeException.invalidIndex();
        }
    }

    public void markToDo(String input) {
        String newInput = input.substring(5);
        if (newInput.isEmpty()) DukeException.taskEmpty("todo");
        else {
            Task x = new ToDo(newInput);
            list.add(x);
            acknowledgment();
        }
    }

    public void markToDo(String description, boolean isDone) {
        Task x = new ToDo(description,isDone);
        list.add(x);
    }

    public void markDeadline(String input) {
        String newInput = input.substring(9);
        if (newInput.isEmpty()) DukeException.taskEmpty("deadline");
        else {
            try {
                String[] finalInput = newInput.split(" /by ");
                Task x = new Deadline(finalInput[0], finalInput[1]);
                list.add(x);
                acknowledgment();
            } catch(Exception e) {
                DukeException.taskWrongFormat("deadline");
            }
        }
    }

    public void markDeadline(String description, boolean isDone, String date) {
        Task x = new Deadline(description,isDone,date);
        list.add(x);
    }

    public void markEvent(String input) {
        String newInput = input.substring(6);
        if (newInput.isEmpty()) DukeException.taskEmpty("event");
        else {
            try {
                String[] finalInput = newInput.split(" /at ");
                Task x = new Event(finalInput[0], finalInput[1]);
                list.add(x);
                acknowledgment();
            } catch(Exception e) {
                DukeException.taskWrongFormat("event");
            }
        }
    }

    public void markEvent(String description, boolean isDone, String date) {
        Task x = new Event(description,isDone,date);
        list.add(x);
    }

    public void printList() { //inout
        if (list.isEmpty()) {
            InOut.output("There are no tasks in the list."); return;
        }
        System.out.println(InOut.HORIZONTAL_LINE);
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0 ; i < list.size(); i++) {
            int count = i+1;
            System.out.println("\t "+count+". "+list.get(i).getItem());
        }
        System.out.println(InOut.HORIZONTAL_LINE);
    }

    public String writeListToFile() {
        String output = "";
        for (int i = 0 ; i < list.size(); i++) {
            String task = list.get(i).getItem().substring(1,2);
            output += (task+";"+list.get(i).isDone+";"+list.get(i).description);
            if (task.equals("D") || task.equals("E")) {
                String[] temp = list.get(i).getItem().split(": ");
                output += ";"+temp[1].substring(0,temp[1].length()-1);
            }
            output+="\n";
        }
        return output;
    }

    protected void acknowledgment() {
        String numberOfTasks = (list.size()!=1) ? "tasks":"task";
        InOut.output("Got it. I've added this task:\n"+SPACE+list.get(list.size()-1).getItem()+"\n\t Now you have "+list.size()+" "+numberOfTasks+" in the list.");
    }
}