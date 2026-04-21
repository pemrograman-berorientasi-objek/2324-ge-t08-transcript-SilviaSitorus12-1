package academic.model;
/**
 * @author 12S24004 Silvia Eklesiana Sitorus
 */
public class Enrollment {
    private Student student;
    private CourseOpening courseOpening;
    private Grade grade;

    public Enrollment(Student student, CourseOpening courseOpening) {
        this.student = student;
        this.courseOpening = courseOpening;
    }

    // =========================
    // INNER CLASS (Nested Class)
    // =========================
    public class Grade {
        private String current;
        private String previous;

        public void setGrade(String grade) {
            this.current = grade;
        }

        public void setRemedial(String grade) {
            this.previous = this.current;
            this.current = grade;
        }

        public String display() {
            if (previous != null) {
                return current + "(" + previous + ")";
            }
            return current;
        }

        public String getCurrent() {
            return current;
        }
    }

    public void setGrade(String g) {
        if (grade == null) grade = new Grade();
        grade.setGrade(g);
    }

    public void setRemedial(String g) {
        if (grade == null) grade = new Grade();
        grade.setRemedial(g);
    }

    public String getDisplayGrade() {
        return grade == null ? "" : grade.display();
    }

    public String getGrade() {
        return grade == null ? null : grade.getCurrent();
    }

    public Student getStudent() { return student; }
    public CourseOpening getCourseOpening() { return courseOpening; }
}