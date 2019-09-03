package Duke;

import ErrorHandling.ErrorMessages;

import java.util.Scanner;

public class UI {
    public String HORIZONTAL_LINE = "\t___________________________________________________________________________";
    public Scanner scanner;
    public ErrorMessages errorMessages;

    public UI() {
        scanner = new Scanner(System.in);
        errorMessages = new ErrorMessages();
    }

    public void welcomeDuke() {
        String tab = "\t ";
        String logo = tab+" ____        _        \n"
                +tab+ "|  _ \\ _   _| | _____ \n"
                +tab+ "| | | | | | | |/ / _ \\\n"
                +tab+ "| |_| | |_| |   <  __/\n"
                +tab+"|____/ \\__,_|_|\\_\\___|\n";
        System.out.print(logo);
        printDuke("Hello! I'm Duke\n\t What can I do for you?");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void printDuke(String input) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t "+input);
        System.out.println(HORIZONTAL_LINE);
    }

    public void byeDuke() {
        printDuke("Bye. Hope to see you again soon!");
        scanner.close();
    }
}