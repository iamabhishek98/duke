package Tasks;

import org.junit.Test;

import static org.junit.Assert.*;

public class EventTest {

    @Test
    public void getItem() {
        Event userEvent = new Event("return book", "31/05/2020 1756-2359");
        assertEquals(userEvent.getItem(), "[E][✘] return book (at: 31st of May 2020, 5:56pm-11:59pm)");

        Event storageEvent = new Event("return book", true,"31st of May 2020, 5:56pm-11:59pm");
        assertEquals(storageEvent.getItem(), "[E][✓] return book (at: 31st of May 2020, 5:56pm-11:59pm)");
    }
}