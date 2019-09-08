package test.DukeOperations;

import main.Commands.*;
import main.DukeOperations.Parser;
import main.ErrorHandling.DukeException;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ParserTest {
    Parser parser;

    public ParserTest() {
        parser = new Parser();
    }

    @Test
    public void parseInput() {
        try {
            assertTrue(this.parser.parseInput("bye") instanceof ExitCommand);
            assertTrue(this.parser.parseInput("list") instanceof ListCommand);
            assertTrue(this.parser.parseInput("done 1") instanceof DoneCommand);
            assertTrue(this.parser.parseInput("delete 1") instanceof DeleteCommand);
            assertTrue(this.parser.parseInput("todo return book") instanceof ToDoCommand);
            assertTrue(this.parser.parseInput("deadline return book /by 12/12/2012 1800") instanceof DeadlineCommand);
            assertTrue(this.parser.parseInput("event return book /at 12/12/2012 1800-1900") instanceof EventCommand);
            assertTrue(this.parser.parseInput("find book") instanceof FindCommand);
        } catch(DukeException e) {
            System.out.println("Testing failed");
        }
    }
}