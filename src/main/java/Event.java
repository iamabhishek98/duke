public class Event extends Task {
    protected String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String getItem() {
        return "[E]"+super.getItem()+" (at: "+this.date+")";
    }
}
