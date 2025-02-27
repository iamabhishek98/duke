package Duke;

import Duke.Commands.Command;
import Duke.DukeOperations.UI;
import Duke.DukeOperations.Storage;
import Duke.DukeOperations.Parser;
import Duke.ErrorHandling.DukeException;
import Duke.Tasks.TaskList;

public class Duke {
    private static final String filePath = "src/Duke/data/duke.txt";
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
     * Runs the Duke.Duke.DukeOperations program
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