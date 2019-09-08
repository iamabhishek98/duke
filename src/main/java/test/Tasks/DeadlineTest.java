package test.Tasks;

import main.Tasks.Deadline;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeadlineTest {

    @Test
    public void getItem() {
        Deadline userDeadline = new Deadline("return book", "31/05/2020 1756");
        assertEquals(userDeadline.getItem(), "[D][✘] return book (by: 31st of May 2020, 5:56pm)");

        Deadline storageDeadline = new Deadline("return book",true, "31st of May 2020, 5:56pm");
        assertEquals(storageDeadline.getItem(), "[D][✓] return book (by: 31st of May 2020, 5:56pm)");
    }
}