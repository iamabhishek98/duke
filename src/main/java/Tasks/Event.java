package Tasks;

public class Event extends Task {
    protected DateAndTime dateAndTime;

    /**
     * Constructor which checks the format of validity of the event command
     * and interprets the date and time entered by the user
     *
     * @param description string containing the task description
     * @param dateAndTime string containing the date and time
     */
    public Event(String description, String dateAndTime) {
        super(description);
        DateAndTime tempDate = new DateAndTime(dateAndTime,1);
        if (tempDate.dateAndTimeChecker(dateAndTime,1)) {
            this.dateAndTime = tempDate;
        } else {
            super.format = false;
        }
    }

    /**
     * Constructor called when reading the event task from persistent file storage
     *
     * @param description string containing the task description
     * @param isDone boolean variable indicating whether the task has been marked as done or not
     * @param dateAndTime string containing the date and time
     */
    public Event(String description, boolean isDone, String dateAndTime) { // when reading from file
        super(description, isDone);
        this.dateAndTime = new DateAndTime(dateAndTime, 1,0);;
    }

    /**
     * Returns the string containing the task description and the date and time in the Duke format
     *
     * @return string in the specific format
     */
    @Override
    public String getItem() {
        return "[E]"+super.getItem()+" (at: "+this.dateAndTime.getDateAndTime(1)+")";
    }
}