public class DukeException {
    public static void notRecognized() {
        InOut.output("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void taskEmpty(String task) {
        InOut.output("☹ OOPS!!! The description of a "+task+" cannot be empty.");
    }

    public static void taskWrongFormat(String task) {
        InOut.output("☹ OOPS!!! The format of the "+task+" is invalid.");
    }

    public static void invalidIndex() {
        InOut.output("☹ OOPS!!! The list index of the task entered is invalid.");
    }

    public static void invalidDateAndTime() {
        InOut.output("☹ OOPS!!! The date and time entered is invalid.");
    }

    public static void fileNotFound() {
        InOut.output("☹ OOPS!!! There was an error in opening the file.");
    }
}
