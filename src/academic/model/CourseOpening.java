package academic.model;

/**
 * @author 12S24004 Silvia Eklesiana Sitorus
 */

public class CourseOpening {

    private String code;
    private String name;
    private Integer credit;
    private String grade;
    private String academicyear;
    private String semester;
    private String[] inisial;
    private Lecturer[] lecturers;
    private String LecturerInitial;
    private String email;

    public CourseOpening(String code, String name, Integer credit, String grade, String academicyear, String semester,
            String lecturerinitiallist, Lecturer[] lecturers, String LecturerInitial, String email) {
        this.code = code;
        this.name = name;
        this.credit = credit;
        this.grade = grade;
        this.academicyear = academicyear;
        this.semester = semester;
        this.inisial = lecturerinitiallist.split(",");
        this.lecturers = lecturers;
        this.LecturerInitial = LecturerInitial;
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Integer getCredit() {
        return credit;
    }

    public String getGrade() {
        return grade;
    }

    public String getAcademicYear() {
        return academicyear;
    }

    public String getSemester() {
        return semester;
    }

    public String[] getLecturerInitialList() {
        return this.inisial;
    }

    public String getEmailFromInitial(String initial) {
        for (Lecturer lecturer : lecturers) {
            if (lecturer.getInitial().equalsIgnoreCase(initial)) {
                return lecturer.getEmail();
            }
        }
        return null;
    }

    public String getLecturerInitial() {
        return LecturerInitial;
    }

    public String getEmail() {
        return email;
    }
}
