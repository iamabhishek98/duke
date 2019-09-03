package Duke;

import Commands.*;
import ErrorHandling.DukeException;

public class Parser {
    private UI ui;

    public Parser() {
        ui = new UI();
    }

    public Command parseInput(String input) throws DukeException {
        if (input.length()==3 && input.equals("bye"))
            return new ExitCommand(input);
        else if (input.length() == 4 && input.equals("list"))
            return new ListCommand(input);
        else if (input.length() >= 5 && input.substring(0, 5).equals("done "))
            return new DoneCommand(input);
        else if (input.length() >= 7 && input.substring(0, 7).equals("delete "))
            return new DeleteCommand(input);
        else if (input.length() >= 5 && input.substring(0, 5).equals("todo "))
            return new ToDoCommand(input);
        else if (input.length() >= 9 && input.substring(0, 9).equals("deadline "))
            return new DeadlineCommand(input);
        else if (input.length() >= 6 && input.substring(0, 6).equals("event "))
            return new EventCommand(input);
        else if (input.length() >= 5 && input.substring(0, 5).equals("find "))
            return new FindCommand(input);
        else
            throw new DukeException(ui.errorMessages.notRecognized());
    }
}
