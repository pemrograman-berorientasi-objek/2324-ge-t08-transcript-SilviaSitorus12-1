package academic.model;
/**
 * @author 12S24004 Silvia Eklesiana Sitorus
 */
public
import java.util.*;

public class Student {
    private String id;
    private String name;
    private int year;
    private String program;

    private List<Enrollment> enrollments = new ArrayList<>();

    public Student(String id, String name, int year, String program) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.program = program;
    }

    public void addEnrollment(Enrollment e) {
        enrollments.add(e);
    }

    public List<Enrollment> getTranscript() {
        Map<String, Enrollment> latest = new HashMap<>();

        for (Enrollment e : enrollments) {
            String key = e.getCourseOpening().getCourse().getCode();
            latest.put(key, e);
        }

        List<Enrollment> result = new ArrayList<>(latest.values());

        result.sort((a, b) -> {
            int yearCompare = a.getCourseOpening().getAcademicYear()
                    .compareTo(b.getCourseOpening().getAcademicYear());
            if (yearCompare != 0) return yearCompare;
            return a.getCourseOpening().getSemester()
                    .compareTo(b.getCourseOpening().getSemester());
        });

        return result;
    }

    public double calculateGPA() {
        double total = 0;
        int credits = 0;

        for (Enrollment e : getTranscript()) {
            String g = e.getGrade();
            if (g == null) continue;

            int sks = e.getCourseOpening().getCourse().getCredits();
            total += gradeToWeight(g) * sks;
            credits += sks;
        }

        return credits == 0 ? 0 : total / credits;
    }

    public int getTotalCredits() {
        int total = 0;
        for (Enrollment e : getTranscript()) {
            total += e.getCourseOpening().getCourse().getCredits();
        }
        return total;
    }

    private double gradeToWeight(String g) {
        switch (g) {
            case "A": return 4.0;
            case "AB": return 3.5;
            case "B": return 3.0;
            case "BC": return 2.5;
            case "C": return 2.0;
            case "D": return 1.0;
            default: return 0.0;
        }
    }

    // getter
    public String getId() { return id; }
    public String getName() { return name; }
    public int getYear() { return year; }
    public String getProgram() { return program; }
}