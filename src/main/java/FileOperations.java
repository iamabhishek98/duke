import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileOperations {
    public static List readFile() {
        List list = new List();
        File file = new File("./src/main/data/duke.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(" \\| ");
                boolean status = (Integer.parseInt(line[1]) == 1) ? true : false;
                if (line[0].equals("T")) {
                    list.markToDo(line[2],status);
                }
                else if (line[0].equals("D")) {
                    list.markDeadline(line[2],status,line[3]);
                }
                else if (line[0].equals("E")) {
                    list.markEvent(line[2],status,line[3]);
                }
            }
            scanner.close();
        } catch (Exception e) {
            DukeException.fileNotFound();
        }
        return list;
    }

    public static void writeFile(List list) {
        try {
            PrintWriter writer = new PrintWriter("./src/main/data/duke.txt");
            writer.print(list.writeListToFile());
            writer.close();
        } catch (Exception e) {
            DukeException.fileNotFound();
        }
    }
}