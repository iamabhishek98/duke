public class Parser {
    private static UI ui;
    private static ErrorMessages errorMessages;

    public Parser() {
        ui = new UI();
        errorMessages = new ErrorMessages();
    }

    public void parseInput(String input, TaskList listOfTasks) throws DukeException {
        if (input.length() == 4 && input.equals("list")) listOfTasks.printList();
        else if (input.length() >= 5 && input.substring(0, 5).equals("done ")) listOfTasks.markDone(input);
        else if (input.length() >= 7 && input.substring(0, 7).equals("delete ")) listOfTasks.markDelete(input);
        else if (input.length() >= 5 && input.substring(0, 5).equals("todo ")) listOfTasks.markToDo(input);
        else if (input.length() >= 9 && input.substring(0, 9).equals("deadline ")) listOfTasks.markDeadline(input);
        else if (input.length() >= 6 && input.substring(0, 6).equals("event ")) listOfTasks.markEvent(input);
        else if (input.length() >= 5 && input.substring(0, 5).equals("find ")) listOfTasks.printMatchingTasks(input);
        else ui.printDuke(errorMessages.notRecognized());
    }
}
