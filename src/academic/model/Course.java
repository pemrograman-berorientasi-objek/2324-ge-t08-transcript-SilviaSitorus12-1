package academic.model;
/**
 * @author 12S24004 Silvia Eklesiana Sitorus
 */
public class Course {
    private String code;
    private String name;
    private int credits;
    private String passingGrade;

    public Course(String code, String name, int credits, String passingGrade) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.passingGrade = passingGrade;
    }

    public String getCode() { return code; }
    public int getCredits() { return credits; }
}