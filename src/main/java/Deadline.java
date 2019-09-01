public class Deadline extends Task {
    protected DateAndTime date;

    public Deadline(String description, String date) {
        super(description);
        DateAndTime tempDate = new DateAndTime(date,0);
        if (tempDate.dateAndTimeChecker(date,0)) {
            this.date = tempDate;
        } else {
            super.format = false;
        }
    }

    public Deadline(String description, boolean isDone, String date) { // when reading from file
        super(description, isDone);
        this.date = new DateAndTime(date,0,0);
    }

    @Override
    public String getItem() {
        return "[D]"+super.getItem()+" (by: "+date.getDateAndTime(0)+")";
    }
}