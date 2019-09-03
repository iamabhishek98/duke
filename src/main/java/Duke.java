
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
        while (true) {
            String fullCommand = ui.readCommand();
            try {
                if (ui.byeDuke(fullCommand)) break;
                parser.parseInput(fullCommand, listOfTasks);
            } catch(DukeException e) {
                ui.printDuke(e.getMessage());
            }
            storage.update(listOfTasks);
        }
        storage.update(listOfTasks);
    }

    public static void main(String[] args) {
        new Duke("src/main/data/duke.txt").runDuke();
    }
}