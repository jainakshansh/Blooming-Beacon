package in.bloomingbeacon.bloomingbeacon.Objects;

/**
 * Created by Akshansh on 23-10-2017.
 */

public class ImageItem {

    private String event_image;

    private ImageItem() {
    }

    public ImageItem(String event_image) {
        this.event_image = event_image;
    }

    public String getEvent_image() {
        return event_image;
    }
}
