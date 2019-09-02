import java.util.Scanner;

public class Duke {
    private TaskList listOfTasks;
    private UI ui;
    private Storage storage;
    private Parser parser;

    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        listOfTasks = storage.load();
        parser = new Parser();
    }

    public void runDuke() {
        ui.welcomeDuke();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            try {
                if (ui.byeDuke(input)) break;
                parser.parseInput(input, listOfTasks);
            } catch(DukeException e) {
                ui.printDuke(e.getMessage());
            }
            storage.update(listOfTasks);
        }
        storage.update(listOfTasks);
        scanner.close();
    }

    public static void main(String[] args) {
        new Duke("src/main/data/duke.txt").runDuke();
    }
}