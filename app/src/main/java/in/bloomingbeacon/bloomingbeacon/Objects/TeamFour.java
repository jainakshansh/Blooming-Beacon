package in.bloomingbeacon.bloomingbeacon.Objects;

/**
 * Created by Akshansh on 22-10-2017.
 */

public class TeamFour {

    private String four_name;
    private String four_post;
    private String four_image;

    public TeamFour(String four_name, String four_post, String four_image) {
        this.four_name = four_name;
        this.four_post = four_post;
        this.four_image = four_image;
    }

    public TeamFour() {
    }

    public String getFour_name() {
        return four_name;
    }

    public String getFour_post() {
        return four_post;
    }

    public String getFour_image() {
        return four_image;
    }
}