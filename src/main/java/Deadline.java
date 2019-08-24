public class Deadline extends Task {
    protected String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    public Deadline(String description, boolean isDone, String date) {
        super(description, isDone);
        this.date = date;
    }

    @Override
    public String getItem() {
        return "[D]"+super.getItem()+" (by: "+this.date+")";
    }
}