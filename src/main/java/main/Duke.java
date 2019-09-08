package main;

import main.Commands.Command;
import main.DukeOperations.UI;
import main.DukeOperations.Storage;
import main.DukeOperations.Parser;
import main.ErrorHandling.DukeException;
import main.Tasks.TaskList;

public class Duke {
    private static final String filePath = "src/main/data/duke.txt";
    private TaskList tasks;
    private UI ui;
    private Storage storage;
    private Parser parser;

    /**
     * Constructor to instantiate the ui, storage, tasks and parser objects
     *
     * @param filePath string containing the path of the persistent file storage
     */
    public Duke(String filePath) {
        this.ui = new UI();
        this.storage = new Storage(filePath);
        this.tasks = this.storage.load();
        this.parser = new Parser();
    }

    /**
     * Runs the main.main.DukeOperations program
     */
    public void runDuke() {
        this.ui.welcomeDuke();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = this.ui.readCommand();
                Command command = this.parser.parseInput(fullCommand);
                command.execute(this.tasks,this.ui,this.storage);
                isExit = command.isExit();
            } catch(DukeException e) {
                this.ui.printDuke(e.getMessage());
            }
        }
    }

    /**
     * Main function
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke(filePath).runDuke();
    }
}