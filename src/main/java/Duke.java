import java.util.Scanner;

public class Duke {
    private static List list = new List();

    public static void main(String[] args) {
        startup();
        process();
    }

    public static void startup() {
        String tab = "\t\t\t\t\t\t";
        String logo = tab+" ____        _        \n"
                +tab+ "|  _ \\ _   _| | _____ \n"
                +tab+ "| | | | | | | |/ / _ \\\n"
                +tab+ "| |_| | |_| |   <  __/\n"
                +tab+"|____/ \\__,_|_|\\_\\___|\n";
        System.out.print(logo);
        InOut.output("Hello! I'm Duke\n\t What can I do for you?");
    }

    public static void process() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (bye(input)) break;
            else if (input.equals("list")) list.printList();
            else if (input.length()>=5 && input.substring(0,5).equals("done ")) list.markDone(input);
            else if (input.length()>=5 && input.substring(0,5).equals("todo ")) list.markToDo(input);
            else if (input.length()>=9 && input.substring(0,9).equals("deadline ")) list.markDeadline(input);
            else if (input.length()>=6 && input.substring(0,6).equals("event ")) list.markEvent(input);
            else {
                list.addList(input);
            }
        }
        scanner.close();
    }

    public static boolean bye(String input) {
        if (input.equals("bye")) {
            InOut.output("Bye. Hope to see you again soon!");
            return true;
        }
        return false;
    }
}