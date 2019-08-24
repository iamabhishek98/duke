import java.util.Scanner;

public class Duke {
    public static final String HORIZONTAL_LINE = "\t____________________________________________________________";

    public static void main(String[] args) {
        startup();
        process();
    }

    public static void startup() { //inout
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        output("Hello! I'm Duke\n\tWhat can I do for you?");
    }

    public static void process() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (bye(input)) break;
            else {
                output(input);
            }
        }
        scanner.close();
    }

    public static void output(String input) { //inout
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t"+input);
        System.out.println(HORIZONTAL_LINE);
    }

    public static boolean bye(String input) { //userdefined
        if (input.equals("bye")) {
            output("Bye. Hope to see you again soon!");
            return true;
        }
        return false;
    }

}