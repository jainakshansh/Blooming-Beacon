package in.bloomingbeacon.bloomingbeacon.Objects;

/**
 * Created by Akshansh on 23-10-2017.
 */

public class EventLatest {

    private String event_name;
    private String event_date;
    private String event_date_more;
    private String event_location;
    private String event_description;

    private EventLatest() {
    }

    public EventLatest(String event_name, String event_date, String event_date_more, String event_location, String event_description) {
        this.event_name = event_name;
        this.event_date = event_date;
        this.event_date_more = event_date_more;
        this.event_location = event_location;
        this.event_description = event_description;
    }

    public String getEvent_name() {
        return event_name;
    }

    public String getEvent_date() {
        return event_date;
    }

    public String getEvent_date_more() {
        return event_date_more;
    }

    public String getEvent_location() {
        return event_location;
    }

    public String getEvent_description() {
        return event_description;
    }
}