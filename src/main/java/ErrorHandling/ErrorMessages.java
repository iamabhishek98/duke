package ErrorHandling;

public class ErrorMessages {
    /**
     * Prints relevant error message
     *
     * @return string indicating that the command entered by the user is unrecognized
     */
    public String notRecognized() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    /**
     * Prints relevant error message
     *
     * @param task the string containing the relevant task
     * @return string indicating that the task description of the entered task cannot be empty
     */
    public String taskDescriptionEmpty(String task) {
        return "☹ OOPS!!! The description of a "+task+" task cannot be empty.";
    }

    /**
     * Prints relevant error message
     *
     * @param task the string containing the relevant task
     * @return string indicating that the date and time component of the specific task cannot be empty
     */
    public String taskDateAndTimeEmpty(String task) {
        return "☹ OOPS!!! The date and time component of a "+task+" task cannot be empty.";
    }

    /**
     * Prints relevant error message
     *
     * @param task the string containing the relevant task
     * @return string indicating the invalidity of the entered task
     */
    public String taskWrongFormat(String task) {
        return "☹ OOPS!!! The format of the "+task+" task is invalid.";
    }

    /**
     * Prints relevant error message
     *
     * @return string indicating the invalidity of the index entered by the user for the specific task
     */
    public String invalidIndex() {
        return "☹ OOPS!!! The list index of the task entered is invalid.";
    }

    /**
     * Prints relevant error message
     *
     * @return string indicating the invalidity of the entered date and time
     */
    public String invalidDateAndTime() {
        return "☹ OOPS!!! The date and time entered is invalid.";
    }

    /**
     * Prints relevant error message
     *
     * @return string indicating that the persistent file storage could not be opened
     */
    public String fileNotFound() {
        return "☹ OOPS!!! There was an error in opening the contents of the file.";
    }
}
