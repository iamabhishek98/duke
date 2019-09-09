package test.Tasks;

import main.Tasks.ToDo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;

@RunWith(Parameterized.class)
public class ToDoTest {
    private String description;
    private boolean isDone;
    private String expected;

    public ToDoTest(String description, boolean isDone, String expected) {
        this.description = description;
        this.isDone = isDone;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testConditions() {
        return Arrays.asList(new Object[][] {
                {"return book",false, "[T][✘] return book"},
                {"return book",true,"[T][✓] return book"}
        });
    }

    @Test
    public void getItem() {
        ToDo userToDo = new ToDo("return book");
        assertEquals("[T][✘] return book",userToDo.getItem());

        ToDo storageToDo = new ToDo(description, isDone);
        assertEquals(expected, storageToDo.getItem());
    }
}