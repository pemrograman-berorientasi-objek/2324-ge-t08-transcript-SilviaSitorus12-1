package academic.model;
/**
 * @author 12S24004 Silvia Eklesiana Sitorus
 */
public class CourseOpening {
    private Course course;
    private String academicYear;
    private String semester;

    public CourseOpening(Course course, String academicYear, String semester) {
        this.course = course;
        this.academicYear = academicYear;
        this.semester = semester;
    }

    public Course getCourse() { return course; }
    public String getAcademicYear() { return academicYear; }
    public String getSemester() { return semester; }
}