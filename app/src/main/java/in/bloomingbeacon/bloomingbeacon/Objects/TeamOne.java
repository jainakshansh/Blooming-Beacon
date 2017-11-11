package in.bloomingbeacon.bloomingbeacon.Objects;

/**
 * Created by Akshansh on 22-10-2017.
 */

public class TeamOne {

    private String one_name;
    private String one_post;
    private String one_image;

    public TeamOne(String one_name, String one_post, String one_image) {
        this.one_name = one_name;
        this.one_post = one_post;
        this.one_image = one_image;
    }

    public TeamOne() {
    }

    public String getOne_name() {
        return one_name;
    }

    public String getOne_post() {
        return one_post;
    }

    public String getOne_image() {
        return one_image;
    }
}