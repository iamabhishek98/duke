import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Storage {
    protected String filePath;
    protected TaskList taskList;
    protected ErrorMessages errorMessages;

    public Storage(String filePath) {
        this.filePath = filePath;
        taskList = new TaskList();
        errorMessages = new ErrorMessages();
    }

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