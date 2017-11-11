package in.bloomingbeacon.bloomingbeacon.Objects;

/**
 * Created by Akshansh on 21-10-2017.
 */

public class SponsorItem {

    private String image;
    private String name;
    private String url;

    private SponsorItem() {
    }

    public SponsorItem(String image, String name, String url) {
        this.image = image;
        this.name = name;
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
