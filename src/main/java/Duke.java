import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "\t____________________________________________________________";
    private static List list = new List();

    public static void main(String[] args) {
        startup();
        process();
    }

    public static void startup() { //inout
        String tab = "\t\t\t\t\t\t";
        String logo = tab+" ____        _        \n"
                +tab+ "|  _ \\ _   _| | _____ \n"
                +tab+ "| | | | | | | |/ / _ \\\n"
                +tab+ "| |_| | |_| |   <  __/\n"
                +tab+"|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        output("Hello! I'm Duke\n\t What can I do for you?");
    }

    public static void process() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (bye(input)) break;
            else if (input.equals("list")) list.printList();
            else if (input.length()>=4 && input.substring(0,4).equals("done")) list.markDone(input);
            else {
                list.addList(input);
            }
        }
        scanner.close();
    }

    public static boolean bye(String input) { //userdefined
        if (input.equals("bye")) {
            output("Bye. Hope to see you again soon!");
            return true;
        }
        return false;
    }

    private static void output(String input) { //inout
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t "+input);
        System.out.println(HORIZONTAL_LINE);
    }
}