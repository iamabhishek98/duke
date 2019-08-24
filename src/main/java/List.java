import java.util.ArrayList;

public class List {
    private final String HORIZONTAL_LINE = "\t____________________________________________________________";
    private ArrayList<Task> list = new ArrayList<>();

    public void addList(String input) {
        Task x = new Task(input);
        list.add(x);
        output("added: "+input);
    }

    public void markDone(String input) {
        String[] inputWords = input.split(" ");
        int i = Integer.parseInt(inputWords[1]);
        if (i>list.size()||i<=0) {
            output("Invalid entry."); return;
        }
        String description = list.get(i - 1).description;
        list.get(i - 1).isDone = true;
        output("Nice! I've marked this task as done:\n"+"\t    [" + "\u2713" + "] " + description);
    }

    public void printList() { //inout
        if (list.isEmpty()) {
            output("There are no tasks in the list."); return;
        }
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0 ; i < list.size(); i++) {
            int count = i+1;
            System.out.println("\t "+count+". ["+list.get(i).getStatusIcon()+"] "+list.get(i).description);
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private void output(String input) { //inout
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t "+input);
        System.out.println(HORIZONTAL_LINE);
    }
}