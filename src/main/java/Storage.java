import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList readFile() {
        TaskList taskList = new TaskList();
        File file = new File(this.filePath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(" \\| ");
                boolean status = (Integer.parseInt(line[1]) == 1) ? true : false;
                if (line[0].equals("T")) {
                    taskList.markToDo(line[2],status);
                }
                else if (line[0].equals("D")) {
                    taskList.markDeadline(line[2],status,line[3]);
                }
                else if (line[0].equals("E")) {
                    taskList.markEvent(line[2],status,line[3]);
                }
            }
            scanner.close();
        } catch (Exception e) {
            ErrorMessages.fileNotFound();
        }
        return taskList;
    }

    public void writeFile(TaskList taskList) {
        try {
            PrintWriter writer = new PrintWriter("./src/main/data/duke.txt");
            writer.print(taskList.writeListToFile());
            writer.close();
        } catch (Exception e) {
            ErrorMessages.fileNotFound();
        }
    }
}