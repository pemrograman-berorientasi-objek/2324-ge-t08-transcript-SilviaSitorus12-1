package academic.driver;
/**
 * @author 12S24004 Silvia Eklesiana Sitorus
 */
import academic.model.Course;
import academic.model.CourseOpening;
import academic.model.Enrollment;
import academic.model.Lecturer;
import academic.model.Student;
import java.util.ArrayList;
import java.util.Scanner;
public class Driver1 {
    
    public class TranscriptProcessor {
        private String studentId;
        
        public TranscriptProcessor(String studentId) {
            this.studentId = studentId;
        }
        
        public void printTranscript(ArrayList<Student> studentList, ArrayList<Enrollment> enrollList, ArrayList<Course> courseList) {
            String output = "";
            Boolean cekstudent = false;
            Boolean printed = false;
            Double index = 0.0;
            Double total = 0.0;
            Double jumlah = 0.0;
            Double jumlahs = 0.0;
            int jumlah1 = 0;
            
            for (Student p : studentList) {
                if (p.getId().contains(studentId)) {
                    cekstudent = true;
                    
                    ArrayList<Enrollment> latestEnrollments = Driver1.getLatestEnrollments(enrollList, studentId);
                    
                    boolean grades = false;
                    total = 0.0;
                    jumlahs = 0.0;
                    
                    for (Enrollment e : latestEnrollments) {
                        if (!e.getgrade().equals("None")) {
                            grades = true;
                            
                            switch (e.getgrade()) {
                                case "A":
                                    index = 4.0;
                                    break;
                                case "AB":
                                    index = 3.5;
                                    break;
                                case "B":
                                    index = 3.0;
                                    break;
                                case "BC":
                                    index = 2.5;
                                    break;
                                case "C":
                                    index = 2.0;
                                    break;
                                case "D":
                                    index = 1.5;
                                    break;
                                case "E":
                                    index = 1.0;
                                    break;
                                default:
                                    index = 0.0;
                                    break;
                            }
                            
                            for (Course a : courseList) {
                                if (e.getids().contains(a.getid())) {
                                    jumlah = Double.valueOf(a.getsks());
                                    jumlahs = jumlahs + jumlah;
                                    total = total + (jumlah * index);
                                    break;
                                }
                            }
                        }
                    }
                    
                    if (!grades) {
                        total = 0.0;
                        jumlahs = 0.0;
                    }
                    
                    jumlah1 = jumlahs.intValue();
                    
                    if (jumlahs == 0.0) {
                        output = p.toString() + "|" + "0.00" + "|" + "0";
                    } else {
                        output = p.toString() + "|" + String.format("%.2f", total / jumlahs) + "|" + jumlah1;
                    }
                    
                    if (!printed) {
                        System.out.println(output);
                        printed = true;
                    }
                    
                    for (Enrollment e : latestEnrollments) {
                        System.out.println(e.toString());
                    }
                    break;
                }
            }
        }
    }
    
    public static void main(String[] _args) {
        ArrayList<Course> course = new ArrayList<>();
        ArrayList<Student> student = new ArrayList<>();
        ArrayList<Enrollment> enrol = new ArrayList<>();
        ArrayList<Lecturer> lecturer = new ArrayList<>();
        ArrayList<CourseOpening> courseopen = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        String lecturerss[] = new String[100];
        String temp;
        String temp1;

        //objek course
        String id;
        String matkul;
        String sks;
        String nilai;
        String dosens;

        //objek student
        String nim;
        String nama;
        String tahun;
        String prodi;

        //objek enroll
        String ids;
        String nims;
        String year;
        String sems;
        String grade = "None";

        //objek lecturer
        String nidn;
        String nama_dosen;
        String inisial;
        String email;
        String prodi_dosen;
        
        //objek enrollgrade
        String ids_;
        String nims_;
        String year_;
        String sems_;
        String grade_;
        
        //objek details
        String nims1;
        
        boolean courseprinted = false;
        boolean studentprinted = false;

        //objek remedial
        String courseid;
        String studentid;
        String courseyear;
        String coursesems;
        String coursegrade;
        
        //objek courseopen
        String idcourse;
        String yearcourse;
        String semscourse;

        //objek coursehistory
        String idcoursehistory;

        //objek studenttranscript
        String idtranscriptstudent;

        int z = 0;

        while (true) {
            temp = input.nextLine();
            if (temp.equals("---")) {
                break;
            }
            String[] hasil = temp.split("#");
            temp1 = hasil[0];
            if (temp1.equals("course-add")) {
                id = hasil[1];
                matkul = hasil[2];
                sks = hasil[3];
                nilai = hasil[4];

                course.add(new Course(id, matkul, sks, nilai));
                
            } else if (temp1.equals("student-add")) {
                nim = hasil[1];
                nama = hasil[2];
                tahun = hasil[3];
                prodi = hasil[4];
                
                boolean ada = false;
                for (Student s : student) {
                    if (s.getId().contains(nim)) {
                        ada = true;
                    }
                }
                if (!ada) {
                    student.add(new Student(nim, nama, tahun, prodi));
                }
            } else if (temp1.equals("enrollment-add")) {
                ids = hasil[1];
                nims = hasil[2];
                year = hasil[3];
                sems = hasil[4];
                
                boolean courseada = false;
                boolean studentada = false;
                for (Course courses : course) {
                    if (ids.contains(courses.getid())) {
                        courseada = true;
                    }
                }
                
                for (Student students : student) {
                    if (nims.contains(students.getId())) {
                        studentada = true;
                    }
                }
                
                boolean sudahAda = false;
                for (Enrollment e : enrol) {
                    if (e.getids().equals(ids) && e.getnims().equals(nims) && e.getyear().equals(year) && e.getsems().equals(sems)) {
                        sudahAda = true;
                        break;
                    }
                }
                
                if (!studentada && !studentprinted) {
                    System.out.println("invalid student|" + nims);
                    studentprinted = true;
                }
                
                if (!courseada && !courseprinted) {
                    System.out.println("invalid course|" + ids);
                    courseprinted = true;
                }
                
                if (studentada && courseada && !sudahAda) {
                    enrol.add(new Enrollment(ids, nims, year, sems, grade));
                }
            } else if (temp1.equals("enrollment-grade")) {
                ids_ = hasil[1];
                nims_ = hasil[2];
                year_ = hasil[3];
                sems_ = hasil[4];
                grade_ = hasil[5];
                
                boolean found = false;

                for (Enrollment e : enrol) {
                    if (e.getids().equals(ids_) && e.getnims().equals(nims_) && 
                        e.getyear().equals(year_) && e.getsems().equals(sems_)) {
                        e.setgrade(grade_);
                        found = true;
                        break;
                    }
                }
                
                if (!found) {
                    enrol.add(new Enrollment(ids_, nims_, year_, sems_, grade_));
                }
                
            } else if (temp1.equals("lecturer-add")) {
                nidn = hasil[1];
                nama_dosen = hasil[2];
                inisial = hasil[3];
                email = hasil[4];
                prodi_dosen = hasil[5];

                boolean dosen_ada = false;
                for (Lecturer lecturers : lecturer) {
                    if (nidn.contains(lecturers.getId())) {
                        dosen_ada = true;
                    }
                }
                
                if (!dosen_ada) {
                    lecturer.add(new Lecturer(nidn, nama_dosen, inisial, email, prodi_dosen));
                }

            } else if (temp1.equals("student-details")) {
                String output = "";
                Boolean cekstudent = false;
                Boolean printed = false;
                Double index = 0.0;
                Double total = 0.0;
                Double jumlah = 0.0;
                Double jumlahs = 0.0;
                int jumlah1 = 0;
                nims1 = hasil[1];
                
                for (Student p : student) {
                    total = 0.0;
                    jumlah1 = 0;
                    if (p.getId().contains(nims1)) {
                        boolean grades = false;

                        ArrayList<String> coursecek = new ArrayList<>();
                        for (int i = enrol.size() - 1; i >= 0; i--) {
                            Enrollment e = enrol.get(i);
                            if (e.getnims().equals(nims1) && !coursecek.contains(e.getids())) {
                                coursecek.add(e.getids());
                                double gradePoint;
                                
                                grades = true;
                                switch (e.getgrade()) {
                                    case "A":
                                    index = 4.0;
                                        break;
                                        case "AB":
                                        index = 3.5;
                                        break;
                                        case "B":
                                        index = 3.0;
                                        break;
                                        case "BC":
                                        index = 2.5;
                                        break;
                                        case "C":
                                        index = 2.0;
                                        break;
                                        case "D":
                                        index = 1.5;
                                        break;
                                        case "E":
                                        index = 1.0;
                                        break;
                                        default:
                                        index = 0.0;
                                        break;
                                    }
                                    for (Course a : course) {
                                    if (e.getids().contains(a.getid())) {
                                        jumlah = Double.valueOf(a.getsks());
                                        jumlahs = jumlahs + jumlah;
                                        total = total + (jumlah * index);
                                    }
                                }
                            }
                        }
                        
                        if (!grades) {
                            total = 0.0;
                            jumlahs = 0.0;
                        }
                        
                        jumlah1 = jumlahs.intValue();
                        
                        for (Enrollment e : enrol) {
                            for (Student q : student) {
                                if (q.getId().contains(nims1)) {
                                    if (e.getgrade().equals("None")) {
                                        output = q.toString() + "|" + "0.00" + "|" + "0";
                                    } else {
                                        output = q.toString() + "|" + String.format("%.2f", total / jumlahs) + "|" + jumlah1;
                                    }
                                }
                            }
                        }
                        
                        for (Student s : student) {
                            if (s.getId().contains(nims1)) {
                                cekstudent = true;
                            }
                        }
                        if (cekstudent && !printed) {
                            System.out.println(output);
                            printed = true;
                        }
                    }
                }
            } else if (temp1.equals("enrollment-remedial")) {
                    courseid = hasil[1];
                    studentid = hasil[2];
                    courseyear = hasil[3];
                    coursesems = hasil[4];
                    coursegrade = hasil[5];
                    
                    
                    for (Enrollment i : enrol) {
                        if (i.getnims().equals(studentid) && i.getids().equals(courseid) && i.getyear().equals(courseyear) && i.getsems().equals(coursesems)) {
                            if (!i.getgrade().equals("None") && i.getcounter() == 0) {
                                i.setgradelama(i.getgrade());
                                i.setgrade(coursegrade);
                                i.setremedial("ya");
                                i.setcounter();
                            }
                        }
                    }

                } else if (temp1.equals("course-open")) {
                    idcourse = hasil[1];
                    yearcourse = hasil[2];
                    semscourse = hasil[3];
                    dosens = hasil[4];
                    courseopen.add(new CourseOpening(idcourse, yearcourse, semscourse, dosens));

                    z++;
                } else if (temp1.equals("course-history")) {
                    idcoursehistory = hasil[1];

                    Course nowcourse = null;
                    String lecturerinisial = "";
                    String lectureremail = "";

                    for (Course s : course) {
                        if (s.getid().contains(idcoursehistory)) {
                            nowcourse = s;
                            break;
                        }
                    }

                    ArrayList<CourseOpening> odd = new ArrayList<CourseOpening>();
                    ArrayList<CourseOpening> even = new ArrayList<CourseOpening>();

                    for (CourseOpening i : courseopen) {
                        if (i.getid().contains(idcoursehistory)) {
                            if (i.getsems().contains("odd")) {
                                odd.add(i);
                            } else {
                                even.add(i);
                            }
                        }
                    }

                    for (CourseOpening g : odd) {
                        lecturerinisial = g.getinisial();

                        for (Lecturer s : lecturer) {
                            if (s.getinisial().contains(lecturerinisial)) {
                                lectureremail = s.getemail();
                                break;
                            }
                        }
                        System.out.println(nowcourse.getid() + "|" + nowcourse.getmatkul() + "|" + nowcourse.getsks() + "|" + nowcourse.getnilai() + "|" + g.getyear() + "|" + g.getsems() + "|" + lecturerinisial + " (" + lectureremail + ")");

                        for (Enrollment s : enrol) {
                            if (s.getids().contains(idcoursehistory) && s.getyear().contains(g.getyear()) && s.getsems().contains(g.getsems())) {                                    
                                System.out.println(s.toString());
                            }
                        }
                    }

                    for (CourseOpening h : even) {
                        lecturerinisial = h.getinisial();

                        for (Lecturer c : lecturer) {
                            if (c.getinisial().contains(lecturerinisial)) {
                                lectureremail = c.getemail();
                                break;
                            }
                        }
                        System.out.println(nowcourse.getid() + "|" + nowcourse.getmatkul() + "|" + nowcourse.getsks() + "|" + nowcourse.getnilai() + "|" + h.getyear() + "|" + h.getsems() + "|" + lecturerinisial + " (" + lectureremail + ")");

                        for (Enrollment s : enrol) {
                            if (s.getids().contains(idcoursehistory) && s.getyear().contains(h.getyear()) && s.getsems().contains(h.getsems())) {                                    
                                System.out.println(s.toString());
                            }
                        }
                        }
                    } else if (temp1.equals("student-transcript")) {
                    idtranscriptstudent = hasil[1];
                    
                    Driver1 driver = new Driver1();
                    TranscriptProcessor transcriptProcessor = driver.new TranscriptProcessor(idtranscriptstudent);
                    
                    transcriptProcessor.printTranscript(student, enrol, course);
                }
        }
            

        for (Lecturer p : lecturer) {
            System.out.println(p.toString());
        }

        for (Course a : course) {
            System.out.println(a.toString());
        }

        for (Student j : student) {
            System.out.println(j.toString());
        }

        for (Enrollment k : enrol) {
            System.out.println(k.toString());            
        }

        input.close();
    }

    public static ArrayList<Enrollment> getLatestEnrollments(ArrayList<Enrollment> enrollList, String studentId) {    
        ArrayList<String> processedCourses = new ArrayList<>();
        ArrayList<Enrollment> result = new ArrayList<>();
        
        ArrayList<Enrollment> studentEnrollments = new ArrayList<>();
        for (Enrollment e : enrollList) {
            if (e.getnims().equals(studentId)) {
                studentEnrollments.add(e);
            }
        }
        
        for (int i = 0; i < studentEnrollments.size(); i++) {
            Enrollment current = studentEnrollments.get(i);
            String courseId = current.getids();
            
            if (!processedCourses.contains(courseId)) {
                Enrollment latest = current;
                int latestYear = Integer.parseInt(latest.getyear().split("/")[0]);
                boolean latestIsEven = latest.getsems().equals("even");
                
                for (int j = 0; j < studentEnrollments.size(); j++) {
                    if (i != j && studentEnrollments.get(j).getids().equals(courseId)) {
                        Enrollment other = studentEnrollments.get(j);
                        int otherYear = Integer.parseInt(other.getyear().split("/")[0]);
                        boolean otherIsEven = other.getsems().equals("even");
                        
                        if (otherYear > latestYear) {
                            latest = other;
                            latestYear = otherYear;
                            latestIsEven = otherIsEven;
                        } 
                        else if (otherYear == latestYear) {
                            if (otherIsEven && !latestIsEven) {
                                latest = other;
                                latestYear = otherYear;
                                latestIsEven = otherIsEven;
                            }
                        }
                    }
                }
                
                result.add(latest);
                processedCourses.add(courseId);
            }
        }
        
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.size() - 1; j++) {
                int year1 = Integer.parseInt(result.get(j).getyear().split("/")[0]);
                int year2 = Integer.parseInt(result.get(j + 1).getyear().split("/")[0]);
                
                if (year1 > year2) {
                    Enrollment temp = result.get(j);
                    result.set(j, result.get(j + 1));
                    result.set(j + 1, temp);
                } else if (year1 == year2) {
                    if (result.get(j).getsems().equals("even") && 
                        result.get(j + 1).getsems().equals("odd")) {
                        Enrollment temp = result.get(j);
                        result.set(j, result.get(j + 1));
                        result.set(j + 1, temp);
                    }
                }
            }
        }
        
        return result;
    }
}