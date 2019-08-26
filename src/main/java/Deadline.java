public class Deadline extends Task {
    protected DateAndTime date;

    public Deadline(String description, String date) {
        super(description);
        DateAndTime tempDate = new DateAndTime(date);
        if (tempDate.dateAndTimeFormatChecker(date)) {
            this.date = tempDate;
        } else {
            super.format = false;
        }
    }

    public Deadline(String description, boolean isDone, String date) {
        super(description, isDone);
        DateAndTime tempDate = new DateAndTime(date,0);
        if (tempDate.dateAndTimeFormatChecker(date)) {
            this.date = tempDate;
        } else {
            super.format = false;
        }
    }

    @Override
    public String getItem() {
        return "[D]"+super.getItem()+" (by: "+date.getDateAndTime()+")";
    }
}