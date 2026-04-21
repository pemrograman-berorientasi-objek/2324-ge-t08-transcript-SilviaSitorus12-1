package academic.model;

/**
 * @author 12S24004 Silvia Eklesiana Sitorus
 */

public class Enrollment {
    private String code;
    private String id;
    private String academicYear;
    private String semester;
    public String grade;
    public String remedi;
    public int sumRemedi;
    public String newGrade;

    public Enrollment(String code, String id, String academicYear, String semester, String grade,
            String remedi, int sumRemedi, String newGrade) {
        this.code = code;
        this.id = id;
        this.academicYear = academicYear;
        this.semester = semester;
        this.grade = grade;
        this.remedi = remedi;
        this.sumRemedi = sumRemedi;
        this.newGrade = newGrade;
    }

    public String getCode() {
        return code;
    }

    public String getId() {
        return id;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public String getSemester() {
        return semester;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade2) {
        this.grade = grade2;
    }

    public double SumGps(String grade) {
        switch (grade) {
            case "A":
                return 4.0;
            case "AB":
                return 3.5;
            case "B":
                return 3.0;
            case "BC":
                return 2.5;
            case "C":
                return 2.0;
            case "D":
                return 1.0;
            case "E":
                return 0.0;
            default:
                return 0.0;
        }
    }
}