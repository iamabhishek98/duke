package Test.Tasks;

import Duke.Tasks.TaskList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TaskListTest {

    @Test
    public void writeListToFile() {
        TaskList taskList = new TaskList();
        taskList.addToDo("return book",true);
        taskList.addDeadline("return book", true, "31st of May 2020, 5:56pm");
        taskList.addEvent("return book",true,"31st of May 2020, 5:56pm-11:59pm");
        String expectedOutput = "T | 1 | return book\nD | 1 | return book | 31st of May 2020, 5:56pm\nE | 1 | return book | 31st of May 2020, 5:56pm-11:59pm\n".trim();
        String actualOutput = taskList.writeListToFile().toString().trim();
        assertEquals(expectedOutput,actualOutput);
    }
}