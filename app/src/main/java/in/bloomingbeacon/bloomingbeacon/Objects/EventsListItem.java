package in.bloomingbeacon.bloomingbeacon.Objects;

/**
 * Created by Akshansh on 22-10-2017.
 */

public class EventsListItem {

    private String event_name;
    private String event_description;
    private String event_location;
    private String event_date;
    private String event_date_more;
    private String event_desc_detailed;
    private String event_lat;
    private String event_long;
    private String event_location_detailed;
    private String event_image;
    private String event_date_time;

    private EventsListItem() {
    }

    public EventsListItem(String event_name, String event_description, String event_location, String event_date, String event_date_more, String event_desc_detailed, String event_lat, String event_long, String event_location_detailed, String event_image, String event_date_time) {
        this.event_name = event_name;
        this.event_description = event_description;
        this.event_location = event_location;
        this.event_date = event_date;
        this.event_date_more = event_date_more;
        this.event_desc_detailed = event_desc_detailed;
        this.event_lat = event_lat;
        this.event_long = event_long;
        this.event_location_detailed = event_location_detailed;
        this.event_image = event_image;
        this.event_date_time = event_date_time;
    }

    public String getEvent_name() {
        return event_name;
    }

    public String getEvent_description() {
        return event_description;
    }

    public String getEvent_location() {
        return event_location;
    }

    public String getEvent_date() {
        return event_date;
    }

    public String getEvent_date_more() {
        return event_date_more;
    }

    public String getEvent_desc_detailed() {
        return event_desc_detailed;
    }

    public String getEvent_lat() {
        return event_lat;
    }

    public String getEvent_long() {
        return event_long;
    }

    public String getEvent_location_detailed() {
        return event_location_detailed;
    }

    public String getEvent_image() {
        return event_image;
    }

    public String getEvent_date_time() {
        return event_date_time;
    }
}
