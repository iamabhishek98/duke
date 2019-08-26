public class Event extends Task {
    protected DateAndTime date;

    public Event(String description, String date) {
        super(description);
        DateAndTime tempDate = new DateAndTime(date);
        if (tempDate.dateAndTimeFormatChecker(date)) {
            this.date = tempDate;
        } else {
            super.format = false;
        }
    }

    public Event(String description, boolean isDone, String date) {
        super(description, isDone);
        DateAndTime tempDate = new DateAndTime(date, 0);
        this.date = tempDate;
    }

    @Override
    public String getItem() {
        return "[E]"+super.getItem()+" (at: "+date.getDateAndTime()+")";
    }
}