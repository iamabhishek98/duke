import java.util.ArrayList;

public class List {
    protected final String SPACE = "\t    ";
    protected ArrayList<Task> list = new ArrayList<>();

    public void addList(String input) {
        Task x = new Task(input);
        list.add(x);
        InOut.output("added: "+input);
    }

    public void markDone(String input) {
        String[] newInput = input.split(" ");
        int i = Integer.parseInt(newInput[1]);
        if (i>list.size()||i<=0) {
            InOut.output("Invalid entry."); return;
        }
        list.get(i - 1).isDone = true;
        InOut.output("Nice! I've marked this task as done:\n"+SPACE+list.get(i-1).getItem());
    }

    public void markToDo(String input) {
        String newInput = input.substring(5);
        Task x = new ToDo(newInput);
        list.add(x);
        acknowledgment();
    }

    public void markDeadline(String input) {
        String newInput = input.substring(9);
        String[] finalInput = newInput.split(" /by ");
        Task x = new Deadline(finalInput[0],finalInput[1]);
        list.add(x);
        acknowledgment();
    }

    public void markEvent(String input) {
        String newInput = input.substring(6);
        String[] finalInput = newInput.split(" /at ");
        Task x = new Event(finalInput[0],finalInput[1]);
        list.add(x);
        acknowledgment();
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

    protected void acknowledgment() {
        String numberOfTasks = (list.size()>1) ? "tasks":"task";
        InOut.output("Got it. I've added this task:\n"+SPACE+list.get(list.size()-1).getItem()+"\n\t Now you have "+list.size()+" "+numberOfTasks+" in the list.");
    }

}