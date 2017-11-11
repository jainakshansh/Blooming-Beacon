package in.bloomingbeacon.bloomingbeacon.Objects;

import java.util.List;

/**
 * Created by Akshansh on 24-10-2017.
 */

public class RegisterItem {

    public String name;
    public String email;
    public String contact_number;
    public String dob;
    public String hometown;
    public String stream;
    public String year;
    public String student_id;
    public List<String> languages;
    public String department;
    public String why_interested;
    public String skills;
    public String how_help;
    public String experience;
    public boolean canchat;

    private RegisterItem() {
    }

    public RegisterItem(String name, String email, String contact_number, String dob, String hometown, String stream, String year, String student_id, List<String> languages, String department, String why_interested, String skills, String how_help, String experience, boolean canchat) {
        this.name = name;
        this.email = email;
        this.contact_number = contact_number;
        this.dob = dob;
        this.hometown = hometown;
        this.stream = stream;
        this.year = year;
        this.student_id = student_id;
        this.languages = languages;
        this.department = department;
        this.why_interested = why_interested;
        this.skills = skills;
        this.how_help = how_help;
        this.experience = experience;
        this.canchat = canchat;
    }
}
