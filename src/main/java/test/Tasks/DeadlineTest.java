package test.Tasks;

import main.Tasks.Deadline;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class DeadlineTest {
    private String description;
    private boolean isDone;
    private String dateAndTime;
    private String expected;

    public DeadlineTest(String description, boolean isDone, String dateAndTime, String expected) {
        this.description = description;
        this.isDone = isDone;
        this.dateAndTime = dateAndTime;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testConditions() {
        return Arrays.asList(new Object[][] {
                {"return book",false, "31st of May 2020, 5:56pm","[D][✘] return book (by: 31st of May 2020, 5:56pm)"},
                {"return book",true,"31st of May 2020, 5:56pm","[D][✓] return book (by: 31st of May 2020, 5:56pm)"}
        });
    }

    @Test
    public void getItem() {
        Deadline userDeadline = new Deadline("return book", "31/05/2020 1756");
        assertEquals("[D][✘] return book (by: 31st of May 2020, 5:56pm)",userDeadline.getItem());

        Deadline storageDeadline = new Deadline(description,isDone,dateAndTime);
        assertEquals(expected,storageDeadline.getItem());
    }
}