import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String HORIZONTAL_LINE = "\t____________________________________________________________";
    public static ArrayList<String> list = new ArrayList<String>();

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

    public static void output(String input) { //inout
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t"+input);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void output() { //inout
        System.out.println(HORIZONTAL_LINE);
        for (int i = 0 ; i < list.size(); i++) {
            int count = i+1;
            System.out.println("\t"+count+". "+list.get(i));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void process() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (bye(input)) break;
            else if (list(input)) continue;
            else {
                add(input);
            }
        }
        scanner.close();
    }

    public static void add(String input) {
        list.add(input);
        output("added: "+input);
    }

    public static boolean list(String input) {
        if (input.equals("list")) {
            output();
            return true;
        }
        return false;
    }

    public static boolean bye(String input) { //userdefined
        if (input.equals("bye")) {
            output("Bye. Hope to see you again soon!");
            return true;
        }
        return false;
    }

}