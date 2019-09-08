package test.Tasks;

import main.Tasks.ToDo;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ToDoTest {

    @Test
    public void getItem() {
        ToDo userToDo = new ToDo("return book");
        assertEquals(userToDo.getItem(), "[T][✘] return book");

        ToDo storageToDo = new ToDo("return book", true);
        assertEquals(storageToDo.getItem(), "[T][✓] return book");
    }
}