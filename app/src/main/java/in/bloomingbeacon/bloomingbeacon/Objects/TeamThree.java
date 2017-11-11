package in.bloomingbeacon.bloomingbeacon.Objects;

/**
 * Created by Akshansh on 22-10-2017.
 */

public class TeamThree {

    private String three_name;
    private String three_post;
    private String three_image;

    public TeamThree() {
    }

    public TeamThree(String three_name, String three_post, String three_image) {
        this.three_name = three_name;
        this.three_post = three_post;
        this.three_image = three_image;
    }

    public String getThree_name() {
        return three_name;
    }

    public String getThree_post() {
        return three_post;
    }

    public String getThree_image() {
        return three_image;
    }
}