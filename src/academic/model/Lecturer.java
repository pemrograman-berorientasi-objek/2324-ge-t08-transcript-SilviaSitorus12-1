package academic.model;

/**
 * @author 12S24004 Silvia Eklesiana Sitorus
 */

public class Lecturer extends Person{

    private String initial;
    private String email;

    public Lecturer(String id, String name, String initial, String email, String studyprogram) {
        super(id, name, studyprogram);
        this.initial = initial;
        this.email = email;
        this.studyprogram = studyprogram;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInitial() {
        return initial;
    }

    public String getEmail() {
        return email;
    }

    public String getStudyProgram() {
        return studyprogram;
    }

}
