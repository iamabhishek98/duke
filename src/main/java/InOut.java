public class InOut {
    public static String HORIZONTAL_LINE = "\t___________________________________________________________________________";

    public static void startDuke() {
        String tab = "\t\t\t\t\t\t\t  ";
        String logo = tab+" ____        _        \n"
                +tab+ "|  _ \\ _   _| | _____ \n"
                +tab+ "| | | | | | | |/ / _ \\\n"
                +tab+ "| |_| | |_| |   <  __/\n"
                +tab+"|____/ \\__,_|_|\\_\\___|\n";
        System.out.print(logo);
        outputDuke("Hello! I'm Duke\n\t What can I do for you?");
    }

    public static void outputDuke(String input) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t "+input);
        System.out.println(HORIZONTAL_LINE);
    }

    public static boolean byeDuke(String input) {
        if (input.length()==3 && input.equals("bye")) {
            outputDuke("Bye. Hope to see you again soon!");
            return true;
        }
        return false;
    }
}