import java.util.Scanner;

public class Duke {
    private static TaskList listOfTasks;
    private static Storage storage;

    public static void main(String[] args) {
        runDuke("./duke/src/main/data/duke.txt");
    }

    public static void runDuke(String filePath) {
        storage = new Storage(filePath);
        listOfTasks = storage.readFile();
        UI.startDuke();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (UI.byeDuke(input)) break;
            else if (input.length()==4 && input.equals("list")) listOfTasks.printList();
            else if (input.length()>=5 && input.substring(0,5).equals("done ")) {
                try {
                    listOfTasks.markDone(input);
                } catch (DukeException e) {
                    UI.outputDuke(e.getMessage());
                }
            }
            else if (input.length()>=7 && input.substring(0,7).equals("delete ")) {
                try {
                    listOfTasks.markDelete(input);
                } catch (DukeException e) {
                    UI.outputDuke(e.getMessage());
                }
            }
            else if (input.length()>=5 && input.substring(0,5).equals("todo ")) {
                try {
                    listOfTasks.markToDo(input);
                } catch (DukeException e) {
                    UI.outputDuke(e.getMessage());
                }
            }
            else if (input.length()>=9 && input.substring(0,9).equals("deadline ")) {
                try {
                    listOfTasks.markDeadline(input);
                } catch (DukeException e) {
                    UI.outputDuke(e.getMessage());
                }
            }
            else if (input.length()>=6 && input.substring(0,6).equals("event ")) {
                try {
                    listOfTasks.markEvent(input);
                } catch (DukeException e) {
                    UI.outputDuke(e.getMessage());
                }
            }
            else if (input.length()>=5 && input.substring(0,5).equals("find ")) {
                try {
                    listOfTasks.printMatchingTasks(input);
                } catch (DukeException e) {
                    UI.outputDuke(e.getMessage());
                }
            }
            else {
                UI.outputDuke(ErrorMessages.notRecognized());
            }
            storage.writeFile(listOfTasks);
        }
        storage.writeFile(listOfTasks);
        scanner.close();
    }
}