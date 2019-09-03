package Tasks;

public class Event extends Task {
    protected DateAndTime date;

    public Event(String description, String date) {
        super(description);
        DateAndTime tempDate = new DateAndTime(date,1);
        if (tempDate.dateAndTimeChecker(date,1)) {
            this.date = tempDate;
        } else {
            super.format = false;
        }
    }

    public Event(String description, boolean isDone, String date) { // when reading from file
        super(description, isDone);
        this.date = new DateAndTime(date, 1,0);;
    }

    @Override
    public String getItem() {
        return "[E]"+super.getItem()+" (at: "+date.getDateAndTime(1)+")";
    }
}