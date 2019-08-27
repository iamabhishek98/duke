public class ErrorMessages {
    public static String notRecognized() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    public static String taskDescriptionEmpty(String task) {
        return "☹ OOPS!!! The description of a "+task+" task cannot be empty.";
    }

    public static String taskDateAndTimeEmpty(String task) {
        return "☹ OOPS!!! The date and time component of a "+task+" task cannot be empty.";
    }

    public static String taskWrongFormat(String task) {
        return "☹ OOPS!!! The format of the "+task+" task is invalid.";
    }

    public static String invalidIndex() {
        return "☹ OOPS!!! The list index of the task entered is invalid.";
    }

    public static String invalidDateAndTime() {
        return "☹ OOPS!!! The date and time entered is invalid.";
    }

    public static String fileNotFound() {
        return "☹ OOPS!!! There was an error in opening the contents of the file.";
    }
}
