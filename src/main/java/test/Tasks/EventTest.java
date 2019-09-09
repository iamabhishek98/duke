package test.Tasks;

import main.Tasks.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class EventTest {
    private String description;
    private boolean isDone;
    private String dateAndTime;
    private String expected;

    public EventTest(String description, boolean isDone, String dateAndTime, String expected) {
        this.description = description;
        this.isDone = isDone;
        this.dateAndTime = dateAndTime;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testConditions() {
        return Arrays.asList(new Object[][] {
                {"return book",false, "31st of May 2020, 5:56pm-11:59pm","[E][✘] return book (at: 31st of May 2020, 5:56pm-11:59pm)"},
                {"return book",true,"31st of May 2020, 5:56pm-11:59pm","[E][✓] return book (at: 31st of May 2020, 5:56pm-11:59pm)"}
        });
    }

    @Test
    public void getItem() {
        Event userEvent = new Event("return book", "31/05/2020 1756-2359");
        assertEquals("[E][✘] return book (at: 31st of May 2020, 5:56pm-11:59pm)",userEvent.getItem());

        Event storageEvent = new Event(description,isDone,dateAndTime);
        assertEquals(expected,storageEvent.getItem());
    }
}