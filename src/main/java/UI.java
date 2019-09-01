public class UI {
    public String HORIZONTAL_LINE = "\t___________________________________________________________________________";

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

    public void printDuke(String input) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t "+input);
        System.out.println(HORIZONTAL_LINE);
    }

    public boolean byeDuke(String input) {
        if (input.length()==3 && input.equals("bye")) {
            printDuke("Bye. Hope to see you again soon!");
            return true;
        }
        return false;
    }
}