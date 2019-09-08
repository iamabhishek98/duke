package main.ErrorHandling;

public class DukeException extends Exception{
    /**
     * Constructor to instantiate the contents of the parent class
     *
     * @param message error message
     */
    public DukeException(String message) {
        super(message);
    }
}
