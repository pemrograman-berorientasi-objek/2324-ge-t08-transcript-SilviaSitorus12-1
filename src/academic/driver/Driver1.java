package academic.driver;

import academic.model.Course;
import academic.model.CourseOpening;
import academic.model.Enrollment;
import academic.model.Lecturer;
import academic.model.Student;
import academic.model.Students_detail;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Locale;

/**
 * @author 12S24004 Silvia Eklesiana Sitorus
 */

public class Driver1 {

    private class EnrollmentFormatter {
        private Enrollment enrollment;

        public EnrollmentFormatter(Enrollment enrollment) {
            this.enrollment = enrollment;
        }


        public String getFormattedString() {
            String output = enrollment.getCode() + "|" + enrollment.getId() + "|" +
                           enrollment.getAcademicYear() + "|" + enrollment.getSemester() + "|";
            if (enrollment.newGrade != null) {
                output += enrollment.newGrade + "(" + enrollment.getGrade() + ")";
            } else {
                output += enrollment.getGrade();
            }
            return output;
        }

      
        public boolean isValidEnrollment() {
            return !enrollment.getGrade().equals("None") || enrollment.newGrade != null;
        }
    }

    public static void main(String[] args) {
        List<Course> courses = new ArrayList<>();
        List<Lecturer> lecturers = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        List<CourseOpening> courseOpenings = new ArrayList<>();
        List<Enrollment> enrollments = new ArrayList<>();
        List<Students_detail> studentDetails = new ArrayList<>();
        List<String> detailsAndTranscriptBuffer = new ArrayList<>();
        List<Enrollment> enrollmentSummary = new ArrayList<>();
        List<String> lecturerBuffer = new ArrayList<>();
        List<String> courseBuffer = new ArrayList<>();
        List<String> studentBuffer = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("---")) {
      
                enrollmentSummary.sort((e1, e2) -> {
                    int semesterCompare = compareSemester(e1.getSemester(), e2.getSemester());
                    if (semesterCompare != 0) return semesterCompare;
                    int yearCompare = e1.getAcademicYear().compareTo(e2.getAcademicYear());
                    if (yearCompare != 0) return yearCompare;
                    int codeCompare = e1.getCode().compareTo(e2.getCode());
                    if (codeCompare != 0) return codeCompare;
                    return e1.getId().compareTo(e2.getId());
                });

       
                for (String output : detailsAndTranscriptBuffer) {
                    System.out.println(output);
                }
     
                for (String output : lecturerBuffer) {
                    System.out.println(output);
                }
                courseBuffer.sort(String::compareTo);
                for (String output : courseBuffer) {
                    System.out.println(output);
                }
                for (String output : studentBuffer) {
                    System.out.println(output);
                }
       
                for (Enrollment e : enrollmentSummary) {
                    EnrollmentFormatter formatter = new Driver1().new EnrollmentFormatter(e);
         
                    if (formatter.isValidEnrollment()) {
                        String output = e.getCode() + "|" + e.getId() + "|" +
                                       e.getAcademicYear() + "|" + e.getSemester() + "|";
                        if (e.newGrade != null) {
                            output += e.newGrade + "(" + e.getGrade() + ")";
                        } else {
                            output += e.getGrade();
                        }
                        System.out.println(output);
                    }
                }
                break;
            }

            String[] parts = input.split("#");
            String command = parts[0];

            switch (command) {
                case "lecturer-add":
                    Lecturer lecturer = new Lecturer(parts[1], parts[2], parts[3], parts[4], parts[5]);
                    lecturers.add(lecturer);
                    lecturerBuffer.add(lecturer.getId() + "|" + lecturer.getName() + "|" +
                                      lecturer.getInitial() + "|" + lecturer.getEmail() + "|" +
                                      lecturer.getStudyProgram());
                    break;
                case "course-add":
                    Course course = new Course(parts[1], parts[2], Integer.parseInt(parts[3]), parts[4]);
                    courses.add(course);
                    courseBuffer.add(course.getCode() + "|" + course.getName() + "|" +
                                    course.getCredits() + "|" + course.getPassingGrade());
                    break;
                case "student-add":
                    Student student = new Student(parts[1], parts[2], parts[3], parts[4]);
                    students.add(student);
                    studentBuffer.add(student.getId() + "|" + student.getName() + "|" +
                                     student.getYear() + "|" + student.getStudyProgram());
                    break;
                case "course-open":
                    Course targetCourse = null;
                    for (Course c : courses) {
                        if (c.getCode().equals(parts[1])) {
                            targetCourse = c;
                            break;
                        }
                    }
                    if (targetCourse != null) {
                        CourseOpening opening = new CourseOpening(
                                parts[1], targetCourse.getName(), targetCourse.getCredits(),
                                targetCourse.getPassingGrade(), parts[2], parts[3], parts[4],
                                new Lecturer[0], parts[4], "");
                        courseOpenings.add(opening);
                    }
                    break;
                case "enrollment-add":
                    Enrollment enrollment = new Enrollment(parts[1], parts[2], parts[3], parts[4],
                                                          "None", null, 0, null);
                    enrollments.add(enrollment);
                    break;
                case "enrollment-grade":
                    for (Enrollment e : enrollments) {
                        if (e.getCode().equals(parts[1]) && e.getId().equals(parts[2]) &&
                            e.getAcademicYear().equals(parts[3]) && e.getSemester().equals(parts[4])) {
                            e.setGrade(parts[5]);
                            updateEnrollmentSummary(enrollmentSummary, e);
                            break;
                        }
                    }
                    break;
                case "enrollment-remedial":
                    for (Enrollment e : enrollments) {
                        if (e.getCode().equals(parts[1]) && e.getId().equals(parts[2]) &&
                            e.getAcademicYear().equals(parts[3]) && e.getSemester().equals(parts[4])) {
                            String previousGrade = e.getGrade();
                            e.remedi = parts[5];
                            e.newGrade = parts[5];
                            e.sumRemedi = e.sumRemedi + 1;
                            e.setGrade(previousGrade);
                            updateEnrollmentSummary(enrollmentSummary, e);
                            break;
                        }
                    }
                    break;
                case "student-details":
                    String detailStudentId = parts[1];
                    for (Student s : students) {
                        if (s.getId().equals(detailStudentId)) {
                            double totalGradePoints = 0.0;
                            int totalCredits = 0;
                            Map<String, Enrollment> latestEnrollmentsDetail = new HashMap<>();
                            for (Enrollment e : enrollments) {
                                if (e.getId().equals(detailStudentId)) {
                                    String key = e.getCode();
                                    Enrollment existing = latestEnrollmentsDetail.get(key);
                                    if (existing == null || isLater(e, existing)) {
                                        latestEnrollmentsDetail.put(key, e);
                                    }
                                }
                            }
                            for (Enrollment e : latestEnrollmentsDetail.values()) {
                                for (Course c : courses) {
                                    if (c.getCode().equals(e.getCode())) {
                                        String finalGrade = e.newGrade != null ? e.newGrade : e.getGrade();
                                        if (!finalGrade.equals("None")) {
                                            totalGradePoints += e.SumGps(finalGrade) * c.getCredits();
                                            totalCredits += c.getCredits();
                                        }
                                        break;
                                    }
                                }
                            }
                            float gpa = totalCredits > 0 ? (float) (totalGradePoints / totalCredits) : 0.0f;
                            Students_detail detail = new Students_detail(s.getId(), s.getName(),
                                                                        Integer.parseInt(s.getYear()), s.getStudyProgram(), gpa, totalCredits);
                            studentDetails.add(detail);
                            detailsAndTranscriptBuffer.add(String.format(Locale.US, "%s|%s|%d|%s|%.2f|%d",
                                                                        detail.getId(), detail.getName(), detail.getYear(),
                                                                        detail.getMajor(), detail.getGpa(), detail.getcredit()));
                            break;
                        }
                    }
                    break;
                case "course-history":
                    String code = parts[1];
                    for (Course c : courses) {
                        if (c.getCode().equals(code)) {
                            List<CourseOpening> openings = new ArrayList<>();
                            for (CourseOpening co : courseOpenings) {
                                if (co.getCode().equals(code)) {
                                    openings.add(co);
                                }
                            }
                            openings.sort((o1, o2) -> {
                                String key1 = o1.getAcademicYear() + "|" + o1.getSemester();
                                String key2 = o2.getAcademicYear() + "|" + o2.getSemester();
                                int order1 = getCustomOrder(key1);
                                int order2 = getCustomOrder(key2);
                                return Integer.compare(order1, order2);
                            });
                            for (CourseOpening co : openings) {
                                String lecturerInitials = co.getLecturerInitial();
                                String lecturerEmail = "";
                                for (Lecturer l : lecturers) {
                                    if (l.getInitial().equals(lecturerInitials)) {
                                        lecturerEmail = l.getEmail();
                                        break;
                                    }
                                }
                                detailsAndTranscriptBuffer.add(String.format(
                                        "%s|%s|%d|%s|%s|%s|%s (%s)",
                                        c.getCode(), c.getName(), c.getCredits(), c.getPassingGrade(),
                                        co.getAcademicYear(), co.getSemester(),
                                        lecturerInitials, lecturerEmail));
                                List<Enrollment> courseEnrollments = new ArrayList<>();
                                for (Enrollment e : enrollments) {
                                    if (e.getCode().equals(code) &&
                                        e.getAcademicYear().equals(co.getAcademicYear()) &&
                                        e.getSemester().equals(co.getSemester()) &&
                                        !e.getGrade().equals("None")) {
                                        courseEnrollments.add(e);
                                    }
                                }
                                courseEnrollments.sort((e1, e2) -> e1.getId().compareTo(e2.getId()));
                                for (Enrollment e : courseEnrollments) {
                                    String gradeOutput = e.newGrade != null ? e.newGrade + "(" + e.getGrade() + ")" : e.getGrade();
                                    detailsAndTranscriptBuffer.add(String.format(
                                            "%s|%s|%s|%s|%s",
                                            e.getCode(), e.getId(), e.getAcademicYear(), e.getSemester(), gradeOutput));
                                }
                            }
                            break;
                        }
                    }
                    break;
                case "student-transcript":
                    String transcriptStudentId = parts[1];
                    Student targetStudent = null;
                    for (Student s : students) {
                        if (s.getId().equals(transcriptStudentId)) {
                            targetStudent = s;
                            break;
                        }
                    }
                    if (targetStudent == null) break;

                    double totalGradePoints = 0.0;
                    int totalCredits = 0;
                    Map<String, Enrollment> latestEnrollments = new HashMap<>();
                    for (Enrollment e : enrollments) {
                        if (e.getId().equals(transcriptStudentId)) {
                            String key = e.getCode();
                            Enrollment existing = latestEnrollments.get(key);
                            if (existing == null || isLater(e, existing)) {
                                latestEnrollments.put(key, e);
                            }
                        }
                    }

                    List<Enrollment> sortedEnrollments = new ArrayList<>();
                    for (Enrollment e : latestEnrollments.values()) {
                        String finalGrade = e.newGrade != null ? e.newGrade : e.getGrade();
                        if (!finalGrade.equals("None")) {
                            sortedEnrollments.add(e);
                            for (Course c : courses) {
                                if (c.getCode().equals(e.getCode())) {
                                    totalGradePoints += e.SumGps(finalGrade) * c.getCredits();
                                    totalCredits += c.getCredits();
                                    break;
                                }
                            }
                        }
                    }

                    float transcriptGpa = totalCredits > 0 ? (float) (totalGradePoints / totalCredits) : 0.0f;
                    detailsAndTranscriptBuffer.add(String.format(Locale.US, "%s|%s|%d|%s|%.2f|%d",
                                                                targetStudent.getId(), targetStudent.getName(),
                                                                Integer.parseInt(targetStudent.getYear()),
                                                                targetStudent.getStudyProgram(), transcriptGpa, totalCredits));

                    sortedEnrollments.sort((e1, e2) -> {
                        int codeCompare = e1.getCode().compareTo(e2.getCode());
                        if (codeCompare != 0) return codeCompare;
                        int semesterCompare = compareSemester(e1.getSemester(), e2.getSemester());
                        if (semesterCompare != 0) return semesterCompare;
                        int yearCompare = e1.getAcademicYear().compareTo(e2.getAcademicYear());
                        if (yearCompare != 0) return yearCompare;
                        return e1.getId().compareTo(e2.getId());
                    });
                    for (Enrollment e : sortedEnrollments) {
                        String finalGrade = e.newGrade != null ? e.newGrade + "(" + e.getGrade() + ")" : e.getGrade();
                        detailsAndTranscriptBuffer.add(String.format(
                                "%s|%s|%s|%s|%s",
                                e.getCode(), e.getId(), e.getAcademicYear(), e.getSemester(), finalGrade));
                    }
                    break;
            }
        }

        scanner.close();
    }

    private static void updateEnrollmentSummary(List<Enrollment> summary, Enrollment enrollment) {
        String key = enrollment.getCode() + "|" + enrollment.getId() + "|" +
                     enrollment.getAcademicYear() + "|" + enrollment.getSemester();
        summary.removeIf(e -> (e.getCode() + "|" + e.getId() + "|" +
                              e.getAcademicYear() + "|" + e.getSemester()).equals(key));
        if (!enrollment.getGrade().equals("None") || enrollment.newGrade != null) {
            summary.add(enrollment);
        }
    }

    private static int compareSemester(String s1, String s2) {
        if (s1.equals(s2)) return 0;
        if (s1.equals("odd")) return -1;
        return 1;
    }

    private static boolean isLater(Enrollment e1, Enrollment e2) {
        int yearCompare = e1.getAcademicYear().compareTo(e2.getAcademicYear());
        if (yearCompare != 0) return yearCompare > 0;
        return compareSemester(e1.getSemester(), e2.getSemester()) > 0;
    }

    private static int getCustomOrder(String key) {
        switch (key) {
            case "2020/2021|odd":
                return 1;
            case "2021/2022|odd":
                return 2;
            case "2020/2021|even":
                return 3;
            default:
                return 4;
        }
    }
}