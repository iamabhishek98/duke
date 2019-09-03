public class Duke {
    private static final String filePath = "src/main/data/duke.txt";
    private TaskList tasks;
    private UI ui;
    private Storage storage;
    private Parser parser;

    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        tasks = storage.load();
        parser = new Parser();
    }

    public void runDuke() {
        ui.welcomeDuke();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = parser.parseInput(fullCommand);
                command.execute(tasks,ui,storage);
                isExit = command.isExit();
            } catch(DukeException e) {
                ui.printDuke(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke(filePath).runDuke();
    }
}