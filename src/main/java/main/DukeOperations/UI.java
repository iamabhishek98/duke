package main.DukeOperations;

import main.ErrorHandling.ErrorMessages;

import java.util.Scanner;

public class UI {
    public String HORIZONTAL_LINE = "\t___________________________________________________________________________";
    public Scanner scanner;
    public ErrorMessages errorMessages;

    /**
     * Constructor to instantiate the scanner and errorMessages objects
     */
    public UI() {
        this.scanner = new Scanner(System.in);
        this.errorMessages = new ErrorMessages();
    }

    /**
     * Prints welcome message
     */
    public void welcomeDuke() {
        String tab = "\t ";
        String logo = tab+" ____        _        \n"
                +tab+ "|  _ \\ _   _| | _____ \n"
                +tab+ "| | | | | | | |/ / _ \\\n"
                +tab+ "| |_| | |_| |   <  __/\n"
                +tab+"|____/ \\__,_|_|\\_\\___|\n";
        System.out.print(logo);
        printDuke("Hello! I'm main.main.DukeOperations\n\t What can I do for you?");
    }

    /**
     * Reads the command from the user
     *
     * @return the string entered by the user
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Prints the input string in the format specific to main.main.DukeOperations
     *
     * @param output contains the string to be printed
     */
    public void printDuke(String output) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t "+output);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints the exit message and closes the scanner object
     */
    public void byeDuke() {
        printDuke("Bye. Hope to see you again soon!");
        this.scanner.close();
    }
}