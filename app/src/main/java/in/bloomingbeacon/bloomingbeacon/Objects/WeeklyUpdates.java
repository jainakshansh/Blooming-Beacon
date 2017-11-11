package in.bloomingbeacon.bloomingbeacon.Objects;

/**
 * Created by Akshansh on 23-10-2017.
 */

public class WeeklyUpdates {

    private String update_text;
    private String update_datetime;
    private String update_link;

    private WeeklyUpdates() {
    }

    public WeeklyUpdates(String update_text, String update_datetime, String update_link) {
        this.update_text = update_text;
        this.update_datetime = update_datetime;
        this.update_link = update_link;
    }

    public String getUpdate_text() {
        return update_text;
    }

    public String getUpdate_datetime() {
        return update_datetime;
    }

    public String getUpdate_link() {
        return update_link;
    }
}
