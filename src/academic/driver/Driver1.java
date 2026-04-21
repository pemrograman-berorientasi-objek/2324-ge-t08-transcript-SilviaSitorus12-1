package academic.driver;
/**
 * @author 12S24004 Silvia Eklesiana Sitorus
 */
import academic.model.*;
import java.util.*;

public class Driver1 {

    static Map<String, Student> students = new HashMap<>();
    static Map<String, Course> courses = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("---")) break;

            String[] parts = input.split("#");

            switch (parts[0]) {

                case "student-add":
                    students.put(parts[1],
                            new Student(parts[1], parts[2],
                                    Integer.parseInt(parts[3]), parts[4]));
                    break;

                case "course-add":
                    courses.put(parts[1],
                            new Course(parts[1], parts[2],
                                    Integer.parseInt(parts[3]), parts[4]));
                    break;

                case "enrollment-add":
                    students.get(parts[2])
                            .addEnrollment(parts[1], parts[3], parts[4]);
                    break;

                case "enrollment-grade":
                    students.get(parts[2])
                            .setGrade(parts[1], parts[3], parts[4], parts[5]);
                    break;

                case "enrollment-remedial":
                    students.get(parts[2])
                            .setRemedial(parts[1], parts[3], parts[4], parts[5]);
                    break;

                case "student-transcript":
                    printTranscript(parts[1]);
                    break;
            }
        }
    }

    static void printTranscript(String id) {
        Student s = students.get(id);

        double gpa = s.calculateGPA(courses);
        int credits = s.getTotalCredits(courses);

        System.out.printf("%s|%s|%d|%s|%.2f|%d\n",
                s.getId(), s.getName(), s.getYear(),
                s.getProgram(), gpa, credits);

        for (Student.Enrollment e : s.getTranscript()) {
            System.out.printf("%s|%s|%s|%s|%s\n",
                    e.courseCode, s.getId(),
                    e.academicYear, e.semester,
                    e.getDisplayGrade());
        }
    }
}