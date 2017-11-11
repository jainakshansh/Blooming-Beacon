package in.bloomingbeacon.bloomingbeacon.Objects;

/**
 * Created by Akshansh on 18-10-2017.
 */

public class ContactUsItem {

    private String cu_name;
    private String cu_image;
    private String cu_phone;
    private String cu_email;
    private String cu_desc;

    private ContactUsItem() {
    }

    public ContactUsItem(String cu_name, String cu_image, String cu_phone, String cu_email, String cu_desc) {
        this.cu_name = cu_name;
        this.cu_image = cu_image;
        this.cu_phone = cu_phone;
        this.cu_email = cu_email;
        this.cu_desc = cu_desc;
    }

    public String getCu_name() {
        return cu_name;
    }

    public String getCu_image() {
        return cu_image;
    }

    public String getCu_phone() {
        return cu_phone;
    }

    public String getCu_email() {
        return cu_email;
    }

    public String getCu_desc() {
        return cu_desc;
    }
}
