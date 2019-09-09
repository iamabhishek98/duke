package Duke.DukeOperations;

import Duke.ErrorHandling.ErrorMessages;
import Duke.Tasks.TaskList;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Storage {
    protected String filePath;
    protected TaskList taskList;
    protected ErrorMessages errorMessages;

    /**
     * Constructor to update the filepath variable and instantiate the
     * taskList and errorMessages objects
     *
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.taskList = new TaskList();
        this.errorMessages = new ErrorMessages();
    }

    /**
     * Reads the task list stored in the data file and updates the task list object
     *
     * @return object containing the task list
     */
    public TaskList load() {
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(" \\| ");
                boolean status = (Integer.parseInt(line[1])==1);
                if (line[0].equals("T")) {
                    taskList.addToDo(line[2],status);
                }
                else if (line[0].equals("D")) {
                    taskList.addDeadline(line[2],status,line[3]);
                }
                else if (line[0].equals("E")) {
                    taskList.addEvent(line[2],status,line[3]);
                }
            }
            scanner.close();
        } catch (Exception e) {
            errorMessages.fileNotFound();
        }
        return taskList;
    }

    /**
     * Writes the contents of the task list to the data file
     *
     * @param taskList object containing the task list
     */
    public void update(TaskList taskList) {
        try {
            PrintWriter writer = new PrintWriter(filePath);
            writer.print(taskList.writeListToFile());
            writer.close();
        } catch (Exception e) {
            errorMessages.fileNotFound();
        }
    }
}