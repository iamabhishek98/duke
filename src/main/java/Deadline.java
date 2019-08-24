public class Deadline extends Task {
    protected String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String getItem() {
        return "[D]"+super.getItem()+" (by: "+this.date+")";
    }
}